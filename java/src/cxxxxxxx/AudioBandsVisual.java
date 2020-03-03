package cxxxxxxx;

import processing.core.*;

public class AudioBandsVisual
{
    MyVisual mv;

    public AudioBandsVisual(MyVisual mv)
    {
        this.mv = mv; 
    }

    public void render()
    {
        float gap = mv.width / (float) mv.bands.length;
        mv.noStroke();
        for(int i = 0 ; i < mv.bands.length ; i ++)
        {
            mv.fill(PApplet.map(i, 0, mv.bands.length, 0, 255), 255, 255);
            mv.rect(i * gap, mv.height, gap,-mv.lerpedBands[i]); 
        }
    }
}