package C21725659;
import java.util.ArrayList;

import Global.*;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;

public class TestingSubtitles extends Visual {

    int currentTime = 0;
    boolean isPlaying = false;
    SubtitleHandler subtitleHandler;
    ArrayList<Subtitle> subtitles;
    int currentSubtitleIndex = 0;
    int previousSubtitleIndex = -1;

    public void settings()
    {
        size(800, 800, P3D);
        println("CWD: " + System.getProperty("user.dir"));
    }

    public void keyPressed()
    {
        if (key == ' ')
        {
            currentTime = getAudioPlayer().position();
            if(isPlaying){
                getAudioPlayer().pause();
                getAudioPlayer().cue(currentTime);
                isPlaying = false;
            }
            else{
                getAudioPlayer().play();
                getAudioPlayer().cue(currentTime);
                isPlaying = true;
            }
            
        }
        if(key == 'r'){
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
    }

    public void setup()
    {
        colorMode(HSB);
        noCursor();
        
        SubtitleHandler subtitleHandler = new SubtitleHandler(this);
        subtitles =  subtitleHandler.parseSrt("InitialD-KillingMyLove.mp3.srt");
        
        setFrameSize(256);

        startMinim();
        loadAudio("InitialD-KillingMyLove.mp3");
        getAudioPlayer().play();
        // Set time to 1 minute
        getAudioPlayer().cue(60000);
        //startListening(); 
        
    }

    public void RotatingAudioCube(){
         calculateAverageAmplitude();
        try
        {
            calculateFFT();
        }
        catch(VisualException e)
        {
            e.printStackTrace();
        }
        calculateFrequencyBands();
        background(0);
        noFill();
        stroke(255);
        lights();
        stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        camera(0, -500, 500, 0, 0, 0, 0, 1, 0);
        //translate(0, 0, -250);

        rot += getAmplitude() / 8.0f;

        rotateY(rot);
        float[] bands = getSmoothedBands();
        for(int i = 0 ; i < bands.length ; i ++)
        {
            float theta = map(i, 0, bands.length, 0, TWO_PI);

            stroke(map(i, 0, bands.length, 0, 255), 255, 255);
            float x = sin(theta) * radius;
            float z = cos(theta) * radius;
            float h = bands[i];
            pushMatrix();
            translate(x, - h / 2 , z);
            rotateY(theta);
            box(50, h, 50);
            popMatrix();
        }
    }

    float radius = 200;

    float smoothedBoxSize = 0;

    float rot = 0;
    public void displayCurrentSubtitle() {
        int playPosition = getAudioPlayer().position();
        int totalTimeInSeconds = playPosition / 1000;
        int subtitleIndex = -1;
    
        for (int i = 0; i < subtitles.size(); i++) {
            Subtitle subtitle = subtitles.get(i);
            if (totalTimeInSeconds >= subtitle.getStart() / 1000.0f && totalTimeInSeconds <= subtitle.getEnd() / 1000.0f) {
                subtitleIndex = i;
                break;
            }
        }
    
        if (subtitleIndex != currentSubtitleIndex) {
            currentSubtitleIndex = subtitleIndex;
        }
    
 
    
         if (currentSubtitleIndex >= 0 && currentSubtitleIndex < subtitles.size()) {
        // Display the subtitle text on the screen
        String currentSubtitle = subtitles.get(currentSubtitleIndex).getText();
        textAlign(CENTER);
        // Black text
        fill(0);
        textSize(32);
        text(currentSubtitle, 0, -300);
        println("Current subtitle: " + currentSubtitle);
    }
    }
    
    
    

    public void draw()
    {
        // Print subtitles
        displayCurrentSubtitle();

       
    }
    float angle = 0;

}