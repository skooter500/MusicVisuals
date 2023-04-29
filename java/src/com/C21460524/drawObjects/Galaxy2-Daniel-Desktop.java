package com.C21460524.drawObjects;

import java.util.ArrayList;

// Dependencies
import ddf.minim.AudioBuffer;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import ie.tudublin.DrawObjectAbstractClass;

public class Galaxy2 extends DrawObjectAbstractClass {

    // Private Fields
    PApplet pApplet;
    int windowWidth;
    int windowHeight;

    AudioBuffer audioBuffer;

    // Star fields
    int numStars = 50;
    Star[] stars = new Star[numStars];
    float starSpeed = 1.5f; // speed at which stars move

    public Galaxy2(PApplet pApplet, AudioBuffer audioBuffer, int windowWidth, int windowHeight) {

        this.pApplet = pApplet;
        this.audioBuffer = audioBuffer;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        // Initialize stars
        for (int i = 0; i < numStars; i++) {

            float x = i * windowWidth / numStars; // evenly space stars along the x axis
            float y = pApplet.random(0, windowHeight);
            stars[i] = new Star(x, y);

        }
    }

    // Particle system
    ArrayList<Particle> particles = new ArrayList<>();


    public void drawExample() {

        pApplet.pushMatrix();
        pApplet.pushStyle();

        // Draw earthquake effect
        drawEarthquakeEffect();

        pApplet.background(0);
        pApplet.stroke(255);
        pApplet.strokeWeight(2);

        // Draw starfield
        drawStarfield();

        // Draw Circle
        drawCircle();

        // Draw particle system
        updateParticles();

        // Screen flash effect
        drawScreenFlash();

        pApplet.popMatrix();
        pApplet.popStyle();

    }

    private float timeElapsed = 0;
    private float timeScale = 0.04f; // controls the rate at which the diameter increases
    private float initialDiameter = 300;
    private float rotationAngle = 0;
    private float rotationSpeed = 0.005f; // controls the speed of rotation

    private void drawCircle() {
        float x = windowWidth / 2;
        float y = windowHeight / 2;
        float amplitude = audioBuffer.level() * 1000;
        float diameter = (initialDiameter + amplitude + (timeElapsed * timeScale)) / 2;

        // Map the amplitude value to a hue value
        pApplet.colorMode(PConstants.HSB, 360, 100, 100);
        float hue = PApplet.map(amplitude, 0, 200, 0, 360);

        // Calculate the opacity based on a sine wave
        float opacity = PApplet.map(PApplet.sin(pApplet.frameCount * 0.05f), -1, 1, 50, 255);

        for (int k = 0; k < 4; k++) {
            float offsetX = PApplet.cos(rotationAngle) * diameter * 1.5f;
            float offsetY = PApplet.sin(rotationAngle) * diameter * 1.5f;

            pApplet.pushMatrix();
            pApplet.pushStyle();
            pApplet.translate(x + offsetX, y + offsetY, -diameter / 2);

            pApplet.noStroke();

            float adjustedRotationSpeed = PApplet.map(amplitude, 0, 200, rotationSpeed, rotationSpeed * 10);
            rotationAngle += adjustedRotationSpeed;

            pApplet.fill(hue, 100, 100, opacity); // Use the calculated opacity value
            pApplet.rotate(rotationAngle, 1, 1, 0);
            pApplet.beginShape(PConstants.TRIANGLES);

            int detail = 100;
            for (int j = 0; j < detail; j++) {
                float theta1 = PApplet.map(j, 0, detail, 0, PConstants.TWO_PI);
                float theta2 = PApplet.map(j + 1, 0, detail, 0, PConstants.TWO_PI);

                for (int i = 0; i < detail; i++) {
                    float phi1 = PApplet.map(i, 0, detail, 0, PConstants.PI);
                    float phi2 = PApplet.map(i + 1, 0, detail, 0, PConstants.PI);
                    float x1 = diameter * PApplet.sin(phi1) * PApplet.cos(theta1);
                    float y1 = diameter * PApplet.sin(phi1) * PApplet.sin(theta1);
                    float z1 = diameter * PApplet.cos(phi1);
                    float x2 = diameter * PApplet.sin(phi1) * PApplet.cos(theta2);
                    float y2 = diameter * PApplet.sin(phi1) * PApplet.sin(theta2);
                    float z2 = diameter * PApplet.cos(phi1);
                    float x3 = diameter * PApplet.sin(phi2) * PApplet.cos(theta2);
                    float y3 = diameter * PApplet.sin(phi2) * PApplet.sin(theta2);
                    float z3 = diameter * PApplet.cos(phi2);
                    pApplet.vertex(x1, y1, z1);
                    pApplet.vertex(x2, y2, z2);
                    pApplet.vertex(x3, y3, z3);
                    pApplet.vertex(x1, y1, z1);
                    pApplet.vertex(x3, y3, z3);
                    pApplet.vertex(x2, y2, z2);
                }
            }

            pApplet.endShape();

            if (amplitude > 20) {
                particles.add(new Particle(new PVector(x + offsetX, y + offsetY), hue));
                if (amplitude > 220) {
                    createExplosion(x + offsetX, y + offsetY, hue, 100);
                }
            }

            pApplet.popMatrix();
            pApplet.popStyle();
            rotationAngle += PConstants.PI / 2; // Divide the full circle into 4 equal parts for each circle
        }

        pApplet.noStroke();

        // Increment time elapsed
        timeElapsed += 1;
    }

