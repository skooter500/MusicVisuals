package C21725659;

import processing.core.PApplet;
import processing.core.PVector;

class Particle {
  PVector position;
  PVector velocity;
  float size;
  int particleColor;
  float lifespan;
  PApplet parent;

  Particle(PApplet parent, PVector position, PVector velocity, float size, int particleColor, float lifespan) {
    this.parent = parent;
    this.position = position;
    this.velocity = velocity;
    this.size = size;
    this.particleColor = particleColor;
    this.lifespan = lifespan;
  }

  void update() {
    position.add(velocity);
    lifespan -= 1;
  }

  void display() {
    parent.fill(particleColor, lifespan);
    parent.noStroke();
    parent.ellipse(position.x, position.y, size, size);
  }

  boolean isDead() {
    return lifespan <= 0;
  }
}

