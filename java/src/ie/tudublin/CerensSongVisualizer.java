package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class CerensSongVisualizer extends PApplet {
    Minim minim; // Audio library object
    AudioPlayer ap; // Audio player object
    AudioBuffer ab; // Audio buffer object

    private static final int NUM_PARTICLES = 5000; // Number of particles
    private Particle[] particles; // Array to store particles

    private float[] waveform; // Array to store the waveform data

    private float rotationAngle = 0; // Angle for rotation
    private float rotationSpeed = 0.01f; // Speed of rotation

    public void settings() {
        size(800, 600); // Set the size of the window
    }

    public void setup() {
        minim = new Minim(this); // Initialize the minim object
        particles = new Particle[NUM_PARTICLES]; // Initialize the particle array

        for (int i = 0; i < NUM_PARTICLES; i++) {
            float angle = random(TWO_PI); // Random angle
            float radius = random(width * 0.4f); // Random radius within a range
            float speed = random(0.5f, 2.0f); // Random speed within a range
            float size = random(1, 4); // Random size within a range

            particles[i] = new Particle(angle, radius, speed, size); // Create a new particle
        }

        smooth(); // Enable smoothing of edges

        ap = minim.loadFile("java/data/Parasite.mp3", 1024); // Load the audio file
        ap.play(); // Start playing the audio
        ab = ap.mix; // Get the audio buffer

        colorMode(HSB, 360, 100, 100); // Set the color mode

        waveform = ab.toArray(); // Convert audio buffer to an array
    }

    public void draw() {
        background(0); // Clear the background

        // Update and display particles
        for (Particle particle : particles) {
            particle.update(); // Update the particle
            particle.display(); // Display the particle
        }

        drawWaveform(); // Draw the waveform
        rotateStar(); // Rotate the star
    }

    private void drawWaveform() {
        float waveformHeight = height / 2; // Height of the waveform
        noFill(); // Don't fill shapes
        strokeWeight(1); // Set stroke weight

        // Draw waveform graph
        beginShape();
        for (int i = 0; i < waveform.length; i += 10) {
            float waveX = map(i, 0, waveform.length - 1, 0, width); // Map x-coordinate
            float waveY = map(waveform[i], -1, 1, waveformHeight, -waveformHeight); // Map y-coordinate based on
                                                                                    // waveform data
            stroke(i % 360, 100, 100); // Set stroke color based on index
            vertex(waveX, waveY); // Add vertex to the shape
        }
        endShape();
    }

    private void rotateStar() {
        translate(width / 2, height / 2); // Translate to the center of the window
        rotate(rotationAngle); // Apply rotation transformation

        float amplitude = max(abs(ap.left.get(0)), abs(ap.right.get(0))); // Get the maximum amplitude
        float starSize = map(amplitude, 0, 1, 50, 200); // Map amplitude to star size

        noStroke(); // Disable stroke
        int npoints = floor(map(sin(starSize * 0.3f), -1, 1, 1, 12)); // Calculate the number of points for the star
                                                                      // shape
        drawStar(0, 0, starSize, starSize / 2, npoints); // Draw a star shape with variable number of points
        drawStar(0, 0, starSize, starSize / 2, 5); // Draw a 5-pointed star
        rotationAngle += rotationSpeed; // Update the rotation angle
    }

    private void drawStar(float x, float y, float radius1, float radius2, int npoints) {
        float amplitude = max(abs(ap.left.get(0)), abs(ap.right.get(0))); // Get the maximum amplitude
        float hueValue = map(amplitude, 0, 1, 0, 360); // Map amplitude to hue value

        fill(hueValue, 100, 100); // Set fill color based on hue value
        drawStarShape(x, y, radius1, radius2, npoints); // Draw the star shape
    }

    // Helper method to draw a star shape
    private void drawStarShape(float x, float y, float radius1, float radius2, int npoints) {
        float angle = TWO_PI / npoints; // Calculate the angle between each point
        float halfAngle = angle / 2.0f; // Calculate the half angle

        beginShape();
        for (float a = 0; a < TWO_PI; a += angle) {
            float sx = x + cos(a) * radius2; // Calculate x-coordinate for outer radius
            float sy = y + sin(a) * radius2; // Calculate y-coordinate for outer radius
            vertex(sx, sy); // Add vertex to the shape
            sx = x + cos(a + halfAngle) * radius1; // Calculate x-coordinate for inner radius
            sy = y + sin(a + halfAngle) * radius1; // Calculate y-coordinate for inner radius
            vertex(sx, sy); // Add vertex to the shape
        }
        endShape(CLOSE); // Close the shape
    }

    private class Particle {
        private float angle; // Angle of the particle
        private float radius; // Distance from the center of the screen
        private float speed; // Speed at which the particle moves
        private float size; // Size of the particle
        private float rotation; // Rotation angle of the particle

        Particle(float angle, float radius, float speed, float size) {
            this.angle = angle;
            this.radius = radius;
            this.speed = random(2.0f, 1.0f); // Adjust the speed range here
            this.size = size;
            this.rotation = 0;
        }

        void update() {
            radius += speed; // Update the radius based on the speed
            if (radius > 400) {
                radius = 0; // Reset the radius if it exceeds a certain value
            }
        }

        void display() {
            float x = width / 2 + cos(angle) * radius; // Calculate the x-coordinate of the particle
            float y = height / 2 + sin(angle) * radius; // Calculate the y-coordinate of the particle

            // Get the amplitude from the audio buffer based on the angle
            float amplitude = ab.get((int) (angle * 10)) * 100;

            // Vary the hue based on the sum of the angle and amplitude
            float hueValue = (angle + amplitude) % 360;

            // Vary the size based on the amplitude
            float sizeValue = size + amplitude * 0.05f;

            pushMatrix(); // Save the current transformation matrix
            translate(x, y); // Translate to the particle's position
            rotate(rotation); // Apply rotation transformation

            noStroke(); // Disable stroke
            fill(hueValue, 100, 100); // Set fill color based on hue value
            ellipse(0, 0, sizeValue, sizeValue); // Draw the particle as an ellipse

            popMatrix(); // Restore the previous transformation matrix
        }
    }

    public static void main(String[] args) {
        PApplet.main("ie.tudublin.CerensSongVisualizer"); // Launch the application
    }
}
