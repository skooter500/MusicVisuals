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
        for(int i = 0; i < s.width ;i++){
            float index = PApplet.map(i, 0 , s.width, 0, s.getAudioBuffer().size());
            float y = s.getAudioBuffer().get((int)index) * 50 + s.height / 2;

            s.stroke(PApplet.map(index, 0, s.getAudioBuffer().size()* 2, 0, 255), 255, 255);

            s.line(i, y, i, y + y* s.getAudioBuffer().get((int)index));

        }
    }
    
}
