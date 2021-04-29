package ryan;


import ie.tudublin.*;

public class RyansVisual extends Visual{

Vision vision;
  


 
  int which = 0;

  //float angle = 0; //angle of spin
  //for changing stroke colour

  //int angleChange = 5;//amount spin is incremented by
  //final int ANGLE_LIMIT = 360; //max rotation

  //int gap = 50; //gap between arcs
  //int thickness = 10; //thickness of each arc
 

    public void settings()
    {
        size(600, 600, P3D);
    }

    

    public void setup()
    {
        startMinim();
        loadAudio("liveforever.mp3");
        colorMode(HSB);
        vision = new Test1(this);
        //vision = new SphereCircle(this);
    
       
   }
  

public void keyPressed() {
    
    if (key == ' ')
    {
        getAudioPlayer().cue(0);
        getAudioPlayer().play();
    }
    
        if (key == '0')
        {
            vision = new Test1(this);
        }
        if (key == '1')
        {
            vision = new SphereCircle(this);
        }
        if ( key == '2')
        {
            vision = new Sphere(this);
        }
        if (key =='3')
        {
            vision = new Circles(this);
        }
        
       

}


   


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








