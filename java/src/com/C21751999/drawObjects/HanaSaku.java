package com.C21751999.drawObjects;
import ddf.minim.analysis.FFT;
import ddf.minim.AudioBuffer;
import ie.tudublin.DrawObjectAbstractClass;
import processing.core.*;

public class HanaSaku extends DrawObjectAbstractClass {
    float rot = 0;
    float lerpedAverage = 0;
    
 
    public HanaSaku(PApplet pApplet2, AudioBuffer audioBuffer, FFT fft, int windowWidth, int windowHeight) {
        this.pApplet = pApplet2;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.audioBuffer = audioBuffer;
        this.fft = fft;
       
    }

    public void render(){
        pApplet.noFill();
        pApplet.pushMatrix();
        pApplet.camera(0, 0, 200, 0, 0, 0, 1, 0, 0);
        pApplet.translate(0, 0, -200);
        pApplet.strokeWeight(2);
        fft.forward(audioBuffer);
        int startBin = 20;
        int endBin = 500;
        float sum = 0;
        for (int i = startBin; i <= endBin; i++) {
            sum += fft.getBand(i);
        }
        lerpedAverage = sum / (endBin - startBin + 1) / 17.0f;
    
        pApplet.rotate(PApplet.radians(rot));
        for(float i = 0; i < PApplet.TWO_PI; i+= 0.0004f){
            float color = PApplet.map(i, 0, PApplet.TWO_PI, 0, 255);
            float colorTwo = PApplet.map(i, 0, PApplet.TWO_PI, 0, 255);
            float colorThree = PApplet.map(i, 0, PApplet.TWO_PI, 0, 255);

            // Use sin() and cos() functions to smoothly interpolate between colors
            float timeFactor = 0.00005f * pApplet.millis(); // Adjust this factor to control the speed of color change
            color += 255 + 255 * PApplet.sin(timeFactor);
            colorTwo += 255 + 255 * PApplet.cos(timeFactor);
            colorThree += 255 + 255 * PApplet.sin(timeFactor + PApplet.radians(120));

            // Keep the colors within the range of 0 to 255
            color = color % 255;
            colorTwo = colorTwo % 255;
            colorThree = colorThree % 255;

            pApplet.stroke(color, colorTwo, colorThree);
            
            //outer petals aka dots
            float r = 100 * PApplet.cos(6*i);
            float x = r * PApplet.cos(i);
            float y = r * PApplet.sin(i);
    
            pApplet.point(50 * (x * lerpedAverage), 50 *(y * lerpedAverage)); //2nd petal formation
            
            
            // first 4 petals
            r = 100 * PApplet.cos(4 * i);
            x = r * PApplet.cos(i);
            y = r * PApplet.sin(i);
            pApplet.point(50 * (x * lerpedAverage), 50 *(y * lerpedAverage)); 
        }
    
        rot += 0.75;
        pApplet.popMatrix();
    }
    
    
}