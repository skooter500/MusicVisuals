package example;

import ie.tudublin.*;

public class MyVisual extends Visual {
    WaveForm wf;
    AudioBandsVisual abv;

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
        loadAudio("song.mp3");

        // Call this instead to read audio from the microphone
        // startListening();

        wf = new WaveForm(this);
        abv = new AudioBandsVisual(this);
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
                background(0);
                try {
                    // Call this if you want to use FFT data
                    calculateFFT();
                } catch (VisualException e) {
                    e.printStackTrace();
                }
                // Call this is you want to use frequency bands
                calculateFrequencyBands();

                // Call this is you want to get the average amplitude
                calculateAverageAmplitude();
                wf.render();
                abv.render();
                break;
            case 1: 
            {
                float angle = 0;
                background(0);
                calculateAverageAmplitude();
                stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
                strokeWeight(5);
                noFill();
                lights();
                pushMatrix();
                
                camera(0, 0, 0, 0, 0, -1, 0, 1, 0);
                translate(0, 0, -200);
                rotateX(angle);
                rotateZ(angle);       
                float boxSize = 50 + (200 * getSmoothedAmplitude()); 
                box(boxSize);   
                popMatrix();
                angle += 0.01f;
                break;
            }             
            
            default:
                break;
        }
    }
}
