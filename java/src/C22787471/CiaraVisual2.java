package C22787471;

import processing.core.*;
import ddf.minim.*;
import ie.tudublin.OurVisual;

public class CiaraVisual2 {

    OurVisual ov;
    float cy = 0;

    public CiaraVisual2(OurVisual ov)
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

            ov.ellipse(i, cy, i, cy + cy * ov.getAudioBuffer().get(i));
        }

    }
    
}
