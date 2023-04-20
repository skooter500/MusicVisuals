package ie.tudublin;

import ddf.minim.*;
import ddf.minim.analysis.*;
import processing.core.*;

import java.util.ArrayList;

public class Visualizer extends PApplet {

    Minim minim;
    AudioPlayer player;
    FFT fft;
    float[] bands;

    ArrayList<PVector> points = new ArrayList<PVector>();
    int maxPoints = 1000;

    public void settings() {
        size(1024, 768, P3D);
    }

    public void setup() {
        colorMode(HSB, 360, 100, 100);
        noFill();

        minim = new Minim(this);
        player = minim.loadFile("Parasite.mp3", 1024);
        player.play();

        fft = new FFT(player.bufferSize(), player.sampleRate());
        bands = new float[fft.specSize()];

        // Create initial set of points
        for (int i = 0; i < maxPoints; i++) {
            points.add(new PVector(random(width), random(height), random(50, 200)));
        }
    }

    public void draw() {
        background(0);
        fft.forward(player.mix);

        // Update point positions based on FFT bands
        for (int i = 0; i < points.size(); i++) {
            PVector p = points.get(i);
            float band = bands[i % bands.length];
            float x = map(band, 0, 255, -width, width);
            float y = map(band, 0, 255, -height, height);
            p.x += x * noise(p.z) * 0.1f;
            p.y += y * noise(p.z) * 0.1f;
            p.z -= 1;
            if (p.z < 0) {
                p.z = random(50, 200);
                p.x = random(width);
                p.y = random(height);
            }
        }

        // Calculate FFT bands
        for (int i = 0; i < bands.length; i++) {
            bands[i] = fft.getBand(i);
        }

        // Draw jagged lines using FFT bands
        strokeWeight(3);
        stroke(0, 80, 100);
        beginShape();
        for (int i = 0; i < bands.length; i++) {
            float x = map(i, 0, bands.length, 0, width);
            float y = map(bands[i], 0, 255, height / 2, 0);
            y += noise(i) * 50;
            vertex(x, y);
        }
        endShape();

        // Draw particles at each point
        strokeWeight(2);
        for (PVector p : points) {
            float hue = map(p.z, 50, 200, 0, 360);
            stroke(hue, 100, 100);
            point(p.x, p.y, p.z);
        }
    }

    public void stop() {
        player.close();
        minim.stop();
        super.stop();
    }

    public static void main(String[] args) {
        String[] processingArgs = {"Visualizer"};
        Visualizer visualizer = new Visualizer();
        PApplet.runSketch(processingArgs, visualizer);
    }

}
