package example;


import processing.core.*;



public class Stars 
{
    MyVisual mv;
    float x;
    float y;
    float weight;
    int color;

    public Stars(MyVisual mv)
    {
        this.mv = mv;
        this.x = mv.random(this.mv.width);
        this.y = mv.random(this.mv.height);
        int MAXIMUM_WEIGHT = 10;
        this.weight = mv.random(MAXIMUM_WEIGHT);
        this.color = mv.color(mv.random(255),mv.random(255),mv.random(255));



    }

    public void draw()
    {
        
        mv.stroke(color);
        mv.strokeWeight(weight);
        
        // making the circles into a more star like shape by drawing five lines radiating out from center
        for (int i = 0; i < 5; i++)
        {
            float angle = PApplet.TWO_PI * i / 5.0f;
            float x2 = x + 20 * PApplet.cos(angle);
            float y2 = y + 20 * PApplet.sin(angle);
            mv.line(x,y,x2,y2);

            
        }
     
        mv.ellipse(x,y,weight,weight);
    }
        
}





