package C20401562;

import ie.tudublin.*;
import processing.core.*;

public class MendesVisual extends Visual{

    Start s;
    float cy = 0;

    public MendesVisual(Start start) 
    {
        this.s = start;
    }


    public void render()
    {
        cy = this.s.height/2;
        for(int i = 0 ; i < s.width; i ++)
        {
            s.stroke(
                PApplet.map(i, 0, s.getAudioBuffer().size()*2, 0, 255), 255, 255);

            s.line(i, cy, i, cy + cy * s.getAudioBuffer().get(i));
        }

        s.pushMatrix();
        s.translate(s.height/2, s.width/2);
        

        s.popMatrix();


    }
    
}
