package C20401562;

import ddf.minim.analysis.*;
import ie.tudublin.*;
import processing.core.PApplet;

public class Start extends Visual{

    //For Beat Detection
    BeatDetect beat;

    int mode = 0;
    int colour = 0;
    int choice = 0;
    int firstClick = 0;

    int buttonMode = 1;

    //Booleans for Menu
    boolean isPlaying = false;
    boolean allowToPlay = false;

    float lerpedAverage = 0;
    float average;
    float sum;

    //Smooth the shapes In Visuals
    float[] lerpedBuffer;
    float[] lerpedBuffer2;

    String[] name = {"","Jay", "Alex", "Mende"};


    //____Initializing Visuals
    AlexsVisual alex;
    JaycelsVisual jay;
    MendesVisual mende;
    StartMenu startm;
    
    public void settings()
    {
        size(1400, 800, PApplet.P3D);

        noSmooth();

    }

    public void keyPressed()
    {
        //Pause or Play Song

        if (key == ' ')
        {
            if(ap.isPlaying()){
                ap.pause();

                buttonMode = 0;
                isPlaying = false;
            }else if(allowToPlay){
                ap.play();

                buttonMode = 1;
                isPlaying = true;
            }
            
        }
    }

    public void setup()
    {

        startMinim();
        loadAudio("Song.wav");

        // startListening();
    
        lerpedBuffer = new float[width];
        lerpedBuffer2 = new float[width];

        beat = new BeatDetect();

        colorMode(HSB);
       
    }

    public void draw()
    {
        //Creat Beat Detection On Ap
        beat.detect(ap.mix);

        background(30);

        calculateAverageAmplitude();   

        alex = new AlexsVisual(this);
        jay = new JaycelsVisual(this);
        mende = new MendesVisual(this);
        startm = new StartMenu(this);

        //_______________Switch Statment For Visuals

        if(allowToPlay){
            switch (mode) 
            {
			case 0:
                break;
            case 1:
                //First Visual
                jay.render();
                startm.lowerMenu();
                break;
            case 2:
                //Second Visual
                alex.render();
                startm.lowerMenu();
                break;
            case 3:
                //Third Visual
                mende.render();
                startm.lowerMenu();
                break;
            case 4:
                break;

            }
        }else{
            //At the start when song isnt playing show menu
            startm.render();
        }
    }
    //___________________End Draw Method()


    public void mouseClicked()
    {   
        //If statments to check the cordinates of the mouse and which box it is selecting

        if(isPlaying == false && mouseX >= startm.jayBoxX && mouseX <= startm.jayBoxX + startm.BoxWidth && mouseY >= startm.BoxY && mouseY <= startm.BoxY + startm.BoxHeight){
            
            firstClick = 1;
            mode = 1;
            choice = 1;
            colour = 200;

        }else if(isPlaying == false && mouseX >= startm.alexBoxX && mouseX <= startm.alexBoxX + startm.BoxWidth && mouseY >= startm.BoxY && mouseY <= startm.BoxY + startm.BoxHeight){
            
            firstClick = 1;
            mode = 2;
            choice = 2;
            colour = 200;

        }else if(isPlaying == false && mouseX >= startm.mendeBoxX && mouseX <= startm.mendeBoxX + startm.BoxWidth && mouseY >= startm.BoxY && mouseY <= startm.BoxY + startm.BoxHeight){
            
            firstClick = 1;
            mode = 3;
            choice = 3;
            colour = 200;

        }

        //Mouse coridnates for the play button, first checks if the first click was made
        if(firstClick != 0 && mouseX <= startm.playButtonX + 50 && mouseX >= startm.playButtonX - 50 && mouseY >= startm.playButtonY - 50 && mouseY <= startm.playButtonY + 50){
            
            allowToPlay = true;
            ap.play();
            isPlaying = true;

        }else if(isPlaying == false){

            allowToPlay = false;

        }

        //If the Song is Playing
        if(allowToPlay){

            //Lower Menu Button to pause and play the song
            if(mouseX <= startm.pressButtonX + startm.ButtoRadius && mouseX >= startm.pressButtonX - startm.ButtoRadius && mouseY >= startm.ButtonY - startm.ButtoRadius &&  mouseY <= startm.ButtonY + startm.ButtoRadius){
                if(ap.isPlaying()){

                    ap.pause();
                    buttonMode = 0;

                }else{

                    buttonMode = 1;
                    ap.play();
                    
                }
            }

            //Lower Menu Loop button
            if(mouseX <= startm.loopButtonX + startm.ButtoRadius && mouseX >= startm.loopButtonX - startm.ButtoRadius && mouseY >= startm.ButtonY - startm.ButtoRadius &&  mouseY <= startm.ButtonY + startm.ButtoRadius){
               
                ap.loop();
                buttonMode = 1;

            }

            //Scrolling through the renders in the lower menu

            if(mouseX <= startm.rightbuttonX + startm.nextButtonWidth && mouseX >= startm.rightbuttonX && mouseY >= startm.nextButtonY && mouseY <= startm.nextButtonY + startm.nextButtonHeight){
                mode = startm.rightIndex;
            }
            
            if(mouseX <= startm.leftbuttonX + startm.nextButtonWidth && mouseX >= startm.leftbuttonX && mouseY >= startm.nextButtonY && mouseY <= startm.nextButtonY + startm.nextButtonHeight){
                mode = startm.leftIndex;
            }
        }

    }
    //___________________End Mouse Click Method
    
}
