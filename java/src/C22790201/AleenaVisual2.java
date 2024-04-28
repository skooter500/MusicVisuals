package C22790201;

import ie.tudublin.OurVisual;
import processing.core.*;

public class AleenaVisual2
{
    OurVisual ov;
    float angle = 0;
    float rotationSpeed = 0.01f;
    float amplitudeScale = 100;
    int numSpirals = 10;

    public AleenaVisual2(OurVisual ov)
    {
        this.ov = ov;
    }

    public void render() {
        ov.colorMode(PApplet.HSB);
        ov.background(0);
    
        float amplitude = ov.getAmplitude(); // Get the amplitude of the audio
    
        ov.translate(ov.width / 2, ov.height / 2); // Translate origin to center of the screen
    
        // Render spirals
        renderSpirals(amplitude);
    
        // Render radial burst
        renderRadialBurst(amplitude);
    }
    
    private void renderSpirals(float amplitude) {
        ov.strokeWeight(3);
        for (int i = 0; i < numSpirals; i++) {
            float size = amplitude * amplitudeScale * (i + 1); // Scale size with amplitude
            float x = size * PApplet.cos(angle);
            float y = size * PApplet.sin(angle);
    
            // Set color based on frequency bands
            int hue = (int) PApplet.map(i, 0, numSpirals, 0, 255);
            ov.stroke(hue, 255, 255);
            ov.noFill();
    
            ov.beginShape();
            for (float a = 0; a < PApplet.TWO_PI; a += 0.1) {
                float sx = x + size * PApplet.cos(a);
                float sy = y + size * PApplet.sin(a);
                ov.vertex(sx, sy);
            }
            ov.endShape(PConstants.CLOSE);
    
            angle += rotationSpeed;
        }
    }
    
    public void renderRadialBurst(float amplitude) {
        int numPoints = 100; // Number of points in the burst
        float step = PApplet.TWO_PI / numPoints; // Angle step between each point
    
        for (int i = 0; i < numPoints; i++) {
            float x1 = 0;
            float y1 = 0;
            float x2 = amplitude * PApplet.cos(i * step) * 200; // Scale with amplitude
            float y2 = amplitude * PApplet.sin(i * step) * 200; // Scale with amplitude
    
            // Set color based on amplitude
            int brightness = (int) PApplet.map(amplitude, 0, 1, 100, 255);
            ov.stroke(255, brightness);
            
            ov.line(x1, y1, x2, y2);
        }
    }    
    
}    