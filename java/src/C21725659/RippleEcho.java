package C21725659;

import ie.tudublin.Visual;
import processing.core.PApplet;
import processing.core.PVector;

public class RippleEcho extends Visual {

    final int nb = 90;
    final int step = 8;
    final float DIST = 100;
    final float DISTORTION = 110;
    Part[][] parts = new Part[nb][nb];
    Boolean mode = true;

    public void settings() {
        size(800, 800, P3D);
        println("CWD: " + System.getProperty("user.dir"));
    }

   public  void setup() {
        
        setFrameSize(256);

        startMinim();
        loadAudio("InitialD-KillingMyLove.mp3");
        getAudioPlayer().play();
        // Set time to 1 minute
        getAudioPlayer().cue(60000);

        stroke(random(50, 150), random(50, 150), random(50, 150), 200);
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
        for (int i = 0; i < nb; i++) {
            for (int j = 0; j < nb; j++) {
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
            if (mode) {
                tmp.mult(DISTORTION * sin(c));
            }
            if (d < DIST) {
                strokeWeight(1 + 10 * abs(cos(c / 2)));
                if (!mode) {
                    tmp.mult(DISTORTION * sin(c));
                }
            } else {
                strokeWeight(map(min(d, width), 0f, width, 6f, (float) .3));
            }

            PVector target = new PVector(origin.x + tmp.x, origin.y + tmp.y);
            tmp = pos.get();
            tmp.sub(target);
            tmp.mult(-map(m.dist(pos), 0f, 2f * width, .1f, .01f));
            speed.add(tmp);
            speed.mult(.57f);
            pos.add(speed);

            point(pos.x, pos.y);
        }
    }

    public void  mousePressed() {
        mode = !mode;
    }

}
