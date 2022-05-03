package C20401562;

import ie.tudublin.Visual;
import processing.core.PApplet;

public class Particals extends Visual{

    Start s; 
    int numsnows;
    Particals[] partical = new Particals[400];   
    float y;
    float x;
    float z;
    float max = 1000;
    float start = -1000;
 
    

    public Particals(Start s){

        this.s = s; 
        y = s.random(-s.height, s.height);
        x = s.random(-s.width, s.width);
        z = s.random(s.width); 
        
    }

    public void update(){

        z = z - 10;

        if(z < 1){

            z = s.width;
            y = s.random(-s.height, s.height);
            x = s.random(-s.width, s.width);
        }
    }

    public void display(){

        
        s.fill(255);
        s.stroke(48, 139, 206);

        float sx = PApplet.map(x / z , 0, 1, 0, s.width);
        float sy = PApplet.map(y / z, 0, 1, 0, s.height);

        float n = PApplet.map(z, 0, s.width, 16, 0);
        s.ellipse(sx, sy, n, n);

        
        y += ((max * s.getAmplitude()) * 0.2f);

        if (y >= max)
        {
            y = start;
        }
        
    
    }

}