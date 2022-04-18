package C20401562;

import ie.tudublin.*;

public class AlexsVisual extends Visual{

    Start start;

    //constructor 
    public AlexsVisual(Start start) 
    {
        this.start = start;
    }

    public void render()
    {
        start.background(0);
        start.fill(130);
        start.stroke(255);
        start.ellipse(300, 300, 300,300);
    }

    public void setup()
    {

    }
    
}
