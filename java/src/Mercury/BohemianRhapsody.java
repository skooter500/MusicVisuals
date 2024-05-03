package Mercury;

import ie.tudublin.Visual;
import C22739165.Alex;
import C22410766.AaronVisual;
import C22410766.start;
import C22483302.SergeiAwesomeVisual;
import C22483302.SergeiAwesomeVisual2;

public class BohemianRhapsody extends Visual {
    int mode = 1;
    Alex alex;
    AaronVisual aaron;
    SergeiAwesomeVisual sergei;
    SergeiAwesomeVisual2 sergei2;
    start start1;

    public void settings() {
        //size(800, 600, P3D);
        fullScreen(P3D, SPAN);
    }

    public void setup() {
        startMinim();
        // Make sure the path is correct. It assumes the file is in the data folder.
        loadAudio("java/data/Section3.mp3");
        getAudioPlayer().play();  // Start playing the audio for debugging
        colorMode(HSB);
        alex = new Alex(this); // Pass 'this' as the PApplet context
        aaron = new AaronVisual(this);
        sergei = new SergeiAwesomeVisual(this);
        sergei2 = new SergeiAwesomeVisual2(this);
        start1 = new start(this);
    }

    public void keyPressed() {
        if (key == 'p') {  // Press 'p' to play/pause for testing
            if (getAudioPlayer().isPlaying()) {
                getAudioPlayer().pause();
            } else {
                getAudioPlayer().play();
            }
        }
        if (key >= '0' && key <= '9') {
            mode = key - '0';
        }
    }

    public void draw() {
        switch (mode) {
            case 0:
                alex.draw();
                break;
            case 1:
                aaron.draw();
                break;
            case 2:
                sergei.draw();
                break;
            case 3:
                sergei2.draw();
                break;
            case 4:
                start1.draw();
                break;
            default:
                background(0); // Default background
                break;
        }
    }
}
