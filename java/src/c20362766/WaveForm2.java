package c20362766;


import processing.core.*;



// This is an example of a visual that renders the waveform
public class WaveForm2
{
    HabeebsVisuals mv;
    float cy = 0;
    float cx = 0;

    public WaveForm2(HabeebsVisuals mv)
    {
        this.mv = mv;
        cy = this.mv.height / 2;
        cx = this.mv.width / 2;
    }

    public void render()
    {
        mv.colorMode(PApplet.HSB);
        
        for(int i = 0 ; i < mv.getAudioBuffer().size() ; i ++)
        {
            // changes color to rainbow range
            mv.stroke(PApplet.map(i, 0, mv.getAudioBuffer().size(), 0, 255), 255, 255);

            mv.circle(cx, cy, 10000 * mv.getAudioBuffer().get(i));

        
        }
    }
}