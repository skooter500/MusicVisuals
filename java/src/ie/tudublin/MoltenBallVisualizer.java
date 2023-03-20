package ie.tudublin;
import ddf.minim.*;
import ddf.minim.analysis.*;
import processing.core.*;

public class MoltenBallVisualizer extends PApplet {

  private AudioPlayer player;
  private FFT fft;
  private float[] spectrum;
  private float[] spectrumHistory;
  private int spectrumHistorySize = 50;

  private int numRays = 200;
  private float minRadius = 100;
  private float maxRadius = 250;
  private float rayWidth = 5;
  private float angleInc = TWO_PI / numRays;
  private float noiseScale = 0.01f;
  private float noiseOffset = 0;

  public void settings() {
    size(800, 800);
  }

  public void setup() {
    colorMode(HSB, 255);
    ellipseMode(RADIUS);

    Minim minim = new Minim(this);
    player = minim.loadFile("Parasite.mp3", 1024);
    player.loop();

    fft = new FFT(player.bufferSize(), player.sampleRate());
    fft.logAverages(22, numRays);

    spectrum = new float[numRays];
    spectrumHistory = new float[numRays * spectrumHistorySize];
  }

  public void draw() {
    background(0);

    fft.forward(player.mix);

    // Compute the average spectrum manually
    float nyquist = fft.specSize() / 2;
    float binFreq = player.sampleRate() / player.bufferSize();
    for (int i = 0; i < numRays; i++) {
      int startBin = (int) (fft.freqToIndex(binFreq * pow(2, i * 10f / (numRays - 1))) + 0.5f);
      int endBin = (int) (fft.freqToIndex(binFreq * pow(2, (i + 1) * 10f / (numRays - 1)) - 1) + 0.5f);
      float sum = 0;
      for (int j = startBin; j <= endBin; j++) {
        sum += fft.getBand(j);
      }
      spectrum[i] = sum / (endBin - startBin + 1);
    }

    // Add the new spectrum to the spectrum history
    for (int i = 0; i < numRays; i++) {
      int index = i * spectrumHistorySize + frameCount % spectrumHistorySize;
      spectrumHistory[index] = spectrum[i];
    }

    // Draw the rays
    float radius = maxRadius;
    float angle = 0;
    float hue = map(player.position(), 0, player.length(), 0, 255);
    float saturation = 255;
    float brightness = 255;
    strokeWeight(rayWidth);
    for (int i = 0; i < numRays; i++) {
      float x1 = radius * cos(angle);
      float y1 = radius * sin(angle);
      float x2 = (radius + map(getAverageSpectrum(i), 0, 255, -100, 100)) * cos(angle);
      float y2 = (radius + map(getAverageSpectrum(i), 0, 255, -100, 100)) * sin(angle);
      stroke(hue, saturation, brightness);
      line(width/2 + x1, height/2 + y1, width/2 + x2, height/2 + y2);
      angle += angleInc;
    }
  }
}
    //
