package ie.tudublin;

// Dependencies
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

// Visuals
import com.C21782059.visual1.Visual1;
import com.C21394933.visual2.Visual2;
import com.C21460524.visual3.Visual3;
import com.C21751999.visual4.Visual4;


public class MusicVisualizer extends PApplet {
    // Render Visuals
    // C21394933 (Ernest John Decina)
    Visual1 visual1;
    Visual2 visual2;
    Visual3 visual3;
    Visual4 visual4;

    // Private Variables
    int windowHeight = 720;
    int windowWidth = 1080;

    Minim minim;
    AudioInput audioInput;
    AudioPlayer audioPlayer;
    AudioBuffer audioBuffer;

    int frameSize = 1024;
    int sampleRate = 96000; // 44100;
    int bitDepth = 16;
    float lerpedR = 0;

    public void settings() {
        size(windowWidth, windowHeight, P3D);
    } // End void settings()

    public void setup() {
        colorMode(HSB);
        loadSong();
        loadVisuals();
    } // End void setup()

    public void draw() {
        background(0);
        // this.visual1.drawVisual1();
        // this.visual2.drawVisual2();
        // this.visual3.drawVisual3();
        // this.visual4.drawVisual4();
    } // End void draw()


    // Onload Functions
    private void loadSong() {
        // Initialize minim
        this.minim = new Minim(this);
        this.audioPlayer = minim.loadFile("songs/somethingComforting.mp3", 1024); // minim.getLineIn(Minim.MONO, frameSize, sampleRate, bitDepth); // minim.loadFile("shelter.mp3", 1024); // minim.getLineIn(Minim.MONO, frameSize, sampleRate, bitDepth);// minim.loadFile("shelter.mp3", 1024); // minim.getLineIn(Minim.MONO, frameSize, sampleRate, bitDepth); // minim.loadFile("shelter.mp3", 1024); // minim.getLineIn(Minim.MONO, frameSize, sampleRate, bitDepth);
        this.audioPlayer.play();
        this.audioBuffer = audioPlayer.mix;
    } // End void loadSong()

    private void loadVisuals() {
        // Refresh Frame
        // background(0);
        this.visual1 = new Visual1(this, this.audioBuffer, this.windowWidth, this.windowHeight);
        this.visual2 = new Visual2(this, this.audioBuffer, this.windowWidth, this.windowHeight);
        this.visual3 = new Visual3(this, this.audioBuffer, this.windowWidth, this.windowHeight);
        this.visual4 = new Visual4(this, this.audioBuffer, this.windowWidth, this.windowHeight);
    } // End void loadVisuals

} // End class MusicVisualizer
