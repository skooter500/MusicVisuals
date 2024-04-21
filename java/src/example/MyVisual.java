package example;

import ie.tudublin.*;
import processing.core.PApplet;
import processing.video.Capture;

public class MyVisual extends Visual {
    WaveForm wf;
    AudioBandsVisual abv;

    public void settings() {
        size(1024, 500);

        // if ( PApplet.platform == WINDOWS )
        // System.setProperty("gstreamer.library.path",sketchPath() +  "/lib/windows-amd64");
        // else if ( PApplet.platform == MACOS )
        // System.setProperty("gstreamer.library.path", sketchPath() + "/lib/macos-x86_64");
        // else if ( PApplet.platform == LINUX )
        // System.setProperty("gstreamer.library.path", sketchPath() + "/lib/linux-amd64");
        // System.setProperty("gstreamer.plugin.path", System.getProperty("gstreamer.library.path") + "/gstreamer-1.0");
        // // Use this to make fullscreen
        // fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        // fullScreen(P3D, SPAN);
    }

    public void setup() {
        startMinim();

        // Call loadAudio to load an audio file to process
        // loadAudio("heroplanet.mp3");

        // Call this instead to read audio from the microphone
        startListening();

        wf = new WaveForm(this);
        abv = new AudioBandsVisual(this);

        printArray(Capture.list());
        capture = new Capture(this, 640, 480);
        capture.start();
        
    }

    Capture capture;

    public void keyPressed() {
        if (key == ' ') {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
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
        wf.render();
        abv.render();

        if (capture.available()) {
            capture.read();
          }
        image(capture, 500, 0, 640, 480);
    }
}
