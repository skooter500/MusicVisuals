package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class CerensSongVisualizer extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioBuffer ab;

    private static final int NUM_PARTICLES = 5000;
    private Particle[] particles;

    private float[] waveform; // Array to store the waveform data

    private float rotationAngle = 0;
    private float rotationSpeed = 0.01f;

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        minim = new Minim(this);
        particles = new Particle[NUM_PARTICLES];

        for (int i = 0; i < NUM_PARTICLES; i++) {
            float angle = random(TWO_PI);
            float radius = random(width * 0.4f);
            float speed = random(0.5f, 2.0f);
            float size = random(1, 4);

            particles[i] = new Particle(angle, radius, speed, size);
        }

        smooth();

        ap = minim.loadFile("java/data/Parasite.mp3", 1024);
        ap.play();
        ab = ap.mix;

        colorMode(HSB, 360, 100, 100);

        waveform = ab.toArray(); // Convert audio buffer to an array
    }


    public void draw() {
        background(0); // Clear the background

        // Update and display particles
        for (Particle particle : particles) {
            particle.update();
            particle.display();
        }

        drawWaveform();
        rotateStar();
    }

    private void drawWaveform() {
        float waveformHeight = height / 2;
        noFill();
        strokeWeight(1);

        // Draw waveform graph
        beginShape();
        for (int i = 0; i < waveform.length; i += 10) {
            float waveX = map(i, 0, waveform.length - 1, 0, width);
            float waveY = map(waveform[i], -1, 1, waveformHeight, -waveformHeight);
            stroke(i % 360, 100, 100);
            vertex(waveX, waveY);
        }
        endShape();
    }

    private void rotateStar() {
        translate(width / 2, height / 2);
        rotate(rotationAngle);

        float amplitude = max(abs(ap.left.get(0)), abs(ap.right.get(0))); // Get the maximum amplitude
        float starSize = map(amplitude, 0, 1, 50, 200); // Map amplitude to star size

        noStroke();
        int npoints = floor(map(sin(starSize * 0.3f), -1, 1, 1, 12));
        drawStar(0, 0, starSize, starSize / 2, npoints); // Draw a 5-pointed star
        drawStar(0, 0, starSize, starSize / 2, 5); // Draw a 5-pointed star
        rotationAngle += rotationSpeed;
    }

    private void drawStar(float x, float y, float radius1, float radius2, int npoints) {
        float amplitude = max(abs(ap.left.get(0)), abs(ap.right.get(0))); // Get the maximum amplitude
        float hueValue = map(amplitude, 0, 1, 0, 360); // Map amplitude to hue value

        fill(hueValue, 100, 100);
        drawStarShape(x, y, radius1, radius2, npoints);
    }

        // Helper method to draw a star shape
        private void drawStarShape(float x, float y, float radius1, float radius2, int npoints) {
            float angle = TWO_PI / npoints;
            float halfAngle = angle / 2.0f;
            beginShape();
            for (float a = 0; a < TWO_PI; a += angle) {
                float sx = x + cos(a) * radius2;
                float sy = y + sin(a) * radius2;
                vertex(sx, sy);
                sx = x + cos(a + halfAngle) * radius1;
                sy = y + sin(a + halfAngle) * radius1;
                vertex(sx, sy);
            }
            endShape(CLOSE);
        }
    
        private class Particle {
            private float angle;
            private float radius;
            private float speed;
            private float size;
            private float rotation;
    
            Particle(float angle, float radius, float speed, float size) {
                this.angle = angle;
                this.radius = radius;
                this.speed = random(2.0f, 1.0f); // Adjust the speed range here
                this.size = size;
                this.rotation = 0;
            }
    
            void update() {
                radius += speed;
                if (radius > 400) {
                    radius = 0;
                }
            }
    
            void display() {
                float x = width / 2 + cos(angle) * radius;
                float y = height / 2 + sin(angle) * radius;
    
                float amplitude = ab.get((int) (angle * 10)) * 100; // Get amplitude from audio buffer
    
                float hueValue = (angle + amplitude) % 360; // Vary the hue based on angle and amplitude
                float sizeValue = size + amplitude * 0.05f; // Vary the size based on amplitude
    
                pushMatrix();
                translate(x, y);
                rotate(rotation);
                noStroke();
                fill(hueValue, 100, 100);
                ellipse(0, 0, sizeValue, sizeValue);
                popMatrix();
            }
        }
    
        public static void main(String[] args) {
            PApplet.main("ie.tudublin.CerensSongVisualizer");
        }
    }
    
