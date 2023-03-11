package com.C21394933.visual2;

// Dependencies
import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import processing.core.PApplet;

import com.C21394933.drawObjects.BigBangUniverse;
// Draw Objects
import com.C21394933.drawObjects.WaveFormVisualize;

public class Visual2 extends PApplet {
    // Private Variables
    int windowWidth;
    int windowHeight;
    PApplet pApplet;
    AudioPlayer audioPlayer;
    AudioBuffer audioBuffer;

    //  Render Objects
    WaveFormVisualize waveFormVisualize;
    BigBangUniverse bigBangUniverse;

    // LEAVE ALONE
    public Visual2(PApplet pApplet, AudioBuffer audioBuffer, int windowWidth, int windowHeight) {
        this.pApplet = pApplet;
        this.audioBuffer = audioBuffer;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        loadRenderObjects();
    } // End Visual2 Constructor

    // Draw Function for Visual 2
    public void drawVisual2() {
        // waveFormVisualize.render();
        bigBangUniverse.render();
    } // End drawVisual2


    // Load Render Objects
    private void loadRenderObjects() {
        // Create your draw Objects here
        this.waveFormVisualize = new WaveFormVisualize(this.pApplet, this.audioBuffer, this.windowHeight, this.windowHeight);
        this.bigBangUniverse = new BigBangUniverse(pApplet, audioBuffer, windowWidth, windowHeight);
    } // End void oadRenderObjects
} // End class Visual2
