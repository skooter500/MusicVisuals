package ie.tudublin;

import C22533826.NoelsVisual;
import c22371846.PatricksVisuals;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Heartbeat extends Visual {

    int mode = 0;
    NoelsVisual noelsVisual;
    PatricksVisuals PatricksVisuals;
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    public void settings() {
        // size(800, 800, P3D);
        println("CWD: " + System.getProperty("user.dir"));
        fullScreen(P3D, SPAN);
    }

    public void keyPressed() {
		if (key >= '0' && key <= '9') 
        {
			mode = key - '0';
		}
		if (keyCode == ' ') {
            if (ap.isPlaying()) 
            {
                ap.pause();
            } 
            else 
            {
                ap.rewind();
                ap.play();
            }
        }
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
        PatricksVisuals = new PatricksVisuals();
    }

    public void draw() {
        switch (mode) {
            case 0:
                noelsVisual.render(this);
                break;
            case 2:
                PatricksVisuals.render(this);
                break;
            default:
                break;
        }
    }

}
