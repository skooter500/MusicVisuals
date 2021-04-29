package ie.tudublin;

import ryan.RyansVisual;
import processing.core.*;
public class Wave extends Vision{

    public Wave(RyansVisual rv)
    {
        super(rv);
    }
//float halfHeight = rv.height/2;
float[] lerpedBuffer =  new float[512];
boolean color = false;

    public void render()
    {
        
        rv.strokeWeight(1);
        rv.background(150,50,100);

        rv.rectMode(PApplet.CENTER);
        rv.fill(255);
        rv.rect(rv.width/2,rv.height/2,740,400);
        rv.fill(0);
        rv.rect(rv.width/2,rv.height/2,700,350);

        rv.fill(255);
        rv.textSize(200);
        rv.text("OASIS",rv.width/2 - 280,rv.height/2 + 80);

 

        for (int i = 0; i < rv.getAudioBuffer().size(); i++) {

            float c = PApplet.map(i, 0, rv.getAudioBuffer().size() , 0, 255);
           
                rv.stroke(c,255,255);
          
            lerpedBuffer[i] = PApplet.lerp(lerpedBuffer[i], rv.getAudioBuffer().get(i), 0.1f);        
            rv.line(i * 2.5f, rv.height - lerpedBuffer[i] * rv.height * 4, i * 2.5f, rv.height + lerpedBuffer[i] * rv.height * 4);

        }    
    }

 
    
}
