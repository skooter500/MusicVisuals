package ie.tudublin;

import java.util.ArrayList;
import ddf.minim.*;
import ddf.minim.analysis.*;

public class stars extends Visual {

    Minim minim;
    AudioPlayer player;
    FFT fft;

    ArrayList<Particle> particles = new ArrayList<Particle>();

    public void settings() {
        size(1024, 1000, P3D);
        // fullScreen(P3D, SPAN);
    }

    public void setup() {
        startMinim();
        minim = new Minim(this);
        player = minim.loadFile("MusicVisuals/java/data/Victoria_Mon_t_ft_Khalid_-_Experience.mp3", 512);
        player.play();

        fft = new FFT(player.bufferSize(), player.sampleRate());

        for (int i = 0; i < 100; i++) {
            Particle p = new Particle();
            particles.add(p);
        }

    }

    void drawDaisy() {
        // Set the center point of the daisy
        float centerX = width / 2;
        float centerY = height / 2;

        // Set the size of the daisy
        float daisySize = 200;

        // Get the amplitude of the 20th frequency band
        float amplitude = fft.getBand(10);

        // Map the amplitude value to a range of values that will control the size of
        // the center circle
        float size = map(amplitude, 0, 1, 130, 145);

        // Set the color of the daisy
        fill(255, 255, 0); // yellow

        // Draw the center of the daisy with the mapped size value
        ellipse(centerX, centerY, size, size);

        // Draw the petals of the daisy
        fill(255, 255, 255); // white
        for (int i = 0; i < 6; i++) {
            float angle = i * TWO_PI / 6;
            float petalX = centerX + cos(angle) * (daisySize / 2);
            float petalY = centerY + sin(angle) * (daisySize / 2);
            pushMatrix();
            translate(petalX, petalY);
            rotate(angle);
            ellipse(0, 0, 110, 100);
            popMatrix();
        }

        // Set the color of the daisy
        fill(255, 255, 0); // yellow

        // Draw the center of the daisy with the mapped size value
        ellipse(centerX, centerY, 130, 130);

        // Set the color and stroke for the smile
        // Draw the smile
        strokeWeight(5);
        stroke(0);
        noFill();
        arc(centerX, centerY + 25, 60, 60, 0, PI);

        // Draw the eyes
        fill(0);
        noStroke();
        ellipse(centerX - 25, centerY - 10, 20, 20);
        ellipse(centerX + 25, centerY - 10, 20, 20);

        // blush
        fill(255, 192, 203);
        noStroke();
        ellipse(centerX - 35, centerY + 9, 15, 10);
        ellipse(centerX + 35, centerY + 9, 15, 10);

    }

    public void draw() {
        background(0);
        drawDaisy();

        fft.forward(player.mix);

        // maintain a variable number of particles between 10 and 20
        int minParticles = 10;
        int maxParticles = 20;
        int numParticles = particles.size();
        int targetParticles = (int) map(fft.getBand(20), 0, 1, minParticles, maxParticles);
        if (numParticles < targetParticles) {
            for (int i = 0; i < targetParticles - numParticles; i++) {
                Particle p = new Particle();
                particles.add(p);
            }
        } else if (numParticles > targetParticles) {
            particles.subList(targetParticles, numParticles).clear();
        }

        for (int i = 0; i < particles.size(); i++) {
            Particle p = particles.get(i);
            p.update();
            p.draw();
        }

    }

    class Particle {
        float x, y;
        float vx, vy;
        float size;
        int color;

        Particle() {
            x = random(width);
            y = random(height);
            vx = random(-1, 1);
            vy = 4; // set the falling speed to a constant value of 2
            size = random(10, 20);
            color = color(random(255), random(255), random(255));
        }

        void update() {
            x += vx;
            y += vy;

            if (y > height) {
                y = 0;
            }
        }

        void draw() {
            int index = (int) map(x, 100, width, 100, fft.specSize());
            float amplitude = fft.getBand(index);

            size = amplitude * 70;
            // limit the maximum size
            if (size > 50) {
                size = 50;
            }
            fill(color);
            noStroke();
            ellipse(x, y, size, size);
        }
    }

}