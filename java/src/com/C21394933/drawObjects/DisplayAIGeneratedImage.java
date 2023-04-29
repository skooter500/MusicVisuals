package com.C21394933.drawObjects;

import com.C21394933.network.DownloadAIGeneratedImage;
import com.C21394933.network.PostOpenAPI;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.FFT;
import ie.tudublin.DrawObjectAbstractClass;
import ie.tudublin.Utils;
import processing.core.PApplet;
import processing.core.PImage;

public class DisplayAIGeneratedImage extends DrawObjectAbstractClass {
    // Private Feilds
    PImage aiGeneratedImage;
    float position = 30;

    // TODO: REMOVE
    float tiles;
    float tileSize;
    float rotate = 10;

    public DisplayAIGeneratedImage(PApplet pApplet2, AudioBuffer audioBuffer, int windowWidth, int windowHeight) {
        this.pApplet = pApplet2;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.audioBuffer = audioBuffer;
        this.fft = new FFT(2048, 44100);
        
        aiGeneratedImage = pApplet.loadImage("images/ai-image-0.png");
    } // End WaveFormVisualize Constructor

    public void render() {
        drawAIGeneratedImage();
    } // End void render()

    public void drawAIGeneratedImage() {
        if(rotate % 5 == 0 && tiles < 512)
            tiles++;

        tileSize = 512 / tiles;

         
        pApplet.pushMatrix();
        pApplet.pushStyle();

        pApplet.color(255, 255, 255);
        pApplet.noStroke();

        pApplet.translate((windowWidth / 2) - 256, (windowHeight / 2) - 256, 500 - rotate);
        rotate += 1;

        for(int x = 0; x < tiles; x++) {
            for(int y = 0; y < tiles; y++) {
                
                int pixelValue = aiGeneratedImage.get((int)(x * tileSize), (int)(y * tileSize));
                float size = PApplet.map(pApplet.brightness(pixelValue), 0, 255, 0, 5);
                float brightness = PApplet.map(pApplet.brightness(pixelValue), 0, 255, 0, 100);

                pApplet.pushMatrix();
                pApplet.translate(0, 0, brightness);
                pApplet.fill(pixelValue);
                pApplet.circle((x * tileSize), (y * tileSize), size);
                pApplet.popMatrix();
            } // End for
        } // End for
        // pApplet.image(aiGeneratedImage, windowWidth / 2, windowHeight / 2);
    
        
        pApplet.popStyle();
        pApplet.popMatrix();
    } // End void drawWaveForm()

    public void drawFrequencySpectrum() {
        int[] highestFrequencyIndex = Utils.getHighestFrequencyIndex(fft, audioBuffer);
        pApplet.stroke(255);
        pApplet.line(highestFrequencyIndex[0] * 2.0f, windowHeight, highestFrequencyIndex[0] * 2.0f, windowHeight - fft.getBand( highestFrequencyIndex[0]) * 5.0f);
        pApplet.line(highestFrequencyIndex[1] * 2.0f, windowHeight, highestFrequencyIndex[1] * 2.0f, windowHeight - fft.getBand( highestFrequencyIndex[1]) * 5.0f);
        pApplet.line(highestFrequencyIndex[2] * 2.0f, windowHeight, highestFrequencyIndex[2] * 2.0f, windowHeight - fft.getBand( highestFrequencyIndex[2]) * 5.0f);
    } // End void drawFrequencySpectrum()
} // End class WaveFormVisualize
