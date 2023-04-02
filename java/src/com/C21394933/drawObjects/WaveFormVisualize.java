package com.C21394933.drawObjects;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.FFT;
import ie.tudublin.Utils;
import processing.core.PApplet;

public class WaveFormVisualize extends PApplet {
    // Private Feilds
    PApplet pApplet;
    int windowWidth;
    int windowHeight;

    AudioBuffer audioBuffer;
    FFT fft;

    float position = 30;

    public WaveFormVisualize(PApplet pApplet2, AudioBuffer audioBuffer, int windowWidth, int windowHeight) {
        this.pApplet = pApplet2;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.audioBuffer = audioBuffer;
        this.fft = new FFT(2048, 44100);
    } // End WaveFormVisualize Constructor

    public void render() {

        // Utils.waitFor(200);
        // drawFrequencySpectrum();
        drawWaveForm();
    } // End void render()

    public void drawWaveForm() {
        float half = windowHeight / 2;

        for (int i = 0; i < audioBuffer.size(); i++) {
            pApplet.strokeWeight(2);
            pApplet.stroke(255);
            pApplet.point(i, position + audioBuffer.get(i) * (half - 400));
            pApplet.stroke(0);
            pApplet.line(i, position, i, position + audioBuffer.get(i) * (half - 400));
        } // End for

        position += 10;

        if (position >= 740) {
            position = 0;
            pApplet.background(0);
        } // End ifF
    } // End void drawWaveForm()

    public void drawFrequencySpectrum() {
        int[] highestFrequencyIndex = Utils.getHighestFrequencyIndex(fft, audioBuffer);
        pApplet.stroke(255);
        pApplet.line(highestFrequencyIndex[0] * 2.0f, windowHeight, highestFrequencyIndex[0] * 2.0f, windowHeight - fft.getBand( highestFrequencyIndex[0]) * 5.0f);
        pApplet.line(highestFrequencyIndex[1] * 2.0f, windowHeight, highestFrequencyIndex[1] * 2.0f, windowHeight - fft.getBand( highestFrequencyIndex[1]) * 5.0f);
        pApplet.line(highestFrequencyIndex[2] * 2.0f, windowHeight, highestFrequencyIndex[2] * 2.0f, windowHeight - fft.getBand( highestFrequencyIndex[2]) * 5.0f);
        println("Freq( " + fft.getFreq(highestFrequencyIndex[0]) + " ) ( " + fft.getFreq(highestFrequencyIndex[1]) + " ) ( " + fft.getFreq(highestFrequencyIndex[2]) + ")");
    } // End void drawFrequencySpectrum()
} // End class WaveFormVisualize
