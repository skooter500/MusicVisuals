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
        mv.colorMode(PApplet.HSB, 255);
        float hue = mv.random(255);
        float saturation = 255;
        float brightness = PApplet.map(this.weight, 0, MAXIMUM_WEIGHT, 150, 255);
        this.color = mv.color(hue, saturation, brightness);
        

    }

    public void draw()
    {
        // calculting amplitude in order for stars to move dynamically with star_speed
        float amplitude = mv.getAmplitude();
        float star_speed = PApplet.map(amplitude, 0, 1, 0.5f, 5);
        x += mv.random(-star_speed, star_speed);
        y += mv.random(-star_speed, star_speed);



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





