package c20362766;


import ie.tudublin.Visual;
import ie.tudublin.VisualException;

public class HabeebsVisuals extends Visual
{

    WaveForm wf;
    
    
    public void settings()
    {
        size(500, 500);
    }

    public void setup()
    {
        startMinim();
        
        // Call this instead to read audio from the microphone
        startListening(); 
        
        wf = new WaveForm(this);
        
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
        wf.render();
        
    }


}