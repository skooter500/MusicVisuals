package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;


public class Rendering extends PApplet{
    Minim minim; // Connect to minim
    AudioInput ai; // How to connect to mic
    AudioPlayer ap;
    AudioBuffer ab; // Samples
    float[] lerpedBuffer;
    float angle;
    float c;


    int gap = 20; //gap between arcs
    int thickness = 2; //thickness of each arc
    
    public void settings(){
        size(600,600, P3D);
    }

    public void setup(){
        minim = new Minim(this);
        //ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        ap = minim.loadFile("heroplanet.mp3", width);
        ap.play();
        ab = ap.mix; // Connect the buffer to the mp3 file
        //ab = ai.mix; 
        lerpedBuffer = new float[width];
        colorMode(HSB);
        noFill();
        strokeWeight(thickness);
    }

    float lerpedAverage = 0;

    public void draw(){
    background(0);
    pushMatrix(); //prevents entire canvas from being transformed
    translate(width/2, height/2);
    angle = 5;
    c = cos(angle);
    
    


    for(int j = 0; j< ab.size(); j++){ 
        for(int i = gap; i< width-gap; i+= gap){            
            //Colour formatting
            float c = map(i, 0, ab.size(), 0, 255);
            stroke(c, 255, 255);
                      

            //angle of rotation for the spiral and buffer for lerped
            float angle = radians(i);
            lerpedBuffer[j] = lerp(lerpedBuffer[j], ab.get(j), 0.1f); 

            arc(0, 0, i, i, angle, angle + (lerpedBuffer[j] * 5));
            }
        }
    popMatrix();
    }
}
