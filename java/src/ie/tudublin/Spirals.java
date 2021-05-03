package ie.tudublin;


import c19444404.RyansVisual;
import processing.core.*;

public class Spirals extends Vision {

    public Spirals( RyansVisual rv)
    {
        super(rv);
    }

    public void render()
    {
        float rad = 2f; // the float size for the spiral
        int numPoints = 6;
        float point = PApplet.TWO_PI / (float) numPoints;
        rv.strokeWeight(7);                
        float x1 = rv.width / 2, y1 = rv.height / 2;
        for(int i = 0 ; i < 100 ; i ++)
        {
            for (int j=0; j< 10 ; j++)
            {
            float c = PApplet.map(i, 0, 300, 0, 255) % 255.0f;
            rv.stroke(c, 255, 255, 255);
            float theta = i * (point + rv.getSmoothedAmplitude() * 5);
            float x = rv.width / 2 + PApplet.sin(theta) * rad; // the rotation x
            float y = rv.height / 2 - PApplet.cos(theta) * rad;// rotation y
            rad += 0.5f + rv.getSmoothedAmplitude(); // the rotation movement
            rv.line(x1, y1, x, y);// the lines mapped
            x1 = x;//declaration
            y1 = y;
            }
        }
    }
    
}
