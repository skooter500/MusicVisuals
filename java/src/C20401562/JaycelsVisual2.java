package C20401562;

import ie.tudublin.*;
import processing.core.PVector;



public class JaycelsVisual2 extends Visual{

    Start start;
    PVector pos;

    public JaycelsVisual2(Start start) 
    {
        this.start = start;
    }

    public void render() {

        // Call this is you want to use frequency bands
        calculateFrequencyBands(); 

        // Call this is you want to get the average amplitude
        calculateAverageAmplitude();
    }

}
