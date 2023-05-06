package C21468162;

import processing.core.PApplet;
import processing.core.PVector;

public class Particle {

    PApplet sketch;
    PVector pos;
    PVector vel;
    PVector acc;
    float lifespan;
    float size;

    public Particle(PApplet sketch, PVector pos, float size) {
        this.sketch = sketch;
        this.pos = pos.copy();
        vel = new PVector(sketch.random(-1, 1), sketch.random(-2, 0), sketch.random(-1, 1));
        acc = new PVector(0, 0, 0);
        lifespan = 750;
        this.size = size;
    }

    public void applyForce(PVector force) {
        acc.add(force);
    }

    public void update() {
        vel.add(acc);
        pos.add(vel);
        acc.mult(0);
        lifespan -= 2;
    }

    public void show() {
        sketch.pushMatrix();
        sketch.translate(pos.x, pos.y, pos.z);
        sketch.colorMode(PApplet.RGB);
        sketch.strokeWeight(2);
        sketch.stroke(sketch.random(255), sketch.random(255), sketch.random(255), lifespan);
        sketch.ellipse(0, 0, size, size);
        sketch.popMatrix();
    }

    public boolean isDone() {
        float boxSize = sketch.width;
        return pos.x < -boxSize/2 || pos.x > boxSize/2 || 
               pos.y < -boxSize/2 || pos.y > boxSize/2 || 
               pos.z < -boxSize/2 || pos.z > boxSize/2;
    }
}