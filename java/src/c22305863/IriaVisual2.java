package c22305863;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class IriaVisual2 extends PApplet {

    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;

    float[] lerpedBuffer;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    public void keyPressed() {
        if (key >= '0' && key <= '9') {
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
        // size(1024, 1000, P3D);
        // fullScreen(P3D, SPAN);
        size(1024, 1000);
    }

    public void setup() {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix;
        ap = minim.loadFile("song.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];
    }

    float off = 0;

    public void draw() {
        // background(0);
        float halfH = height / 2;
        float average = 0;
        float sum = 0;
        off += 1;
        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for (int i = 0; i < ab.size(); i++) {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
        }
        average = sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

        float cx = width / 2;
        float cy = height / 2;

        switch (mode) {
            case 0:
                background(0);

                break;
            case 1: {
                background(0);

                break;
            }
            case 2: {
                background(0);
                break;
            }

            case 3: {
                background(0);
                // Code goes here

                stroke(165, 180, 360);
                noFill();
                strokeWeight(2);
                circle(cy * 1f, cy, average * cy * 5);
                break;
            }
            case 4: {
                background(0);
                // Code goes here
                stroke(165, 180, 360);
                noFill();
                rectMode(CENTER);
                rect(cx, cy, average * width * 2, average * width * 2);
                break;
            }
            case 5: {
                background(0);
                // Code goes here
                for (int i = 0; i < ab.size(); i++) {

                    float hue = map(i, 0, ab.size(), 0, 256);
                    float s = lerpedBuffer[i] * cx;
                    stroke(hue, 255, 300);
                    noFill();
                    line(cy * s, i * s, s, ab.get(i) + s * 100);
                    line(cx, cy, cx + cos(TWO_PI / ab.size() * i) * s, cy + sin(TWO_PI / ab.size() * i) * s);

                }

                break;

            }

        }

    }
}
