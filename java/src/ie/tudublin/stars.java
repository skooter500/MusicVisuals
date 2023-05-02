package ie.tudublin;

import java.util.ArrayList;
import ddf.minim.*;
import ddf.minim.analysis.*;
import processing.core.PApplet;

public class stars extends PApplet {

    Minim minim;
    AudioPlayer player;
    FFT fft;

    AudioInput ai;
    AudioBuffer ab;

    Heart leftHeart;
    Heart rightHeart;

    int mode = 0;

    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    ArrayList<Particle> particles = new ArrayList<Particle>();

    public void settings() {
        size(1024, 1000, P3D);
        // fullScreen(P3D, SPAN);
    }

    public void setup() {
        start();

        minim = new Minim(this);
        player = minim.loadFile("MusicVisuals/java/data/Victoria_Mon_t_ft_Khalid_-_Experience.mp3", 512);
        player.play();
        ab = player.mix;

        fft = new FFT(player.bufferSize(), player.sampleRate());

        for (int i = 0; i < 100; i++) {
            Particle p = new Particle();
            particles.add(p);
        }

        y = height / 2;
        smoothedY = y;

        // Create the left and right hearts
        leftHeart = new Heart(width * 0.2f, height / 2, 10, color(255, 0, 0));
        rightHeart = new Heart(width * 0.8f, height / 2, 10, color(255, 0, 0));

    }

    float off = 0;

    float lerpedBuffer[] = new float[1024];

    void drawDaisy() {

   
        // Set the center point of the daisy
        float centerX = width / 2;
        float centerY = height / 2;
        float average = 0;
        float sum = 0;

        

        // Set the size of the daisy
        float daisySize = 200;

        // Calculate sum and average of the samples
        for (int i = 0; i < ab.size(); i++) {
            sum += abs(ab.get(i));
        }
        average = sum / (float) ab.size();
        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.2f);

        // Map the amplitude value to a range of values that will control the size of
        // the center circle
        float size = map(smoothedAmplitude, 0, 1, 130, 800);

        // Set the color of the daisy
        fill(255, 255, 0); // yellow

        // Draw the center of the daisy with the mapped size value
        ellipse(centerX, centerY, size, size);

        // Draw the petals of the daisy
        fill(255, 255, 255); // white
        for (int i = 0; i < 6; i++) {
            float angle = i * TWO_PI / 6;
            float petalX = centerX + cos(angle) * (daisySize / 2);
            float petalY = centerY + sin(angle) * (daisySize / 2);
            pushMatrix();
            translate(petalX, petalY);
            rotate(angle);
            ellipse(0, 0, 110, 100);
            popMatrix();
        }

        // Set the color of the daisy
        fill(255, 255, 0); // yellow

        // Draw the center of the daisy with the mapped size value
        ellipse(centerX, centerY, 130, 130);

        // Set the color and stroke for the smile
        // Draw the smile
        strokeWeight(5);
        stroke(0);
        noFill();
        arc(centerX, centerY + 25, 60, 60, 0, PI);

        // Draw the eyes
        fill(0);
        noStroke();
        ellipse(centerX - 25, centerY - 10, 20, 20);
        ellipse(centerX + 25, centerY - 10, 20, 20);

        // blush
        fill(255, 192, 203);
        noStroke();
        ellipse(centerX - 35, centerY + 9, 15, 10);
        ellipse(centerX + 35, centerY + 9, 15, 10);
    }

    void drawstem() {
        pushMatrix(); // save the current coordinate system
        float halfH = (height / 2) + 65;
        float halfW = (width / 2);
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

        for (int i = 0; i < ab.size(); i++) {
            float x = halfW - (lerpedBuffer[i] * halfH * 0.5f);
            float y = map(i, 0, ab.size(), halfH, height);
            stroke(map(i, 0, ab.size(), 0, 255), 252, 0);
            line(halfW, y, x, y);
        }
        popMatrix(); // restore the previous coordinate system
    }

    public void draw() {
        background(0);
        drawDaisy();
        drawstem();	
        
      

        fft.forward(player.mix);

        // maintain a variable number of particles between 10 and 20
        int minParticles = 10;
        int maxParticles = 20;
        int numParticles = particles.size();
        int targetParticles = (int) map(fft.getBand(20), 0, 1, minParticles, maxParticles);
        if (numParticles < targetParticles) {
            for (int i = 0; i < targetParticles - numParticles; i++) {
                Particle p = new Particle();
                particles.add(p);
            }
        } else if (numParticles > targetParticles) {
            particles.subList(targetParticles, numParticles).clear();
        }

        for (int i = 0; i < particles.size(); i++) {
            Particle p = particles.get(i);
            p.update();
            p.draw();
        }

      // Update the left and right hearts based on the audio amplitude
  leftHeart.update(smoothedAmplitude);
  rightHeart.update(smoothedAmplitude);

  // Draw the left and right hearts
  leftHeart.draw();
  rightHeart.draw();
    }

    class Particle {
        float x, y;
        float vx, vy;
        float size;
        int color;

        Particle() {
            x = random(width);
            y = random(height);
            vx = random(-1, 1);
            vy = 4; // set the falling speed to a constant value of 2
            size = random(10, 20);
            color = color(random(255), random(255), random(255));
        }

        void update() {
            x += vx;
            y += vy;

            if (y > height) {
                y = 0;
            }
        }

        void draw() {
            int index = (int) map(x, 100, width, 100, fft.specSize());
            float amplitude = fft.getBand(index);

            size = amplitude * 70;
            // limit the maximum size
            if (size > 50) {
                size = 50;
            }
            fill(color);
            noStroke();
            ellipse(x, y, size, size);
        }
    }
    class Heart {
        float x, y;
        float size;
        int color;
        
        Heart(float x, float y, float size, int color) {
          this.x = x;
          this.y = y;
          this.size = size;
          this.color = color;
        }
        
        void draw() {
          // Draw the heart shape using bezier curves
          smooth();
          noStroke();
          fill(color);
          beginShape();
          vertex(x, y);
          bezierVertex(x - size * 1, y - size * 2, x - size * 3, y + size / 2, x, y + size * 2);
          bezierVertex(x + size * 3, y + size / 2, x + size * 1, y - size * 2, x, y);
          endShape();
        }
        
        void update(float amplitude) {
          // Map the amplitude value to a range of values that will control the size of the heart
          size = map(amplitude, 0, 1, 25, 100);
        }
      }
}