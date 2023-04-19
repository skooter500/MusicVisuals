package com.C21394933.drawObjects;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.FFT;
import ie.tudublin.DrawObjectAbstractClass;
import ie.tudublin.Utils;
import processing.core.PApplet;

public class BigBangUniverse extends DrawObjectAbstractClass {
    // Private Variables
    float bigBangRadiusLerped = 0.0f;
    float bigBangRotate = 0.0f;
    float bigBangRotateSpeed = 0.02f;
    float bigBangRotateLerped = 0.0f;

    float ringLerped = 0.0f;

    int[] frequencyIndex;

    int totalStars = 200;
    float[][] starPositions = new float[totalStars][2];

    // Constructor
    public BigBangUniverse(PApplet pApplet2, AudioBuffer audioBuffer, FFT fft, int windowWidth, int windowHeight) {
        this.pApplet = pApplet2;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.audioBuffer = audioBuffer;
        this.fft = fft;
        
        for(int counter = 0; counter < totalStars; counter++) {
            starPositions[counter][0] = pApplet.random(-2000, windowWidth + 2000);
            starPositions[counter][1] = pApplet.random(-2000, windowHeight + 2000);
        } // End for
    } // End WaveFormVisualize Constructor

    public void render() {        
        drawBigBang();
    } // End void render()

    private void drawBigBang() {
        frequencyIndex = Utils.getHighestFrequencyIndex(fft, audioBuffer);
        drawSphere();
        drawBlackHole();
        drawStarField();
    } // End void drawBigBang()

    private void drawSphere() {
        float chageRadius = 10.0f;
        float highestBassFrequency = fft.indexToFreq(frequencyIndex[0]);

        // Render Sun
        
        bigBangRadiusLerped = PApplet.lerp(bigBangRadiusLerped, highestBassFrequency, 0.2f) * 1.1f;
        
        pApplet.pushMatrix();
        pApplet.pushStyle();

        pApplet.translate(windowWidth / 2, windowHeight / 2, -1000);
        pApplet.rotateX(bigBangRotate);
        pApplet.rotateY(bigBangRotate);
        pApplet.rotateZ(bigBangRotate);

        drawRings();
        
        //  Planets
        for(int planetIndex = 0; planetIndex < 30; planetIndex++) {
            pApplet.fill(pApplet.color(200 * planetIndex % 255, 255, 255));
            pApplet.rotateZ(PApplet.radians(chageRadius + (chageRadius * planetIndex)));
            pApplet.translate(0, ringLerped * (2 * planetIndex), 0);
            pApplet.sphere(0.9f * planetIndex);      
        }

        bigBangRotate  = bigBangRotate + bigBangRotateSpeed;

        pApplet.popMatrix();
        pApplet.popStyle();
    } // End void drawSphere

    private void drawRings() {
        float baseRadius = 4f;
        ringLerped = PApplet.lerp(ringLerped, frequencyIndex[1], 0.4f);
        
        pApplet.noFill();
        pApplet.stroke(255);
        for(int circleIndex = 0; circleIndex < 50; circleIndex++) {
            pApplet.circle(0, 0, ringLerped * baseRadius * circleIndex);
        } // End for
    }

    int counter = 0;
    private void drawBlackHole() {
        pApplet.pushMatrix();

        pApplet.translate(windowWidth / 2, windowHeight / 2, -1000);

        pApplet.pushStyle();
        pApplet.stroke(255);
        pApplet.strokeWeight(20);
        pApplet.noFill();
        pApplet.circle(0, 0, counter * 2.3f);
        pApplet.popStyle();
        pApplet.pushStyle();
        pApplet.fill(0);
        pApplet.noStroke();
        pApplet.sphere(counter);

        counter += 2;
        pApplet.popMatrix();
        pApplet.noStroke();
        pApplet.popStyle();
    } // End void drawStarField()

    private void drawStarField() {
        pApplet.pushMatrix();
        pApplet.pushStyle();

        // Render Star Field
        pApplet.translate(0, 0, -2000);
        for(int starIndex = 0; starIndex < totalStars; starIndex++) {
            pApplet.circle(starPositions[starIndex][0], starPositions[starIndex][1], 20);
        } // End for
        

        pApplet.popMatrix();
        pApplet.popStyle();
    } // End void drawStarField()
}
