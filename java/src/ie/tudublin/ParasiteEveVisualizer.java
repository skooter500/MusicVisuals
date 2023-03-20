package ie.tudublin;
import processing.core.PApplet;
import ddf.minim.*;
import ddf.minim.analysis.*;

public class ParasiteEveVisualizer extends PApplet {
  
  private Minim minim;
  private AudioPlayer player;
  private FFT fft;

  private float[] spectrum;

  private int numRects = 80;
  private float rectWidth;
  private float rectHeight = 5;
  private float rectSpacing = 2;

  public void settings() {
    size(800, 600);
  }

  public void setup() {
    minim = new Minim(this);
    player = minim.loadFile("Parasite.mp3");
    player.play();
    fft = new FFT(player.bufferSize(), player.sampleRate());
    fft.logAverages(22, numRects);

    rectWidth = (width - (numRects - 1) * rectSpacing) / numRects;

    spectrum = new float[numRects];
  }

  public void draw() {
    background(0);

    fft.forward(player.mix);

    for (int i = 0; i < numRects; i++) {
      int startBin = (int) fft.getFreq(i * 10);
      int endBin = (int) fft.getFreq((i + 1) * 10);

      float sum = 0;
      for (int j = startBin; j < endBin; j++) {
        sum += fft.getBand(j);
      }
      float average = sum / (endBin - startBin);
      spectrum[i] = average;
    }

    float yPos = height - rectHeight;

    for (int i = 0; i < numRects; i++) {
      float rectHeightScaled = map(spectrum[i], 0, 255, 0, height - 50);
      float xPos = i * (rectWidth + rectSpacing);
      float rectY = yPos - rectHeightScaled;

      float r = map(spectrum[i], 0, 255, 0, 255);
      float g = map(spectrum[i], 0, 255, 255, 0);
      float b = map(spectrum[i], 0, 255, 255, 0);

      fill(r, g, b);
      rect(xPos, rectY, rectWidth, rectHeightScaled);
    }
  }

  public void stop() {
    player.close();
    minim.stop();
    super.stop();
  }

  public static void main(String[] args) {
    PApplet.main("ParasiteEveVisualizer");
  }
}
