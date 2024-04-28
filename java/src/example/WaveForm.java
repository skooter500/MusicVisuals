package example;

import processing.core.*;
import ie.tudublin.OurVisual;



// This is an example of a visual that renders the waveform
public class WaveForm
{
    OurVisual ov;
    float cy = 0;

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