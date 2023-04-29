package ie.tudublin;

// Dependencies
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import java.util.ArrayList;

// Visuals
import com.C21782059.visual1.Visual1;
import com.C21394933.visual2.StartMenuVisual;
import com.C21394933.visual2.Visual2;
import com.C21460524.visual3.Visual3;
import com.C21751999.visual4.Visual4;


public class MusicVisualizer extends PApplet {
    // Render Visuals
    // C21394933 (Ernest John Decina)
    StartMenuVisual startMenuVisual;
    Visual1 visual1;
    //
    Visual2 visual2;
    //
    Visual3 visual3;
    //
    Visual4 visual4;

    // Private Variables
    int windowHeight = 720;
    int windowWidth = 1080;
    ArrayList<VisualAbstractClass> visualList;

    Minim minim;
    AudioInput audioInput;
    AudioPlayer audioPlayer;
    AudioBuffer audioBuffer;

    int frameSize = 1024;
    int sampleRate = 96000; // 44100;
    int bitDepth = 16;
    
    float lerpedR = 0;
    int[] timings = {5, 667, 1075, 1328, 1868, 2262, 5000};
    public static int timingsCounter = 0;
    int currentTime = 0;


    public MusicVisualizer() {
        visualList = new ArrayList<VisualAbstractClass>();
    } // End MusicVisualizer

    public void settings() {
        size(windowWidth, windowHeight, P3D);
    } // End void settings()

    public void setup() {
        colorMode(RGB);
  
        frameRate(60);
        loadSong();
        loadVisuals();
    } // End void setup()

    public void draw() {
        background(0);
        playVisuals();
    } // End void draw()


    // Onload Functions
    private void loadSong() {
        // Initialize minim
        this.minim = new Minim(this);
        this.audioPlayer = minim.loadFile("songs/somethingComforting.mp3", 2048); 
        this.audioBuffer = audioPlayer.mix;
        // this.audioPlayer.play();
        // Utils.skipSecondsSong(audioPlayer, 132.8f);
    } // End void loadSong()

    private void loadVisuals() {
        this.startMenuVisual = new StartMenuVisual(this, this.audioBuffer, this.audioPlayer, this.windowWidth, this.windowHeight);
        this.visual2 = new Visual2(this, this.audioBuffer, this.audioPlayer, this.windowWidth, this.windowHeight);
        this.visual3 = new Visual3(this, this.audioBuffer, this.audioPlayer, this.windowWidth, this.windowHeight);
        this.visual1 = new Visual1(this, this.audioBuffer, this.audioPlayer, this.windowWidth, this.windowHeight);
        this.visual4 = new Visual4(this, this.audioBuffer, this.audioPlayer, this.windowWidth, this.windowHeight);

        visualList.add(startMenuVisual);
        visualList.add(visual3);
        visualList.add(visual2);
        visualList.add(visual4);
        visualList.add(visual1);
        visualList.add(visual1);
        visualList.add(visual1);
        visualList.add(visual1);
    } // End void loadVisuals

    private void playVisuals() 
    {
        currentTime = audioPlayer.position();
        
        System.out.println((float)currentTime / 100);
        if(currentTime / 100 > timings[timingsCounter]) 
            timingsCounter++;
        
        visualList.get(timingsCounter).drawVisual();

    } // End void playVisual

} // End class MusicVisualizer
