package C20401562;

import ie.tudublin.*;
import processing.core.*;

public class MendesVisual extends Visual{

    Start start;
    float lerpedAverage = 0;
    float y= 0;
    float smallpend = 0;
    float smallpstart = 15;
    float bigpstart = 0;
    
    public MendesVisual(Start start) 
    {
        this.start = start;
    }

    public void render()
    {
        
        
        start.pushMatrix();
        start.getFFT();
        start.translate(start.width/2, start.height/2);
        start.fill(170,255,250);
        start.circle(0, 0, 600);
        start.strokeWeight(start.width/120);
        start.text("Game Over", -80, 250);
        if (start.beat.isOnset() == true) {
            smallpstart =smallpstart + 5 % 255;
            smallpend =smallpend + random(200) % 255;                    
        }

        if (start.beat.isHat()) {
            bigpstart = bigpstart + 120;
        }
        
        for(float i = 0f; i < 360; i++){
            //big pupil size
            float pb = PApplet.map(y, 0, 1, (start.getSmoothedAmplitude()*500) * -1 ,start.getSmoothedAmplitude()*300);

            //colour to change with the beat
            start.stroke(
                PApplet.map(i, 0, start.getAudioBuffer().size(),smallpstart , smallpend), 255, 255
                );
            start.ellipse(300,0,pb,10);
        }

        for(float i = 0f; i < 360; i++){
            //small pupil size
            float ps = PApplet.map(y, 0, 1, (start.getSmoothedAmplitude() * 1000) * 1 ,start.getSmoothedAmplitude()*30);
            //colour to change with the beat
            start.stroke(
                PApplet.map(i, 0, start.getAudioBuffer().size(),255 , 255), 255, bigpstart);
            start.ellipse(5,0,0,ps);
            }
        start.popMatrix();
    }
    
}
