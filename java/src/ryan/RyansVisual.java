package ryan;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ie.tudublin.*;

public class RyansVisual extends Visual{

  SphereCircle sc;

  Minim minim; //connecting to the minim libraries
  AudioInput ai; //connectig to AI libraries
  AudioPlayer ap; //connecting to the ap libraries
  AudioBuffer ab; //the samples

  float[] lerpedBuffer;
  int which = 0;

  float angle = 0; //angle of spin
  //for changing stroke colour

  int angleChange = 5;//amount spin is incremented by
  final int ANGLE_LIMIT = 360; //max rotation

  int gap = 50; //gap between arcs
  int thickness = 10; //thickness of each arc
 

    public void settings()
    {
        size(600, 600, P3D);
    }

    

    public void setup()
    {
        startMinim();
        minim = new Minim(this);
        ap = minim.loadFile("heroplanet.mp3", width);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);
        lerpedBuffer = new float[width];
        sc = new SphereCircle(this);
       
        //strokeWeight(15);
        
    
        


       startListening();
       
   }
  

public void keyPressed() {
    if (keyCode >= '0' && keyCode <= '6') {
        which = keyCode - '0';
    }
    if (keyCode == ' ') {
        if (ap.isPlaying()) {
            ap.pause();
        } else {
            ap.rewind();
            ap.play();
        }
        if (keyCode == UP)
        {
            colour = ! colour;
        }
    }
}

    float lerpedAverage =0;
    private  boolean colour = false;


   public void draw()
   {
    background(0);
  
    float sum =0;
    float average =0;

            // Calculate the average of the buffer
            for (int i = 0; i < ab.size(); i ++)
            {
                sum += abs(ab.get(i));
            }
            average = sum / ab.size();
            // Move lerpedAverage 10% closer to average every frame
            lerpedAverage = lerp(lerpedAverage, average, 0.1f);
       /* try
        {
            // Call this if you want to use FFT data
            calculateFFT(); 
        }
        catch(VisualException e)
        {
            e.printStackTrace();
        }
        // Call this is you want to use frequency bands
        calculateFrequencyBands(); 

        // Call this is you want to get the average amplitude
        calculateAverageAmplitude();  */
    
   // stroke(255);
   float halfHeight = height/2;

   switch (which)
   {
       case 0:
       {
           noFill();
        pushMatrix(); //prevents entire canvas from being transformed
        translate(width/2, height/2); //move the 0,0 position to the center of the screen so w/2, h/2
        rotate(radians(-angle)); //rotate negative so it rotates the direction I want
    
        for(int j = 0; j< ab.size(); j++){ //for loop through the total size of the audio buffer, used for expanding arc lines
            for(int i = gap; i< width-gap; i+= gap){ //used to create the maximum size the spiral can become
                //Colour formatting
                float c = map(i, 0, ab.size(), 0, 255);
                stroke(c, 255, 255);
                          
    
                //angle of the arcs and the lerped buffer given it's value which will constantly change depending on music frequency buffer size
                float angle = radians(i);
                lerpedBuffer[j]  = lerp(lerpedBuffer[j], ab.get(j), 0.1f); 
    
                //arc starting at 0,0 so middle of the screen after the translate and the final value is using lerped buffer to let the arc length change
                arc(0, 0, i, i, angle, angle + (lerpedBuffer[j]  * 5));
                }
            }
            //incrementing angle of rotation 
            angle += angleChange;
            //if it hits 360 then it will reset to 0
            if (angle >= ANGLE_LIMIT || angle < 0)
            {
                angle = 0;
            }
            popMatrix();     
           break;
       }   
       case 1:
       {
           strokeWeight(25);
        for (int i = 0; i < ab.size(); i++) {

            float c = map(i, 0, ab.size(), 0, 255);
            if( !  colour)
            {
            stroke(c, 0, 120);
            }
            else
            {stroke(c,255,255);}
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);        
            line(i, halfHeight - lerpedBuffer[i] * halfHeight * 4, i, halfHeight + lerpedBuffer[i] * halfHeight * 4);
        }        
        break;
        }
        case 2:
        {
            sc.render();
            break;
        }
          
        
       }
    }
}







