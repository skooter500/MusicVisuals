package c21430484;

import processing.core.*;

// This is an example of a visual that uses the audio bands
public class TrumpetBandVisual
{
    BensVisual mv;
    int noCircles; 
    long lastIncrement = 0; 

    public TrumpetBandVisual(BensVisual mv)
    {
        this.mv = mv; 
        this.noCircles = 1; 
        this.lastIncrement = 0;
    }

    public void render()
    {
        float gap = mv.width / (float) mv.getBands().length;
        mv.noStroke();

        
        // mv.fill(PApplet.map(6, 0, mv.getBands().length, 255, 0), 255, 255);
        mv.fill(208, 152, 3);
        
        mv.rect(0, mv.height, 75, -mv.getSmoothedBands()[6] * 0.75f); 
        mv.rect(mv.width - 75, mv.height, 75,-mv.getSmoothedBands()[6] * 0.75f); 

        mv.noFill(); 
        mv.stroke(208, 152, 3);
        mv.translate(0, +30, 0);
        

        // mv.rotateX(0.7f);
        mv.circle(mv.width / 2, mv.height / 2, 300 + (mv.getSmoothedBands()[6] * 0.35f)); 

        // System.out.println((mv.timeElapsed() + 5) / 10 * 5);
        

        
        // if(mv.timeElapsed() > 38700 && mv.timeElapsed() < 62300)
        // {
        //     noCircles = 2; 
        //     // mv.circle(mv.width / 2, mv.height / 2, 300 + (mv.getSmoothedBands()[6] * 0.4f)); 
        //     // mv.circle(mv.width / 2, mv.height / 2, 300 + (mv.getSmoothedBands()[6] * 0.45f)); 
        // }
        
        if((mv.timeElapsed() > 63500 && mv.timeElapsed() < 71000) ||
           (mv.timeElapsed() > 104400 && mv.timeElapsed() < 120000))
        {
            if(lastIncrement == 0)
                lastIncrement = (mv.timeElapsed() + 5) / 10 * 5;
            
            
            if(mv.currentTime - lastIncrement > 500)
            {
                noCircles++;
                lastIncrement = mv.currentTime;
            }
        }
        
        if((mv.timeElapsed() > 71000 && mv.timeElapsed() < 104400 ) ||
            mv.timeElapsed() > 120000)
        {
            if(noCircles > 1)
            {
                if(mv.currentTime - lastIncrement > 100)
                {
                    noCircles--;
                    lastIncrement = mv.currentTime;
                }
            }
        }

        for(int i = 0; i < noCircles; i++)
        {
            mv.circle(mv.width / 2, mv.height / 2, 300 + (mv.getSmoothedBands()[6] * (0.35f - (0.07f * i)))); 
        }

        mv.image(mv.justice, (mv.width/2) - 125, -40);
        mv.translate(0, -30, 0);
    }
}