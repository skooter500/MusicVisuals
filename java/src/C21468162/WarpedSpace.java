package C21468162;

import processing.core.PApplet;

public class WarpedSpace extends PApplet {

  int numParticles = 100; // Number of particles
  float maxSpeed = 2.5f; // Maximum speed of particles
  float maxRadius = 10; // Maximum radius of particles
  Particle[] particles; // Array to store particles

  public void settings() {
      size(800, 600); // Set the size of the canvas
  }

  public void setup() {
      particles = new Particle[numParticles]; // Initialize particle array

      // Create particles with random positions, velocities, and radius
      for (int i = 0; i < numParticles; i++) {
          particles[i] = new Particle(random(width), random(height), 
                                       random(-maxSpeed, maxSpeed), 
                                       random(-maxSpeed, maxSpeed), 
                                       random(1, maxRadius));
      }

      background(0); // Set the background color to black
      smooth(); // Enable anti-aliasing for smoother rendering
  }

  public void draw() {
      // Draw a transparent black rectangle to create a fading trail effect
      fill(0, 15);
      rect(0, 0, width, height);

      // Update and draw particles
      for (int i = 0; i < numParticles; i++) {
          particles[i].update();
          particles[i].draw();
      }
  }

  // Particle class
  class Particle {
      float x, y, vx, vy, r;

      Particle(float x, float y, float vx, float vy, float r) {
          this.x = x;
          this.y = y;
          this.vx = vx;
          this.vy = vy;
          this.r = r;
      }

      void update() {
          // Update particle position
          x += vx;
          y += vy;

          // Wrap particle around the edges of the canvas
          if (x < -r) {
              x = width + r;
          } else if (x > width + r) {
              x = -r;
          }
          if (y < -r) {
              y = height + r;
          } else if (y > height + r) {
              y = -r;
          }
      }

      void draw() {
          // Draw particle as a circle with random color and transparency
          noStroke();
          fill(random(255), random(255), random(255), random(255));
          ellipse(x, y, r, r);
      }
  }
}