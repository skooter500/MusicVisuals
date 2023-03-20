package ie.tudublin;
import processing.core.PApplet;
import ddf.minim.*;
import ddf.minim.analysis.*;

public class ParasiteEveFlowerVisualizer extends PApplet {

  private AudioPlayer player;
  private FFT fft;

  private int numPetals = 12;
  private float petalWidth = 10;
  private float petalHeight = 100;

  private float[] spectrum;

  private float radius = 200;
  private float angle = 0;
  private float angleInc = 0.05f;

  public void settings() {
    size(800, 600);
  }

  public void setup() {
    Minim minim = new Minim(this);
    player = minim.loadFile("Parasite.mp3", 1024);
    player.loop();

    fft = new FFT(player.bufferSize(), player.sampleRate());
    fft.logAverages(22, numPetals);

    spectrum = new float[numPetals];
  }

  public void draw() {
    background(0);

    fft.forward(player.mix);

    // Compute the average spectrum manually
    float nyquist = fft.specSize() / 2;
    float binFreq = player.sampleRate() / player.bufferSize();
    for (int i = 0; i < numPetals; i++) {
      int startBin = (int) (fft.freqToIndex(binFreq * pow(2, i * 10f / (numPetals - 1))) + 0.5f);
      int endBin = (int) (fft.freqToIndex(binFreq * pow(2, (i + 1) * 10f / (numPetals - 1)) - 1) + 0.5f);
      float sum = 0;
      for (int j = startBin; j <= endBin; j++) {
        sum += fft.getBand(j);
      }
      spectrum[i] = sum / (endBin - startBin + 1);
    }

    strokeWeight(petalWidth);
    stroke(255);

    pushMatrix();
    translate(width/2, height/2);

    for (int i = 0; i < numPetals; i++) {
      float x1 = radius * cos(angle + TWO_PI * i / numPetals);
      float y1 = radius * sin(angle + TWO_PI * i / numPetals);
      float x2 = (radius + petalHeight * spectrum[i] / 255) * cos(angle + TWO_PI * i / numPetals);
      float y2 = (radius + petalHeight * spectrum[i] / 255) * sin(angle + TWO_PI * i / numPetals);
      line(x1, y1, x2, y2);
    }

    popMatrix();

    angle += angleInc;
  }

  public void stop() {
    player.close();
    super.stop();
  }

  public static void main(String[] args) {
    PApplet.main("ParasiteEveFlowerVisualizer");
  }
}
