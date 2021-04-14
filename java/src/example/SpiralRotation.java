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
    float[] lerpedBuffer; //buffer size of song playing, so the current volume level
    float angle = 0; //angle of spin
    float c; //for changing stroke colour

    int angleChange = 2;//amount spin is incremented by
    final int ANGLE_LIMIT = 360; //max rotation

    int gap = 20; //gap between arcs
    int thickness = 2; //thickness of each arc
    
    public void settings(){
        size(600,600, P3D); //screen size
    }

    public void setup(){
        minim = new Minim(this); //calling minim libraries
        //ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        ap = minim.loadFile("MONTEL.mp3", width);
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
    translate(width/2, height/2); //move the 0,0 position to the center of the screen so w/2, h/2
    rotate(radians(-angle)); //rotate negative so it rotates the direction I want

    for(int j = 0; j< ab.size(); j++){ //for loop through the total size of the audio buffer, used for expanding arc lines
        for(int i = gap; i< width-gap; i+= gap){ //used to create the maximum size the spiral can become
            //Colour formatting
            float c = map(i, 0, ab.size(), 0, 255);
            stroke(c, 255, 255);
                      

            //angle of the arcs and the lerped buffer given it's value which will constantly change depending on music frequency buffer size
            float angle = radians(i);
            lerpedBuffer[j] = lerp(lerpedBuffer[j], ab.get(j), 0.1f); 

            //arc starting at 0,0 so middle of the screen after the translate and the final value is using lerped buffer to let the arc length change
            arc(0, 0, i, i, angle, angle + (lerpedBuffer[j] * 5));
            }
        }
        //incrementing angle of rotation 
        angle += angleChange;
        //if it hits 360 then it will reset to 0
        if (angle >= ANGLE_LIMIT || angle < 0)
        {
            angle = 0;
        }
    popMatrix();
    }
}
