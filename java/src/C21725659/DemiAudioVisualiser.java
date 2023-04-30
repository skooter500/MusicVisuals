package C21725659;

import ie.tudublin.Visual;

import java.util.ArrayList;

import processing.core.PVector;

public class DemiAudioVisualiser extends Visual {
    ArrayList<Particle> particles = new ArrayList<Particle>();

    // Define the required variables here
    float threshold = 5;
    float velocityMultiplier = 5;
    float maxAmplitude = 200;
    float minSize = 2;
    float maxSize = 20;
    int startColor = color(255, 0, 0);
    int endColor = color(0, 255, 0);
    float minLifespan = 20;
    float maxLifespan = 100;

    public void settings() {
        size(600, 600);
        println("CWD: " + System.getProperty("user.dir"));
    }

    public void setup() {
        setFrameSize(256);

        startMinim();
        loadAudio("InitialD-KillingMyLove.mp3");
        getAudioPlayer().play();
        getAudioPlayer().cue(60000);
    }

    public void draw() {
        background(0);
        calculateAverageAmplitude();
        calculateFrequencyBands();

        for (int i = 0; i < getBands().length; i++) {
            float amplitude = getBands()[i];
            if (amplitude > threshold) {
                PVector position = new PVector(width / 2, height / 2);
                PVector velocity = PVector.random2D().mult(amplitude * velocityMultiplier);
                float size = map(amplitude, 0, maxAmplitude, minSize, maxSize);
                int particleColor = color(map(i, 0, getBands().length, startColor, endColor), 255);
                float lifespan = map(amplitude, 0, maxAmplitude, minLifespan, maxLifespan);

                Particle p = new Particle(this, position, velocity, size, particleColor, lifespan);

                particles.add(p);
            }
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

