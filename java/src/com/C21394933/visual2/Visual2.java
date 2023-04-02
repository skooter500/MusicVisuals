package com.C21394933.visual2;

// Dependencies
import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.analysis.FFT;
import ie.tudublin.DrawObjectAbstractClass;
import ie.tudublin.VisualAbstractClass;
import processing.core.PApplet;
import java.util.ArrayList;


// Draw Objects
import com.C21394933.drawObjects.WaveFormVisualize;
import com.C21394933.drawObjects.BigBangUniverse;
import com.C21394933.drawObjects.FlyingThroughSound;

public class Visual2 extends VisualAbstractClass {
    // Private Variables
    int[] visual2Timings = {802, 935, 1075};
    int drawObjectsIndex = 0;
    ArrayList<DrawObjectAbstractClass> drawObjects;

    //  Render Objects
    WaveFormVisualize waveFormVisualize;
    BigBangUniverse bigBangUniverse;
    FlyingThroughSound flyingThroughSound;

    // LEAVE ALONE
    public Visual2(PApplet pApplet, AudioBuffer audioBuffer, AudioPlayer audioPlayer, int windowWidth, int windowHeight) {
        this.pApplet = pApplet;
        this.audioBuffer = audioBuffer;
        this.audioPlayer = audioPlayer;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.fft = new FFT(2048, 44100);

        drawObjects = new ArrayList<DrawObjectAbstractClass>();
        loadRenderObjects();
    } // End Visual2 Constructor

    // Draw Function for Visual 2
    public void drawVisual() {
        if(audioPlayer.position() / 100 > visual2Timings[drawObjectsIndex]) 
            drawObjectsIndex++;

        drawObjects.get(drawObjectsIndex).render();
    } // End drawVisual2


    // Load Render Objects
    private void loadRenderObjects() {
        // Create your draw Objects here
        this.waveFormVisualize = new WaveFormVisualize(this.pApplet, this.audioBuffer, this.windowHeight, this.windowHeight);
        this.bigBangUniverse = new BigBangUniverse(pApplet, audioBuffer, fft, windowWidth, windowHeight);
        this.flyingThroughSound = new FlyingThroughSound(pApplet, audioBuffer, fft, windowWidth, windowHeight);

        drawObjects.add(bigBangUniverse);
        drawObjects.add(waveFormVisualize);
        drawObjects.add(flyingThroughSound);
    } // End void oadRenderObjects
} // End class Visual2
