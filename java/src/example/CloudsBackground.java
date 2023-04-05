package example;

import processing.core.*;

// This is an example of a visual that renders the waveform
public class CloudsBackground
{
    MyVisual mv;
    float cy = 0;


    public CloudsBackground(MyVisual mv)
    {
        this.mv = mv;
        cy = this.mv.height / 2;
    }


    public void render()
    {
        mv.colorMode(PApplet.HSB);
        
        

    }
}