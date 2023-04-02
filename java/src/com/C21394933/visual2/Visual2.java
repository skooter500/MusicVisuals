package com.C21394933.visual2;

// Dependencies
import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

import com.C21394933.drawObjects.BigBangUniverse;
import com.C21394933.drawObjects.FlyingThroughSound;
// Draw Objects
import com.C21394933.drawObjects.WaveFormVisualize;

public class Visual2 extends PApplet {
    // Private Variables
    int windowWidth;
    int windowHeight;
    PApplet pApplet;
    AudioPlayer audioPlayer;
    AudioBuffer audioBuffer;
    FFT fft;

    //  Render Objects
    WaveFormVisualize waveFormVisualize;
    BigBangUniverse bigBangUniverse;
    FlyingThroughSound flyingThroughSound;

    // LEAVE ALONE
    public Visual2(PApplet pApplet, AudioBuffer audioBuffer, int windowWidth, int windowHeight) {
        this.pApplet = pApplet;
        this.audioBuffer = audioBuffer;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.fft = new FFT(2048, 44100);
        loadRenderObjects();
    } // End Visual2 Constructor

    // Draw Function for Visual 2
    public void drawVisual2() {
        // waveFormVisualize.render();
        bigBangUniverse.render();
        // flyingThroughSound.render();
    } // End drawVisual2


    // Load Render Objects
    private void loadRenderObjects() {
        // Create your draw Objects here
        this.waveFormVisualize = new WaveFormVisualize(this.pApplet, this.audioBuffer, this.windowHeight, this.windowHeight);
        this.bigBangUniverse = new BigBangUniverse(pApplet, audioBuffer, fft, windowWidth, windowHeight);
        this.flyingThroughSound = new FlyingThroughSound(pApplet, audioBuffer, fft, windowWidth, windowHeight);
    } // End void oadRenderObjects
} // End class Visual2
