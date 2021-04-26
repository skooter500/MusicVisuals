package ie.tudublin;


import ryan.*;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class SphereCircle extends Visual{

        
Minim minim; //connecting to the minim libraries
AudioInput ai; //connectig to AI libraries
AudioPlayer ap; //connecting to the ap libraries
AudioBuffer ab; //the samples
float lerpedBuffer[];


float lerpedAverage = 0;
int border = 20;

RyansVisual ryansVisual;


public SphereCircle(RyansVisual ryansVisual) {
    this.ryansVisual = ryansVisual;
}



public void render()
{
    float average = 0;
    float sum = 0;
    background(0);

    for (int i = 0; i < ab.size(); i ++)
    {
        sum += abs(ab.get(i));
    }
    average = sum / ab.size();
    // Move lerpedAverage 10% closer to average every frame
    lerpedAverage = lerp(lerpedAverage, average, 0.1f);

    for(int i=0;i< ab.size(); i++)
    {
        float c = map(i,0,ab.size(),0,255);
        stroke(c,255,255);
        lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);

                 noFill();
         

         float wave = sin(radians(frameCount/2));

         ellipse(width/2 + wave * 200 + lerpedBuffer[i] * 200 , height/2 + wave * 200,100,100);
         ellipse(width/2 - wave * 200 + lerpedBuffer[i] * 200, height/2 - wave * 200,100,100);
        
         ellipse(width/2 + wave * 200 + lerpedBuffer[i] * 200, height/2 - wave * 200,100,100);
         ellipse(width/2 - wave * 200 + lerpedBuffer[i] * 200, height/2 + wave * 200,100,100);

    }
    pushMatrix();
    translate(width/2,height/2);
    rotate(radians(100));
    sphere(40);
    popMatrix();
}
}




