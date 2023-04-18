package com.C21460524.drawObjects;

// Dependencies
import ddf.minim.AudioBuffer;
import processing.core.PApplet;

public class Galaxy extends PApplet {
    // Private Fields
    PApplet pApplet;
    int windowWidth;
    int windowHeight;

    AudioBuffer audioBuffer;

    // Star fields
    int numStars = 100;
    Star[] stars = new Star[numStars];

    // Planet fields
    int numPlanets = 5;
    Planet[] planets = new Planet[numPlanets];

    public Galaxy(PApplet pApplet, AudioBuffer audioBuffer, int windowWidth, int windowHeight) {
        this.pApplet = pApplet;
        this.audioBuffer = audioBuffer;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        // Initialize stars
        for (int i = 0; i < numStars; i++) {
            float x = random(0, windowWidth);
            float y = random(0, windowHeight);
            float speedX = random(-10, 10);
            float speedY = random(-10, 10);
            stars[i] = new Star(x, y, speedX, speedY);
        }

        // Initialize planets
        for (int i = 0; i < numPlanets; i++) {
            float x = random(0, windowWidth);
            float y = random(0, windowHeight);
            float speedX = random(-5, 5);
            float speedY = random(-5, 5);
            float radius = random(50, 80);
            planets[i] = new Planet(x, y, speedX, speedY, radius);
        }
    }

    public void drawExample() {
        pApplet.background(0);
        pApplet.stroke(255);
        pApplet.strokeWeight(2);

        // Draw stars
        drawStars();

        // Draw planets
        drawPlanets();
    }

    private void drawStars() {
        for (int i = 0; i < numStars; i++) {
            Star star = stars[i];
            float radius = 2;
            float diameter = radius * 2;
            // Update star position
            star.x += star.speedX;
            star.y += star.speedY;

            // Check if star has reached screen edges
            if (star.x < 0 || star.x > windowWidth) {
                star.speedX *= -1; // reverse horizontal direction
            }
            if (star.y < 0 || star.y > windowHeight) {
                star.speedY *= -1; // reverse vertical direction
            }

            // Draw star
            pApplet.ellipse(star.x, star.y, diameter, diameter);
        }
    }

    private void drawPlanets() {
        for (int i = 0; i < numPlanets; i++) {
            Planet planet = planets[i];
            float diameter = planet.radius * 2;

            // Update planet position
            planet.x += planet.speedX;
            planet.y += planet.speedY;

            // Check if planet has reached screen edges
            if (planet.x < 0 || planet.x > windowWidth) {
                planet.speedX *= -1; // reverse horizontal direction
            }
            if (planet.y < 0 || planet.y > windowHeight) {
                planet.speedY *= -1; // reverse vertical direction
            }

            // Draw planet
            pApplet.fill(random(0, 255), random(0, 255), random(0, 255)); // choose random color
            pApplet.ellipse(planet.x, planet.y, diameter, diameter);
        }
    }

    
    // Inner class representing a star object
    private class Star {
        float x;
        float y;
        float speedX;
        float speedY;

        public Star(float x, float y, float speedX, float speedY) {
            this.x = x;
            this.y = y;
            this.speedX = speedX;
            this.speedY = speedY;
        }
    }


    private class Planet {

        public float radius;
        float x;
        float y;
        float speedX;
        float speedY;

        public Planet(float x, float y, float speedX, float speedY, float radius) {
            this.x = x;
            this.y = y;
            this.speedX = speedX;
            this.speedY = speedY;
            this.radius = radius;
        }

    }

}
