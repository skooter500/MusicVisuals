package c21430484;

import processing.core.*;

// This is an example of a visual that uses the audio bands
public class TrumpetBandVisual
{
    BensVisual mv;

    public TrumpetBandVisual(BensVisual mv)
    {
        this.mv = mv; 
    }

    public void render()
    {
        float gap = mv.width / (float) mv.getBands().length;
        mv.noStroke();

        
        // mv.fill(PApplet.map(6, 0, mv.getBands().length, 255, 0), 255, 255);
        mv.fill(208, 152, 3);
        
        mv.rect(0, mv.height, 100,-mv.getSmoothedBands()[6] * 0.75f); 
        mv.rect(mv.width - 100, mv.height, 100,-mv.getSmoothedBands()[6] * 0.75f); 

        mv.noFill(); 
        mv.stroke(208, 152, 3);
        mv.translate(0, +30, 0);
        mv.circle(mv.width / 2, mv.height / 2, 300 + (mv.getSmoothedBands()[6] * 0.35f)); 
        mv.translate(0, -30, 0);

    }
}