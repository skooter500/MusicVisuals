package example;

import C22398106.Eadaoinsvisual;
import C22337521.FionansVisual;
import C22339066.Alannahsvisual;
import ie.tudublin.*;

public class MyVisual extends Visual {
    WaveForm wf;
    AudioBandsVisual abv;

    public void settings() {
        //size(1024, 500);

        // Use this to make fullscreen
        // fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        fullScreen(P3D, SPAN);
    }

    public void setup() {
        startMinim();
        colorMode(HSB,360,255,255);

        // Call loadAudio to load an audio file to process
        loadAudio("Bicep - Glue (Original Mix)_q5rliCxX8xc.mp3");
        rectMode(CENTER);
        // Call this instead to read audio from the microphone
        //startListening();

        wf = new WaveForm(this);
        abv = new AudioBandsVisual(this);
    }

    public void keyPressed() {
        if (key == ' ') {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
        if (key == '1')
        {
            String[] a = { "MAIN" };
            processing.core.PApplet.runSketch(a, new Eadaoinsvisual());
        }
        if (key == '2')
        {
            String[] a = { "MAIN" };
            processing.core.PApplet.runSketch(a, new FionansVisual());
        }
        if (key == '3')
        {
            String[] a = { "MAIN" };
            processing.core.PApplet.runSketch(a, new Alannahsvisual());
        }
    }

    public void draw() {
        background(0);
        stroke(200,200,200);
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
    }
}
