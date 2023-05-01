package C21725659;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;
import processing.core.PVector;

import java.util.ArrayList;

public class DemiAudioVisualiser extends Visual {
    ArrayList<Particle> particles = new ArrayList<Particle>();

    

    public void settings() {
        size(600, 600, P3D);
    }

    public void setup() {
        startMinim();
        loadAudio("InitialD-KillingMyLove.mp3");
        getAudioPlayer().play();
    }

    float radius = 200;
    float rot = 0;

    public void draw() {
        calculateAverageAmplitude();
        try {
            calculateFFT();
        } catch (VisualException e) {
            e.printStackTrace();
        }
        calculateFrequencyBands();
        background(0);
        noFill();
        stroke(255);
        lights();
        stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        camera(0, -500, 500, 0, 0, 0, 0, 1, 0);

        rot += getAmplitude() / 8.0f;
        rotateY(rot);

        float[] bands = getSmoothedBands();
        for (int i = 0; i < bands.length; i++) {
            float theta = map(i, 0, bands.length, 0, TWO_PI);
            stroke(map(i, 0, bands.length, 0, 255), 255, 255);
            float x = sin(theta) * radius;
            float z = cos(theta) * radius;
            float h = bands[i];
            pushMatrix();
            translate(x, -h / 2, z);
            rotateY(theta);
            box(50, h, 50);
            popMatrix();
        }

        // Particle system
        if (getAmplitude() > 0.1) {
            PVector position = new PVector(0, 0, 0);
            PVector velocity = PVector.random3D().mult(getAmplitude() * 200);
            float size = map(getAmplitude(), 0, 1, 5, 20);
            int color = color(random(255), random(255), random(255));
            float lifespan = random(20, 100);
            particles.add(new Particle(this,position, velocity, size, color, lifespan));
        }

        for (int i = particles.size() - 1; i >= 0; i--) {
            Particle p = particles.get(i);
            p.update();
            p.display();

            if (p.isDead()) {
                particles.remove(i);
            }
        }
    }
}
