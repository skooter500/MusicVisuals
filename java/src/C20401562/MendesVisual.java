package C20401562;

import ie.tudublin.*;
import processing.core.*;

public class MendesVisual extends Visual{

    Start s;
    float lerpedAverage = 0;
    float y= 0;
    float bigpend = 0;
    float bigpstart = 15;
    float smallpstart = 0;
    float space = 0.2f;
    
    public MendesVisual(Start start) 
    {
        this.s = start;
    }

    public void render()
    {
        

        s.pushMatrix();
        s.fill(map(s.getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        s.noStroke();
        s.translate(s.width/2, s.height/2);
        s.fill(170,255,250);
        s.circle(0, 0, 600);
        s.strokeWeight(s.width/120);
       
        if (s.beat.isOnset() == true) {
            bigpstart =bigpstart + 5 % 255;
            bigpend =bigpend + random(200) % 255;                    
        }

        if (s.beat.isHat()) {
            smallpstart = smallpstart + 120;
        }
        
        for(float i = 0f; i < 360; i++){
            //big pupil size
            float pb = PApplet.map(y, 0, 1, (s.getSmoothedAmplitude()*600) * -1 ,s.getSmoothedAmplitude()*300);

            //colour to change with the beat
            s.stroke(
                PApplet.map(i, 0, s.getAudioBuffer().size(),bigpstart , bigpend), 255, 255
                );
            s.ellipse(290,0,pb,10);

            s.rotate(space);
        } 

        for(float i = 0f; i < 360; i++){
            //small pupil size
            float ps = PApplet.map(y, 0, 1, (s.getSmoothedAmplitude() * 1000) * 1 ,s.getSmoothedAmplitude()*150);
            //colour to change with the beat
            s.stroke(
                PApplet.map(i, 0, s.getAudioBuffer().size(),255 , smallpstart), 255, smallpstart);
            s.ellipse(5,0,0,ps);

            s.rotate(space);
            }
        s.popMatrix();
    }
    
}
