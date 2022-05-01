package c20362766;

import ie.tudublin.Visual;

public class ColourCircleVisual extends Visual {

    HabeebsVisuals mv;

    public void settings() {
        println("CWD: " + System.getProperty("user.dir"));
        fullScreen(P3D, SPAN);
    }

    public ColourCircleVisual(HabeebsVisuals mv) {
        this.mv = mv;
    }

    public void circleBall() {
        int numCircles = 22;
        int Mwidth = 140;

        float c = mv.map(mv.getAmplitude(), 0, 1, 0, 255);
        // circles
        for (int i = 0; i < numCircles; i++) {
            mv.stroke(c * i, 255, 255);
            mv.strokeWeight(2);
            mv.noFill();
            mv.ellipse(mv.width / 2, mv.height / 2, Mwidth + (mv.getSmoothedAmplitude() * 400),
                    Mwidth + (mv.getSmoothedAmplitude() * 400));
            Mwidth += 70;
        }

    }

    public void render() {
        mv.background(0, 0, 0);

        circleBall();

    }
}