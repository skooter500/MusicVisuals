package c21331753;

import ie.tudublin.CombinedVisual;
import processing.core.PApplet;
import processing.core.PImage;

public class TaniaVisual2 extends PApplet {
    CombinedVisual cv;
    PImage img;
    float angle = 0;
    float circleSize = 700; // Increase circle size to make it larger
    float imgAngle = 0; // Initial angle for image rotation

    public TaniaVisual2(CombinedVisual cv) {
        this.cv = cv;
        img = cv.loadImage("images/coverCD.png");
        img.resize(cv.width / 3, cv.height / 3); // Resize the image to make it smaller
    }

    public void render() {
        cv.calculateAverageAmplitude();
        float amplitude = cv.getSmoothedAmplitude();
        cv.colorMode(HSB, 255);
        cv.background(0);
        drawRainbowSynthRing(); // Draw rainbow synth ring first so it's behind the image
        drawSpinningImage(cv.width / 2, cv.height / 2); // Draw the spinning image in the center
        //drawAmplitudeCircles(); // Draw amplitude circles
    }


    private void drawRainbowSynthRing() {
        float halfWidth = cv.width / 2;
        float halfHeight = cv.height / 2;
        float circleSize = (float) (cv.getSmoothedAmplitude() * halfHeight * 2);
        cv.noFill();
        float amplitude = cv.getSmoothedAmplitude();
        for (int i = 0; i < 360; i += 10) {
            float hue = cv.frameCount % 360;
            cv.stroke(hue, 255, 255);
            float x = halfWidth + cos(radians(i)) * circleSize;
            float y = halfHeight + sin(radians(i)) * circleSize;
            cv.ellipse(halfWidth, halfHeight, x, y);
        }
    }

    private void drawSpinningImage(float x, float y) {
        cv.pushStyle();
        cv.imageMode(CENTER);
        cv.translate(x, y);
        cv.rotate(angle);
        cv.image(img, 0, 0);
        cv.popStyle();
        float amplitude = cv.getSmoothedAmplitude();
        angle += amplitude; 
    }
}
