package com.C21394933.visual2;

import com.C21394933.drawObjects.AIGenerationMenu;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ie.tudublin.VisualAbstractClass;
import processing.core.PApplet;

public class StartMenuVisual extends VisualAbstractClass {
    // Private Variables

    // Draw Objects
    AIGenerationMenu aiGenerationMenu;


    // LEAVE ALONE
    public StartMenuVisual(PApplet pApplet, AudioBuffer audioBuffer, AudioPlayer audioPlayer, int windowWidth, int windowHeight) {
        this.pApplet = pApplet;
        this.audioBuffer = audioBuffer;
        this.audioPlayer = audioPlayer;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        
        loadRenderObjects();
    } // End Visual2 Constructor

    // Draw Function for Start Menu Visual
    public void drawVisual() {
        aiGenerationMenu.render();
    } // End drawVisual2


    // Load Render Objects
    private void loadRenderObjects() {
        this.aiGenerationMenu = new AIGenerationMenu(this.pApplet, this.audioBuffer, this.audioPlayer, this.fft, this.windowHeight, this.windowHeight);
    } // End void oadRenderObjects

}
