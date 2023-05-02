package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

import java.util.ArrayList;

public class AudioVisual extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioBuffer ab;
    FFT fft;

    ArrayList<Particle> particles;
    int numParticles = 200;
    int[] palette;
    int[] particlePalette;

    float smoothedAmplitude = 0;

    public void settings() {
        size(1024, 768, P3D);
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("Parasite.mp3", 1024);
        ap.play();
        ab = ap.mix;
        fft = new FFT(ab.size(), ap.sampleRate());
        colorMode(HSB);

        lerpedBuffer = new float[ab.size()];
        for (int i = 0; i < ab.size(); i++) {
            lerpedBuffer[i] = 0;
        }

        particles = new ArrayList<Particle>();
        for (int i = 0; i < numParticles; i++) {
            particles.add(new Particle(this));
        }

        palette = new int[]{
            color(0, 200, 255),
            color(50, 255, 200),
            color(255, 200, 100),
            color(200, 50, 255)
        };

        particlePalette = new int[]{
                color(20, 180, 200),
                color(70, 235, 150)
        };
    }

    float[] lerpedBuffer;
    class Particle {
        PApplet parent;
        float x, y, z;
        float vx, vy, vz;
        float lifespan;
        float size;
    
        Particle(PApplet parent) {
            this.parent = parent;
            x = random(-width / 2, width / 2);
            y = random(-height / 2, height / 2);
            z = random(-200, 200);
            vx = random(-2, 2);
            vy = random(-2, 2);
            vz = random(-2, 2);
            lifespan = random(100, 255);
            size = random(2, 5);
        }
    
        void update(float amplitude) {
            x += vx * amplitude;
            y += vy * amplitude;
            z += vz * amplitude;
            lifespan -= 1;
    
            if (lifespan <= 0 || x < -width / 2 || x > width / 2 || y < -height / 2 || y > height / 2 || z < -200 || z > 200) {
                x = random(-width / 2, width / 2);
                y = random(-height / 2, height / 2);
                z = random(-200, 200);
                vx = random(-2, 2);
                vy = random(-2, 2);
                vz = random(-2, 2);
                lifespan = random(100, 255);
                size = random(2, 5);
            }
        }
        void display() {
            parent.pushMatrix();
            parent.translate(width / 2 + x, height / 2 + y, z);
            parent.noStroke();
            float alpha = map(lifespan, 0, 255, 0, 1);
            parent.fill(lerpColor(particlePalette[0], particlePalette[1], alpha), alpha * 255);
            parent.sphere(size);
            parent.popMatrix();
        }
    }

    public void draw() {
        float halfH = height / 2;
        float sum = 0;

        for (int i = 0; i < ab.size(); i++) {
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
            sum += abs(lerpedBuffer[i]);
        }

        smoothedAmplitude = lerp(smoothedAmplitude, sum / (float) ab.size(), 0.5f);

        background(0);

        float colorAngle = (frameCount * 0.01f) % TWO_PI;
        float hue = map(sin(colorAngle), -1, 1, 0, 255);
        float sat = map(smoothedAmplitude, 0, 1, 50, 255);
        float brightness = map(smoothedAmplitude, 0, 1, 200, 255);

        fill(hue, sat, brightness);
        stroke(hue, sat, brightness);

        for (int i = 0; i < ab.size() - 1; i++) {
            float x1 = map(i, 0, ab.size(), 0, width);
            float x2 = map(i + 1, 0, ab.size(), 0, width);
            float y1 = halfH - lerpedBuffer[i] * halfH;
            float y2 = halfH - lerpedBuffer[i + 1] * halfH;

            line(x1, y1, x2, y2);
        }

        for (Particle p : particles) {
            p.update(smoothedAmplitude);
            p.display();
        }

        // Flashy border
        float flashiness = map(smoothedAmplitude, 0, 1, 0, 255);
        int numLights = 20;
        float lightWidth = width / (float) numLights;
        float lightHeight = height / (float) numLights;
        
        for (int i = 0; i < numLights; i++) {
            float xPos = i * lightWidth;
            float yPos = i * lightHeight;
            stroke(hue, sat, flashiness);
            strokeWeight(5);

            // Top border
            line(xPos, 0, xPos + lightWidth, 0);

            // Bottom border
            line(xPos, height, xPos + lightWidth, height);

            // Left border
            line(0, yPos, 0, yPos + lightHeight);

            // Right border
            line(width, yPos, width, yPos + lightHeight);
        }
    }

    public static void main(String[] args) {
        PApplet.main("ie.tudublin.AudioVisual");
    }
}

