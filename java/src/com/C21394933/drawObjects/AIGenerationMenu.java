package com.C21394933.drawObjects;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.analysis.FFT;
import ie.tudublin.DrawObjectAbstractClass;
import ie.tudublin.MusicVisualizer;
import processing.core.PApplet;

public class AIGenerationMenu extends DrawObjectAbstractClass {
    static int timingsCounter;
    AudioPlayer audioPlayer;

    // Constructor
    public AIGenerationMenu(PApplet pApplet2, AudioBuffer audioBuffer, AudioPlayer audioPlayer, FFT fft, int windowWidth, int windowHeight) {
        this.pApplet = pApplet2;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.audioBuffer = audioBuffer;
        this.audioPlayer = audioPlayer;
        this.fft = fft;
    } // End WaveFormVisualize Constructor


    public void render() {
        if(pApplet.keyPressed && timingsCounter == 0) {
            MusicVisualizer.timingsCounter++;
            audioPlayer.play();
            System.out.println("Hello");
        }
    }
}
