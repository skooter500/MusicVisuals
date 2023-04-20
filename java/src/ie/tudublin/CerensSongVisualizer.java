package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class CerensSongVisualizer extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;


  float xoff = 0;
  float noiseScale = 0.1f;

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
        //fullScreen(P3D, SPAN);
    }

    public void setup() {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix;

        // And comment the next two lines out
        ap = minim.loadFile("Parasite.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;
    }

    float off = 0;

    float lerpedBuffer[] = new float[1024];

    public void draw() {
        // Graph 1: Sine Wave
        stroke(255);
        for (int x = 0; x < width; x++) {
          float y = sin(x * 0.01f + millis() * 0.001f) * 100 + height / 2;
          point(x, y);
        }
    
        // Graph 2: Noise
        stroke(255, 0, 0);
        noFill();
        beginShape();
        for (float x = 0; x < width; x += 2) {
          float y = noise(xoff) * height;
          vertex(x, y);
          xoff += noiseScale;
        }
        endShape();
    
        // Graph 3: Perlin Noise
        stroke(0, 255, 0);
        noFill();
        beginShape();
        float yoff = 0;
        for (float x = 0; x < width; x += 2) {
          float y = map(noise(xoff, yoff), 0, 1, 0, height);
          vertex(x, y);
          xoff += noiseScale;
          yoff += noiseScale;
        }
        endShape();
    

        
          // Calculate the lerped amplitude
          float sum = 0;
          int numSamples = 0;
          for (int i = 0; i < ab.size(); i++) {
            float sample = ab.get(i);
            sum += sample * sample;
            numSamples++;
          }
          float rms = sqrt(sum / numSamples);
          float lerpedAmplitude = lerp(smoothedAmplitude, rms, 0.1f);
          smoothedAmplitude = lerpedAmplitude;
        
          // Draw the waveform
          stroke(255);
          noFill();
          beginShape();
          for (int i = 0; i < ab.size(); i++) {
            float x = map(i, 0, ab.size(), 0, width);
            float y = map(ab.get(i), -1, 1, height, 0);
            vertex(x, y);
          }
          endShape();
        
          // Draw a rectangle that reacts to the amplitude
          float rectHeight = lerpedAmplitude * height * 5;
          float rectY = height - rectHeight;
          float rectColor = map(lerpedAmplitude, 0, 1, 0, 255);
          fill(rectColor, 255, 255);
          noStroke();
          rect(0, rectY, width, rectHeight);
        }
        
        
      }
    