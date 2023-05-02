package ie.tudublin;
import ddf.minim.*;
import ddf.minim.analysis.*;
import processing.core.PApplet;

public class AudioVisual extends PApplet {
  Minim minim;
  AudioPlayer player;
  AudioMetaData metadata;
  FFT fft;

  int numRings = 50;
  float[] ringRadius;
  float[] ringThickness;
  int[] ringColor;

  public void settings() {
    fullScreen(P3D);
  }

  public void setup() {
    minim = new Minim(this);
    player = minim.loadFile("Parasite.mp3", 1024);
    metadata = player.getMetaData();
    fft = new FFT(player.bufferSize(), player.sampleRate());

    ringRadius = new float[numRings];
    ringThickness = new float[numRings];
    ringColor = new int[numRings];

    for (int i = 0; i < numRings; i++) {
      ringRadius[i] = 50 + i * (height/numRings);
      ringThickness[i] = (float)i/numRings * 30;
      ringColor[i] = color(random(255), random(255), random(255));
    }

    player.play();
  }

  public void draw() {
    background(0);
    fft.forward(player.mix);

    pushMatrix();
    translate(width/2, height/2);

    for (int i = 0; i < numRings; i++) {
      float angle = (float) (2 * PI / 40);
      float rotAngle = (float) (frameCount * PI/256.0 + 2*PI * i/numRings);

      float r = ringRadius[i];
      float t = ringThickness[i];
      int c = ringColor[i];

      beginShape(TRIANGLE_STRIP);
      for (int j = 0; j < 41; j++) {
        float x = r * cos(j * angle + rotAngle);
        float y = r * sin(j * angle + rotAngle);
        float z = map(fft.getBand(j), 0, 200, t, t*2);

        stroke(c);
        strokeWeight(z);
        vertex(x, y, -r);

        strokeWeight(z*2);
        vertex(x, y, -r-100);
      }
      endShape();

      ringRadius[i] += map(fft.getBand(i), 0, 200, 1, 5);
      ringColor[i] = color(
        map(fft.getBand(i), 0, 200, 0, 255),
        map(fft.getBand(i), 0, 200, 255, 0),
        map(fft.getBand(i), 0, 200, 100, 200)
      );

      if (ringRadius[i] > height/2) {
        ringRadius[i] = 50;
      }
    }

    popMatrix();
  }

}