    public void render() {
        drawExample();
    } // End void render()

    private void drawEarthquakeEffect() {
        float amplitude = audioBuffer.level() * 1000;
        if (amplitude > 150) { // You can adjust the threshold as needed
            float shakeIntensity = PApplet.map(amplitude, 50, 300, 5, 100);
            float shakeX = pApplet.random(-shakeIntensity, shakeIntensity);
            float shakeY = pApplet.random(-shakeIntensity, shakeIntensity);
            pApplet.translate(shakeX, shakeY);
        }
    }

    private void createExplosion(float x, float y, float hue, int numParticles) {
        for (int i = 0; i < numParticles; i++) {
            PVector velocity = PVector.random2D();
            velocity.mult(pApplet.random(4, 8));
            Particle particle = new Particle(new PVector(x, y), hue);
            particle.velocity = velocity;
            particles.add(particle);
        }
    }

    // Particle System
    private void updateParticles() {
        pApplet.noStroke();
        for (int i = particles.size() - 1; i >= 0; i--) {
            Particle particle = particles.get(i);
            particle.update();
            particle.display();
            if (particle.isDead()) {
                particles.remove(i);
            }
        }
    }

    private class Particle {
        PVector position;
        PVector velocity;
        float lifespan;
        float hue;

        Particle(PVector position, float hue) {
            this.position = position.copy();
            this.velocity = new PVector(pApplet.random(-2, 2), pApplet.random(-2, 2));
            this.lifespan = 255;
            this.hue = hue;
        }

        void update() {
            position.add(velocity);
            lifespan -= 3; // Decrease the value for a slower fade-out effect
            velocity.mult(0.95f); // Add some friction to the particle movement
        }

        void display() {
            float particleSize = pApplet.random(2, 6);
            pApplet.fill(hue, 100, 100, lifespan);
            pApplet.ellipse(position.x, position.y, particleSize, particleSize);
        }

        boolean isDead() {
            return lifespan <= 0;
        }
    }

    private void drawScreenFlash() {
        float amplitude = audioBuffer.level() * 1000;
        float flashOpacity = PApplet.map(amplitude, 0, 800, 0, 255);
        pApplet.fill(255, 255, 255, flashOpacity);
        pApplet.rect(0, 0, windowWidth, windowHeight);
    }

    private void drawStarfield() {
        for (int i = 0; i < numStars; i++) {
            Star star = stars[i];
            float radius = pApplet.random(1, 3);
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
    private class Star {
        float x;

        float y;

        public Star(float x, float y) {
            this.x = x;
            this.y = y;
        }

    }

}
