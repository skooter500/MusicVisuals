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

    public void render(int phase)
    {

        if(mv.timeElapsed() > 235000)
            return; 

        float gap = mv.width / (float) mv.getBands().length;

        mv.noStroke();

        
        // mv.fill(PApplet.map(6, 0, mv.getBands().length, 255, 0), 255, 255);
        mv.fill(208, 152, 3);

        mv.rect(0, mv.height, 75, -mv.getSmoothedBands()[6] * 0.75f);  
        mv.rect(mv.width - 75, mv.height, 75,-mv.getSmoothedBands()[6] * 0.75f); 
        
        
        if(phase == 2)
        {
            mv.translate(0, 0, -200f);
            for(int i = 0 ; i < mv.getBands().length ; i ++)
            {   
                mv.translate(0, 0, -90f);
                mv.fill(69, 50, 1);
                mv.rect(i * gap, mv.height + 165, gap,-mv.getSmoothedBands()[i] * 0.4f); 
                mv.translate(0, 0, 90f);

                mv.translate(0, 0, -70f);
                mv.fill(115, 84, 1);
                mv.rect(i * gap, mv.height + 165, gap,-mv.getSmoothedBands()[i] * 0.3f); 
                mv.translate(0, 0, 70f);
                
                mv.fill(163, 120, 3);
                mv.rect(i * gap, mv.height + 125, gap,-mv.getSmoothedBands()[i] * 0.2f); 
            }
            mv.translate(0, 0, +200f);
        }

        mv.noFill(); 
        mv.stroke(208, 152, 3);
        mv.translate(0, +30, 0);
        
        if((mv.timeElapsed() > 63500 && mv.timeElapsed() < 71000) ||
           (mv.timeElapsed() > 104400 && mv.timeElapsed() < 120000))
        {
            if(lastIncrement == 0)
                lastIncrement = (mv.timeElapsed() + 5) / 10 * 5;
            
            
            if(mv.currentTime - lastIncrement > 500)
            {
                noCircles += 2;
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

        mv.translate(0, 0, -175f);
        for(int i = 0; i < noCircles; i++)
        {
            mv.circle(mv.width / 2, mv.height / 2, 380 + (mv.getSmoothedBands()[6] * (0.45f - (0.07f * i)))); 
        }

        mv.image(mv.justice, (mv.width/2) - 125, -100);
        mv.translate(0, -30, 175);
    }
}