package cxxxxxxx;

import ie.tudublin.*;

public class MyVisual extends Visual
{    
    WaveForm wf;
    AudioBandsVisual abv;

    public void settings()
    {
        size(1024, 500);
    }

    public void setup()
    {

        startMinim();
        
        startListening(); // microphone input
        
        // Call loadAudio to load an audio file to process 
        // instead of startListening 
        
        wf = new WaveForm(this);
        abv = new AudioBandsVisual(this);
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
        abv.render();
    }
}
