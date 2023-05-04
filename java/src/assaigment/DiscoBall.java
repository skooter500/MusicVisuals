


package assaigment;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.AudioSource;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import jogamp.opengl.glu.nurbs.Curve;
import processing.core.PApplet;

public class DiscoBall extends PApplet {

  Minim minim;
  AudioPlayer ap;
  AudioInput ai;
  AudioBuffer ab;
  FFT fft;


  int mode = 0;
  float theta = 0;
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

    // Create gradient background
    for (int y = 0; y < height; y++) {

      // Calculate the color at each row
      int c = lerpColor(color(139, 0, 139), color(255, 140, 0), map2(y, 0, height, 0, 1));

      // Set the color for the row
      stroke(c);

      // Draw a line for the row
      line(0, y, width, y);

    } 
  }

  public void draw() {


    pushMatrix(); // Save the current transformation matrix

    // displays hearts on the screen
    for (int i = 0; i < 5; i++) {

      Heart heart = new Heart();
      heart.display();

    }

    popMatrix(); // Restore the previous transformation matrix
  
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
  
    rotationSpeed = map2(average, 0, 255, 0.001, 0.1); // Map the average frequency value to a rotation speed range
    rotateY(rotationSpeed * frameCount); // Rotate the sphere based on the current frame count and rotation speed
  
    // set the thickness of the lines in the disco ball
    stroke(204,204,255); 
    strokeWeight((float) 3.5); // sets the thickness of lines in the disco Ball
    line(0, -250, 0, 0);

    

    fill(160,160,160);// set th colour of the sphere
    sphere(250); // Draw the sphere

    discoBallRope();

  
  }
  
  private float map2(float value, double f, double g, double d, double e) {

    return (float) (d + (e - d) * ((value - f) / (g - f)));

  }


  void discoBallRope() {

    stroke(192,192,192); 
    strokeWeight(10); // Set the thickness of the line
    line(0, 0, 0, 0, -height/2, 0); // Draw the line from the top of the sphere to the top of the screen

  }

  class Heart {

    float heartSize;
    float heartX;
    float heartBottomY;
    float r;
    
    Heart() {

      heartSize = random(10, 100);
      heartX = random(width);
      heartBottomY = random(height+heartSize);
      r = random(255);

    }


    
    
    void display() {


      float level = ap.mix.level();
  
      if (level > 0.1) {

        // Set heart position and color
        heartX = random(width);
        heartBottomY = random(height+heartSize);
        r = random(255);

      }
  
      fill(r, 0, 0);
      stroke(r, 0, 0);
  
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



    

  }

}
