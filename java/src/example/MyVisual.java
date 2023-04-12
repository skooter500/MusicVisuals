package example;

import example.parts.Part4;
import example.parts.Part5;
import example.parts.PixelDisintegrationVisual;
import ie.tudublin.*;


public class MyVisual extends Visual
{
    WaveForm wf;
    Part4 p4;
    Part5 p5;
    AudioBandsVisual abv;
    PixelDisintegrationVisual pdv;



    int mode = 1;
    int numbersOfPurts = 3;
    boolean lastPressed = false;


    public void settings()
    {
        size(1024, 1024);
        
        // Use this to make fullscreen
        // fullScreen();

        ///Use this to make fullscreen and use P3D for 3D graphics
        //fullScreen(P3D, SPAN); 
    }


    public void setup()
    {
        startMinim();
                
        // Call loadAudio to load an audio file to process 
        loadAudio("Hensonn_Flare.mp3");   

        
        // Call this instead to read audio from the microphone
        //startListening(); 
        
        wf = new WaveForm(this);
        p4 = new Part4(this);
        p5 = new Part5(this);
        abv = new AudioBandsVisual(this);
        pdv = new PixelDisintegrationVisual(this);
    }


    public void keyPressed()
    {
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
    }


    @Override
    public void draw(){
        background(0);

		switch(mode) {
            case 1: 
                partFour();
                //partOne();
                break;
            case 2: 
                partTwo(); 
                break;
            case 0: 
                partThree();
                break;
            default:
                break;
        }   

        keyPressingLogic();
}


    void keyPressingLogic(){
        if(keyPressed){
            if(key == 'n'){
                if(!lastPressed){
                    mode = (mode+1) % numbersOfPurts;
                }
                lastPressed = true;
            }
        } else{
            lastPressed = false;
        }
    }


    void partOne(){
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
    }


    void partTwo(){
        color(255);
        text("Part Two", 100, 100);
    }


    void partThree(){
        color(255);
        text("Part Three", 100, 100);
    }

    void partFour(){
        try {
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

            pdv.render();
            p4.render();
    }
}