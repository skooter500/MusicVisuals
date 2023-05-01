package C21725659;

import processing.core.PApplet;
import processing.core.PVector;

class Particle {
  PApplet p;
  PVector position;
  PVector velocity;
  float size;
  int color;
  float lifespan;

  Particle( PApplet p,PVector position, PVector velocity, float size, int color, float lifespan) {
      this.p = p;
      this.position = position;
      this.velocity = velocity;
      this.size = size;
      this.color = color;
      this.lifespan = lifespan;
  }

  void update() {
      position.add(velocity);
      lifespan--;
  }

  void display() {
      p.pushMatrix();
      p.translate(position.x, position.y, position.z);
      p.fill(color, lifespan);
      p.sphere(size);
      p.popMatrix();
  }

  boolean isDead() {
      return lifespan <= 0;
  }
}