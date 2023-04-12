package example;
import example.parts.Part4;
import example.parts.PixelDisintegrationVisual;
import processing.core.*;
import ie.tudublin.*;



public class MyVisual extends Visual
{
    WaveForm wf;
    Part4 p4;
    AudioBandsVisual abv;
    PixelDisintegrationVisual pdv;
    CloudsBackground cb;
    PoliceText pt;


    int mode = 1;
    int numbersOfPurts = 4;
    boolean lastPressed = false;
    

    public void settings()
    {
        size(1024, 1024);
        
        // Use this to make fullscreen
        //fullScreen();

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
        abv = new AudioBandsVisual(this);
        pdv = new PixelDisintegrationVisual(this);
        cb = new CloudsBackground(this);
        pt =  new PoliceText(this);
    }


    public void keyPressed()
    {
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
        else if(key == 'p') 
        {
            pt.showTape = true;
        }
        else if(key == 'o') 
        {
            pt.showTape = false;
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
            case 3: 
                partThree();
                break;    
            case 0: 
                partFour();
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
        color(255);
        text("Part Two", 100, 100);
        
        calculateAverageAmplitude(); 
        cb.render();

        
        int numStars = PApplet.round(map(getAmplitude(), 0, 1, 0, 50));
        for (int i = 0; i < numStars ; i++){
            Stars s = new Stars(this);
            s.draw();
        }
    }


    void partThree(){
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

    void partFour(){
               pt.draw();

    }
}

 
