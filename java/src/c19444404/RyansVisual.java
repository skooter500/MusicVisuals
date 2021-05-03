package c19444404;


import ie.tudublin.*;

public class RyansVisual extends Visual{

Vision vision;
  
    public void settings()
    {
        //size(600, 600, P3D);
        fullScreen(P3D);
    }

    

    public void setup()
    {
        startMinim();
        loadAudio("liveforever.mp3");
        colorMode(HSB);
        vision = new Wave(this);
        //vision = new SphereCircle(this);
    
       
   }
  

public void keyPressed() {
    
    if (key == ' ')
    {
        getAudioPlayer().cue(0);
        getAudioPlayer().play();
    }
    
       
        if (key == '2')
        {
            vision = new SphereCircle(this);
        }
        if ( key == '8')
        {
            vision = new Sphere(this);
        }
        if (key =='3')
        {
            vision = new Circles(this);
        }
        if (key == '4')
        {
            vision = new Spiral(this);
        }
        if (key == '1')
        {
            vision = new Wave(this);
        }
        if (key == '6')
        {
            vision = new Line(this);
        }
        if (key == '5')
        {
            vision = new Spirals(this);
        }
        if (key == '7')
        {
            vision = new Abduction(this);
        }
              
       

}


  // float[] lerpedBuffer;


   public void draw()
   {
    
    background(0);
    try
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
    calculateAverageAmplitude();        
    vision.render(); 
        // will draw either a circle or rect depending on what the instance of vision is 

                    
           

    }
         
    
}









