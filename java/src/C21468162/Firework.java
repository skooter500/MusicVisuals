package C21468162;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Firework {

    PApplet sketch;
    ArrayList<Particle> particles;
    PVector pos;

    public Firework(PApplet sketch, PVector pos) {
        this.sketch = sketch;
        this.pos = pos.copy();
        particles = new ArrayList<>();
        int numParticles = PApplet.floor(sketch.random(80, 120));
        for (int i = 0; i < numParticles; i++) {
            particles.add(new Particle(sketch, pos));
        }
    }

    public void applyForce(PVector force) {
        for (Particle p : particles) {
            p.applyForce(force);
        }
    }

    public void update() {
        for (int i = particles.size() - 1; i >= 0; i--) {
            Particle p = particles.get(i);
            p.update();
            if (p.isDone()) {
                particles.remove(i);
            }
        }
    }

    public void show() {
        for (Particle p : particles) {
            p.show();
        }
    }

    public boolean isDone() {
        return particles.isEmpty();
    }
}