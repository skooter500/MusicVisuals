package C20401562;

import ie.tudublin.*;
import processing.core.*;

public class MendesVisual extends Visual{

    Start start;

    public MendesVisual(Start start) 
    {
        this.start = start;
    }


    public void render()
    {
        // float gap = start.width / (float) start.getBands().length;
        // start.noStroke();
        // for(int i = 0 ; i < start.getBands().length ; i ++)
        // {
        //     start.fill(PApplet.map(i, 0, start.getBands().length, 255, 0), 255, 255);
        //     start.rect(i * gap, start.height, gap,-start.getSmoothedBands()[i] * 100f); 
        // }


    }
    
}
