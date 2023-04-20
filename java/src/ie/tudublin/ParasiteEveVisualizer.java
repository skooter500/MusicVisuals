package ie.tudublin;

import processing.core.PApplet;
import ddf.minim.*;
import ddf.minim.analysis.*;

public class ParasiteEveVisualizer extends PApplet {

  private Minim minim;
  private AudioPlayer player;
  private FFT fft;

  private float[] spectrum;

  private int numPoints = 80;
  private float pointSpacing;
  private float terrainHeight = 200;

  private float[] terrain;

  public void settings() {
    size(800, 600, P3D);
  }

  public void setup() {
    minim = new Minim(this);
    player = minim.loadFile("java/data/Parasite.mp3", 1024);
    player.play();
    fft = new FFT(player.bufferSize(), player.sampleRate());
    fft.logAverages(22, numPoints);

    pointSpacing = width / (numPoints - 1);

    spectrum = new float[numPoints];
    terrain = new float[numPoints];
  }

  public void draw() {
    background(0);

    fft.forward(player.mix);

    stroke(255, 255, 255);
    noFill();

    float yOffset = height / 2;
    float xOffset = width / 2;
    float terrainScale = 0.1f;

    float spectrumTotal = 0;
    for (int i = 0; i < numPoints; i++) {
      int startBin = (int) fft.getFreq(i * 10);
      int endBin = (int) fft.getFreq((i + 1) * 10);

      float sum = 0;
      for (int j = startBin; j < endBin; j++) {
        sum += fft.getBand(j);
      }
      float average = sum / (endBin - startBin);
      spectrum[i] = average;
      spectrumTotal += average;
    }

    float spectrumAvg = spectrumTotal / numPoints;
    terrainHeight = map(spectrumAvg, 0, 255, 100, 400);

    pushMatrix();
    translate(xOffset, yOffset + terrainHeight);
    rotateX(PI / 3);
    rotateZ(frameCount * 0.01f);

    float terrainX = 0;
    float terrainY = 0;
    float terrainZ = 0;
    float noiseScale = 0.1f;

    for (int y = 0; y < height; y += 20) {
      beginShape(QUAD_STRIP);
      for (int x = 0; x < width; x += 20) {
        terrainX = x * terrainScale;
        terrainY = y * terrainScale;

        terrainZ = (noise(terrainX * noiseScale, terrainY * noiseScale) - 0.5f) * terrainHeight * 0.25f;

        vertex(x, y, terrainZ);
        vertex(x, y + 20, terrainZ);
      }
      endShape();
    }

    popMatrix();
  }

  public void stop() {
    player.close();
    minim.stop();
    super.stop();
  }

  public static void main(String[] args) {
    PApplet.main("ie.tudublin.ParasiteEveVisualizer");
  }
}
