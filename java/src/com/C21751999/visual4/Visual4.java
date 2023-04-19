package com.C21751999.visual4;

import com.C21460524.drawObjects.ExampleDraw;
import com.C21751999.drawObjects.HanaSaku;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.analysis.FFT;
import ie.tudublin.VisualAbstractClass;
import processing.core.PApplet;

public class Visual4 extends VisualAbstractClass {
    // Private Variables
    int windowWidth;
    int windowHeight;
    PApplet pApplet;
    AudioBuffer audioBuffer;

    //  Render Objects
    // Place Objects Here
    ExampleDraw exampleDraw;
    HanaSaku hana;
    //
    //

  // LEAVE ALONE
  public Visual4(PApplet pApplet, AudioBuffer audioBuffer, AudioPlayer audioPlayer, int windowWidth, int windowHeight) {
    this.pApplet = pApplet;
    this.audioBuffer = audioBuffer;
    this.audioPlayer = audioPlayer;
    this.windowWidth = windowWidth;
    this.windowHeight = windowHeight;
    this.fft = new FFT(2048, 44100);
    loadRenderObjects();
} // End Visual4 Constructor

    // Draw Function for Visual 3
    public void drawVisual() {
        // Call Draw Functions in here
        hana.render();
    } // End drawVisual2


    // Load Render Objects
    private void loadRenderObjects() {
        // Create your draw Objects here
        // exampleDraw = new ExampleDraw(this.pApplet, this.audioBuffer, this.windowWidth, this.windowHeight);
        hana = new HanaSaku(pApplet, audioBuffer, fft, windowWidth, windowHeight);
    } // End void oadRenderObjects
} // End clas Visual4
