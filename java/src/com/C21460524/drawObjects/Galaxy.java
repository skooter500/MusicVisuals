package com.C21460524.drawObjects;

import ddf.minim.AudioBuffer;
import processing.core.PApplet;
import processing.core.PVector;

public class Galaxy {
    // Private Fields
    PApplet pApplet;
    int windowWidth;
    int windowHeight;
    AudioBuffer audioBuffer;
    int numStars;
    Star[] stars;

    // Constructor
    public Galaxy(PApplet pApplet, AudioBuffer audioBuffer, int windowWidth, int windowHeight, int numStars) {
        this.pApplet = pApplet;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.audioBuffer = audioBuffer;
        this.numStars = numStars;
        this.stars = new Star[numStars];
        for (int i = 0; i < numStars; i++) {
            stars[i] = new Star(pApplet.random(windowWidth), pApplet.random(windowHeight), pApplet.random(0, 1));
        }
    }

    public void update() {
        float speed = PApplet.map(audioBuffer.get(0), 0, 1, 1, 10);
        for (int i = 0; i < numStars; i++) {
            stars[i].position.x += speed;
            if (stars[i].position.x > windowWidth) {
                stars[i].position.x = 0;
            }
        }
    }

    public void display() {
        pApplet.noStroke();
        for (int i = 0; i < numStars; i++) {
            float alpha = PApplet.map(stars[i].brightness, 0, 1, 0, 255);
            pApplet.fill(255, alpha);
            pApplet.ellipse(stars[i].position.x, stars[i].position.y, stars[i].size, stars[i].size);
        }
    }

    // Private inner class to represent a star
    private class Star {
        PVector position;
        float size;
        float brightness;

        public Star(float x, float y, float brightness) {
            position = new PVector(x, y);
            size = pApplet.random(1, 4);
            this.brightness = brightness;
        }
    }
}
