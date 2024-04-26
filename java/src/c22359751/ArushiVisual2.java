package c22359751;

import ie.tudublin.CombinedVisual;
import processing.core.PApplet;
import processing.core.PImage;

public class ArushiVisual2 extends PApplet {

    CombinedVisual cv;
    PImage albumCover;
    float angleOffset;
    int numLines; 

    public ArushiVisual2(CombinedVisual cv) {
        this.cv = cv;
        albumCover = cv.loadImage("images/cover.png");
        albumCover.resize(cv.width * 2 / 3, cv.height); 
        angleOffset = 0;
        numLines = 30;
 
    }

    public void render() { 
        cv.calculateAverageAmplitude();
        cv.background(0);
        cv.colorMode(HSB, 360, 100, 100);
    
        float amplitude = cv.getSmoothedAmplitude();
    
        cv.blendMode(ADD);
    
        // Lines moving in a circle
        cv.strokeWeight(2);
        float angleStep = TWO_PI / numLines;
        for (int i = 0; i < numLines; i++) {
            float angle = angleOffset + i * angleStep;
            float radius = min(cv.width, cv.height) * 4;
    
            float x1 = cv.width / 2;
            float y1 = cv.height / 2;
            float x2 = x1 + cos(angle) * radius;
            float y2 = y1 + sin(angle) * radius;
    
            float hue = map(i, 0, numLines, 0, 360);
            cv.stroke(hue, 80, 80);
            cv.line(x1, y1, -x2, -y2);
    
            angleOffset += cv.getSmoothedAmplitude();
        }
    
        cv.blendMode(BLEND);
    
        cv.pushMatrix();
        cv.translate(0, 0, 1);
        cv.imageMode(PApplet.CENTER);
        float hue = cv.frameCount % 360;
        cv.tint(hue, 180, 180);
        cv.image(albumCover, cv.width / 2, cv.height / 2); 
        cv.popMatrix();
    }
    
}
