package C20401562;

import ie.tudublin.*;
import processing.core.*;

public class MendesVisual extends Visual{

    Start s;
    float average;
    float sum;
    float lerpedAverage = 0;
    

    public MendesVisual(Start start) 
    {
        this.s = start;
    }


    public void render()
    {
        average = 0;
        //calculate average buffer
        for (int i = 0; i < s.getAudioBuffer().size(); i ++)
        {
            sum += abs(s.getAudioBuffer().get(i));
        }
        average = sum / s.getAudioBuffer().size(); 

        lerpedAverage = lerp(lerpedAverage, average, 0.1f); // move lerped average closer to frame 10%
        for(int i = 0; i < s.width ;i++){
            float index = PApplet.map(i, 0 , s.width, 0, s.getAudioBuffer().size());
            float y = s.getAudioBuffer().get((int)index) * 50 + s.height / 2;

            s.stroke(PApplet.map(index, 0, s.getAudioBuffer().size()* 2, 0, 255), 255, 255);

            s.line(i, y, i, y + s.getAudioBuffer().get((int)index));

        }
    }
    
}
