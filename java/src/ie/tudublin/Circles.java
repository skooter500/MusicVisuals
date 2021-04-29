package ie.tudublin;


import ryan.*;
import processing.core.*;

public class Circles extends Vision {


    public Circles(RyansVisual rv){
        super(rv);
    }
      
float[] lerpedBuffer = new float [1024];

int border = 20;
@Override
public void render() 
{
  
    //rv.noFill()    
        // stroke(255);
            rv.noFill();
            for(int i=0;i < rv.getAudioBuffer().size(); i++)
            {
            float c = PApplet.map(i, 0, rv.getAudioBuffer().size(), 0, 255);
            rv.stroke(c,255,255);
            lerpedBuffer[i] = PApplet.lerp(lerpedBuffer[i], rv.getAudioBuffer().get(i), 0.1f);
    
    
    
    
    
    
            rv.ellipse(rv.width/2,rv.height/2 + lerpedBuffer[i] * 100,100 + (rv.getSmoothedAmplitude() * 500), 100 + (rv.getSmoothedAmplitude() * 500));
           rv. ellipse(rv.width/2 - 50,rv.height/2  + lerpedBuffer[i] * 100,100 + (rv.getSmoothedAmplitude() * 500), 100 + (rv.getSmoothedAmplitude() * 500));
           rv. ellipse(rv.width/2 + 50,rv.height/2  + lerpedBuffer[i] * 100,100 + (rv.getSmoothedAmplitude() * 500), 100 + (rv.getSmoothedAmplitude() * 500));
           rv. ellipse(rv.width/2,rv.height/2 + 50  + lerpedBuffer[i] * 100,100 + (rv.getSmoothedAmplitude() * 500), 100 + (rv.getSmoothedAmplitude() * 500));
           rv. ellipse(rv.width/2,rv.height/2 - 50  + lerpedBuffer[i] * 100,100 + (rv.getSmoothedAmplitude() * 500), 100 + (rv.getSmoothedAmplitude() * 500));
    
    
            rv.ellipse(rv.width/4,rv.height/4,50 + (rv.getSmoothedAmplitude() * 1000), 50 + (rv.getSmoothedAmplitude() * 500));
            rv.ellipse(rv.width/4 -25,rv.height/4,50 + (rv.getSmoothedAmplitude() * 500), 50 + (rv.getSmoothedAmplitude() * 500));
            rv. ellipse(rv.width/4 + 25,rv.height/4,50 + (rv.getSmoothedAmplitude() * 500), 50 + (rv.getSmoothedAmplitude() * 500));
            rv. ellipse(rv.width/4,rv.height/4 + 25,50 + (rv.getSmoothedAmplitude() * 500), 50 + (rv.getSmoothedAmplitude() * 500));
            rv.ellipse(rv.width/4,rv.height/4 - 25,50 + (rv.getSmoothedAmplitude() * 500), 50 + (rv.getSmoothedAmplitude() * 500));
    
            rv.ellipse(rv.width-rv.width/4,rv.height/4,50 + (rv.getSmoothedAmplitude() * 500), 50 + (rv.getSmoothedAmplitude() * 500));
            rv.ellipse(rv.width-rv. width/4 -25,rv.height/4,50 + (rv.getSmoothedAmplitude() * 500), 50 + (rv.getSmoothedAmplitude() * 500));
            rv.ellipse(rv.width - rv.width/4 + 25,rv.height/4,50 + (rv.getSmoothedAmplitude() * 500), 50 + (rv.getSmoothedAmplitude() * 500));
            rv. ellipse(rv.width- rv.width/4,rv.height/4 + 25,50 + (rv.getSmoothedAmplitude() * 500), 50 + (rv.getSmoothedAmplitude() * 500));
            rv.ellipse(rv.width - rv.width/4,rv.height/4 - 25,50 + (rv.getSmoothedAmplitude() * 500), 50 + (rv.getSmoothedAmplitude() * 500));
    
            rv.ellipse(rv.width-rv.width/4, rv.height -rv.height/4,50 + (rv.getSmoothedAmplitude() * 500), 50 + (rv.getSmoothedAmplitude() * 500));
            rv.ellipse(rv.width- rv.width/4 -25,rv.height - rv.height/4,50 + (rv.getSmoothedAmplitude() * 500), 50 + (rv.getSmoothedAmplitude() * 500));
            rv.ellipse(rv.width - rv.width/4 + 25, rv.height - rv.height/4,50 + (rv.getSmoothedAmplitude() * 500), 50 + (rv.getSmoothedAmplitude() * 500));
            rv.ellipse(rv.width- rv.width/4, rv.height - rv.height/4 + 25,50 + (rv.getSmoothedAmplitude() * 500), 50 + (rv.getSmoothedAmplitude() * 500));
            rv.ellipse(rv.width - rv.width/4, rv.height - rv.height/4 - 25,50 + (rv.getSmoothedAmplitude() * 500), 50 + (rv.getSmoothedAmplitude() * 500));
    
            
            rv.ellipse(rv.width/4, rv.height -rv.height/4,50 + (rv.getSmoothedAmplitude() * 500), 50 + (rv.getSmoothedAmplitude() * 500));
            rv.ellipse(rv. width/4 -25,rv.height - rv.height/4,50 + (rv.getSmoothedAmplitude() * 500), 50 + (rv.getSmoothedAmplitude() * 500));
            rv. ellipse(rv. width/4 + 25, rv.height - rv.height/4,50 + (rv.getSmoothedAmplitude() * 500), 50 + (rv.getSmoothedAmplitude() * 500));
            rv. ellipse( rv.width/4, rv.height -rv. height/4 + 25,50 + (rv.getSmoothedAmplitude() * 500), 50 + (rv.getSmoothedAmplitude() * 500));
            rv.ellipse( rv.width/4,rv. height - rv.height/4 - 25,50 + (rv.getSmoothedAmplitude() * 500), 50 + (rv.getSmoothedAmplitude() * 500));
}


}
}




