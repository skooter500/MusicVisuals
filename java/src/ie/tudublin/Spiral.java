package ie.tudublin;

import c19444404.RyansVisual;
import processing.core.*;

public class Spiral extends Vision {

    public Spiral(RyansVisual rv){
        super(rv);
    }
     

    float angle = 0; //angle of spin
    //for changing stroke colour
  
    int angleChange = 5;//amount spin is incremented by
    final int LIM = 360; //max rotation
  
    int gap = 50; //gap between arcs
    int thickness = 10; //thickness of each arc
    float[] lerpedBuffer = new float [512];

    @Override
    public void render()
    {

       
        rv.noFill();
        rv.pushMatrix(); //prevents entire canvas from being transformed
       rv. translate(rv.width/2, rv.height/2); //move the 0,0 position to the center of the screen so w/2, h/2
        rv.rotate(PApplet.radians(-angle)); //rotate negative anti clockwise
    
        for(int j = 0; j< rv.getAudioBuffer().size(); j++){ //for loop through the total size of the audio buffer, used for expanding arc lines
            for(int i = gap; i< rv.width-gap; i+= gap){ //used to create the maximum size the spiral can become
                //Colour formatting
                float c = PApplet.map(i, 0, rv.getAudioBuffer().size(), 0, 255);
                rv.stroke(c, 255, 255);
                          
    
                //angle of the arcs and the lerped buffer given it's value which will constantly change depending on music frequency buffer size
                float angle = PApplet.radians(i);
                lerpedBuffer[j]  = PApplet.lerp(lerpedBuffer[j], rv.getAudioBuffer().get(j), 0.1f); 
    
                //arc starting at 0,0 so middle of the screen after the translate and the final value is using lerped buffer to let the arc length change
                rv.arc(0, 0, i, i, angle, angle + (lerpedBuffer[j]  * 10));
                }
            }
            // angle of rotation 
            angle += angleChange;
            //if it hits 360 then it will reset to 0
            if (angle >= LIM || angle < 0)
            {
                angle = 0;
            }
            rv.popMatrix();     

    }

    
}
