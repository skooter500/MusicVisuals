package c21430484;

import processing.core.*;

// This is an example of a visual that uses the audio bands
public class AudioBandsVisual
{
    BensVisual mv;

    public AudioBandsVisual(BensVisual mv)
    {
        this.mv = mv; 
    }

    public void render()
    {
        float gap = mv.width / (float) mv.getBands().length;
        float testGap = mv.height / (float) mv.getBands().length;
        
        float brightness = 60 / (float) mv.getBands().length;
        
        mv.noStroke();
        for(int i = 0 ; i < mv.getBands().length ; i ++)
        {
            mv.fill(208, 152, 3, 40 + (i * brightness));

            // mv.rect(i * gap, mv.height, gap,-mv.getSmoothedBands()[i] * 0.2f); 
            // mv.rect(i * gap, 0, gap, mv.getSmoothedBands()[i] * 0.2f); 

            mv.rect(0, i * testGap, +mv.getSmoothedBands()[i] * 0.2f, testGap);
            mv.rect(mv.width, i * testGap, -mv.getSmoothedBands()[i] * 0.2f, testGap);
        }
    }
}