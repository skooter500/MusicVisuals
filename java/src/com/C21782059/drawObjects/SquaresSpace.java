package com.C21782059.drawObjects;

import ie.tudublin.DrawObjectAbstractClass;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class SquaresSpace extends DrawObjectAbstractClass {

    // Constructor
    public SquaresSpace(final PApplet pApplet2, final AudioBuffer audioBuffer, final FFT fft, final int windowWidth,
            final int windowHeight) {
        this.pApplet = pApplet2;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.audioBuffer = audioBuffer;
        this.fft = fft;

    }

    FFT fft;
    int numBands = 512;
    float[] bandSize = new float[numBands];

    float angle = 0;
    float colorAngle = 0;
    float noiseScale = 0.05f;
    float noiseStrength = 100;

    public void render() {
        pApplet.background(0);
        pApplet.lights();

        // Cube field in top-left corner
        pApplet.pushMatrix();
        pApplet.translate(windowWidth / 4, windowHeight / 4, -100);
        drawCubeField();
        pApplet.popMatrix();

        // Cube field in top-right corner
        pApplet.pushMatrix();
        pApplet.translate(windowWidth / 4 * 3, windowHeight / 4, -100);
        drawCubeField();
        pApplet.popMatrix();

        // Cube field in bottom-right corner
        pApplet.pushMatrix();
        pApplet.translate(windowWidth / 4 * 3, windowHeight / 4 * 3, -100);
        drawCubeField();
        pApplet.popMatrix();

        // Cube field in bottom-left corner
        pApplet.pushMatrix();
        pApplet.translate(windowWidth / 4, windowHeight / 4 * 3, -100);
        drawCubeField();
        pApplet.popMatrix();

        // Cube field in center of screen
        pApplet.pushMatrix();
        pApplet.translate(windowWidth / 2, windowHeight / 2, -100);
        drawCubeField();
        pApplet.popMatrix();
    }

    private void drawCubeField() {

        // Rotating angles
        pApplet.rotateX(angle / 30.0f);
        pApplet.rotateY(angle * 1.3f / 30.0f);
        pApplet.rotateZ(angle * 0.7f / 30.0f);
        pApplet.noStroke();
        fft.forward(audioBuffer);

        // get size of freq band
        for (int i = 0; i < numBands; i++) {
            bandSize[i] = fft.getBand(i) * 5;
        }

        // update angle, colour depending on freq band
        angle += PApplet.map(bandSize[0], 0, 100, 0.01f, 0.1f);
        colorAngle += PApplet.map(bandSize[1], 0, 100, 0.001f, 0.01f);

        // draw cubes
        for (int i = -200; i < 200; i += 20) {
            for (int j = -200; j < 200; j += 20) {
                for (int k = -200; k < 200; k += 20) {
                    float x = i;
                    float y = j;
                    float z = k;
                    float size = PApplet.map(bandSize[(i + 200) / 20], 0, 255, 0, 50);
                    float yOffset = pApplet.noise(i * noiseScale, j * noiseScale, k * noiseScale) * noiseStrength;
                    // Assign a different color for each cube based on its position
                    int cubeColor = pApplet.color(PApplet.map(i + colorAngle, 0, 512, 0, 255),
                            PApplet.map(j + colorAngle, 0, 512, 0, 255),
                            PApplet.map(k + colorAngle, 0, 512, 0, 255));
                    pApplet.fill(cubeColor);
                    pApplet.pushMatrix(); // save curr matrix
                    pApplet.translate(x, y + yOffset, z);
                    pApplet.box(size);
                    pApplet.popMatrix(); // get prev matrix
                }
            }
        }
    }
}
