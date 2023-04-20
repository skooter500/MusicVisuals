package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class AudioVisual extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioBuffer ab;

    float smoothedAmplitude = 0;

    public void settings() {
        size(1024, 768, P3D);
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("Parasite.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        // Initialize the lerpedBuffer array
        lerpedBuffer = new float[ab.size()];
        for (int i = 0; i < ab.size(); i++) {
            lerpedBuffer[i] = 0;
        }
    }

    float[] lerpedBuffer;

    public void draw() {
        float halfH = height / 2;
        float sum = 0;
    
        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for (int i = 0; i < ab.size(); i++) {
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
            sum += abs(lerpedBuffer[i]);
        }
    
        smoothedAmplitude = lerp(smoothedAmplitude, sum / (float) ab.size(), 0.5f);
    
        background(0);
    
        // Set colors and shapes to represent the mood of the song
        float colorAngle = (frameCount * 0.01f) % TWO_PI;
        float hue = map(sin(colorAngle), -1, 1, 0, 255);
        float sat = map(smoothedAmplitude, 0, 1, 50, 255);
        float brightness = map(smoothedAmplitude, 0, 1, 200, 255);
    
        fill(hue, sat, brightness);
        stroke(hue, sat, brightness);
    
        // Draw a series of lines based on the lerped buffer values
        for (int i = 0; i < ab.size() - 1; i++) {
            float x1 = map(i, 0, ab.size(), 0, width);
            float x2 = map(i + 1, 0, ab.size(), 0, width);
            float y1 = halfH - lerpedBuffer[i] * halfH;
            float y2 = halfH - lerpedBuffer[i + 1] * halfH;
    
            line(x1, y1, x2, y2);
        }
    
        // Draw a series of ellipses based on the smoothed amplitude
        float ellipseSize = map(smoothedAmplitude, 0, 1, 10, 200);
        for (int i = 0; i < 10; i++) {
            float x = random(width);
            float y = random(height);
            ellipse(x, y, ellipseSize, ellipseSize);
        }
    }    

    public static void main(String[] args) {
        PApplet.main("ie.tudublin.AudioVisual");
    }
}
