package c20362766;


import ie.tudublin.Visual;
import processing.core.*;



// This is an example of a visual that renders the waveform
public class WaveForm6 extends Visual
{
    HabeebsVisuals mv;


    
    float cy = 0;
    float cx = 0;

    public WaveForm6(HabeebsVisuals mv)
    {
        this.mv = mv;
        cy = this.mv.height / 2;
        cx = this.mv.width / 2;
    }

    public void minimalism(){

        float gap = mv.width / (float) mv.getBands().length; 
        mv.noStroke();


        for(int i = 0 ; i < mv.getBands().length ; i ++)
        {
            mv.fill(PApplet.map(i, 0, mv.getBands().length, 255, 0), 255, 255);
            mv.rect(i * gap, mv.height, gap,-mv.getSmoothedBands()[i] * 0.2f); 
        }


    }

    public void render()
    {
        
        minimalism();
    }
}