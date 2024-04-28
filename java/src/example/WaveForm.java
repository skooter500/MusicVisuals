package example;

import ie.tudublin.OurVisual;
import processing.core.*;

// This is an example of a visual that renders the waveform
public class WaveForm
{
    // MyVisual mv;
    OurVisual ov;

    float cy = 0;

    // public WaveForm(MyVisual mv)
    public WaveForm(OurVisual ov)
    {
        this.ov = ov;
        cy = this.ov.height / 2;
    }

    public void render()
    {
        ov.colorMode(PApplet.HSB);
        for(int i = 0 ; i < ov.getAudioBuffer().size() ; i ++)
        {
            ov.stroke(
                PApplet.map(i, 0, ov.getAudioBuffer().size(), 0, 255)
                , 255
                , 255
            );

            ov.line(i, cy, i, cy + cy * ov.getAudioBuffer().get(i));
        }
    }
}