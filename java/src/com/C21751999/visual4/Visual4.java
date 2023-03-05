package com.C21751999.visual4;

import com.C21460524.drawObjects.ExampleDraw;

import ddf.minim.AudioBuffer;
import processing.core.PApplet;

public class Visual4 extends PApplet {
    // Private Variables
    int windowWidth;
    int windowHeight;
    PApplet pApplet;
    AudioBuffer audioBuffer;

    //  Render Objects
    // Place Objects Here
    ExampleDraw exampleDraw;
    //
    //
    //

    // LEAVE ALONE
    public Visual4(PApplet pApplet, AudioBuffer audioBuffer, int windowWidth, int windowHeight) {
        this.pApplet = pApplet;
        this.audioBuffer = audioBuffer;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        loadRenderObjects();
    } // End Visual2 Constructor

    // Draw Function for Visual 3
    public void drawVisual4() {
        // Call Draw Functions in here
        exampleDraw.drawExample();
    } // End drawVisual2


    // Load Render Objects
    private void loadRenderObjects() {
        // Create your draw Objects here
        exampleDraw = new ExampleDraw(this.pApplet, this.audioBuffer, this.windowWidth, this.windowHeight);
    } // End void oadRenderObjects
} // End clas Visual4
