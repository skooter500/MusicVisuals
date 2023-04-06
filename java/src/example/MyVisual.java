package example;

import ie.tudublin.*;


public class MyVisual extends Visual
{
    WaveForm wf;
    AudioBandsVisual abv;
    CloudsBackground cb;

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
        abv = new AudioBandsVisual(this);
        cb = new CloudsBackground(this);
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
                partOne();
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

            wf.render();
            abv.render();
            text("Part One", 100, 100);
    }


    void partTwo(){
        
        cb.render();
        text("Part Two", 100, 100);

    }


    void partThree(){
        color(255);
        text("Part Three", 100, 100);
    }
}