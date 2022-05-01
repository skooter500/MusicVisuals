package c20362766;

import ie.tudublin.Visual;
import processing.core.*;

public class WaveForm7 extends Visual {

    float cy = 0;
    float cx = 0;
    float angle = 0;
    float angle2 = 0;
    float angle3 = 0;

    public void settings() {
        size(800, 600, P3D);
        println("CWD: " + System.getProperty("user.dir"));

    }

    public void setup()
    {
        cy = height / 2;
        cx = width / 2;

    }


    public void draw() {
        
        background(0);


        float c = map(getAmplitude(), 0, 1, 0, 255);

        lights();
        strokeWeight(2);
        stroke(c, 255, 255);
        noFill();

        angle += 0.01f;
        angle2 -= 0.02f;
        float s = 40 + (100 * getSmoothedAmplitude() * 10);
        float s1 = 20 + (100 * getSmoothedAmplitude() * 10);
        translate(width / 2, height / 2);

        // cubes
        rotateY(angle);
        rotateX(angle);
        box(s);

        rotateY(angle2);
        rotateX(angle2);
        box(s1);

        stroke(c, 25, 255);
        strokeWeight(3);
        noFill();
        lights();
        pushMatrix();

        camera(0, 0, 0, 0, 0, -1 * getSmoothedAmplitude(), 0, 1, 0);
        translate(0, 0, -200);
        angle = angle - (angle * getSmoothedAmplitude());
        rotateX(angle = angle + (angle * getSmoothedAmplitude()));
        rotateZ(angle = angle + (angle * getSmoothedAmplitude()));
        angle = angle - (angle * getSmoothedAmplitude());

        float boxSize = 25 + (200 * getSmoothedAmplitude());
        sphere(boxSize);
        for (int i = 0; i < getAudioBuffer().size(); i++) {
            // changes color to rainbow range
            stroke(PApplet.map(i, 0, getAudioBuffer().size(), 0, 255), 255, 255);
        }
        sphere(boxSize);
        popMatrix();
        angle3 += 0.01f;
    }

}