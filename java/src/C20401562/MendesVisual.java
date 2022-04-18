package C20401562;

import ie.tudublin.*;

public class MendesVisual extends Visual{

    Start start;

    public MendesVisual(Start start) 
    {
        this.start = start;
    }


    public void render()
    {
        start.background(122);

        start.ellipse(100, 300, 300,300);
    }
    
}
