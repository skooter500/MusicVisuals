package ie.tudublin;

import C21325616.MichaelsVisuals;
import C22533826.NoelsVisual;
import C22533826.menuScreen;
import c22371846.PatricksVisual;
import C22328351.LarinasVisual;

public class Heartbeat extends Visual {

    int mode = 0;
    NoelsVisual noelsVisual;
    MichaelsVisuals michaelsVisuals;
    LarinasVisual LarinasVisual;
    PatricksVisual patricksVisuals;
    menuScreen MenuScreen;

    public void settings() {
        println("CWD: " + System.getProperty("user.dir"));
        fullScreen(P3D, SPAN);
    }

    public void setup() {
        colorMode(HSB);
        setFrameSize(512);
        startMinim();
        loadAudio("/Users/michaelferents/Desktop/OOPAssignment/MusicVisuals/java/data/Heartbeat.mp3");
        getAudioPlayer().play();
        // startListening();
        // noCursor();

        noelsVisual = new NoelsVisual(this);
        michaelsVisuals = new MichaelsVisuals(this);
        patricksVisuals = new PatricksVisual(this);
        LarinasVisual = new LarinasVisual(this);
        MenuScreen = new menuScreen(this);

    }

    public void draw() {
        switch (mode) {

            
            case 0:
                getAudioPlayer().pause();
                MenuScreen.renderMenu();
                break;

            case 1:
                getAudioPlayer().play();
                noelsVisual.renderScene();
                break;
            case 2:
                getAudioPlayer().play();
                noelsVisual.resetCamera();
                noelsVisual.setDefaultCamera();
                patricksVisuals.renderAnimation();
                break;
            case 3:
                getAudioPlayer().play();
                michaelsVisuals.renderVisualOne();
                break;
            case 4:
                getAudioPlayer().play();
                noelsVisual.resetCamera();
                noelsVisual.setDefaultCamera();
                LarinasVisual.draw();
                break;
            case 5:
                noelsVisual.resetCamera();
                noelsVisual.setDefaultCamera();
                patricksVisuals.renderGraph();

                michaelsVisuals.renderVisualTwo();
                break;
            case 5:
                getAudioPlayer().play();
                noelsVisual.resetCamera();
                noelsVisual.setDefaultCamera();
                LarinasVisual.render();
                break;
            case 6:
                // Addtional renderings...

                break;
            default:
                break;
        }
    }

    public void keyPressed() {
        // Pauses playback of the song
        if (key == ' ') {
            if (getAudioPlayer().isPlaying()) {
                getAudioPlayer().pause();
            } else {
                getAudioPlayer().play();
            }
        }

        // Uses mode variable to switch between visuals
        if (keyCode >= '0' && keyCode <= '9') {
            mode = keyCode - '0';
        }

        // Restarts the song
        if (key == 'r') {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
    }

}