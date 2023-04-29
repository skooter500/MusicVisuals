package com.C21394933.drawObjects;

import java.util.ArrayList;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.FFT;
import ie.tudublin.DrawObjectAbstractClass;
import ie.tudublin.Utils;
import processing.core.PApplet;

public class FlyingThroughSound extends DrawObjectAbstractClass {   
    // Private Fields
    private int cols;
    private int rows;
    private int scale = 20;
    private int terrainWidth = 3000;
    private int terrainHeight = 2000;
    private float flyingPosition;
    private ArrayList<ArrayList<Float>> terrainBassArrayList;
    private ArrayList<ArrayList<Float>> terrainTrebbleArrayList;
    private int colourR = 0;
    private int colourG = 0;
    private int colourB = 0;
    private int oldHighest = 0;

    
    public FlyingThroughSound(PApplet pApplet2, AudioBuffer audioBuffer, FFT fft, int windowWidth, int windowHeight) {
        this.pApplet = pApplet2;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.audioBuffer = audioBuffer;
        this.fft = fft;

        this.cols = terrainWidth / scale;
        this.rows = terrainHeight / scale;
        this.terrainBassArrayList = new ArrayList<ArrayList<Float>>();
        this.terrainTrebbleArrayList = new ArrayList<ArrayList<Float>>();

        setup();
    } // End FlyingThroughSound Constructor

    private void setup() {
        for(int indexCols = 0; indexCols < cols; indexCols++) {
            terrainBassArrayList.add(indexCols, new ArrayList<Float>());
            terrainTrebbleArrayList.add(indexCols, new ArrayList<Float>());
            for(int indexRows = 0; indexRows < rows; indexRows++) {
                terrainBassArrayList.get(indexCols).add(indexRows,  0.0f);
                terrainTrebbleArrayList.get(indexCols).add(indexRows,  0.0f);
            }  // End for
        } // End for
    }

    public void render() {
        drawTerrain();
    } // End void render()

      
    private void drawTerrain() {
        pApplet.pushMatrix();
        pApplet.pushStyle();

        int highestBass = Utils.getHighestFrequencyIndex(fft, audioBuffer)[0];
        int highestTrebble = Utils.getHighestFrequencyIndex(fft, audioBuffer)[1];

        flyingPosition -= -.1;

        float yoff = flyingPosition;
        for(int y = 0; y < rows; y++) {
            float xoff = 0;
            for(int x = 0; x < cols; x++) {
                terrainTrebbleArrayList.get(x).set(y, PApplet.map(pApplet.noise(xoff, yoff), 0, 1, -100, highestTrebble * 5));
                terrainBassArrayList.get(x).set(y, PApplet.map(pApplet.noise(xoff, yoff), 0, 1, -100, highestBass * 10));
                xoff += 0.2;
            } // End for
            yoff += 0.2;
        } // End for
        oldHighest = highestBass;

        pApplet.background(0);
        pApplet.translate(+300, 300);
        pApplet.rotateX(PApplet.PI / 2.5f);
        // pApplet.fill(200, 200, 200, 150);

        if(colourB < 200) colourB++;
        else if(colourR < 200) colourR++;
        else colourG++;
           
        pApplet.translate(-terrainWidth / 2, -terrainHeight / 2);

        for(int y = 0; y < rows - 1; y++) {
            pApplet.beginShape(PApplet.TRIANGLE_STRIP);

            for(int x = 0; x < cols; x++) {
                pApplet.fill(terrainBassArrayList.get(x).get(y) + colourR, terrainBassArrayList.get(x).get(y) + colourG, terrainBassArrayList.get(x).get(y) + colourB, 1000);
                pApplet.vertex(x * scale, y * scale, terrainBassArrayList.get(x).get(y));
                pApplet.vertex(x * scale, (y + 1) * scale, terrainBassArrayList.get(x).get(y + 1));
            } // End for

            pApplet.endShape();
        } // End for

        for(int y = 0; y < rows - 1; y++) {
            pApplet.beginShape(PApplet.TRIANGLE_STRIP);

            for(int x = 0; x < cols; x++) {
                pApplet.fill(terrainBassArrayList.get(x).get(y) + colourR, terrainBassArrayList.get(x).get(y) + colourG, terrainBassArrayList.get(x).get(y) + 80, 1000);
                pApplet.vertex(x * scale, y * scale + 200, -terrainTrebbleArrayList.get(x).get(y) + 250);
                pApplet.vertex(x * scale, ((y + 1) * scale) + 200, -terrainTrebbleArrayList.get(x).get(y + 1) + 250);
            } // End for

            pApplet.endShape();
        } // End for




        pApplet.popMatrix();
        pApplet.popStyle();
    } // End void drawTerrain()
} // End class FlyingThroughSound
