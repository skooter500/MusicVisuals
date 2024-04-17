package example;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Assignment_Test extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;

    float[] lerpedBuffer;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    Circle[] shapes;

    int screen_size = 600;
    int lastSpawnTime = 0; // Track the time since last circle spawn

    Square theVoid; // Declare the square as a global variable

    boolean scene1 = true; // Control variable for the initial scene
    boolean scene2 = true; // Control variable for the initial scene


    public void settings() {
        size(screen_size, screen_size);
        //fullScreen(P3D, SPAN);
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("C:\\Users\\ruben\\Downloads\\Cowboys from Hell (2010 Remaster).wav", screen_size);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];

        // Initialize the shapes array with a specific size
        shapes = new Circle[18]; // 18 is arbitrary, allows for 18 circles to show before 1 must be deleted
        shapes[0] = new Circle();
        lastSpawnTime = millis(); // Initialize the last spawn time

        theVoid = new Square(); // Initialize the Void
    }

    float lerpedAvg = 0;

    public void draw() {
        background(0);

        // Check if it's time to spawn a new circle
        if (millis() - lastSpawnTime >= 500) { // Spawn every 4 seconds
            addCircle();
            lastSpawnTime = millis(); // Update last spawn time
        }

        // Calculate sum and average of the samples
        float sum = 0;
        for (int i = 0; i < ab.size(); i++) {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f); // Lerp each element of buffer
        }
        float average = sum / ab.size();
        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

        // Calculate tempo based on amplitude
        float tempo = map(smoothedAmplitude, 0, 1, 0.1f, 5); // Map amplitude to tempo range

        lerpedAvg = lerp(lerpedAvg, average, 0.3f);

        if (millis() > 18000 && !theVoid.expanding) {
            theVoid.expanding = true; // Start expanding the square
            theVoid.startTime = millis(); // Record the start time
        }

        if (theVoid.expanding) {

            if (millis() - theVoid.startTime >= theVoid.duration) {
                scene1 = false; // Set flag to switch to the new scene
                scene2 = true;
            }
        }

        if (scene1) {
            drawScene1(tempo); // Draw the initial scene with circles and squares
        } 
        else if(scene2){
            drawScene2(tempo);
        }
        else {
            background(0); // Set background to black
        }
    }

    void drawScene1(float tempo) {

        drawHole();
        // Move and draw circles
        for (int i = 0; i < shapes.length; i++) {
            if (shapes[i] == null) {
                break;
            }
            shapes[i].move(tempo);
            shapes[i].draw();
        }
        if(theVoid.expanding){
            theVoid.expand();
        }
    }

    void drawScene2(float tempo){
        drawHole(width/3, height/4);
        drawHole(width/3*2, height/4);
        drawWaveForm(height/3*2);
    }

    // Method to draw circles
    void drawHole() {
        for (int i = 0; i < ab.size(); i += 10) {
            // Interpolate the hue value
            float hue = map(i, 0, ab.size(), 0, 256);
            stroke(hue, 255, 255);
            noFill();

            // Draw circles using lerpedAvg for size
            circle(width / 2, height / 2, ab.get(i) * lerpedAvg * screen_size / 2 * 6);
        }
    }

    void drawHole(float centerX, float centerY) {
        for (int i = 0; i < ab.size(); i += 10) {
            // Interpolate the hue value
            float hue = map(i, 0, ab.size(), 0, 256);
            stroke(hue, 255, 255);
            noFill();

            // Draw circles using lerpedAvg for size
            circle(centerX, centerY, ab.get(i) * lerpedAvg * screen_size / 2 * 6);
        }
    }

    void drawWaveForm(float pos){
        for(int i = 0; i < ab.size(); i++){
            float hue = map(i, 0, ab.size(), 0, 256);
            stroke(hue, 255, 255);
            line(i, pos, i, pos + ab.get(i) * pos * lerpedAvg);
        }
    }

    // Method to add a new circle to the next available slot in the array
    void addCircle() {
        for (int i = 0; i < shapes.length; i++) {
            if (shapes[i] == null) {
                shapes[i] = new Circle();
                break;
            }
        }
    }

    class Circle {
        float centerx;
        float centery;
        float radius = 100f;
        float speedX;
        float speedY;
        float hue; 

        Circle() {
            int side = (int) random(1, 4.9f); // left = 1, up = 2, right = 3, down = 4
            if (side == 1) {
                centerx = 0;
                centery = random(0, height);
            } else if (side == 2) {
                centerx = random(0, width);
                centery = 0;
            } else if (side == 3) {
                centerx = width;
                centery = random(0, height);
            } else {
                centerx = random(0, width);
                centery = height;
            }
            speedX = (float) (width / 2 - centerx) * 0.01f;
            speedY = (float) (height / 2 - centery) * 0.01f;
            hue = random(256); // Assign random hue value
        }

        void move(float tempo) {
            centerx += speedX * (tempo);
            centery += speedY * (tempo);
        }

        void draw() {
            // Calculate distance from center
            float distanceFromCenter = dist(centerx, centery, width / 2, height / 2);

            // Map the distance to a radius range (reverse the values)
            float mappedRadius = map(distanceFromCenter, 0, width / 2, 0, 100);

            // Draw the circle with the mapped radius
            fill(hue, 255, 255);
            ellipse(centerx, centery, mappedRadius * 2, mappedRadius * 2);

            // Remove the circle if it reaches the center
            if (distanceFromCenter <= 5) {
                removeCircle(this);
            }
        }
    }

    class Square {
        float startX;
        float startY;
        float endX;
        float endY;
        float duration = 2000; // 10 seconds duration for expansion
        float startTime;
        boolean expanding = false;

        Square() {
            startX = width / 2;
            startY = height / 2;
            endX = width / 2;
            endY = height / 2;
        }
        

        void expand() {
            float elapsedTime = millis() - startTime;

            float expansionFactor = map(elapsedTime, 0, duration, 0, 1);

            // Calculate the change in width and height from the center
            float changeX = (width / 2) * expansionFactor;
            float changeY = (height / 2) * expansionFactor;

            // Calculate the center position of the square
            float centerX = width / 2;
            float centerY = height / 2;

            // Adjust the start and end points equally from the center
            startX = centerX - changeX;
            startY = centerY - changeY;
            endX = centerX + changeX;
            endY = centerY + changeY;

            // Set the fill color to black
            fill(0);

            // Draw the square with the calculated center
            rect(startX, startY, endX - startX, endY - startY);
        }
    }

    // Method to remove a circle from the array
    void removeCircle(Circle circle) {
        for (int i = 0; i < shapes.length; i++) {
            if (shapes[i] == circle) {
                // Remove the circle
                shapes[i] = null;

                // Shift subsequent circles one position back
                for (int j = i; j < shapes.length - 1; j++) {
                    shapes[j] = shapes[j + 1];
                }
                shapes[shapes.length - 1] = null; // Clear the last element

                break; // Exit the loop once the circle is removed
            }
        }
    }
}