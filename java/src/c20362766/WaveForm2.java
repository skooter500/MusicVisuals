package c20362766;

import ie.tudublin.Visual;
import processing.core.*;

// This is an example of a visual that renders the waveform
public class WaveForm2 extends Visual {
    HabeebsVisuals mv;
    float cy = 0;
    float cx = 0;
    float prevMouseVal;

    public WaveForm2(HabeebsVisuals mv) {
        this.mv = mv;
        cy = this.mv.height / 2;
        cx = this.mv.width / 2;
        prevMouseVal = this.mv.height - (this.mv.height / 4);
    }

    public void barsAndSliders() {

        mv.stroke(118, 218, 193);

        // vertical line
        mv.line(this.mv.width / 7, this.mv.height - (this.mv.height / 4), this.mv.width / 7, (this.mv.height / 4));

        // horizontal line 
        mv.line((this.mv.width / 4), this.mv.height - (this.mv.height / 3), this.mv.width - (this.mv.width / 6)-16, this.mv.height - (this.mv.height / 3) );


        mv.circle(this.mv.width / 7, prevMouseVal, 30);

        mv.textSize(20);
        mv.textMode(LEFT);
        mv.text("The Difference - Flume feat. Toro y Moi", (this.mv.width / 4), this.mv.height - (this.mv.height / 4));
        
        mv.textMode(CENTER);
        mv.text("Amplitude Sensitivity", this.mv.width / 18,(this.mv.height / 5));

        if ((mv.mousePressed == true) & (mv.mouseY > (this.mv.height / 4)) & (mv.mouseY < this.mv.height - (this.mv.height / 4))) {

            prevMouseVal = this.mv.mouseY;

        } 

        mv.colorMode(PApplet.HSB);

        

        float gap =(this.mv.height - (this.mv.height / 3) -(this.mv.width / 4) ) / ((float) mv.getBands().length) *2;
        mv.noStroke();
        for(int i = 0 ; i < mv.getBands().length ; i ++)
        {
            mv.fill(PApplet.map(i, 0, mv.getBands().length, 255, 0), 255-prevMouseVal/5,255);
            mv.rect((this.mv.width / 4) + i * gap, this.mv.height - (this.mv.height / 3), gap ,-mv.getSmoothedBands()[i] * (10000.0f / prevMouseVal)); 
        }



    }

    public void render() {

        mv.colorMode(PApplet.HSB);

        barsAndSliders();
    }
}