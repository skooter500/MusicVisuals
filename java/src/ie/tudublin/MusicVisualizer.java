package ie.tudublin;

import ddf.minim.*;
import ddf.minim.analysis.*;
import processing.core.PApplet;
import java.util.ArrayList;
import processing.core.PVector;

public class MusicVisualizer extends PApplet {
  Minim minim;
  AudioPlayer player;
  BeatDetect beat;
  FFT fft;

  float angle = 0;
  float size = 50;
  float posX, posY;
  float speedX, speedY;

  ArrayList<Particle> particles;

  public void settings() {
    size(800, 600);
  }

  public void setup() {
    minim = new Minim(this);

    posX = width / 2;
    posY = height / 2;
    speedX = random(-2, 2);
    speedY = random(-2, 2);

    player = minim.loadFile("java/data/Parasite.mp3");
    player.play();

    beat = new BeatDetect();
    fft = new FFT(player.bufferSize(), player.sampleRate());

    particles = new ArrayList<Particle>();
  }

  public void draw() {
    background(0);

    // Analyze the audio for beats
    beat.detect(player.mix);

    // If a beat is detected, change the visual effect
    if (beat.isOnset()) {
      changeVisualEffect();
    }

    // Update the position of the design element
    posX += speedX;
    posY += speedY;

    // Check boundaries and change direction if necessary
    if (posX < 0 || posX > width) {
      speedX *= -1;
    }
    if (posY < 0 || posY > height) {
      speedY *= -1;
    }

    // Create particles based on the beat
    if (beat.isOnset()) {
      for (int i = 0; i < 10; i++) {
        Particle p = new Particle(posX, posY, speedX, speedY);
        particles.add(p);
      }
    }

    // Update and draw particles
    for (int i = particles.size() - 1; i >= 0; i--) {
      Particle p = particles.get(i);
      p.update();
      p.display();
      if (p.isDead()) {
        particles.remove(i);
      }
    }

    // Draw the current visual effect
    drawVisualEffect();

    // Calculate the FFT to get frequency data
    fft.forward(player.mix);

    // Draw the spectrum visualization

  }

  void changeVisualEffect() {
    // Modify the design element based on the beat
    size = random(10, 100);
    speedX = random(-5, 5);
    speedY = random(-5, 5);
    background(random(255), random(255), random(255));
  }

  void drawVisualEffect() {
    // Draw the design element in all four corners
    for (int i = 0; i < 4; i++) {
      float cornerX = i % 2 == 0 ? posX : width - posX;
      float cornerY = i < 2 ? posY : height - posY;

      pushMatrix();
      translate(cornerX, cornerY);
      rotate(angle);
      rectMode(CENTER);
      fill(random(255), random(255), random(255));
      rect(0, 0, size, size);
      popMatrix();

      // Create particles based on the beat
      if (beat.isOnset()) {
        for (int j = 0; j < 10; j++) {
          Particle p = new Particle(cornerX, cornerY, speedX, speedY);
          particles.add(p);
        }
      }
    }

    // Rotate the design element gradually
    angle += 0.05;
  }

  public static void main(String[] args) {
    PApplet.main("MusicVisualizer");
  }

  class Particle {
    PVector position;
    PVector velocity;
    PVector acceleration;
    float lifespan;
    float size;
    float hue;

    Particle(float x, float y, float speedX, float speedY) {
      position = new PVector(x, y);
      velocity = new PVector(speedX, speedY).add(PVector.random2D().mult(random(1, 5)));
      acceleration = new PVector(0, 0.05f);
      lifespan = 255;
      size = random(4, 16);
      hue = random(360);
    }

    void update() {
      velocity.add(acceleration);
      position.add(velocity);
      lifespan -= 2;
    }

    void display() {
      colorMode(HSB);
      noStroke();
      fill(hue, 255, 255, lifespan);
      ellipse(position.x, position.y, size, size);
    }

    boolean isDead() {
      return lifespan <= 0;
    }
  }

  public void stop() {
    player.close();
    minim.stop();
    super.stop();
  }
}
