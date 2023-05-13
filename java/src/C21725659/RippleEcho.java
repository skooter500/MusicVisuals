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
    part[][] parts = new part[nb][nb];
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
                parts[i][j] = new part(i * step + dx, j * step + dx, DIST);
            }
        }
    }

    public void draw() {
        background(0);
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
                parts[i][j].update(m, mode, DISTORTION, beatValue, width, this);
            }
        }
    }

    public void mousePressed() {
        mode = !mode;
    }
}
