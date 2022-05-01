package c20362766;



import ie.tudublin.*;

public class HabeebsVisuals extends Visual {

    EyesVisual wf;
    AmplitudeVisual wf2;
    CircleVisual wf3;
    ColourCircleVisual wf4;
    WaveyVisual wf5;
    GridFlashVisual wf6;


    int mode = 0;
    public Object prevMouseVal;

    public void settings() {
        size(800, 600);
    }

    public void setup() {

        startMinim();

        startListening();

        // new waveform instance
        wf = new EyesVisual(this);
        wf2 = new AmplitudeVisual(this);
        wf3 = new CircleVisual(this);
        wf4 = new ColourCircleVisual(this);
        wf5 = new WaveyVisual(this);
        wf6 = new GridFlashVisual(this);


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

        switch (key {
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
                wf.render();
                break;
        }
    }

    public void draw() {

        background(0);
        MusicArt();

    }

}