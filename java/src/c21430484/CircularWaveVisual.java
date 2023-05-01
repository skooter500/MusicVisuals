package c21430484;

import processing.core.*;

public class CircularWaveVisual 
{
    BensVisual mv;
    float cy = 0;

    public CircularWaveVisual(BensVisual mv)
    {
        this.mv = mv; 
        this.mv = mv;
        cy = this.mv.height / 2;
    }

    public void render()
    {
        mv.stroke(208, 152, 3);

        mv.pushMatrix();
        mv.translate(mv.width/2, mv.height/2);
        mv.rotate(PApplet.radians(mv.frameCount % 360 * 2));

        for(int j = 0; j < 360; j++) 
        {
            mv.line(PApplet.cos(j)*225, PApplet.sin(j)*225, PApplet.cos(j)*PApplet.abs(mv.getAudioBuffer().get(j))*200 + PApplet.cos(j)*225, PApplet.sin(j)*PApplet.abs(mv.getAudioBuffer().get(j))*200 + PApplet.sin(j)*225);
        }


    }
}
