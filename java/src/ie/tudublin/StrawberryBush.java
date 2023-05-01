package ie.tudublin;

import processing.core.PApplet;
import processing.core.PImage;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;


public class StrawberryBush extends PApplet {
    Minim minim;
    AudioInput ai;
    AudioPlayer ap;
    AudioBuffer ab;
    PImage photo;

    // global variables go here

    public void settings() {
        size(500, 500);
        photo = loadImage("strawberry.png");
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("Mio mao.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);
        
    }

    // any necessary methods related to the draw method go here

    public void draw() {
        background(0);
        // get the current amplitude of the audio signal
        float amplitude = ab.level();
        
        // use the amplitude to set the size of the image
        float imageSize = map(amplitude, 0, 1, 50, 200);
        
        // display the image with the new size
        image(photo, width/2, height/2, imageSize, imageSize);
    }
    
}