
package c21404706;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.*;
import ie.tudublin.*;
import processing.core.*;
import java.util.ArrayList;

public class wissamVisual extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;

    float[] lerpedBuffer;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    PImage bananaPic;
    PImage bg;
    ArrayList<Banana> bananas;


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
        //size(1024, 1000, P3D);
        size(1024, 600, P3D);
        // fullScreen(P3D, SPAN);
    }

    public void setup() {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix;
        ap = minim.loadFile("Squidward's Tiki Land     Psy-Trance Remix.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];

        bananaPic = loadImage("banana_pic.png");
        bananas = new ArrayList<Banana>();

        bg = loadImage("spongeBG.png");

        int numBananas = 30;
        float bananaSpacing = width / (numBananas + 1); // calculate spacing between bananas
        for (int i = 1; i <= numBananas; i++) {
            float bananaX = i * bananaSpacing ;
            bananas.add(new Banana(bananaX, 0)); // Add bananas to the list
        }
    }

    float off = 0;

    public void visiual() {

        for (int i = 0; i < ab.size(); i++) {
            float cx = width / 2;
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
            float hue = map(i, 0, ab.size(), 0, 256);
            float s = lerpedBuffer[i] * cx * 2;

            // from left side to right
            stroke(40, 255, 255);
            line(0, i, s, i);
            // from right side to left
            line(width, i, width - s, i);
            // from top to bottom
            line(i, 0, i, s);
            // from bottom to top
            line(i, height, i, height - s);
        } 

        int gridSize = 40;

        for (int x = gridSize; x <= width - gridSize; x += gridSize) {
            for (int y = gridSize; y <= height - gridSize; y += gridSize) {
                noStroke();
                fill(255);
                rect(x-1, y-1, 3, 3);
                stroke(255, 100);
                line(x, y, width/2, height/2);
            }
        }

        int num = 60;
        float mx[] = new float[num];
        float my[] = new float[num];

        // Cycle through the array, using a different entry on each frame. 
        // Using modulo (%) like this is faster than moving all the values over.
        int which = frameCount % num;
        mx[which] = mouseX;
        my[which] = mouseY;
        
        for (int i = 0; i < num; i++) {
            // which+1 is the smallest (the oldest in the array)
            int index = (which+1 + i) % num;
            ellipse(mx[index], my[index], i, i);
        }

        int x;
        int y;
        float outsideRadius = 150;
        float insideRadius = 100;

        int numPoints = (int) map((float) mouseX, 0, (float) width, 6, 60);
        float angle = 0;
        float angleStep = 180.0f/num;
            
        // Ocean wave parameters
        float waveSpeed = 0.0000000000000000001f; // Speed of the waves
        float waveAmplitude = 10; // Amplitude of the waves
        float waveFrequency = 0.025f; // Frequency of the waves

        for (int z = 0; z < width; z++) {
            stroke(160, 255, 255); // HSB color (blue)
            float waveHeight = sin(z * waveFrequency + off) * waveAmplitude;
            line(z, height * 2 / 3 + waveHeight, z, height); // Drawing waves from the center of the canvas to the bottom
        }

        off += waveSpeed; // Incrementing the offset for the waves animation

        int numBananas = 15;
        float bananaSpacing = width / (numBananas + 1); // calculate spacing between tikis


        for (int i = 1; i <= numBananas; i++) {
            float bananaX  = i * bananaSpacing - 100; // Calculate X position

            // move bananas with music
            float amplitude = map(sin(frameCount * 0.05f), -1, 1, 0, ab.size() / 8);
            float bananaY = -amplitude * 0.5f;
            float bananaSize = map(amplitude, 0, 50, 10, 50);

            float hue = map(amplitude, 0, 50, 0, 360);
            fill(hue, 100, 100);

        }
           
    } 


    public void draw() {
        image(bg,0,0,width,height);
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

        for (Banana banana : bananas) {
            banana.display();
        }


        //calling outline for visiual
        visiual();
    }

    class Banana {
        float x, y;
        int timesReachedEnd;

        Banana(float x, float y) {
            this.x = x;
            this.y = y;
            timesReachedEnd = 0;
        }

        void display() {
            float amplitude = map(sin(frameCount * 0.05f), -1, 1, 0, ab.size() / 8);
            float bananaX = x * (width / 1024.0f); // Adjust x position for different screen widths
            float bananaY = y * (height / 600.0f);
            float bananaSize = map(amplitude, 0, 50, 10, 50);

            float hue = map(amplitude, 0, 50, 0, 360);
            fill(hue, 100, 100);
            image(bananaPic, x, bananaY, bananaSize, bananaSize * 2);
            
            y += 2;
            if (y > height) {
                y = 0; // coconut goes back up when it reaches the bottom
                timesReachedEnd++;
            }
            if (timesReachedEnd >= 2) {
                bananas.remove(this); // remove coconut from the list if reached the end twice
            }
        }
    }







}