package com.C21460524.visual3;

import com.C21460524.drawObjects.ExampleDraw;
import com.C21460524.drawObjects.Galaxy;

// Dependencies
import ddf.minim.AudioBuffer;
import processing.core.PApplet;

// Draw Objects


public class Visual3 extends PApplet {
    // Private Variables
    int windowWidth;
    int windowHeight;
    PApplet pApplet;
    AudioBuffer audioBuffer;

    //  Render Objects
    // Place Objects Here
    ExampleDraw exampleDraw;
    Galaxy stars;
    //
    //
    //

    // LEAVE ALONE
    public Visual3(PApplet pApplet, AudioBuffer audioBuffer, int windowWidth, int windowHeight) {
        this.pApplet = pApplet;
        this.audioBuffer = audioBuffer;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        loadRenderObjects();
    } // End Visual2 Constructor

    // Draw Function for Visual 3
    public void drawVisual3() {
        // Call Draw Functions in here
        stars.drawExample();
    } // End drawVisual2


    // Load Render Objects
    private void loadRenderObjects() {
        // Create your draw Objects here
        stars = new Galaxy(this.pApplet, this.audioBuffer, this.windowWidth, this.windowHeight);
        //galaxy = new Test(this.pApplet, this.audioBuffer, this.windowWidth, this.windowHeight);
    } // End void oadRenderObjects
} // End class Visual3
