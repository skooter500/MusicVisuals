package ie.tudublin;

import processing.core.*;

public class AudioBandsVisual extends PApplet
{
    Seno seno;
    
    public AudioBandsVisual(Seno seno)
    {
        this.seno = seno;
    }

    public void render(int bandColor)
    {
        float gap = seno.width / (float) seno.getBands().length * 4.0f;
        seno.noStroke();
        seno.fill(bandColor);

        for(int i = 0 ; i < seno.getBands().length ; i ++)
        {
            //draw rectangle for each band
            seno.rect((i * gap - gap / 2)-3120, seno.height+2300, gap,-seno.getSmoothedBands()[i] * 1f); 
        }
    }
}