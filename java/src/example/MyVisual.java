package example;

import ie.tudublin.*;

import net.java.games.input.*;
import org.gamecontrolplus.*;
import org.gamecontrolplus.gui.*;

public class MyVisual extends Visual
{    
    WaveForm wf;
    AudioBandsVisual abv;

    public void settings()
    {
        size(1024, 500, P3D);
  
        // Use this to make fullscreen
        //fullScreen(P3D);

        // Use this to make fullscreen and use P3D for 3D graphics
        //fullScreen(P3D, SPAN); 
    }

    public void setup()
    {
        startMinim();
                
        // Call loadAudio to load an audio file to process 
        loadAudio("heroplanet.mp3");   

        
        // Call this instead to read audio from the microphone
        //startListening(); 
        
        wf = new WaveForm(this);
        abv = new AudioBandsVisual(this);

        // Connect to a game controller
        controlIO = ControlIO.getInstance(this);
        connectToController();
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
        wf.render();
        abv.render();

        // Gamepad input
        printSliders();
    }

    void printSliders()
    {
        for(int i = 0 ; i < device.getNumberOfSliders() ; i ++)
        {
            println("Slider: " + i + ": \t" + device.getSlider(i));
        }
    }

    void connectToController()
    {
        for(int i = 0; i < controlIO.getNumberOfDevices(); i++){
            ControlDevice device = controlIO.getDevice(i);
            println(device);
        }
        device = controlIO.getDevice(0);
        
    }

    ControlDevice device;
    ControlIO controlIO;
}
