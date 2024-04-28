package example;

import ie.tudublin.OurVisual;
import processing.core.*;

// This is an example of a visual that uses the audio bands
public class AudioBandsVisual
{
    // MyVisual mv;
    OurVisual ov;

    // public AudioBandsVisual(MyVisual mv)
    public AudioBandsVisual(OurVisual ov)
    {
        this.ov = ov; 
    }

    public void render()
    {
        float gap = ov.width / (float) ov.getBands().length;
        ov.noStroke();
        for(int i = 0 ; i < ov.getBands().length ; i ++)
        {
            ov.fill(PApplet.map(i, 0, ov.getBands().length, 255, 0), 255, 255);
            ov.rect(i * gap, ov.height, gap,-ov.getSmoothedBands()[i] * 0.2f); 
        }
    }
}