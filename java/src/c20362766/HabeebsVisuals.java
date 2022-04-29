package c20362766;

import ie.tudublin.*;

public class HabeebsVisuals extends Visual {

    WaveForm wf;
    WaveForm2 wf2;
    WaveForm3 wf3;
    WaveForm4 wf4;
    WaveForm5 wf5;
    WaveForm6 wf6;


    int mode = 0;
    public Object prevMouseVal;

    public void settings() {
        size(1000, 800);

    }

    public void setup() {

        startMinim();

        startListening();

        // new waveform instance
        wf = new WaveForm(this);
        wf2 = new WaveForm2(this);
        wf3 = new WaveForm3(this);
        wf4 = new WaveForm4(this);
        wf5 = new WaveForm5(this);
        wf6 = new WaveForm6(this);


    }

    public void MusicArt() {
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

        switch (key) {
            case '1':
                wf.render();
                break;
            case '2':
                wf2.render();
                break;
            case '3':
                wf3.render();
                break;
            case '4':
                wf4.render();
                break;
            case '5':
                wf5.render();
                break;
            case '6':
                wf6.render();
                break;
            default:
                wf2.render();
                break;
        }
    }

    public void draw() {

        background(0);
        MusicArt();

    }

}