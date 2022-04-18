package C20401562;

import ie.tudublin.*;

public class JaycelsVisual extends Visual{

    Start start;

    public JaycelsVisual(Start start) 
    {
        this.start = start;
    }

    public void render()
    {
        start.background(0);
        start.fill(230);
        start.stroke(255);
        start.ellipse(200, 300, 300,300);
    }
    
}
