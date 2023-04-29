package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class idea extends PApplet{ 

    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    FFT fft;

    int mode = 0;

    float y = 0;    
    

float[] spectrum;

//Star[] stars;
//int numStars = 100;
//int maxSpeed = 0;

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

Star[] stars = new Star[500];
float speed;


public void settings()
    {
        size(1024, 1024);
    }

//Star[] stars = new Star[numStars];

public void setup() {
 
  minim = new Minim(this);
  // Uncomment this to use the microphone
  // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
  // ab = ai.mix; 

  // And comment the next two lines out
  ap = minim.loadFile("MusicVisuals/java/data/Victoria_Mon_t_ft_Khalid_-_Experience.mp3", 512);
  ap.play();
  ab = ap.mix;
  colorMode(HSB);
  
  
  for (int i = 0; i < stars.length; i++) {
    stars[i] = new Star();
  }
}

public void draw() {

    speed = map(mouseX, 0, width, 0, 50);

    background(0);

    translate(width / 2, height / 2);

    for (int i = 0; i < stars.length; i++) {
        stars[i].update();
        stars[i].show();
    }

    // Draw the word "experience" in the center of the screen
    textAlign(CENTER, CENTER);
    textSize(84);
    fill(255);
    text("EXPERIENCE", 0, 0);

    // Move the origin to the center of the screen
    translate(-width / 2, -height / 2);

}



class Star {
    float x, y, z;
    float pz;

    Star() {
        x = random(-width, width);
        y = random(-height, height);
        z = random(width);
        pz = z;
       // speed = random(1, maxSpeed);
       //speed = map(mouseX, 0, width, 0, 50);
    }

    void update() {
        z = z - speed;

        if (z < 1) {
            z = width;
            x = random(-width, width);
            y = random(-height, height);
            pz = z;
        }
    }
    void show() {
        fill(255);
        noStroke();

        float sx = map(x / z, 0, 1, 0, width);
        float sy = map(y / z, 0, 1, 0, height);

        float r = map(z, 0, width/2, 16, 0);

        ellipse(sx, sy, r, r);

        float px = map(x / pz, 0, 1, 0, width/2);
        float py = map(y / pz, 0, 1, 0, height/2);

        pz = z;

        stroke(255, 100);
        line(px, py, sx, sy);
    }
}

}