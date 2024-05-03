package C22398106;

import ddf.minim.AudioBuffer;
import ie.tudublin.Visual;
import ie.tudublin.VisualException;

public class Eadaoinsvisual extends Visual {
    AudioBuffer ab;

    public void settings() {
        //size(1024, 500);

        // Use this to make fullscreen
        // fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        fullScreen(P3D, SPAN);
    }

    public void setup() {
        startMinim();

        // Call loadAudio to load an audio file to process
        loadAudio("Bicep - Glue (Original Mix)_q5rliCxX8xc.mp3");
    }

    public void draw() {
        background(200);
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

        float smoothedAmplitude = 0;
        float average = 0;

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
        float cx = width / 2;

        colorMode(HSB);
        background(200);
        stroke(0); 
        for (int i = 0; i < width + 25; i += 25) {
            for (int j = 0; j < height; j += 25) {
                float hue = map(i, 0, ab.size() , 0, 256);
                fill(hue, 255, 255);
                stroke(15);
                circle(i, j, cx * smoothedAmplitude);
            }
        }
    }
}
