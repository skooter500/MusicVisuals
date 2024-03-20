package ie.tudublin;

import C22533826.NoelsVisual;
import processing.core.PApplet;

public class Heartbeat extends Visual {

    int mode = 0;
    NoelsVisual noelsVisual;

    public void settings() {
        // size(800, 800, P3D);
        println("CWD: " + System.getProperty("user.dir"));
        fullScreen(P3D, SPAN);
    }

    public void setup() {
        colorMode(HSB);
        // noCursor();
        setFrameSize(256);
        startMinim();
        loadAudio("Heartbeat.mp3");
        getAudioPlayer().play();
        // startListening();
        noelsVisual = new NoelsVisual();
    }

    public void draw() {
        switch (mode) {
            case 0:
                noelsVisual.render(this);
                break;
            case 2:
                // PatricksVisuals.render();
                break;
            default:
                break;
        }
    }

}
