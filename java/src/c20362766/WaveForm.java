package c20362766;

import processing.core.*;

// This is an example of a visual that renders the waveform
public class WaveForm {
    HabeebsVisuals mv;
    float cy = 0;
    float cx = 0;

    public WaveForm(HabeebsVisuals mv) {
        this.mv = mv;
    }

    public void cubeEyes() {
        mv.background(0);

        cy = this.mv.height / 2;
        cx = this.mv.width / 2;

        System.out.println(mv.getAmplitude());
        // border squares
        for (int i = 0; i < this.mv.width; i++) {

            mv.stroke(118, 218, 193);
            mv.fill(118, 100);

            if (i % 60 == 0) {
                mv.rect(i, 0, 35 + 35 * (mv.getSmoothedAmplitude() * 700),
                        100 + 100 * (mv.getSmoothedAmplitude() * 700));

                mv.rect(i, this.mv.height, 35 + 35 * (mv.getSmoothedAmplitude() * 700),
                        -100 + -100 * (mv.getSmoothedAmplitude() * 700));

            }
            if (i % 35 == 0) {
                mv.rect(i, 0, 35 + 35 * (mv.getSmoothedAmplitude() * 700),
                        100 + 50 * (mv.getSmoothedAmplitude() * 800));

                mv.rect(i, this.mv.height, 35 + 35 * (mv.getSmoothedAmplitude() * 700),
                        -100 + -40 * (mv.getSmoothedAmplitude() * 800));
            }
        }

        for (int i = 0; i < this.mv.height; i++) {

            mv.stroke(118, 218, 193);

            mv.fill(100, 100);

            if (i % 60 == 0 || i == 0) {

                mv.rect(0, i, 100 + 100 * (mv.getSmoothedAmplitude() * 700),
                        35 + 35 * (mv.getSmoothedAmplitude() * 700));
                mv.rect((this.mv.width - 100) + -100 * (mv.getSmoothedAmplitude() * 700),
                        (i + 35) + 35 * (mv.getSmoothedAmplitude() * 700), this.mv.width, -35);

                // mv.circle(this.mv.width - 100, i + 35 , 35);
                // mv.circle(this.mv.width, i, 35);

            }

            if (i % 35 == 0 || i == 0) {

                mv.rect(0, i, 100 + 50 * (mv.getSmoothedAmplitude() * 700),
                        35 + 35 * (mv.getSmoothedAmplitude() * 700));
                mv.rect((this.mv.width - 100) + -45 * (mv.getSmoothedAmplitude() * 700),
                        (i + 35) + 20 * (mv.getSmoothedAmplitude() * 700), this.mv.width, -35);

            }

        }

        // eye
        mv.ellipse(cx, cy, 400 + 400 * (mv.getSmoothedAmplitude() * 700),
                175 + 175 * (mv.getSmoothedAmplitude() * 700));
        mv.circle(cx, cy, 175 + 175 * (mv.getSmoothedAmplitude() * 700));
        mv.circle(cx, cy, 12 + 12 * (mv.getSmoothedAmplitude() * 700));

        // upper eyelid
        mv.line(cx + 20, cy - 90, cx + 40, cy - 190);
        mv.line(cx - 20, cy - 90, cx - 40, cy - 190);

        // bottom eyelid
        mv.line(cx + 20, cy + 90, cx + 40, cy + 190);
        mv.line(cx - 20, cy + 90, cx - 40, cy + 190);

    }

    public void render() {
        cubeEyes();

    }
}