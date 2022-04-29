package c20362766;

import ie.tudublin.Visual;
import processing.core.*;

// This is an example of a visual that renders the waveform
public class WaveForm6 extends Visual {
    HabeebsVisuals mv;

    float cy = 0;
    float cx = 0;

    public WaveForm6(HabeebsVisuals mv) {
        this.mv = mv;
        cy = this.mv.height / 2;
        cx = this.mv.width / 2;
    }

    public void gridOfDots() {

        int gridSize = 40;

        for (int x = gridSize; x <= this.mv.width - gridSize; x += gridSize) {
            for (int y = gridSize; y <= this.mv.height - gridSize; y += gridSize) {
                mv.noStroke();
                mv.stroke(PApplet.map(x, 0, mv.getBands().length, 255, 0), 255 * this.mv.getAmplitude(),
                        255 * this.mv.getAmplitude() / 4);
                mv.rect(x - 1, y - 1, 3, 3);
                mv.line(x, y, mv.mouseX, mv.mouseY);
            }
        }

    }

    public void render() {

        gridOfDots();
    }
}