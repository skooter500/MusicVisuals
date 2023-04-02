package com.C21782059.visual1;

import com.C21460524.drawObjects.ExampleDraw;

// Dependencies
import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.analysis.FFT;
import ie.tudublin.VisualAbstractClass;
import processing.core.PApplet;

public class Visual1 extends VisualAbstractClass {
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
  public Visual1(PApplet pApplet, AudioBuffer audioBuffer, AudioPlayer audioPlayer, int windowWidth, int windowHeight) {
    this.pApplet = pApplet;
    this.audioBuffer = audioBuffer;
    this.audioPlayer = audioPlayer;
    this.windowWidth = windowWidth;
    this.windowHeight = windowHeight;
    this.fft = new FFT(2048, 44100);
    loadRenderObjects();
} // End Visual2 Constructor

    // Draw Function for Visual 3
    public void drawVisual1() {
        // Call Draw Functions in here
        exampleDraw.drawExample();
    } // End drawVisual2


    // Load Render Objects
    private void loadRenderObjects() {
        // Create your draw Objects here
        exampleDraw = new ExampleDraw(this.pApplet, this.audioBuffer, this.windowWidth, this.windowHeight);
    } // End void oadRenderObjects
} // End class Visual1
