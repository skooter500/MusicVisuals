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

        // Screen flash effect
        drawScreenFlash();

    }

    private float timeElapsed = 0;
    private float timeScale = 0.04f; // controls the rate at which the diameter increases
    private float initialDiameter = 300;
    private float rotationAngle = 0;
    private float rotationSpeed = 0.01f; // controls the speed of rotation

    private void drawCircle() 
    {

        float x = windowWidth / 2;
        float y = windowHeight / 2;
        float amplitude = audioBuffer.level() * 1000;
        float diameter = initialDiameter + amplitude + (timeElapsed * timeScale);
    
        // Map the amplitude value to a hue value
        pApplet.colorMode(PConstants.HSB, 360, 100, 100);
        float hue = map(amplitude, 0, 200, 0, 360);
        pApplet.fill(hue, 100, 100);
    
        // Draw sphere
        pApplet.pushMatrix();
        pApplet.pushStyle();
        pApplet.translate(x, y, -diameter / 2);

        
        pApplet.noStroke();
    
        float adjustedRotationSpeed = map(amplitude, 0, 350, rotationSpeed, rotationSpeed * 40);
        rotationAngle += adjustedRotationSpeed;
    
        pApplet.rotate(rotationAngle, 1, 1, 0);
        pApplet.beginShape(PConstants.TRIANGLES);
    
        int detail = 100;
        for (int j = 0; j < detail; j++) {
            float theta1 = PApplet.map(j, 0, detail, 0, PConstants.TWO_PI);
            float theta2 = PApplet.map(j + 1, 0, detail, 0, PConstants.TWO_PI);
    
            for (int i = 0; i < detail; i++) {
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
        pApplet.popStyle();

        
        pApplet.noStroke();
    
        // Increment time elapsed
        timeElapsed += 1;
    }

    private void drawScreenFlash() {
        float amplitude = audioBuffer.level() * 1000;
        float flashOpacity = map(amplitude, 0, 800, 0, 255);
        pApplet.fill(255, 255, 255, flashOpacity);
        pApplet.rect(0, 0, windowWidth, windowHeight);
    }
    


    private void drawStarfield() {
        for (int i = 0; i < numStars; i++) {
            Star star = stars[i];
            float radius = random(1, 3);
            float diameter = radius * 2;
            // Move star to the right
            star.x = (star.x + starSpeed) % windowWidth;
            
            // Draw star with a glowing effect
            int glowAlpha = 100;
            int numGlowLayers = 3;
            for (int j = numGlowLayers; j > 0; j--) {
                pApplet.fill(255, glowAlpha / j);
                pApplet.ellipse(star.x, star.y, diameter * j, diameter * j);
            }
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
