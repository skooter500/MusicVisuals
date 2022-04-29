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
        mv.line((this.mv.height / 3), this.mv.height - (this.mv.height / 3), this.mv.width - (this.mv.width / 6),this.mv.height - (this.mv.height / 3) );


        mv.circle(this.mv.width / 7, prevMouseVal, 30);

        mv.textSize(20);
        mv.textMode(LEFT);
        mv.text("The Difference - Flume feat. Toro y Moi", (this.mv.height / 3), this.mv.height - (this.mv.height / 4));

        if ((mv.mousePressed == true) & (mv.mouseY > (this.mv.height / 4)) & (mv.mouseY < this.mv.height - (this.mv.height / 4))) {

            prevMouseVal = this.mv.mouseY;

        } 

        mv.colorMode(PApplet.HSB);

        for(int i = (this.mv.height / 3) ; i < mv.getAudioBuffer().size() ; i ++)
        {
            mv.stroke(PApplet.map(i, 0, mv.getAudioBuffer().size(), 0, 255), 255, 255);

            mv.line(i, this.mv.height - (this.mv.height / 3), i, (this.mv.height - (this.mv.height / 3)) + (this.mv.height - (this.mv.height / 3)) * mv.getAudioBuffer().get(i));
        }



    }

    public void render() {

        mv.colorMode(PApplet.HSB);

        barsAndSliders();
    }
}