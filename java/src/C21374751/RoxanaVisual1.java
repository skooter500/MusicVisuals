package C21374751;

import ie.tudublin.Visual;

public class RoxanaVisual1 extends Visual
{
    private int startTime;
    private int endTime;
    
    public void settings()
    {
        size(800, 800, P3D);
        println("CWD: " + System.getProperty("user.dir"));
        //fullScreen(P3D, SPAN);
    }

    public void keyPressed()
    {
        if (key == ' ')
        {
            getAudioPlayer().cue(43);
            getAudioPlayer().play();
            startTime = millis(); // Record start time
            endTime = startTime + 27 * 1000; // Set end time to 1 minute 10 seconds 
        }
    }

    public void setup()
    {
        colorMode(HSB);
        noCursor();
        
        setFrameSize(256);

        startMinim();
        loadAudio("java/data/CrazyFrog.mp3");
        //getAp().play();
        //startListening(); 
        
    }


    public void draw()
    {
        float hue = map(getAmplitude(), 0, 1, 0, 255);
        fill(hue, 255, 255);
        rectMode(CENTER);
        float size = map(getSmoothedAmplitude(), 0, 1, 0, 400);
        rect(width / 2, height / 2, size, size);

    }
}
