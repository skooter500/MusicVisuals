package com.C21751999.drawObjects;
import ddf.minim.analysis.FFT;
import ddf.minim.AudioBuffer;
import ie.tudublin.DrawObjectAbstractClass;
import processing.core.*;

<<<<<<< HEAD:java/src/com/C21751999/drawObjects/HanaSaku.java
public class HanaSaku extends DrawObjectAbstractClass {
    float rot = 0;
    float lerpedAverage = 0;
    PMatrix3D matrixStack; // Matrix stack to store transformations

    public HanaSaku(PApplet pApplet2, AudioBuffer audioBuffer, FFT fft, int windowWidth, int windowHeight) {
=======

public class Flower extends DrawObjectAbstractClass {

    float lerpedAverage = 0;
    float rot = 0;

    public Flower(PApplet pApplet2, AudioBuffer audioBuffer, FFT fft, int windowWidth, int windowHeight) {
>>>>>>> 093938c236f47324a5a4156863c697a7b347c22f:java/src/com/C21751999/drawObjects/Flower.java
        this.pApplet = pApplet2;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.audioBuffer = audioBuffer;
        this.fft = fft;
        this.matrixStack = new PMatrix3D(); // Initialize the matrix stack
    }

    public void render(){
        pApplet.noFill();
        pApplet.pushMatrix(); // Push the current transformation matrix onto the stack
        pApplet.camera(0, 0, 200, 0, 0, 0, 1, 0, 0);
        pApplet.translate(0, 0, -200);
        pApplet.strokeWeight(4);
        fft.forward(audioBuffer);
        int startBin = 20;
        int endBin = 200;
        float sum = 0;
        for (int i = startBin; i <= endBin; i++) {
            sum += fft.getBand(i);
        }
        lerpedAverage = sum / (endBin - startBin + 1) / 10.0f;

        pApplet.rotate(PApplet.radians(rot));
        for(float i = 0; i < PApplet.TWO_PI; i+= 0.001f){
            float color = PApplet.map(i, 0, PApplet.TWO_PI, 0, 255);
            pApplet.stroke(color, 255, 255);

            float r = 500 * PApplet.cos(6 * i);
            float x = r * PApplet.cos(i);
            float y = r * PApplet.sin(i);

            pApplet.point(20 * (x * lerpedAverage), 20 *(y * lerpedAverage));

            r = 100 * PApplet.cos(4 * i);
            x = r * PApplet.cos(i);
            y = r * PApplet.sin(i);
            pApplet.point(20 * (x * lerpedAverage), 20 *(y * lerpedAverage));
        }

        rot += 1;
        pApplet.popMatrix(); // Pop the top transformation matrix from the stack
    }
}
