package C22410766;

import processing.core.PApplet;
import ie.tudublin.Visual;


public class Particle {
    float x, y, vx, vy, life; // Properties for particle position, velocity, and life
    Visual parent;            // Reference to the Visual class which should be a subclass of PApplet

    // Constructor with correctly named parameters
    public Particle(Visual parent, float centerX, float centerY, float radius, float angle, float velocityFactor) {
        this.parent = parent;
        
        // Calculate initial position on the circle edge
        this.x = centerX + radius * PApplet.cos(angle);
        this.y = centerY + radius * PApplet.sin(angle);
        
        // Initial velocities, possibly away from the center or tangential
        this.vx = PApplet.cos(angle) * velocityFactor;  // This could be adjusted
        this.vy = PApplet.sin(angle) * velocityFactor;  // This could be adjusted
        this.life = 255;  // Starting life value
    }

    public void run() {
        update();
        display();
    }

    private void update() {
        x += vx;
        y += vy;
        life -= 2;  // Decrease life
    }

    private void display() {
        parent.stroke(255, life);
        parent.strokeWeight(2);
        parent.point(x, y);
    }

    public boolean isDead() {
        return life <= 0;
    }
}
