package c20474596;

import processing.core.PApplet;

public class Star extends PApplet {

    float x;
    float y;
    float z;
    float pz;  
    public Star(){
        x = random(-width,width);
        y = random(-height,height);
        z = random(width);
        pz = z;
    }

}