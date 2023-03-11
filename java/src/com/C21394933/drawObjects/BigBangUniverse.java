package com.C21394933.drawObjects;

import com.jogamp.common.util.Bitfield.Util;

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

    float bigBangRadiusLerped = 0.0f;
    float bigBangRotate = 0.0f;
    float bigBangRotateSpeed = 0.01f;
    float bigBangRotateLerped = 0.0f;

    float ringLerped = 0.0f;

    int[] frequencyIndex;

    // Constructor
    public BigBangUniverse(PApplet pApplet2, AudioBuffer audioBuffer, int windowWidth, int windowHeight) {
        this.pApplet = pApplet2;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.audioBuffer = audioBuffer;
        this.fft = new FFT(2048, 44100);
        
    } // End WaveFormVisualize Constructor

    public void render() {        
        drawBigBang();
    } // End void render()

    private void drawBigBang() {
        frequencyIndex = Utils.getHighestFrequencyIndex(fft, audioBuffer);
        pApplet.translate(windowWidth / 2, windowHeight / 2, 10);
        drawSphere();
        drawRings();
    } // End void drawBigBang()

    private void drawSphere() {

        float highestBassFrequency = fft.indexToFreq(frequencyIndex[0]);

        // Render Sphere
        bigBangRadiusLerped = PApplet.lerp(bigBangRadiusLerped, highestBassFrequency, 0.2f) * 1.1f;
    
        
        if(bigBangRadiusLerped > 60) {
            pApplet.fill(pApplet.color(100, 255, 255));
        } // End if

        pApplet.rotateX(bigBangRotate);
        pApplet.fill(pApplet.color(200, 255, 255));

        pApplet.sphere(bigBangRadiusLerped);
        // End Rneder Sphere

        // bigBangRadius += 50;
        bigBangRotate  = bigBangRotate + bigBangRotateSpeed;
    } // End void drawSphere

    private void drawRings() {
        float baseRadius = 2f;
        ringLerped = PApplet.lerp(ringLerped, frequencyIndex[1], 0.2f);
        
        pApplet.noFill();
        pApplet.stroke(255);

        for(int circleIndex = 0; circleIndex < 20; circleIndex++) {
            pApplet.circle(0, 0, ringLerped * baseRadius * circleIndex);
        } // End for
    }

    private void drawStarField() {

    }

    private class star {
        float x;
        float y;
        float z;

        
    } // End class star
}
