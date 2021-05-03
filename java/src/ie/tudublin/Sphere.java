package ie.tudublin;


import c19444404.*;
import processing.core.*;

public class Sphere extends Vision {


    public Sphere(RyansVisual rv){
        super(rv);
    }
      

float angle =0;
float[] lerpedBuffer = new float[512];

@Override
public void render() 
{
  
    rv.noFill();
    rv.strokeWeight(20);
    for(int i=0;i< rv.getAudioBuffer().size(); i++)
    {
        
        float c = PApplet.map(i,0,rv.getAudioBuffer().size(),0,255);
        rv.stroke(c,255,255);
        lerpedBuffer[i] = PApplet.lerp(lerpedBuffer[i], rv.getAudioBuffer().get(i), 0.1f);



            rv.pushMatrix();
            rv.translate(rv.width/2 * 200,rv.height/2);
            rv.camera(0, 0, 0, 0, 0, -1, 0, 1, 0);
            rv.rotateY(angle);
            rv.rotateZ(angle);
            rv.sphere(60 + lerpedBuffer[i] * 200);
            rv.popMatrix();
    }
            angle += 0.1f;

}

      
    
   
}








