package com.C21460524.drawObjects;

// Dependencies

import ddf.minim.AudioBuffer;
import processing.core.PApplet;

public class Test extends PApplet {
    // Private Feilds
    PApplet pApplet;
    int windowWidth;
    int windowHeight;

    AudioBuffer audioBuffer;
    public Test(PApplet pApplet, AudioBuffer audioBuffer, int windowWidth, int windowHeight) {
        this.pApplet = pApplet;
        this.audioBuffer = audioBuffer;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    } // End ExampleDraw Constructor

    public void drawExample() {
        pApplet.circle(windowWidth / 2, windowHeight / 2, 500);
    } // End void drawExample()
} // End class ExampleDraw
