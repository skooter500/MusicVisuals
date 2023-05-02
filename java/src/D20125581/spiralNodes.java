package D20125581;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PVector;
import ddf.minim.analysis.*;

public class spiralNodes extends PApplet {
  Minim m;
  AudioPlayer player;
  FFT fft;

  Node[] nodes = new Node[1200];

  class Node {
    PVector loc;
    PVector velocity = new PVector(random(-2, 2), random(-2, 2));
    float size = 10;
    float angle = random(0, TWO_PI);

    Node(float x, float y) {
      this.loc = new PVector(x, y);
    }

    void run(float freq) {
      this.display();
      this.move(freq);
      this.bounce();
    }

    void display() {
      point(loc.x, loc.y);
    }

    void move(float freq) {
      float spiralSpeed = freq * 0.1f;
      angle += spiralSpeed;
      float radius = freq * 0.5f;
      loc.x += cos(angle) * radius;
      loc.y += sin(angle) * radius;
    }

    void bounce() {
      if ((this.loc.x > width) || (this.loc.x < 0)) {
        velocity.x = velocity.x * -1;
      }
      if ((this.loc.y > height) || (this.loc.y < 0)) {
        velocity.y = velocity.y * -1;
      }
    }
  }

  public void settings() {
    size(1024, 1000);
  }

  public void setup() {
    smooth();

    for (int i = 0; i < nodes.length; i++) {
      nodes[i] = new Node(random(width), random(height));
    }

    m = new Minim(this);
    player = m.loadFile("java/data/horizon.mp3", 1024);
    fft = new FFT(player.bufferSize(), player.sampleRate());
    player.play();

  }

  public void draw() {
    noStroke();
    noCursor();
    background(40, 40, 80);
    fft.forward(player.mix);

    for (int i = 0; i < nodes.length; i++) {
      float freq = fft.getFreq((float) (dist(nodes[i].loc.x, nodes[i].loc.y, width / 2, height / 2) * 2.2));

      strokeWeight(freq / 10);
      stroke((1 - nodes[i].loc.y / 800) * 255, (nodes[i].loc.x / 800) * 255, (nodes[i].loc.y / 800) * 255);
      for (int j = i + 1; j < nodes.length; j++) {
        Node other = nodes[j];
        float dist = nodes[i].loc.dist(other.loc);
        if (dist > 0 && dist < 60) {
          line(nodes[i].loc.x, nodes[i].loc.y, other.loc.x, other.loc.y);
        }
      }
      stroke(255);
      nodes[i].run(freq);
    }
  }
}


