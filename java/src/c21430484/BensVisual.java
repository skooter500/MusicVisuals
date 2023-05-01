package c21430484;

import ddf.minim.AudioBuffer;
import ie.tudublin.*;



public class BensVisual extends Visual
{    
    WaveForm wf;
    CrossVisual cv; 
    AudioBandsVisual abv;
    TrumpetBandVisual tbv;

    AudioBuffer ab; 

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
    }

    public void keyPressed()
    {
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
    }

    public void draw()
    {
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

    public void renderCircle()
    {
        float sum = 0;
        float radius = 1; 
        ab = getAudioBuffer();

        for(int i = 0; i < ab.size(); i++)
        {
            sum += abs(ab.get(i));
        }

        float average = sum / ab.size(); 

        radius = lerp(radius, average * 10000f, 0.1f);


        noFill(); 
        stroke(208, 152, 3);
        circle(width / 2, height / 2, 100 + radius); 

    }
}
