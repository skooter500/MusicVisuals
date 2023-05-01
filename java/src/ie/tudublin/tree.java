package ie.tudublin;

import processing.core.PApplet;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class tree extends PApplet {

    Minim minim;
    AudioInput ai;
    AudioPlayer ap;
    AudioBuffer ab;
    float branchAngle = 0;
    float branchAngleTarget = 0;
    float branchAngleChangeRate = 0.75f; // Adjust this value for wider or narrower angles
    int maxDepth = 7;

    public void settings() {
        size(500, 500);
    }

    public void setup() {
        minim = new Minim(this);

        ap = minim.loadFile("strawberry_fields_forever.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);
    }

    public void branch(float len, int depth, float hue, float thickness) {
        branchAngle = lerp(branchAngle, branchAngleTarget, branchAngleChangeRate);
        float saturation = map(depth, 0, maxDepth, 255, 100);
        float brightness = map(depth, 0, maxDepth, 255, 200);
        stroke(hue, saturation, brightness);
        strokeWeight(thickness); // Set stroke weight based on thickness
        line(0, 0, 0, -len);
        translate(0, -len);

        if (depth < maxDepth) {
            pushMatrix();
            rotate(branchAngle);
            branch(len * 0.75f, depth + 1, hue + 10, thickness * 0.7f); // Decrease thickness for thinner branches
            popMatrix();

            pushMatrix();
            rotate(-branchAngle);
            branch(len * 0.75f, depth + 1, hue + 10, thickness * 0.7f); // Decrease thickness for thinner branches
            popMatrix();
        }
    }

    public void draw() {
        background(155, 206, 200);
        float c = map(0, 0, ab.size(), 0, 255);
        stroke(c, 255, 255);
        translate(250, height);

        float audioValue = ab.get(0);
        branchAngleTarget = map(audioValue, 0, 1, -PI / 12, PI / 12); // Update minimum angle to -PI/12 and PI/12
        branch(100, 0, 25, 20); // Initial hue for brown color, initial thickness of 20
    }
}
