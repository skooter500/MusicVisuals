package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Audio1 extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioBuffer ab;

    int mode = 0;

    float smoothedAmplitude = 0;

    public void keyPressed() {
        if (key >= '0' && key <= '5') {
            mode = key - '0';
        }
        if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
    }

    public void settings() {
        size(1024, 1000, P3D);
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("Parasite.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        // Initialize the array to the same size as the audio buffer
        lerpedBuffer = new float[ab.size()];

        for (int i = 0; i < ab.size(); i++) {
            lerpedBuffer[i] = 0;
        }
    }

    float[] lerpedBuffer;

    public void draw() {
        float halfH = height / 2;
        float average = 0;
        float sum = 0;

        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for (int i = 0; i < ab.size(); i++) {
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
            sum += abs(lerpedBuffer[i]);
        }
        average = sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.5f);

        switch (mode) {
            case 0:
                background(0);
                for (int i = 0; i < ab.size(); i++) {
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    float f = lerpedBuffer[i] * halfH;
                    line(i, halfH + f, i, halfH - f);
                }
                break;
            case 1:
                background(0);
                float x = 0;
                float dx = (float) width / (float) ab.size();
                for (int i = 0; i < ab.size(); i++) {
                    float y = map(ab.get(i), -1, 1, height, 0);
                    line(x, y, x + dx, y);
                    x += dx;
                }
                break;
            case 2:
                background(0);
                float halfW = width / 2;
                float halfHeight = height / 2;
                for (int i = 0; i < ab.size() - 1; i++) {
                    stroke(map(i, 0, ab.size(), 0, 255), 255, 255);
                    float x1 = map(i, 0, ab.size(), 0, halfW);
                    float x2 = map(i + 1, 0, ab.size(), 0, halfW);
                    float y1 = halfHeight - lerpedBuffer[i] * halfHeight;
                    float y2 = halfHeight - lerpedBuffer[i + 1] * halfHeight;
                    line(x1, y1, x2, y2);
                    line(x1, height - y1, x2, height - y2);
                    line(halfW - x1, halfHeight, halfW - x2, halfHeight - y2);
                    line(width - (halfW - x1), halfHeight, width - (halfW - x2), halfHeight - y2);
                }
                break;
            case 3:
                background(0);
                noStroke();
                fill(255, 255, 255, 100);
                float size = map(smoothedAmplitude, 0, 1, 0, height);
                ellipse(width / 2f, height / 2f, size, size);
                break;
            case 4:
                background(0);
                noStroke();
                fill(255, 255, 255, 100);
                float size2 = map(smoothedAmplitude, 0, 1, 0, height);
                rectMode(CENTER);
                rect(width / 2f, height / 2f, size2, size2);
                break;
            case 5:
                background(0);
                for (int i = 0; i < ab.size(); i += 10) {
                    float c = random(255);
                    stroke(c, 255, 255);
                    float z = map(i, 0, ab.size(), 0, width);
                    float y = random(height);
                    float r = abs(lerpedBuffer[i]) * 100;
                    point(z, y, r);
                }
                break;
            
            

            
            }
            
            
            
        }
    }
