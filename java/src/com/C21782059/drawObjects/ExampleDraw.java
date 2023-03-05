package com.C21782059.drawObjects;

import ddf.minim.AudioBuffer;
import processing.core.PApplet;

public class ExampleDraw {
    // Private Feilds
    PApplet pApplet;
    int windowWidth;
    int windowHeight;

    AudioBuffer audioBuffer;
    
    public ExampleDraw(PApplet pApplet, AudioBuffer audioBuffer, int windowWidth, int windowHeight) {
        this.pApplet = pApplet;
        this.audioBuffer = audioBuffer;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    } // End ExampleDraw Constructor

    public void drawExample() {
        pApplet.circle(windowWidth / 2, windowHeight / 2, 5);
    } // End void drawExample()
}
