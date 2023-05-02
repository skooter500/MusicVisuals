package D20125581;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class sphere extends PApplet {
  Minim minim;
  AudioPlayer ap;
  AudioInput ai;
  AudioBuffer ab;

  int mode = 0;
  float y = 0;
  float smoothedY = 0;
  float smoothedAmplitude = 0;

  public void keyPressed() {
    if (key >= '0' && key <= '9') {
      mode = key - '0';
    }
    if (keyCode == ' ') {
      if (ap.isPlaying()) {
        ap.pause();
      } else {
        ap.rewind();
        ap.play();
      }
    }
  }

  public void settings() {
    size(1024, 1000);
    // fullScreen(P3D, SPAN);
  }

  public void setup() {

    minim = new Minim(this);
    ap = minim.loadFile("java/data/horizon.mp3", 1024);
    ap.play();
    ab = ap.mix;
    colorMode(HSB);
    y = height / 2;
    smoothedY = y;

  }

  float off = 0;
  float lerpedBuffer[] = new float[1024];

  public void draw() {
    float average = 0;
    float sum = 0;
    off += 1;

    // Calculate sum and average of the samples
    // Also lerp each element of buffer;
    for (int i = 0; i < ab.size(); i++) {
      sum += abs(ab.get(i));
      lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
    }
    average = sum / (float) ab.size();

    smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

    // Calculate sum and average of the samples
    // Also lerp each element of buffer;
    for (int i = 0; i < ab.size(); i++) {
      sum += abs(ab.get(i));
      lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
    }
    average = sum / (float) ab.size();

    smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

    background(0);
    noFill();
    translate(width / 2, height / 2);

    // Draw the sphere
    float sphereRadius = 300;
    int sphereDetail = 30;
    for (float i = 0; i < PI; i += PI / sphereDetail) {
      float r1 = sin(i) * sphereRadius;
      float r2 = sin(i + PI / sphereDetail) * sphereRadius;
      for (float j = 0; j < TWO_PI; j += TWO_PI / sphereDetail) {
        float theta = random(0, PI);
        float x1 = cos(j) * r1;
        float y1 = sin(j) * r1;
        float x2 = cos(j) * r2;
        float y2 = sin(j) * r2;
        float c = (int) ((theta / PI) * 255);
        stroke(c, 255, 255);
        line(x1, y1, -cos(i) * sphereRadius, x2, y2, -cos(i + PI / sphereDetail) * sphereRadius);
      }
    }

    // Draw the dots
    int numDots = 700;
    for (int i = 0; i < numDots; i++) {
      float theta1 = random(0, PI);
      float theta2 = random(0, TWO_PI);
      float x = sin(theta1) * cos(theta2) * sphereRadius;
      float y = sin(theta1) * sin(theta2) * sphereRadius;
      float z = -cos(theta1) * sphereRadius;
      float movement = map(smoothedAmplitude, 0, 1, 0, 100); // map amplitude to movement range
      x += random(-movement, movement);
      y += random(-movement, movement);
      z += random(-movement, movement);
      float dotSize = map(smoothedAmplitude, 0, 1, 1, 10); // map amplitude to dot size range
      ellipse(x, y, dotSize, dotSize);
      int hue = (int) ((theta1 / PI) * 255);
      fill(hue, 255, 255);
    }

  }
}


