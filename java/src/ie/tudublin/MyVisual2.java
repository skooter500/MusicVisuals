package ie.tudublin;

import ie.tudublin.*;
import processing.core.*;
import c22305863.Iiria2;
import c21404706.*;
public class MyVisual2 extends Visual {
    Iiria2 ip;


    int mode = 0;

    public void settings() {
        //size(1024, 500);

        // Use this to make fullscreen
        // fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        fullScreen(P3D, SPAN);
    }

    public void setup() {
        startMinim();
        colorMode(HSB);
        // Call loadAudio to load an audio file to process
        loadAudio("Squidward's Tiki Land     Psy-Trance Remix.mp3");

        // Call this instead to read audio from the microphone
        // startListening();

        ip = new Iiria2(this);
        //abv = new AudioBandsVisual(this);
    }

    public void keyPressed() {
        if (key >= '0' && key <= '9') {
            mode = key - '0';
        }
        if (key == ' ') {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
        
    }

    public void draw() {

        switch (mode) {
            case 0:
               ip.render();
                break;
            case 1: 
            {
                //wissam render()
            }     //etc        
            
            default:
                break;
        }
    }
}
