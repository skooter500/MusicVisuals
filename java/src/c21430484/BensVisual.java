package c21430484;

import ddf.minim.AudioBuffer;
import ie.tudublin.*;
import processing.core.PImage;
import processing.core.PShape;



public class BensVisual extends Visual
{    
    WaveForm wf;
    CrossVisual cv; 
    AudioBandsVisual abv;
    TrumpetBandVisual tbv;

    PImage justice; 
    AudioBuffer ab; 

    long startTime;
    long currentTime;

    public void settings()
    {
        size(1024, 700, P3D);
        
        // Use this to make fullscreen
        //fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        //fullScreen(P3D, SPAN); 
    }

    public void setup()
    {
        startMinim();
        // colorMode(HSB);
                
        // Call loadAudio to load an audio file to process 
        loadAudio("Genesis.mp3");   

        
        // Call this instead to read audio from the microphone
        // startListening(); 
        
        wf = new WaveForm(this);
        abv = new AudioBandsVisual(this);
        tbv = new TrumpetBandVisual(this);
        cv = new CrossVisual(this);
        
        justice = loadImage("justice2.png");

        startTime = -1;

        background(0);
    }

    public void keyPressed()
    {
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
            startTime = System.currentTimeMillis();
        }
    }

    boolean drawBackground = true; 
    
    public void draw()
    {
        currentTime = System.currentTimeMillis();
        if(startTime != -1)
        {
            if(currentTime - startTime > 125000 && currentTime - startTime < 137500)
                drawBackground = false;
            else 
                drawBackground = true;
        }

        if(drawBackground)
            background(0);


        try
        {
            // Call this if you want to use FFT data
            calculateFFT(); 
        }
        catch(VisualException e)
        {
            e.printStackTrace();
        }
        // Call this is you want to use frequency bands
        calculateFrequencyBands(); 

        // Call this is you want to get the average amplitude
        calculateAverageAmplitude();        
        


        translate(-width/2, -height/2, 0);



        tbv.render();
        wf.render();

        cv.render(); 
        


        // abv.render();
    }  
}
