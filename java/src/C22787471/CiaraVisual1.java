package C22787471;

import processing.core.*;
import ddf.minim.*;
import ie.tudublin.OurVisual;

public class CiaraVisual1
{
    OurVisual ov;
    float cy = 0;


    // public WaveForm(MyVisual mv)
    public CiaraVisual1(OurVisual ov)
    {
        this.ov = ov;
        cy = this.ov.height / 2;

    }

    public void render()
    {
        ov.colorMode(PApplet.HSB);
        for(int i = 0 ; i < ov.getAudioBuffer().size() ; i ++)
        {
            ov.stroke(
                PApplet.map(i, 0, ov.getAudioBuffer().size(), 0, 255)
                , 255
                , 255
            );

            float y1 = cy + cy * ov.getAudioBuffer().get(i); // Calculating y1
            float cy1 = i; // Setting cx1 to the current index

            ov.ellipse(i, cy, i, cy + cy * ov.getAudioBuffer().get(i));

            ov.strokeWeight(10);
            //ov.pushMatrix(); // Save the current transformation matrix
            ov.translate(cy1, y1); // Move the origin to the current position

            ov.rotate(ov.radians(ov.random(360)));      
            //ov.popMatrix(); // Restore the original transformation matrix

            ov.rotate(ov.radians(ov.random(360))); // Rotate the line with a random angle
            ov.line(0, 0, 300, 300); // Draw the line
            //ov.popMatrix(); // Restore the original transformation matrix
     
            ov.fill(0); // Fill the shapes with black

        }
    }
}


