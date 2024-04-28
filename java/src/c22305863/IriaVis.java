package c22305863;

import ie.tudublin.OurVisual;
import ie.tudublin.VisualException;
import processing.core.*;
import java.util.ArrayList;

import ddf.minim.*;


// This is an example of a visual that renders the waveform
public class IriaVis extends PApplet {
    OurVisual mv;
    float cy = 0;
    private AudioBuffer ab;
    float[] lerpedBuffer;
    float y = 0; // vertical position
    float ySpeed = 2;
    float smoothedY = 0;
    float smoothedAmplitude = 0;
    float outsideRadius = 150;
    float insideRadius = 100;
    float off = 0;

    ArrayList<Coconut> coconuts = new ArrayList<Coconut>(); // array to store coconuts
    PImage tikiface;
    PVector tikiPos;

    public IriaVis(OurVisual mv) {
        this.mv = mv;
        this.ab = mv.getAudioBuffer();
        cy = this.mv.height / 2;
        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];
        //  Minim minim = new Minim(this);
        // // AudioPlayer player = minim.loadFile("your_audio_file.mp3"); // Replace "your_audio_file.mp3" with your audio file path
        // // ab = player.mix;
        // AudioPlayer player = minim.loadFile("your_audio_file.mp3"); // Replace "your_audio_file.mp3" with your audio file path
        // if (player != null && player.isPlaying()) {
        //     ab = player.mix; // Initialize ab with the mix AudioBuffer of the player
        // } else {
        //     System.err.println("Error loading audio file or audio player is not playing.");
        // }
    }
    public void setup() {
        // Load image here
        colorMode(HSB, 360, 100, 100); // mode HSB
        tikiface = loadImage("tiki_face3.png");
        tikiPos = new PVector(width / 2, height / 12);
    }
    
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
            PVector tikiPos2 = new PVector(tikiX, height / 1.5f + amplitude);

            float hue = map(amplitude, 0, 50, 0, 360);
            fill(hue, 100, 100);

            // draw tiki face at the updated position
            image(tikiface, tikiPos.x, tikiPos.y);
            image(tikiface, tikiPos2.x, tikiPos2.y);

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
            position.y += 2;
            if (position.y > height) {
                position.y = 0; // coconut goes back up when it reaches the bottom
                timesReachedEnd++;
            }
            if (timesReachedEnd >= 2) {
                coconuts.remove(this); // remove coconut from the list if reached the end twice
            }

        }

    }

    public void render(AudioBuffer ab) {
        this.ab = ab;
        float average = 0;
        float sum = 0;
        
        off += 1;
        // calculate sum and average of the samples
        // lerp each element of buffer;
        lerpedBuffer = new float[ab.size()];

        for (int i = 0; i < ab.size(); i++) {
            sum += abs(ab.get(i));
            // Ensure lerpedBuffer is not accessed beyond its bounds
            if (i < lerpedBuffer.length) {
                lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
            }
        }
        average = sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

        float cx = width / 2;
        float cy = height / 2;

        
        background(209, 58, 100);

        reactToMouseMovement();
        for (int i = coconuts.size() - 1; i >= 0; i--) {
            Coconut c = coconuts.get(i); // get the coconut from the list
            c.update(); // draw the coconut
        }

        for (int i = 0; i < ab.size(); i++) {
            float hue = map(i, 0, ab.size(), 56, 0);
          
            stroke(hue, 100, 100);
            noFill();
            circle(cx, cy, average * i * 2);
        }
        for (int i = 0; i < ab.size(); i++) {
            float s = lerpedBuffer[i] * cx;
            float hue = map(i, 0, ab.size(), 0, 121);
            stroke(hue, 255, 300);
           
            // cool line that moves with the music
            // cos and sin functions to make line move in a circular way
            // two_pi is a full circle
            line(cx, cy + smoothedAmplitude, cx + cos(TWO_PI / ab.size() * i) * s * 2,
                    cy + sin(TWO_PI / ab.size() * i) * s * 2);

        }
        for (int i = 0; i < ab.size(); i++) {
            float hue = map(i, 0, ab.size(), 290, 51);
            stroke(hue, 100, 100);
            // circle to be infront of the cool line
            circle(cx, cy, average * i / 8);
        }

        tiki_face();
         

    }

}