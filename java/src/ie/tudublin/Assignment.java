package ie.tudublin;

//test
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

import ddf.minim.*;
import ddf.minim.signals.*;
import processing.core.PVector;
import ddf.minim.analysis.*;


public class Assignment extends PApplet {

    // sphere
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    int mode = 0;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;
    float off = 0;
    float lerpedBuffer[] = new float[1024];

     //CDWaves
     int x;
     int radius = 200;
     float hue = 0;

    // spiralNodes
    FFT fft;

    Node[] nodes = new Node[1200];

    class Node {
        PVector loc;
        PVector velocity = new PVector(random(-2, 2), random(-2, 2));
        float size = 10;
        float angle = random(0, TWO_PI);

        Node(float x, float y) {
            this.loc = new PVector(x, y);
        }

        void run(float freq) {
            this.display();
            this.move(freq);
            this.bounce();
        }

        void display() {
            point(loc.x, loc.y);
        }

        void move(float freq) {
            float spiralSpeed = freq * 0.1f;
            angle += spiralSpeed;
            float radius = freq * 0.5f;
            loc.x += cos(angle) * radius;
            loc.y += sin(angle) * radius;
        }

        void bounce() {
            if ((this.loc.x > width) || (this.loc.x < 0)) {
                velocity.x = velocity.x * -1;
            }
            if ((this.loc.y > height) || (this.loc.y < 0)) {
                velocity.y = velocity.y * -1;
            }
        }
    }



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
        size(1024, 1000);
        // fullScreen(P3D, SPAN);
    }

    public void setup() {

        // sphere
        minim = new Minim(this);
        ap = minim.loadFile("java/data/horizon.mp3", 1024);
        ap.play();
        ab = ap.mix;
        y = height / 2;
        smoothedY = y;

        // sound particles
        smooth();
        background(0);
        frameRate(24);

        // spiralNodes
        smooth();
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(random(width), random(height));
        }

        minim = new Minim(this);
        fft = new FFT(ap.bufferSize(), ap.sampleRate());
        ap.play();

        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(random(width), random(height));
        }

        x = 0;

    }

    public void draw() {
        float average = 0;
        float sum = 0;
        off += 1;

        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for (int i = 0; i < ab.size(); i++) {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
        }
        average = sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for (int i = 0; i < ab.size(); i++) {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
        }
        average = sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

        switch (mode) {
            // sphere code
            case 1:

                background(0);
                noFill();
                colorMode(HSB);
                translate(width / 2, height / 2);

                // Draw the sphere
                float sphereRadius = 300;
                int sphereDetail = 30;
                for (float i = 0; i < PI; i += PI / sphereDetail) {
                    float r1 = sin(i) * sphereRadius;
                    float r2 = sin(i + PI / sphereDetail) * sphereRadius;
                    for (float j = 0; j < TWO_PI; j += TWO_PI / sphereDetail) {
                        float theta = random(0, PI);
                        float x1 = cos(j) * r1;
                        float y1 = sin(j) * r1;
                        float x2 = cos(j) * r2;
                        float y2 = sin(j) * r2;
                        float c = (int) ((theta / PI) * 255);
                        stroke(c, 255, 255);
                        line(x1, y1, -cos(i) * sphereRadius, x2, y2, -cos(i + PI / sphereDetail) * sphereRadius);
                    }
                }

                // Draw the dots
                int numDots = 700;
                for (int i = 0; i < numDots; i++) {
                    float theta1 = random(0, PI);
                    float theta2 = random(0, TWO_PI);
                    float x = sin(theta1) * cos(theta2) * sphereRadius;
                    float y = sin(theta1) * sin(theta2) * sphereRadius;
                    float z = -cos(theta1) * sphereRadius;
                    float movement = map(smoothedAmplitude, 0, 1, 0, 100); // map amplitude to movement range
                    x += random(-movement, movement);
                    y += random(-movement, movement);
                    z += random(-movement, movement);
                    float dotSize = map(smoothedAmplitude, 0, 1, 1, 10); // map amplitude to dot size range
                    ellipse(x, y, dotSize, dotSize);
                    int hue = (int) ((theta1 / PI) * 255);
                    fill(hue, 255, 255);
                }
                break;

            case 2:
                // sound particles
                colorMode(RGB);
                fill(0, 50);
                noStroke();
                rect(0, 0, width, height);
                translate(width / 2, height / 2);

                float n4 = (float) 0.4;
                float n6 = (float) 0.6;

                for (int i = 0; i < ap.bufferSize() - 1; i++) {

                    float angle = sin(i + n4) * 10;
                    float angle2 = sin(i + n6) * 300;

                    float x = sin(radians(i)) * (angle2 + 30);
                    float y = cos(radians(i)) * (angle2 + 30);

                    float x3 = sin(radians(i)) * (500 / angle);
                    float y3 = cos(radians(i)) * (500 / angle);

                    fill(255, 255, 0); // yellow
                    ellipse(x, y, ap.left.get(i) * 10, ap.left.get(i) * 10);

                    fill(255); // wt
                    rect(x3, y3, ap.left.get(i) * 20, ap.left.get(i) * 10);

                    fill(255, 165, 0); // orange
                    rect(x, y, ap.right.get(i) * 10, ap.left.get(i) * 10);

                    fill(255); // wt
                    rect(x3, y3, ap.right.get(i) * 10, ap.right.get(i) * 20);
                }

                n4 += 0.008;
                n6 += 0.04;

                break;

            case 3:
            //spiralNNodes
                noStroke();
                noCursor();
                colorMode(RGB);
                background(0);
                fft.forward(ap.mix);

                for (int i = 0; i < nodes.length; i++) {
                    float freq = fft
                            .getFreq((float) (dist(nodes[i].loc.x, nodes[i].loc.y, width / 2, height / 2) * 2.2));

                    strokeWeight(freq / 10);
                    stroke((1 - nodes[i].loc.y / 800) * 255, (nodes[i].loc.x / 800) * 255,
                            (nodes[i].loc.y / 800) * 255);
                    for (int j = i + 1; j < nodes.length; j++) {
                        Node other = nodes[j];
                        float dist = nodes[i].loc.dist(other.loc);
                        if (dist > 0 && dist < 60) {
                            line(nodes[i].loc.x, nodes[i].loc.y, other.loc.x, other.loc.y);
                        }
                    }
                    stroke(255);
                    nodes[i].run(freq);
                }
                break;

                case 4:
                //CDWaives
                translate(width / 2, height / 2);
                background(0);
        
                colorMode(HSB, 255);
        
                float avgAmplitude = ap.mix.level() * 255;
                fill(avgAmplitude, 0, 0);
                circle(0, 0, 120);
                fill(255);
                circle(cos(radians(x)) * 5, sin(radians(x)) * 5, 110);
                fill(0);
                circle(0, 0, 10);
        
                if (ap.isPlaying()) x += 2;
        
                fft.forward(ap.mix);
                float bands = fft.specSize();
        
                for (int i = 0; i < bands * 2; i++) {
                    float start_x = radius * cos(PI * (i + x) / bands);
                    float start_y = radius * sin(PI * (i + x) / bands);
        
                    float bandAmplitude;
                    if (i < bands) {
                        bandAmplitude = fft.getBand(i);
                    } else {
                        bandAmplitude = fft.getFreq(i);
                    }
                    hue += 0.5;
                    if (hue > 255) {
                        hue = 0;
                    }
                    int currentColor = color(hue, 255, 255, 100);
                    int nextColor = color(hue + 1, 255, 255, 100);
                    int blendedColor = lerpColor(currentColor, nextColor, bandAmplitude / 2);
                    fill(blendedColor);
                    noStroke();
        
                    float angle = PI * (i + x) / bands;
                    float arcWidth = 20 + bandAmplitude * 2;
                    float arcHeight = 20 + bandAmplitude * 2;
        
                    pushMatrix();
                    translate(start_x, start_y);
                    rotate(angle);
                    arc(0, 0, arcWidth, arcHeight, 0, PI);
                    popMatrix();
                }
                break;
        }

    }

}
