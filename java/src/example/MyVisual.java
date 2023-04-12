package example;
import example.parts.BounceBall;
import example.parts.backgrounds.CloudsBackground;
import example.parts.backgrounds.MolecularsBC;
import example.parts.backgrounds.SpeakersBC;
import processing.core.*;
import ie.tudublin.*;



public class MyVisual extends Visual
{
    WaveForm wf;
    
    AudioBandsVisual abv;

    //pt1
    CloudsBackground cb;

    //pt2
    BounceBall baunceBall;
    SpeakersBC speackersBc;

    //pt3
    PoliceText pt;

    //pt4
        //todo: Roxanas ball

    //p5
    MolecularsBC molecularsBC;


    int mode = 1;
    int numbersOfPurts = 5;
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
        abv = new AudioBandsVisual(this);

        //pt1
        cb = new CloudsBackground(this);

        //pt2
        baunceBall = new BounceBall(this);
        speackersBc = new SpeakersBC(this);

        //pt3
        pt =  new PoliceText(this);

        //pt4
        //todo: Roxanas ball

        //pt5
        molecularsBC = new MolecularsBC(this);
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
            case 4:
                partFour();
                break;   
            case 0: 
                partFive();
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
        calculateAverageAmplitude(); 
        cb.render();

        
        int numStars = PApplet.round(map(getAmplitude(), 0, 1, 0, 50));
        for (int i = 0; i < numStars ; i++){
            Stars s = new Stars(this);
            s.draw();
        }
            // wf.render();
            // abv.render();
            // text("Part One", 100, 100);
    }


    void partTwo(){
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

        molecularsBC.render();
        baunceBall.render();
    }


    void partThree(){
        pt.draw();
    }

    void partFour(){
        fill(255);
        text("Roxana's super cool ball <3", 100, 100);
        //todo: Roxanas ball
    }

    void partFive(){
        speackersBc.render();
    }
}

 
