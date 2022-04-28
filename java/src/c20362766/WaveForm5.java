package c20362766;

import processing.core.PApplet;
// This is an example of a visual that renders the waveform
public class WaveForm5 {
    HabeebsVisuals mv;
    float cy = 0;
    float cx = 0;

    public WaveForm5(HabeebsVisuals mv) {
        this.mv = mv;
        cy = this.mv.height / 2;
        cx = this.mv.width / 2;
    }

    public void TheFlash() {

        int radius = 300;

        mv.stroke(0, 255, 255);

        // Bright red
        mv.fill(0, 255, 0);
        mv.circle(cx, cy, radius);

        // changes color to rainbow range
        mv.line(cx - 30, cy, cx + 30, cy);
        mv.line(cx - 30, cy, cx + 40, cy - 65);
        mv.line(cx + 30, cy, cx - 40, cy + 65);

        
        // generate (i) random points on circle
        var angle = Math.random() * Math.PI *2  ;
        float x =  (float) (Math.cos(angle) * radius);
        float y =  (float) (Math.sin(angle) * radius);

        mv.line(cx+radius, cy+radius, cx + x, cy + y);

    }

    public void render() {
        mv.colorMode(PApplet.HSB);

        TheFlash();
    }

    
    
        
    
}