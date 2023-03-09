package com.C21394933.drawObjects;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.FFT;
import ie.tudublin.Utils;
import processing.core.PApplet;

public class BigBangUniverse {
    // Public Variables

    // Private Variables
    PApplet pApplet;
    int windowWidth;
    int windowHeight;

    AudioBuffer audioBuffer;
    FFT fft;

    // Constructor
    public BigBangUniverse(PApplet pApplet2, AudioBuffer audioBuffer, int windowWidth, int windowHeight) {
        this.pApplet = pApplet2;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.audioBuffer = audioBuffer;
        this.fft = new FFT(2048, 44100);
    } // End WaveFormVisualize Constructor

    public void render() {
        pApplet.circle(windowWidth / 2, windowHeight / 2, 20);
        // Utils.waitFor(200);
    } // End void render()

}
