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
        startListening();        
        wf = new WaveForm(this);
        abv = new AudioBandsVisual(this);
    }

    public void draw()
    {
        background(0);
        try
        {
            calculateFFT();
        }
        catch(VisualException e)
        {
            e.printStackTrace();
        }
        calculateFrequencyBands();
        calculateAverageAmplitude();        
        wf.render();
        abv.render();
    }
}
