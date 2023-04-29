package C21468162.drawObjects;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;

import processing.core.PApplet;

public class WarpedSpace extends Visual {
  float x, y, z, pz;
  float speed;
  PApplet parent;

  public WarpedSpace(PApplet parent, float speed) {
    this.parent = parent;
    this.speed = speed;
    x = parent.random(-parent.width, parent.width);
    y = parent.random(-parent.height, parent.height);
    z = parent.random(parent.width);
    pz = z;
  }

  public void update() {
    z = z - speed;
    if (z < 1) {
      z = parent.width;
      x = parent.random(-parent.width, parent.width);
      y = parent.random(-parent.height, parent.height);
      pz = z;
    }
  }

  public void show() {
    parent.fill(255);
    parent.noStroke();

    float sx = PApplet.map(x / z, 0, 1, 0, parent.width);
    float sy = PApplet.map(y / z, 0, 1, 0, parent.height);

    float r = PApplet.map(z, 0, parent.width, 16, 0);
    parent.ellipse(sx, sy, r, r);

    float px = PApplet.map(x / pz, 0, 1, 0, parent.width);
    float py = PApplet.map(y / pz, 0, 1, 0, parent.height);

    pz = z;

    parent.stroke(255);
    parent.line(px, py, sx, sy);
  }
}