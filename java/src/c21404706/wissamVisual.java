
package c21404706;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.*;
import processing.core.PApplet;
import processing.core.PImage;
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
    }

    float off = 0;

    public void visiual() {

        for (int i = 0; i < ab.size(); i++) {
            float cx = width / 2;
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
            float hue = map(i, 0, ab.size(), 0, 256);
            float s = lerpedBuffer[i] * cx * 2;

            // from left side to right
            stroke(hue, 255, 255);
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
            
        beginShape(TRIANGLE_STRIP); 
        for (int i = 0; i <= numPoints; i++) {
            float px = mouseX + cos(radians(angle)) * outsideRadius;
            float py = mouseY + sin(radians(angle)) * outsideRadius;
            angle += angleStep;
            vertex(px, py);
            px = mouseX + cos(radians(angle)) * insideRadius;
            py = mouseY + sin(radians(angle)) * insideRadius;
            vertex(px, py); 
            angle += angleStep;
        }
        endShape();
           
    } 


    public void draw() {
        background(180);
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


        //calling outline for visiual
        visiual();
    }

}