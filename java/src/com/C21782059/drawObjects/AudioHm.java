package com.C21782059.drawObjects;

import ie.tudublin.DrawObjectAbstractClass;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class AudioHm extends DrawObjectAbstractClass {

    // Constructor
    public AudioHm(final PApplet pApplet2, final AudioBuffer audioBuffer, final FFT fft, final int windowWidth,
            final int windowHeight) {
        this.pApplet = pApplet2;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.audioBuffer = audioBuffer;
        this.fft = fft;

    }

    int numBands = 512;
    float[] bandSize = new float[numBands];

    float angle = 0;
    float colorAngle = 0;

    public void render() {
        pApplet.background(0); // Set the background color to black
        pApplet.lights(); // Enable lighting

        // Sphere field in top-left corner
        pApplet.pushMatrix();
        pApplet.translate(windowWidth / 4, windowHeight / 4, -100);
        drawShapeField();
        pApplet.popMatrix();

        // Sphere field in top-right corner
        pApplet.pushMatrix();
        pApplet.translate(windowWidth / 4 * 3, windowHeight / 4, -100);
        drawShapeField();
        pApplet.popMatrix();

        // Sphere field in bottom-right corner
        pApplet.pushMatrix();
        pApplet.translate(windowWidth / 4 * 3, windowHeight / 4 * 3, -100);
        drawShapeField();
        pApplet.popMatrix();

        // Sphere field in bottom-left corner
        pApplet.pushMatrix();
        pApplet.translate(windowWidth / 4, windowHeight / 4 * 3, -100);
        drawShapeField();
        pApplet.popMatrix();

        // Sphere field in center of screen
        pApplet.pushMatrix();
        pApplet.translate(windowWidth / 2, windowHeight / 2, -100);
        drawShapeField();
        pApplet.popMatrix();
    }

    private void drawShapeField() {

        // Initialize sphereColors after pApplet is initialized
        int[] sphereColors = {
                pApplet.color(255, 0, 0),
                pApplet.color(0, 255, 0),
                pApplet.color(0, 0, 255),
                pApplet.color(255, 255, 0),
                pApplet.color(0, 255, 255)
        };

        pApplet.rotateX(angle / 30.0f); // Rotate the spheres around the X axis
        pApplet.rotateY(angle * 1.3f / 30.0f); // Rotate the spheres around the Y axis
        pApplet.rotateZ(angle * 0.7f / 30.0f); // Rotate the spheres around the Z axis
        pApplet.noStroke(); // Disable stroke drawing
        final float r = 200; // The radius of the sphere field
        fft.forward(audioBuffer); // Perform the FFT analysis on the audio data

        // Calculate the size of each frequency band
        for (int i = 0; i < numBands; i++) {
            bandSize[i] = fft.getBand(i) * 5;
        }

        // Update the rotation angle and color angle based on the audio data
        angle += PApplet.map(bandSize[0], 0, 100, 0.01f, 0.1f);
        colorAngle += PApplet.map(bandSize[1], 0, 100, 0.001f, 0.01f);

        for (int i = 0; i < 360; i += 5) {
            float x = r * PApplet.cos(PApplet.radians(i)); // Calculate the X position of the sphere
            float y = r * PApplet.sin(PApplet.radians(i)); // Calculate the Y position of the sphere
            float z = 0; // The Z position of the sphere (always 0)
            float size = PApplet.map(bandSize[i / 5], 0, 255, 0, 50); // Calculate the size of the sphere based on the
                                                                      // frequency band
            float yOffset = PApplet.map(PApplet.sin(angle + i), -1, 1, -50, 50); // Calculate the Y offset of the sphere
                                                                                 // based on the rotation angle
            int colorIndex = (i / 5) % sphereColors.length; // Calculate the index of the color to use
            int c = sphereColors[colorIndex]; // Get the color for the sphere
            pApplet.fill(c); // Set the fill color
            pApplet.pushMatrix(); // Save the current transformation matrix
            pApplet.translate(x, y + yOffset, z); // Translate to the position of the sphere
            if (i % 2 == 0) {
                pApplet.box(size);
            } else {
                pApplet.sphere(size);
            }
            pApplet.popMatrix();
        }
    }

}
