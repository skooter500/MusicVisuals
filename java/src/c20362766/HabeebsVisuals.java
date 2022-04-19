package c20362766;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;
import processing.core.PApplet;

public class HabeebsVisuals extends Visual {

    WaveForm wf;
    WaveForm2 wf2;
    WaveForm3 wf3;
    WaveForm4 wf4;
    WaveForm5 wf5;
    WaveForm6 wf6;
    WaveForm7 wf7;

    int mode = 0;

    public void settings() {

        size(950, 2000, P3D);

    }

    public void setup() {
        startMinim();

        // Call this instead to read audio from the microphone
        startListening();

        // new waveform instance
        wf = new WaveForm(this);
        wf2 = new WaveForm2(this);
        wf3 = new WaveForm3(this);
        wf4 = new WaveForm4(this);
        wf5 = new WaveForm5(this);
        wf6 = new WaveForm6(this);
        wf7 = new WaveForm7(this);

    }

    public void keyPressed() {
        if (key >= '0' && key <= '9') {
            mode = key - '0';
        }
        println(mode);
    }

    public void draw() {
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

        // wf.render();
        // wf2.render();
        // wf3.render();
        // wf4.render();
        // wf5.render();
        // wf6.render();
        // wf7.render();

        switch (mode) {
            case 0:
                wf.render();
                break;
            case 1:
                wf2.render();
                break;
            case 2:
                wf3.render();
                break;
            case 3:
                wf4.render();
                break;
            case 4:
                wf5.render();
                break;
            case 5:
                wf6.render();
                break;
            case 6:
                wf7.render();
                break;

        }

    }

}