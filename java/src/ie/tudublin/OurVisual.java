
package ie.tudublin;

import c22305863.IriaVis;
import c22368271.MusicVisualizer;

import ddf.minim.AudioBuffer;
import example.AudioBandsVisual;
import example.WaveForm;

public class OurVisual extends Visual {
    WaveForm wf;
    AudioBandsVisual abv;
    IriaVis ip;
    MusicVisualizer mv;

    char mode = ' ';
    int color;
    boolean isPlaying = false;

    public void settings() {
        // size(1024, 500);
        size(1024, 600, P3D);
        // Use this to make fullscreen
        // fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        // fullScreen(P3D, SPAN);
    }

    public void setup() {
        startMinim();

        // Call loadAudio to load an audio file to process
        loadAudio("Squidward's Tiki Land     Psy-Trance Remix.mp3");

        // Call this instead to read audio from the microphone
        // startListening();

        wf = new WaveForm(this);
        abv = new AudioBandsVisual(this);
        ip = new IriaVis(this);
        mv = new MusicVisualizer();
    }

    public void keyPressed() {
        if (key == ' ') {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
            isPlaying = true;
        } else {
            mode = key;
        }
    }

    public void draw() {
        background(0);

        // switch case method
        switch (mode) {
            case '1':

                wf.render();
                break;
            case '2':
                ip.render(getAudioBuffer());

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
