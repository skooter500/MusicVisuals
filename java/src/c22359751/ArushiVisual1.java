package c22359751;

import processing.core.PApplet;
import ie.tudublin.CombinedVisual;


public class ArushiVisual1 extends PApplet {

    CombinedVisual cv;

    public ArushiVisual1(CombinedVisual cv) {
        this.cv = cv;
        
    }

    public void render() {
        cv.calculateAverageAmplitude();
        float amplitude = cv.getSmoothedAmplitude();
        cv.background(0);
        cv.colorMode(HSB, 360, 100, 100);
        float hue = map(amplitude, 0, 1, 0, 360);
        cv.stroke(hue, 80, 80);
        cv.fill(hue, 80, 80);
        //camera(0, 0, 0, 0, 0, -1, 0, 1, 0);
        //translate(0, 0, -250);
        float scale = 0.4f;

        drawHeartCircle(scale, amplitude);
        drawHeart(scale);
        drawGrid();

    }

    private void drawHeartCircle(float scale, float amplitude) {
        cv.stroke(360);
    
        float centerX = cv.width / 2;  
        float centerY = cv.height / 2; 
        
        float circleSize = 350 + amplitude * 200; 
        
        int numHearts = 40; // Number of small hearts
        float angleIncrement = TWO_PI / numHearts;

        float rotationSpeed = map(amplitude, 0, 1, 0, TWO_PI);

        float scaleHeart = 0.1f; // Scale of the small hearts
        for (int i = 0; i < numHearts; i++) {
            float angle = i * angleIncrement + rotationSpeed;;
            float x = centerX + cos(angle) * circleSize;
            float y = centerY + sin(angle) * circleSize;
            for (float a = 0; a < PI; a += 0.01) {
                float r = 10;
                float heartX = x - r * 16 * pow(sin(a), 3) * scaleHeart;
                float heartY = y - r * (13 * cos(a) - 5 * cos(2 * a) - 2 * cos(3 * a) - cos(4 * a)) * scaleHeart;
                drawSmallHeart(heartX, heartY, r, scaleHeart);
            }
            for (float a = 0; a < PI; a += 0.01) {
                float r = 10;
                float heartX = x + r * 16 * pow(sin(a), 3) * scaleHeart;
                float heartY = y - r * (13 * cos(a) - 5 * cos(2 * a) - 2 * cos(3 * a) - cos(4 * a)) * scaleHeart;

                drawSmallHeart(heartX, heartY, r, scaleHeart);
            }
        }
    }
    
    private void drawSmallHeart(float x, float y, float radius, float scale) {
        cv.ellipse(x, y, radius * scale, radius * scale);
    }
    

    private void drawHeart(float scale) {
        float amplitude = cv.getSmoothedAmplitude();
        cv.colorMode(HSB, 360, 100, 100);
        float hue = map(amplitude, 0, 1, 0, 360);
        cv.stroke(hue, 80, 80);
        cv.fill(hue, 80, 80);
    
        float centerX = cv.width / 2;  
        float centerY = cv.height / 2; 
    
        // First half of the heart
        cv.beginShape();
        for (float a = 0; a < PI; a += 0.01) {
            float r = 40;
            float x = centerX + r * 16 * pow(sin(a), 3) * scale + (map(cv.getSmoothedAmplitude()*2, 0, 1, 0, 255));
            float y = centerY - r * (13 * cos(a) - 5 * cos(2 * a) - 2 * cos(3 * a) - cos(4 * a)) * scale;
            cv.vertex(x, y, -5);
        }
        cv.endShape();
    
        // Draw second half of the heart
        cv.beginShape();
        for (float a = 0; a < PI; a += 0.01) {
            float r = 40;
            float x = centerX - r * 16 * pow(sin(a), 3) * scale - (map(cv.getSmoothedAmplitude()*2, 0, 1, 0, 255));
            float y = centerY - r * (13 * cos(a) - 5 * cos(2 * a) - 2 * cos(3 * a) - cos(4 * a)) * scale;
            cv.vertex(x, y, -5);
        }
        cv.endShape();
    }
    
    private void drawGrid() {
        float amplitude = cv.getSmoothedAmplitude();
        float spacing = 50;
        cv.colorMode(HSB, 360, 100, 100);
        float hue = map(amplitude, 0, 1, 0, 360);
        cv.stroke(hue, 80, 80);
        float centerX = cv.width;  
        float centerY = cv.height;
    
        for (float x = -centerX; x < centerX; x += spacing) {
            cv.line(x, -centerY, 0, x, centerY, 0);
        }
    
        for (float y = -centerY; y < centerY; y += spacing) {
            cv.line(-centerX, y, 0, centerX, y, 0);
        }


    }

    
}
