package ie.tudublin;


import c19444404.*;
import processing.core.*;

public class SphereCircle extends Vision {


    public SphereCircle(RyansVisual rv){
        super(rv);
    }
      
float[] lerpedBuffer = new float [512];

@Override
public void render() 
{
  
    rv.noFill();
    

    for(int i=0;i< rv.getAudioBuffer().size(); i++)
    {
        float c = PApplet.map(i,0,rv.getAudioBuffer().size(),0,255);// the colour
        rv.stroke(c,255,255);

        
       lerpedBuffer[i] = PApplet.lerp(lerpedBuffer[i], rv.getAudioBuffer().get(i), 0.1f);//lerped buffer function
         

        float wave = PApplet.sin(PApplet.radians(rv.frameCount/2));// creating the glide effect for the circles
        
         rv.ellipse(rv.width/2 + wave * 200 + lerpedBuffer[i]  * 200 , rv.height/2 + wave * 200,100,100); //all 4 circles
         rv.ellipse(rv.width/2 - wave * 200 +  lerpedBuffer[i] * 200, rv.height/2 - wave * 200,100,100);
        
         rv.ellipse(rv.width/2 + wave * 200 +  lerpedBuffer[i] * 200, rv.height/2 - wave * 200,100,100);
         rv.ellipse(rv.width/2 - wave * 200 +  lerpedBuffer[i]* 200, rv.height/2 + wave * 200,100,100);

    }
    
    rv.pushMatrix(); // the sphere in centre screen
    rv.translate(rv.width/2,rv.height/2);
    rv.rotate(PApplet.radians(100));
    rv.sphere(40);
    rv.popMatrix();
}


}





