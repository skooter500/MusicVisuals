package com.C21394933.drawObjects;

import java.time.format.SignStyle;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class FlyingThroughSound {
    // Private Vairables
    PApplet pApplet;
    int windowWidth;
    int windowHeight;

    AudioBuffer audioBuffer;
    FFT fft;
    
    public FlyingThroughSound(PApplet pApplet2, AudioBuffer audioBuffer, FFT fft, int windowWidth, int windowHeight) {
        this.pApplet = pApplet2;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.audioBuffer = audioBuffer;
        this.fft = fft;
    } // End FlyingThroughSound Constructor


    public void render() {
        drawWaveformMoving();
    } // End void render()

    private void drawWaveformMoving() {
        int audioSize = audioBuffer.size();
        float rectangleWidth = (float) windowWidth / audioSize;
        fft.forward(audioBuffer);

        pApplet.pushMatrix();
        pApplet.pushStyle();
        
        // Render audioBuffer waveform
        pApplet.fill(255);
        pApplet.stroke(255);
        pApplet.strokeWeight(4);
        for(int bufferIndex = 0; bufferIndex < audioSize; bufferIndex++) {
            pApplet.line((bufferIndex * rectangleWidth), windowHeight / 2, (bufferIndex * rectangleWidth), windowHeight / 2 + audioBuffer.get(bufferIndex) * 20.0f);
            // pApplet.line((bufferIndex * rectangleWidth), windowHeight, (bufferIndex * rectangleWidth), windowHeight - fft.getBand(bufferIndex) * 5.0f);
        } // End for


        pApplet.popMatrix();
        pApplet.popStyle();
    } // End void drawWaveformMoving()


} // End class FlyingThroughSound
