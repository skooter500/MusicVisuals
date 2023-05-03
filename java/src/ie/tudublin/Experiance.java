package ie.tudublin;

import assaigment.*;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class Experiance extends PApplet {

    Minim minim;
    AudioPlayer player;
    AudioBuffer ab;


    AudioPlayer ap;
    AudioInput ai;
 

    float smoothedY = 0;
    float smoothedAmplitude = 0;
    FFT fft;

    int mode = 0;

    float y = 0;

    stars s; // declare a Star instance

    public void keyPressed() {
        if (key >= '0' && key <= '9') {
            mode = key - '0';
        }
        if (keyCode == ' ') {
            if (player.isPlaying()) {
                player.pause();
            } else {
                player.rewind();
                player.play();
            }
        }
    }


    public void settings() {
        size(1024, 1000, P3D);
    }

    public void setup() {

        minim = new Minim(this);
        player = minim.loadFile("MusicVisuals/java/data/Victoria_Mon_t_ft_Khalid_-_Experience.mp3", 512);
        player.play();
        ab = player.mix;

        s = new stars(this); // initialize the Star instance
        s.setup();
    }

    public void draw() {
        switch (mode) {
            case 0:
               
                s.render();// render the Star instance

                break;
            case 1:
                background(0);
                break;
            case 2:
                background(0);
                break;
        }
    }
    
  
}