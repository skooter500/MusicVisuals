package com.C21394933.drawObjects;

import com.jogamp.common.util.Bitfield.Util;

import ddf.minim.AudioBuffer;
import ie.tudublin.Utils;
import processing.core.PApplet;

public class WaveFormVisualize extends PApplet {
    // Private Feilds
    PApplet pApplet;
    int windowWidth;
    int windowHeight;

    AudioBuffer audioBuffer;

    float position = 30;

    public WaveFormVisualize(PApplet pApplet2, AudioBuffer audioBuffer, int windowWidth, int windowHeight) {
        this.pApplet = pApplet2;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.audioBuffer = audioBuffer;
    } // End WaveFormVisualize Constructor

    public void render() {
        Utils.waitFor(100);
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
        } // End if
    }
} // End class WaveFormVisualize
