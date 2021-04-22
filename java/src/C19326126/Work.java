package C19326126;

import processing.core.PApplet;
import ddf.minim.analysis.*;
import ddf.minim.*;


public class Work extends PApplet{
    AudioPlayer player;
    Minim minim;

    public void settings(){
        size(1920, 1080, P3D);
    }

    public void setup(){
        smooth(8);
        minim = new Minim(this);
        player = minim.loadFile("heroplanet.mp3");
        player.play();
    }

    public void draw(){
        translate(width/2, height/2);
        background(0);
        rotateY( (float) 1.6);
        for( int i = 0; i< player.bufferSize() -1; i++){
            rotateX(50+player.right.get(i)/100);

            pushMatrix();
            fill(255,0,0);
            popMatrix();
            strokeWeight(1+player.right.get(i));

            stroke(0);
            box(10,10,55+player.right.get(i)*200);
            box(55 + player.right.get(i)*50,10,10);
            box(10,55 + player.right.get(i)*50, 10);




        }
    }
    
}
