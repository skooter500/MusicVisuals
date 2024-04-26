package ie.tudublin;

import c22359751.ArushiVisual1;
import c22359751.ArushiVisual2;
import c22380473.JeniedVisual1;
import c22380473.JeniedVisual2;
import c21331753.TaniaVisual1;
import c21331753.TaniaVisual2;

public class CombinedVisual extends Visual {
    ArushiVisual1 as1;
    ArushiVisual2 as2;
    JeniedVisual1 js1;
    JeniedVisual2 js2;
    TaniaVisual1 ts1;
    TaniaVisual2 ts2;

    char visualSelected = ' ';

    public void settings() {
        println("CWD: " + System.getProperty("user.dir"));
        size(1024, 500);
        // Use this to make fullscreen
        fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        fullScreen(P3D, SPAN);
    }

    public void setup() {
        startMinim();
        // Call loadAudio to load an audio file to process
        loadAudio("java/data/meetmehalfway.mp3");

        // Call this instead to read audio from the microphone
        // startListening();

        as1 = new ArushiVisual1(this);
        as2 = new ArushiVisual2(this);
        js1 = new JeniedVisual1(this);
        js2 = new JeniedVisual2(this);
        ts1 = new TaniaVisual1(this);
        ts2 = new TaniaVisual2(this);
    }

    public void keyPressed() {
        if (key == ' ') {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        } else {
            visualSelected = key;
        }
    }

    public void draw() {
        background(0);

        switch (visualSelected) {
            case '1':
                js1.render();
                break;
            case '2':
                as2.render();
                break;
            case '3':
                as1.render();
                break;
            case '4':
                js2.render();
                break;
            case '5':
                ts1.render();
                break;
            case '6':
                ts2.render();
                break;
            default:
                break;
        }

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
    }
}
