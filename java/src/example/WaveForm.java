package example;

import processing.core.*;

// This is an example of a visual that renders the waveform
public class WaveForm
{
    MyVisual mv;
    float cx = 0;

    public WaveForm(MyVisual mv)
    {
        this.mv = mv;
        cx = this.mv.width / 2;
    }

    public void render()
    {
        mv.colorMode(PApplet.HSB);
        for(int i = 0 ; i < mv.getAudioBuffer().size() ; i ++)
        {
            mv.stroke(
                PApplet.map(i, 0, mv.getAudioBuffer().size(), 0, 255)
                , 255
                , 255
            );

            float x = cx - (cx * mv.getAudioBuffer().get(i)); // Calculate the x-coordinate of the line
           
            mv.line(cx, i, x, i); 
        }
    }
}