package com.C21782059.visual1;

import com.C21782059.drawObjects.AudioHm;
import com.C21782059.drawObjects.SquaresSpace;

// Dependencies
import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.analysis.FFT;
import ie.tudublin.DrawObjectAbstractClass;
import ie.tudublin.VisualAbstractClass;
import processing.core.PApplet;
import java.util.ArrayList;

public class Visual1 extends VisualAbstractClass {
    // Private Variables
    int windowWidth;
    int windowHeight;
    PApplet pApplet;
    AudioBuffer audioBuffer;

    int[] visual1Timings = { 1990, 2263, 4370};
    int drawObjectsIndex = 0;
    ArrayList<DrawObjectAbstractClass> drawObjects;

    //  Render Objects
    AudioHm audioHm;
    SquaresSpace squaresSpace;
    //ExampleDraw exampleDraw;
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

    drawObjects = new ArrayList<DrawObjectAbstractClass>();

    loadRenderObjects();
} // End Visual2 Constructor

    // Draw Function for Visual 3
    public void drawVisual() {
        // Call Draw Functions in here
        //audioHm.render();
        //System.out.println("hello");
        if(audioPlayer.position() / 100 > visual1Timings[drawObjectsIndex]) 
        drawObjectsIndex++;

        drawObjects.get(drawObjectsIndex).render();
    } // End drawVisual2


    // Load Render Objects
    private void loadRenderObjects() {
        // Create your draw Objects here
        //exampleDraw = new ExampleDraw(this.pApplet, this.audioBuffer, this.windowWidth, this.windowHeight);
        this.audioHm = new AudioHm(pApplet, audioBuffer, fft, windowWidth, windowHeight);
        this.squaresSpace = new SquaresSpace(pApplet, audioBuffer, fft, windowWidth, windowHeight);

        drawObjects.add(audioHm);
        drawObjects.add(squaresSpace);
        drawObjects.add(squaresSpace);
    } // End void oadRenderObjects
} // End class Visual1
