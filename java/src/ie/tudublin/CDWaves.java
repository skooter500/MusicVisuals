package ie.tudublin;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PVector;
import ddf.minim.analysis.*;

public class CDWaves extends PApplet {

    Minim m;
    AudioPlayer player;
    FFT fft;

    int x;
    int radius = 200;
    float hue = 0;

    public void settings() {
        size(1024, 1000);
    }

    public void setup() {
        m = new Minim(this);

        player = m.loadFile("java/data/horizon.mp3", 1024);
        player.play();

        fft = new FFT(player.bufferSize(), player.sampleRate());

        x = 0;
    }

    public void draw() {
        translate(width / 2, height / 2);
        background(0);

        colorMode(HSB, 255);

        float avgAmplitude = player.mix.level() * 255;
        fill(avgAmplitude, 0, 0);
        circle(0, 0, 120);
        fill(255);
        circle(cos(radians(x)) * 5, sin(radians(x)) * 5, 110);
        fill(0);
        circle(0, 0, 10);

        if (player.isPlaying()) x += 2;

        fft.forward(player.mix);
        float bands = fft.specSize();

        for (int i = 0; i < bands * 2; i++) {
            float start_x = radius * cos(PI * (i + x) / bands);
            float start_y = radius * sin(PI * (i + x) / bands);

            float bandAmplitude;
            if (i < bands) {
                bandAmplitude = fft.getBand(i);
            } else {
                bandAmplitude = fft.getFreq(i);
            }
            hue += 0.5;
            if (hue > 255) {
                hue = 0;
            }
            int currentColor = color(hue, 255, 255, 100);
            int nextColor = color(hue + 1, 255, 255, 100);
            int blendedColor = lerpColor(currentColor, nextColor, bandAmplitude / 2);
            fill(blendedColor);
            noStroke();

            float angle = PI * (i + x) / bands;
            float arcWidth = 20 + bandAmplitude * 2;
            float arcHeight = 20 + bandAmplitude * 2;

            pushMatrix();
            translate(start_x, start_y);
            rotate(angle);
            arc(0, 0, arcWidth, arcHeight, 0, PI);
            popMatrix();
        }
    }
}
