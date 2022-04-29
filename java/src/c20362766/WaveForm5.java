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
        mv.background(0);
        mv.loadPixels();
        float n = (float) ((mv.getAmplitude() * 10000.0) / mv.width);

        float w = (float) 16.0;
        float h = (float) 16.0;
        float dx = w / mv.width;
        float dy = h / mv.height;
        float x = -w / 2;

        for (int i = 0; i < mv.width; i++) {
            float y = -h / 2;

            for (int j = 0; j < mv.height; j++) {

                float r = mv.sqrt((x * x) + (y * y));
                float theta = mv.atan2(y, x);
                float val = mv.sin(n * mv.cos(r) + 5 * theta);

                mv.pixels[i + j * mv.width] = mv.color((int) ((val + mv.getAmplitude()-200) * 255.0));
                y += dy;
            }
            x += dx;
        }
        mv.updatePixels();
    }

    public void render() {

        TheFlash();
    }

}