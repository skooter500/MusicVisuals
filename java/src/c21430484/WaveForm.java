package c21430484;

import processing.core.*;

// This is an example of a visual that renders the waveform
public class WaveForm
{
    BensVisual mv;
    float cy = 0;

    public WaveForm(BensVisual mv)
    {
        this.mv = mv;
        cy = this.mv.height / 2;
        cy = cy + 350;
    }

    public void render()
    {
        // mv.colorMode(PApplet.HSB);
        for(int i = 0 ; i < mv.getAudioBuffer().size() ; i ++)
        {
            mv.stroke(208, 152, 3);
            // mv.stroke
            // (
            //     PApplet.map(i, 0, mv.getAudioBuffer().size(), 0, 255), 255, 255
            // );

            mv.line(i, cy, i, cy + mv.getAudioBuffer().get(i) * 100);
        }
    }
}

