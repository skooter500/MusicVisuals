package C21725659;

import ddf.minim.AudioInput;
import ddf.minim.analysis.BeatDetect;
import ie.tudublin.Visual;
import processing.core.PApplet;
import processing.core.PVector;

public class RippleEcho extends Visual {

    final int nb = 90;
    final int step = 8;
    final float DIST = 100;
    float DISTORTION = 110;
    Part[][] parts = new Part[nb][nb];
    Boolean mode = true;
    BeatDetect beat;
    float beatValue = 1;

    public void settings() {
        size(800, 800, P3D);
        println("CWD: " + System.getProperty("user.dir"));
    }

    public void setup() {

        setFrameSize(256);

        startMinim();
        loadAudio("InitialD-KillingMyLove.mp3");
        getAudioPlayer().play();
        getAudioPlayer().cue(60000);

        beat = new BeatDetect();

        int dx = (width - nb * step) / 2;
        for (int i = 0; i < nb; i++) {
            for (int j = 0; j < nb; j++) {
                parts[i][j] = new Part(i * step + dx, j * step + dx);
            }
        }
    }

    public void draw() {
        background(255);
        PVector m = new PVector(mouseX, mouseY);

        beat.detect(getAudioPlayer().mix);
        if (beat.isOnset()) {
            beatValue = 1;
        } else {
            beatValue *= 0.98;
        }

        for (int i = 0; i < nb; i++) {
            for (int j = 0; j < nb; j++) {
                if (beat.isOnset()) {
                    stroke(random(50, 150), random(50, 150), random(50, 150), 200);
                }
                parts[i][j].update(m);
            }
        }
    }

    class Part {
        PVector pos, speed, origin;

        Part(int x, int y) {
            pos = new PVector(x, y);
            origin = pos.get();
            speed = new PVector(0, 0);
        }

        void update(PVector m) {
            PVector tmp = origin.get();
            tmp.sub(m);
            float d = tmp.mag();
            float c = map(d, 0, DIST, 0, PI);
            tmp.normalize();

            PVector beatMovement = tmp.copy();
            if (mode) {
                beatMovement.mult(DISTORTION * sin(c) * beatValue);
            }

            PVector mouseMovement = tmp.copy();
            if (d < DIST) {
                strokeWeight(1 + 10 * abs(cos(c / 2)));
                if (!mode) {
                    mouseMovement.mult(DISTORTION * sin(c) * beatValue);
                }
            } else {
                strokeWeight(map(min(d, width), 0f, width, 6f, (float) .3));
            }

            // Calculate the weighted average of beat and mouse movement
            PVector weightedMovement = PVector.add(beatMovement.mult(0.7f), mouseMovement.mult(0.3f));

            PVector target = PVector.add(origin, weightedMovement);
            tmp = pos.get();
            tmp.sub(target);
            tmp.mult(-map(m.dist(pos), 0f, 2f * width, .1f, .01f));
            speed.add(tmp);
            speed.mult(.57f);
            pos.add(speed);
            point(pos.x, pos.y);
        }
    }

    public void mousePressed() {
        mode = !mode;
    }
}
