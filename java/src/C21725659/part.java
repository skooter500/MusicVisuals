package C21725659;

import processing.core.PApplet;
import processing.core.PVector;

public class part {
    PVector pos, speed, origin;
    final float DIST;
    float DISTORTION;
    
    part(int x, int y, float dist) {
        pos = new PVector(x, y);
        origin = pos.get();
        speed = new PVector(0, 0);
        DIST = dist;
    }

    void update(PVector m, Boolean mode, float distortion, float beatValue, float width, PApplet p) {
        PVector tmp = origin.get();
        tmp.sub(m);
        float d = tmp.mag();
        float c = PApplet.map(d, 0, DIST, 0, PApplet.PI);
        tmp.normalize();

        PVector beatMovement = tmp.copy();
        if (mode) {
            beatMovement.mult(distortion * PApplet.sin(c) * beatValue);
        }

        PVector mouseMovement = tmp.copy();
        if (d < DIST) {
            p.strokeWeight(1 + 10 * PApplet.abs(PApplet.cos(c / 2)));
            if (!mode) {
                mouseMovement.mult(distortion * PApplet.sin(c) * beatValue);
            }
        } else {
            p.strokeWeight(PApplet.map(PApplet.min(d, width), 0f, width, 6f, (float) .3));
        }

        // Calculate the weighted average of beat and mouse movement
        PVector weightedMovement = PVector.add(beatMovement.mult(0.7f), mouseMovement.mult(0.3f));

        PVector target = PVector.add(origin, weightedMovement);
        tmp = pos.get();
        tmp.sub(target);
        tmp.mult(-PApplet.map(m.dist(pos), 0f, 2f * width, .1f, .01f));
        speed.add(tmp);
        speed.mult(.57f);
        pos.add(speed);
        p.point(pos.x, pos.y);
    }
}
