package com.C21460524.visual3;

import java.util.ArrayList;

import com.C21460524.drawObjects.ExampleDraw;
import com.C21460524.drawObjects.Galaxy;
import com.C21460524.drawObjects.Galaxy2;

// Dependencies
import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.analysis.FFT;
import ie.tudublin.DrawObjectAbstractClass;
import ie.tudublin.VisualAbstractClass;
import processing.core.PApplet;

// Draw Objects


public class Visual3 extends VisualAbstractClass {
    // Private Variables
    int windowWidth;
    int windowHeight;
    PApplet pApplet;
    AudioBuffer audioBuffer;
    int[] visual2Timings = {392, 667};
    int drawObjectsIndex = 0;
    ArrayList<DrawObjectAbstractClass> drawObjects;

    //  Render Objects
    // Place Objects Here
    ExampleDraw exampleDraw;
    Galaxy stars;
    Galaxy2 stars2;
    //
    //
    //

  // LEAVE ALONE
  public Visual3(PApplet pApplet, AudioBuffer audioBuffer, AudioPlayer audioPlayer, int windowWidth, int windowHeight) {
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
        if(audioPlayer.position() / 100 > visual2Timings[drawObjectsIndex]) 
            drawObjectsIndex++;

        drawObjects.get(drawObjectsIndex).render();
    } // End drawVisual2


    // Load Render Objects
    private void loadRenderObjects() {
        // Create your draw Objects here
        this.stars = new Galaxy(pApplet, audioBuffer, windowWidth, windowHeight);
        this.stars2 = new Galaxy2(pApplet, audioBuffer, windowWidth, windowHeight);
        //galaxy = new Test(this.pApplet, this.audioBuffer, this.windowWidth, this.windowHeight);

        drawObjects.add(stars);
        drawObjects.add(stars2);
    } // End void oadRenderObjects

} // End class Visual3
