package ie.tudublin;

import ddf.minim.*; // Import Minim library for audio processing
import ddf.minim.analysis.*;
import processing.core.PApplet;
import java.util.ArrayList;
import processing.core.PVector;

public class MusicVisualizer extends PApplet {
  Minim minim; // Minim object for audio processing
  AudioPlayer player; // AudioPlayer object for playing audio
  BeatDetect beat; // BeatDetect object for beat detection
  FFT fft; // FFT object for frequency analysis

  float angle = 0; // Angle for rotation
  float size = 50; // Size of design element
  float posX, posY; // Position of design element
  float speedX, speedY; // Speed of design element

  ArrayList<Particle> particles; // List to store particles

  public void settings() {
    size(800, 600); // Set the size of the window
  }

  public void setup() {
    minim = new Minim(this); // Initialize the Minim object

    posX = width / 2; // Set initial X position of design element
    posY = height / 2; // Set initial Y position of design element
    speedX = random(-2, 2); // Set initial X speed of design element
    speedY = random(-2, 2); // Set initial Y speed of design element

    player = minim.loadFile("java/data/Parasite.mp3"); // Load the audio file
    player.play(); // Start playing the audio

    beat = new BeatDetect(); // Initialize the BeatDetect object
    fft = new FFT(player.bufferSize(), player.sampleRate()); // Initialize the FFT object

    particles = new ArrayList<Particle>(); // Initialize the list for particles
  }

  public void draw() {
    background(0); // Set the background color

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
    size = random(10, 100); // Randomize the size of the design element
    speedX = random(-5, 5); // Randomize the horizontal speed of the design element
    speedY = random(-5, 5); // Randomize the vertical speed of the design element
    background(random(255), random(255), random(255)); // Randomize the background color
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
      fill(random(255), random(255), random(255)); // Randomize the fill color of the design element
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
  
  
  class Particle {
    PVector position;
    PVector velocity;
    PVector acceleration;
    float lifespan;
    float size;
    float hue;
  
    Particle(float x, float y, float speedX, float speedY) {
      position = new PVector(x, y);
      velocity = new PVector(speedX, speedY).add(PVector.random2D().mult(random(1, 5))); // Randomize the initial velocity of particles
      acceleration = new PVector(0, 0.05f); // Set the constant acceleration for particles
      lifespan = 255;
      size = random(4, 16); // Randomize the size of particles
      hue = random(360); // Randomize the hue value of particles
    }
  
    void update() {
      velocity.add(acceleration); // Update the velocity of particles
      position.add(velocity); // Update the position of particles based on velocity
      lifespan -= 2; // Decrease the lifespan of particles
    }
  
    void display() {
      colorMode(HSB);
      noStroke();
      fill(hue, 255, 255, lifespan); // Set the fill color of particles with transparency based on lifespan
      ellipse(position.x, position.y, size, size); // Draw particles as ellipses
    }
  
    boolean isDead() {
      return lifespan <= 0; // Check if the lifespan of particles has reached zero
    }
  }
  
  public void stop() {
    player.close();
    minim.stop();
    super.stop();
  }
  public static void main(String[] args) {
    PApplet.main("ie.tudublin.MusicVisualizer");
}
}

