package c20362766;

import processing.core.*;

// This is an example of a visual that renders the waveform
public class WaveForm
{
    HabeebsVisuals mv;
    float cy = 0;
    float cx = 0;
    

    public WaveForm(HabeebsVisuals mv)
    {
        this.mv = mv;
    }

    public void cubeEyes(){
        mv.background(0);

        cy = this.mv.height / 2;
        cx = this.mv.width / 2;

        // border squares
        for (int i = 0; i < this.mv.width; i++) {
            
            mv.stroke(118, 218, 193);
            mv.fill(118, 100);

            if (i % 60 == 0){
                mv.rect(i, 0, 35, 100);

                mv.rect(i, this.mv.height, 35, -100 );
            }
        }



        for (int i = 0; i < this.mv.height; i++) {
            
            mv.stroke(118, 218, 193);
            
            mv.fill(100, 100);

            if (i % 60 == 0 || i == 0){
     
                
                
                

                mv.rect(0, i, 100, 35);
                mv.rect(this.mv.width - 100, i + 35, this.mv.width, -35);

                //mv.circle(this.mv.width - 100, i + 35 , 35);
                //mv.circle(this.mv.width, i, 35);

                

            }
        }

    }



    
    public void render()
    {
        cubeEyes();
        
    }
}