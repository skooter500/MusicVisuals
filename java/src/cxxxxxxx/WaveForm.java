package cxxxxxxx;

import processing.core.*;

public class WaveForm
{
    MyVisual mv;
    float cy = 0;
    
    public WaveForm(MyVisual mv)
    {
        this.mv = mv;
        cy = this.mv.height / 2;
    }

    public void render()
    {
        mv.colorMode(PApplet.HSB);
        for(int i = 0 ; i < mv.ab.size() ; i ++)
        {
            mv.stroke(
                PApplet.map(i, 0, mv.ab.size(), 0, 255)
                , 255
                , 255
            );

            mv.line(i, cy, i, cy + cy * mv.ab.get(i));
        }
    }
}