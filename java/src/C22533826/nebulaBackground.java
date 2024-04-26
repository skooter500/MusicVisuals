package C22533826;

import ie.tudublin.Heartbeat;
import ie.tudublin.VisualException;
import processing.core.PVector;
import java.util.ArrayList;

public class nebulaBackground {

    Heartbeat HB;
    starField sf;
    // Create an arraylist of nebula.
    ArrayList<Nebula> nebulas;

    public nebulaBackground(Heartbeat HB, starField sf) {
        // Initialize variables.
        this.HB = HB;
        this.sf = sf;

        // Initialize arraylist of nebula.
        nebulas = new ArrayList<Nebula>();
        init_nebulas();
    }

    public void render() {
        HB.background(0);

        // Iterate through all pixels and then calculate the sum of the distance between
        // each nebula to give the 'merging effect'.
        HB.loadPixels();
        for (int x = 0; x < HB.width; x++) {
            for (int y = 0; y < HB.height / 2f; y++) {
                float sum = 0;
                for (Nebula n : nebulas) {
                    float d = HB.dist(x, y, n.x, n.y);
                    sum += 10 * n.r / d;
                }
                HB.pixels[x + y * HB.width] = HB.color(sum);
            }
        }
        HB.updatePixels();

        // Update each nebula.
        for (Nebula n : nebulas) {
            n.update();
        }

        // Allows interaction between nebulas and particles in starField.
        nebulaStarInteraction();
    }

    // Interaction between nebulas and particles in starField.
    // Uses nebula distance instead of mouse distance like in starField.java.
    public void nebulaStarInteraction() {
        for (Nebula n : nebulas) {
            for (PVector p : sf.particles) {
                float d = HB.dist(p.x, p.y, n.x, n.y);
                if (d < 75) {
                    float pushStrength = HB.map(d, 0, 100, 10, 0); // Adjust the strength of interaction
                    PVector pushDirection = new PVector(p.x - n.x, p.y - n.y);
                    pushDirection.normalize();
                    pushDirection.mult(pushStrength);
                    p.add(pushDirection);
                }
            }
        }
    }

    // Initialize nebulas at random positions, ensures varied sizes.
    public void init_nebulas() {
        for (int i = 0; i < 7; i++) {
            float x = HB.random(0, HB.width);
            float y = HB.random(0, HB.height / 2.9f); // Draw only on top half of the screen.
            float r = 150 + i * HB.random(50, 200);
            nebulas.add(new Nebula(x, y, r));
        }

    }

    // Class to represent a nebula.
    class Nebula {
        // initialize variables
        float x, y, xspeed, yspeed, r;

        public Nebula(float x, float y, float r) {
            this.x = x;
            this.y = y;
            // Randomize speed and direction of nebula.
            float angle = HB.random(HB.TWO_PI);
            this.xspeed = HB.random(5, 10) * HB.cos(angle);
            this.yspeed = HB.random(5, 10) * HB.sin(angle);
            this.r = r;
        }

        public void update() {
            // updates position of a nebula based on amplitude.
            HB.calculateAverageAmplitude();
            // Adjust speed based on amplitude. Speed multiplier ensures that nebulas move
            // similar to starField.java
            float speedMultiplier = HB.map(HB.getSmoothedAmplitude(), 0, 1, 0, 5);
            x += xspeed * speedMultiplier;
            y += yspeed * speedMultiplier;
            if (this.x > HB.width || this.x < 0)
                this.xspeed *= -1;
            if (this.y > HB.height / 2.6f || this.y < 0)
                this.yspeed *= -1;
        }

        public void show() {
            // Draws ellipse at the given position.
            HB.noFill();
            HB.ellipse(this.x, this.y, this.r * 2, this.r * 2);
        }
    }
}
