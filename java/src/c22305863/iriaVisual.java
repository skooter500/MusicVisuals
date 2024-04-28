package c22305863;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import java.util.ArrayList;

public class iriaVisual extends PApplet {

    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    private AudioBuffer ab;

    int mode = 0;// default mode for switch statement

    float[] lerpedBuffer;
    float y = 0; // vertical position
    float ySpeed = 2;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    ArrayList<Coconut> coconuts = new ArrayList<Coconut>(); // array to store coconuts
    PImage tikiface;
    PImage skirt;
    PVector tikiPos;
    PVector tikiPos2;
    PVector skirtPos;
    PVector skirtPos2;

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
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // am = ai.mix;
        ap = minim.loadFile("Squidward's Tiki Land     Psy-Trance Remix.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;

        textureMode(NORMAL);

        lerpedBuffer = new float[width];

        tikiface = loadImage("tiki_face3.png");
        skirt = loadImage("skirt.png");
        tikiPos = new PVector(width / 2, height / 12);

    }

    float off = 0;

    public void reactToMouseMovement() {
        // make coconuts appear on screen when clicked by mouse
        if (mousePressed) {
            coconuts.add(new Coconut(new PVector(mouseX, mouseY)));// create new coconuts + add to list
        }
    }

    public void tiki_face() {
        int numTikis = 5;
        float tikiSpacing = width / (numTikis + 1); // calculate spacing between tikis

        for (int i = 1; i <= numTikis; i++) {

            float tikiX = i * tikiSpacing - 100; // Calculate X position

            // move tiki with music
            float amplitude = map(sin(frameCount * 0.05f), -1, 1, 0, ab.size() / 8);
            PVector tikiPos = new PVector(tikiX, height / 12 + amplitude);
            PVector tikiPos2 = new PVector(tikiX, height / 1.50f + amplitude);

            // skirt
            PVector skirtPos = new PVector(tikiX , height / 3.4f + amplitude);
            PVector skirtPos2 = new PVector(tikiX , height / 1.14f + amplitude);

            // draw tiki face at the updated position
            image(tikiface, tikiPos.x, tikiPos.y);
            image(tikiface, tikiPos2.x, tikiPos2.y);
            float hue = map(amplitude, 0, 50, 0, 360);
            fill(hue, 100, 100);
            image(skirt, skirtPos.x, skirtPos.y);
            image(skirt, skirtPos2.x, skirtPos2.y);

        }

    }

    class Coconut {
        // position of coconut
        PVector position;
        int timesReachedEnd;

        Coconut(PVector position) {
            this.position = position;
        }

        // update coconut position make it appear on screen
        void update() {
            // Draw coconut
            float coconutSize = 100;

            pushMatrix(); // save the current transformation matrix
            translate(position.x, position.y); // translate to the coconut's position
            noStroke();
            fill(360, 100, 36);
            ellipse(0, 0, coconutSize, coconutSize); // draw coconut at the translated position

            // Draw small circle inside the coconut
            float smallCircleSize = 20;
            float smallCircleOffsetX = coconutSize / 2 - 30;
            float smallCircleOffsetY = -coconutSize / 2 + 30;
            fill(67, 37, 100);
            ellipse(smallCircleOffsetX, smallCircleOffsetY, smallCircleSize, smallCircleSize);

            popMatrix(); // restore the previous transformation matrix

            // makes coconut falls down
            position.y += 3;
            if (position.y > height) {
                position.y = 0; // coconut goes back up when it reaches the bottom
                timesReachedEnd++;
            }
            if (timesReachedEnd >= 2) {
                coconuts.remove(this); // remove coconut from the list if reached the end twice
            }

        }

    }

    public void draw() {

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

        colorMode(HSB, 360, 100, 100); // mode HSB
        background(209, 58, 100);

        reactToMouseMovement();
        for (int i = coconuts.size() - 1; i >= 0; i--) {
            Coconut c = coconuts.get(i); // get the coconut from the list
            c.update(); // draw the coconut
        }

        for (int i = 0; i < ab.size(); i++) {
            float hue = map(i, 0, ab.size(), 297, 58);
            stroke(hue, 100, 100);
            noFill();
            circle(cx, cy, average * i * 4 * smoothedAmplitude);
            // line that surrounds the border of the circle
            line(cx, cy, cx + cos(TWO_PI / average * i) * 100, cy + sin(TWO_PI / average * i) * 100);

        }
        for (int i = 0; i < ab.size(); i++) {

            float hue = map(i, 0, ab.size(), 50, 0);
            float s = lerpedBuffer[i] * cx;
            stroke(hue, 100, 100);
            // cool line that moves with the music
            // cos and sin functions to make line move in a circular way
            // two_pi is a full circle
            line(cx, cy + smoothedAmplitude, cx + cos(TWO_PI / ab.size() * i) * s * 2,
                    cy + sin(TWO_PI / ab.size() * i) * s * 2);

        }
        for (int i = 0; i < ab.size(); i++) {
            float hue = map(i, 0, ab.size(), 290, 51);

            // circle to be infront of the cool line
            circle(cx, cy, average * i / 8);
        }

        tiki_face();

    }
}
