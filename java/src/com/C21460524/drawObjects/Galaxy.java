package com.C21460524.drawObjects;

// Dependencies
import ddf.minim.AudioBuffer;
import processing.core.PApplet;
import processing.core.PConstants;

public class Galaxy extends PApplet 
{

    // Private Fields
    PApplet pApplet;
    int windowWidth;
    int windowHeight;

    AudioBuffer audioBuffer;

    // Star fields
    int numStars = 50;
    Star[] stars = new Star[numStars];
    float starSpeed = 1.5f; // speed at which stars move

    // Planet fields
    int numPlanets = 5;


    public Galaxy(PApplet pApplet, AudioBuffer audioBuffer, int windowWidth, int windowHeight) 
    {

        this.pApplet = pApplet;
        this.audioBuffer = audioBuffer;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        // Initialize stars
        for (int i = 0; i < numStars; i++) 
        {

            float x = i * windowWidth / numStars; // evenly space stars along the x axis
            float y = random(0, windowHeight);
            stars[i] = new Star(x, y);

        }
    }

    public void drawExample() 
    {
        pApplet.background(0);
        pApplet.stroke(255);
        pApplet.strokeWeight(2);

        // Draw starfield
        drawStarfield();

        // Draw Circle
        drawCircle();

    }

    private float timeElapsed = 0;
    private float timeScale = 0.01f; // controls the rate at which the diameter increases
    private float initialDiameter = 300;
    private float rotationAngle = 0;
    private float rotationSpeed = 0.01f; // controls the speed of rotation

    private void drawCircle() {
        float x = windowWidth / 2;
        float y = windowHeight / 2;
        float amplitude = audioBuffer.level() * 1000; // use the amplitude of the audio buffer to control the size of the sphere
        float diameter = initialDiameter + amplitude + (timeElapsed * timeScale); // increase diameter over time
        pApplet.fill(255, 0, 0);
        pApplet.noStroke();

        // Draw sphere
        pApplet.pushMatrix();
        pApplet.translate(x, y, -diameter/2); // move the sphere back along the z-axis
        pApplet.rotate(rotationAngle, 1, 1, 0); // apply rotation around the x and y axes
        pApplet.beginShape(PConstants.TRIANGLES);

        int detail = 100; // set the level of detail for the sphere
        for (int j = 0; j < detail; j++)
        {
            float theta1 = PApplet.map(j, 0, detail, 0, PConstants.TWO_PI);
                
            float theta2 = PApplet.map(j + 1, 0, detail, 0, PConstants.TWO_PI);

            for (int i = 0; i < detail; i++) 
            {

                float phi1 = PApplet.map(i, 0, detail, 0, PConstants.PI);
                float phi2 = PApplet.map(i + 1, 0, detail, 0, PConstants.PI);
                float x1 = diameter * sin(phi1) * cos(theta1);
                float y1 = diameter * sin(phi1) * sin(theta1);
                float z1 = diameter * cos(phi1);
                float x2 = diameter * sin(phi1) * cos(theta2);
                float y2 = diameter * sin(phi1) * sin(theta2);
                float z2 = diameter * cos(phi1);
                float x3 = diameter * sin(phi2) * cos(theta2);
                float y3 = diameter * sin(phi2) * sin(theta2);
                float z3 = diameter * cos(phi2);
                pApplet.vertex(x1, y1, z1);
                pApplet.vertex(x2, y2, z2);
                pApplet.vertex(x3, y3, z3);
                pApplet.vertex(x1, y1, z1);
                pApplet.vertex(x3, y3, z3);
                pApplet.vertex(x2, y2, z2);

            }

        }

        pApplet.endShape();
        pApplet.popMatrix();

        // Increment time elapsed and rotation angle
        timeElapsed += 1;
        rotationAngle += rotationSpeed;

    }


    private void drawStarfield() 
    {

        for (int i = 0; i < numStars; i++) {
            Star star = stars[i];
            float radius = random(1, 3);
            float diameter = radius * 2;
            // Move star to the right
            star.x = (star.x + starSpeed) % windowWidth;
            // Draw star
            pApplet.fill(255);
            pApplet.ellipse(star.x, star.y, diameter, diameter);
        }

    }

    // Inner class representing a star object
    private class Star 
    {
        float x;
        
        float y;

        public Star(float x, float y) {
            this.x = x;
            this.y = y;
        }

    }


}
