

package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.AudioSource;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class DiscoBall extends PApplet {

   
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    FFT fft;


    
    float heartSize = 50;
    float heartX, heartBottomY;
    float r;
    int mode = 0;
    float theta = 0;

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

    float rotationSpeed = (float) 0.01;

    public void settings() {
        size(800, 800, P3D);
        noSmooth();
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("Victoria_Mon_t_ft_Khalid_-_Experience.mp3", 1024);
        ap.play();
        ap.loop();
        ab = ap.mix;
        fft = new FFT(ab.size(), ((AudioSource) ap).sampleRate());
    }


    public void draw() {

         // Create gradient background
       for (int y = 0; y < height; y++) {
        // Calculate the color at each row
        int c = lerpColor(color(139,0,139), color(255,140,0), map2(y, 0, height, 0, 1));
        // Set the color for the row
        stroke(c);
        // Draw a line for the row
        line(0, y, width, y);
    }

       translate(width/2, height/2, 0); // Move the sphere to the center of the screen
        float angle = (float) (frameCount * 0.01); // Use a fixed rotation speed based on frame count
        rotateY(angle); // Rotate the sphere based on the angle

        fft = new FFT(ap.bufferSize(), ap.sampleRate()); // Initialize FFT with the audio buffer size and sample rate
        fft.forward(ab); // Perform FFT on the audio buffer
        float[] spectrum = fft.getSpectrumImaginary(); // Get frequency spectrum data

        float sum = 0;
        for (int i = 0; i < spectrum.length; i++) {
            sum += spectrum[i]; // Calculate the sum of all frequency values
        }
        float average = sum / spectrum.length; // Calculate the average frequency value

        float rotationSpeed = map2(average, 0, 255, 0.001, 0.1); // Map the average frequency value to a rotation speed range
        rotateY(rotationSpeed * frameCount); // Rotate the sphere based on the current frame count and rotation speed

        fill(212,175,55);
        sphere(250);

        float level = ap.mix.level();

        if (level > 0.1) {
          // Set heart position and color
          heartX = random(width);
          heartBottomY = random(height+heartSize);
          r = random(255);


        float heartSize = random(10, 100);
        heartX = random(width);
        heartBottomY = random(height+heartSize);
      
        float r = random(255);
    
      
        fill(r, 0, 0);
        stroke(r, 0, 0);
        }
        
        
        //dark hearts have light outlines and vice versa
        stroke(255-r);
      
        //left half of heart
        beginShape();
        curveVertex(heartX, heartBottomY+heartSize); //anchor point
        curveVertex(heartX, heartBottomY); //bottom tip
        curveVertex(heartX - heartSize/2, (float) (heartBottomY-heartSize/1.5)); //left edge
        curveVertex(heartX - heartSize/3, heartBottomY-heartSize); //top of left edge
        curveVertex(heartX, (float) (heartBottomY-heartSize*.75)); //top middle dip
        curveVertex(heartX, heartBottomY); //guiding point
        endShape();
      
        //right half of heart
        beginShape();
        curveVertex(heartX, heartBottomY);
        curveVertex(heartX, (float) (heartBottomY-heartSize*.75));
        curveVertex(heartX + heartSize/3, heartBottomY-heartSize);
        curveVertex(heartX + heartSize/2, (float) (heartBottomY-heartSize/1.5));
        curveVertex(heartX, heartBottomY);
        curveVertex(heartX, heartBottomY + heartSize);
        endShape();
    }
        



    private float map2(float value, float start1, float stop1, double d, double e) {
        return (float) (d + (e - d) * ((value - start1) / (stop1 - start1)));
      

    }



   }