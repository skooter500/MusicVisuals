package C22410766;

import java.util.ArrayList;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;
import processing.core.PApplet;


public class AaronVisual {
    Visual visual;
    ArrayList<Particle> particles = new ArrayList<>();
    ArrayList<ArrayList<Lines>> lineSets = new ArrayList<>();
    
    
    int len, linesPerBand = 20;
    float amplitude, centerX, centerY;
    float[] bands;
    int radius = 200;

    public AaronVisual(Visual visual) {
        this.visual = visual;
        visual.startMinim();

        this.centerX = visual.width / 2;
        this.centerY = visual.height / 2;

        
        
        visual.calculateFrequencyBands(); // Calculate frequency bands
        visual.calculateAverageAmplitude(); // Calculate amplitude
        this.bands = visual.getSmoothedBands(); // max 667
        this.len = bands.length;
        this.amplitude = visual.getSmoothedAmplitude(); // max value is 0.233

        float[] bandLengthsstart = {radius, 1200, 1400, 1600, 1800, 2000, 2200, 2400, 2600}; // Example specific lengths for each band
        float[] bandLengthsend = {100, 1200, 1400, 1600, 1800, 2000, 2200, 2400, 2600}; // Example specific lengths for each band
        int[] bandColors = {visual.color(255, 255, 125),   // Red
                            visual.color(20, 165, 120), // Orange
                            visual.color(40, 255, 125), // Yellow
                            visual.color(100, 255, 125),   // Green
                            visual.color(150, 55, 125),   // Blue
                            visual.color(195, 255, 120),  // Indigo
                            visual.color(218, 230, 128),// Violet
                            visual.color(225, 230, 125), // Magenta
                            visual.color(255, 192, 123) // Pink
    };

        for (int i = 0; i < len; i++) {
            ArrayList<Lines> lines = new ArrayList<>();
            for (int j = 0; j < linesPerBand; j++) {
                float angle = visual.TWO_PI / linesPerBand * j + visual.TWO_PI / len * i;
                float strokeWeight = visual.random(1, 3);
                lines.add(new Lines(visual, 0, 0, bandLengthsstart[0], angle, bandLengthsend[i], bandColors[i], strokeWeight));
            }
            lineSets.add(lines);
        }
        
    }

    float b = 0;                 // Angle of rotation
    float offset = (float) (visual.PI/24.0);  // Angle offset between boxes
    int num = 60;

    

    public void draw() {
        visual.background(0);
        
        //float bgHue = visual.map(amplitude, 0, 0.3f, 0, 360);  // Use full color wheel
        //visual.background(bgHue, 100, 100, 1);  // Set background with HSB color mode
        
        
        try {
            visual.calculateFFT(); // Perform FFT analysis
        } catch (VisualException e) {
            e.printStackTrace();
        }

        visual.calculateFrequencyBands(); // Calculate frequency bands
        visual.calculateAverageAmplitude(); // Calculate amplitude
        bands = visual.getSmoothedBands(); // max 667
        
        amplitude = visual.getSmoothedAmplitude(); // max value is 0.233
        float radius = visual.map(amplitude, 0, 0.3f, 200, 400);

        
        float hue = visual.map(amplitude, 0.15f, 0.3f, 100, 255);
        //visual.strokeWeight(10);
        
        visual.noFill();
        
        visual.lights();
        visual.translate(centerX, centerY); // Center translate to middle of window
        float rotateVelocity = visual.map(amplitude, 0.1f, 0.24f, 0.4f, 1.1f) * offset;
        

        for (int i = 0; i < num; i++) {
            float gray = visual.map(i, 0, num - 1, 0, 255);
            visual.pushMatrix();
            //visual.fill(gray);
            visual.stroke(hue, 255, 255);
            visual.rotateY(b + rotateVelocity * i);    // Rotate around Y axis
            visual.rotateX(b / 2 + rotateVelocity * i); // Rotate around X axis
            visual.ellipse(0, 0, radius, radius);
            visual.popMatrix();
        }

        b += 0.01;  // Increment angle
        if (b > visual.TWO_PI) {  // Reset 'b' to avoid overflow
            b = 0;
                }
        // Draw each circle
        
        
        for (int i = particles.size() - 1; i >= 0; i--) {
            Particle p = particles.get(i);
            p.run();  // Update and display the particle

            if (p.isDead()) {
                particles.remove(i);  // Remove dead particles
            }
        }

        // Add new particles based on some condition (e.g., on beats)
        int numParticlesToSpawn = (int) (amplitude * 350);  // Scale number of particles with amplitude
        for (int i = 0; i < numParticlesToSpawn; i++) {
            float angle = visual.random(visual.TWO_PI);
            float velocityFactor = visual.map(amplitude, 0.1f, 0.24f, 1, 7);  // Map the band amplitude to a useful velocity range
            particles.add(new Particle(visual, 0, 0, radius/2, angle, velocityFactor));
        }

        
        int maxBandIndex = 0;
        float maxBandValue = 0;
        for (int i = 0; i < len; i++) {
            if (bands[i] > maxBandValue) {
                maxBandValue = bands[i];
                maxBandIndex = i;
            }
        }
        

        // Draw only the lines of the band with the highest current value
        for (int i = 0; i < len; i++) {
            for (Lines line : lineSets.get(i)) {
                if (i == maxBandIndex) {
                    line.display();
                }
            }
        }
        
        
    }
    
}
