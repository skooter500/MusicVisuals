package C19326126;

import processing.core.PApplet;

public class Work extends PApplet{
    float r;
    float ex, ey;

    public void settings(){
        size(800,800);
    }

    public void setup(){
        ex = 140;
        ey = 0;
    }

    public void draw(){
        background(0);
        pushMatrix();
        translate(width/2, height/2);
        rotate(radians(r));
        //sun
        fill(255,255,0);
        ellipse(0,0,100,100);
        //earth
        fill(0,255,100);
        ellipse(ex,ey,20,20);
            pushMatrix();
            //moon
            translate(ex,ey); //sets center to center of the earth
            rotate(radians(-r*3));
            fill(255);
            ellipse(25,ey,5,5);
            popMatrix();
        popMatrix();
    r += 1;
    }
    
}
