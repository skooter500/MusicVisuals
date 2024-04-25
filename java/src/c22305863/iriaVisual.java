package c22305863;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import ie.tudublin.*;

public class iriaVisual extends PApplet {

    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;

    float[] lerpedBuffer;
    float y = 0; // vertical position
    float ySpeed = 2;
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
        size(1024, 600, P3D);
        // fullScreen(P3D, SPAN);
        // size(1024,700);
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("Squidward's Tiki Land     Psy-Trance Remix.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];
    }

    float off = 0;

    public void draw() {
        float halfH = height / 2;
        float average = 0;
        float sum = 0;
        off += 1;
        // calculate sum and average of the samples
        // lerp each element of buffer;
        for (int i = 0; i < ab.size(); i++) {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
        }
        average = sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

        float cx = width / 2;
        float cy = height / 2;

        background(0);
        switch (mode) {
            case 1:
                colorMode(HSB, 360, 100, 100); // mode HSB
                background(199, 100, 100);

                // position of the coconuts
                float coconutSpacing = 80; // spacing between coconuts
                float coconutSize = 100;
                float coconutLeftX = coconutSize / 2 + 50; // left coconut
                float coconutRightX1 = width - coconutSize / 2 - 150; // right coconut
                float coconutRightX2 = width - coconutSize / 2; // right coconut
                float coconutY = y;

                // coconut on the left
                // noStroke();
                stroke(360, 100, 15);
                fill(360, 100, 36);
                ellipse(coconutLeftX, coconutY, coconutSize, coconutSize);

                // coconut on the right
                ellipse(coconutRightX2, coconutY + coconutSpacing, coconutSize, coconutSize);
                ellipse(coconutRightX1, coconutY - coconutSpacing, coconutSize, coconutSize);

                // position of the small circle inside each coconut
                float smallCircleSize = 20; // Size of the small circle
                float smallCircleOffsetX = coconutSize / 4;
                float smallCircleOffsetY = coconutSize / 4;
                // draw small circles inside each coconut
                stroke(67, 37, 56);
                fill(67, 37, 100); // light reflection color

                ellipse(coconutLeftX + smallCircleOffsetX, coconutY - smallCircleOffsetY, smallCircleSize,
                        smallCircleSize);
                ellipse(coconutRightX2 + smallCircleOffsetX, coconutY + coconutSpacing - smallCircleOffsetY,
                        smallCircleSize, smallCircleSize);
                ellipse(coconutRightX1 + smallCircleOffsetX, coconutY - coconutSpacing - smallCircleOffsetY,
                        smallCircleSize, smallCircleSize);

                // vertical position
                y += ySpeed;
                if (y > height) {
                    y = 0; // reset the coconuts position when it reaches the bottom
                }
                break;
            case 2: {
                colorMode(HSB, 360, 100, 100); // mode HSB
                background(199, 100, 100);

                // small circle inside leaf
                //fill(39, 100, 100);
                //stroke(285, 100, 75);
                //circle(512, 300, average * cy * 5);
                for (int i = 0; i < ab.size(); i++) {

                    float hue = map(i, 0, ab.size(), 0, 256);
                    float s = lerpedBuffer[i] * cx;
                    stroke(hue, 255, 300);
                    noFill();
                    circle(512, 300, average * cy * 5);
                    //line(cy * s, i * s, s, ab.get(i) + s * 100);
                    line(512, 300, cx + cos(TWO_PI / ab.size() * i) * s * 2, cy + sin(TWO_PI / ab.size() * i) * s * 2);

                }

                

               

                break;
            }

            default:
                break;
        }

    }
}
