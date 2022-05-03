
package c20474596;

import processing.core.PApplet;

public class drop extends PApplet{
   
    





    float x = random(width);
    float y = random(-500,-100);
    float z= random(0,20);
    float length =random(10,20);
    float len = map(z,0,20,10,20);
    float yspeed =map(z,0,20,1,20); 


    void fall(){
        y=y+yspeed;
        float grav= map(Z,0,20,0.01,0.02);
    
        yspeed = yspeed +grav;

        if (y>height){
            y=random(-200,-100);
            yspeed = map(z,0,20,4,10);
        }
    }
    
    void show(){

    float thick= map(z,0,20,1,3); 
    strokeWeight(thick);
    stroke(138,43,226);
    line(x,y,x,y+len);


    }

    
    


    private float map(int z2, int i, int j, double d, double e) {
        return 0;
    }

}