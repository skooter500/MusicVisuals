package example;

import ie.tudublin.stars;
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

    public WaveForm(stars stars) {
    }

    public void render()
    {
        mv.colorMode(PApplet.HSB);
        for(int i = 0 ; i < mv.getAudioBuffer().size() ; i ++)
        {
            mv.stroke(
                PApplet.map(i, 0, mv.getAudioBuffer().size(), 0, 255),
                255,
                255
            );

            float x = cx - (cx * mv.getAudioBuffer().get(i)); // Calculate the x-coordinate of the line
            float y = mv.height - i/2; // Calculate the y-coordinate of the line
            mv.line(cx, y/2, x, y); 
        }
    }
}