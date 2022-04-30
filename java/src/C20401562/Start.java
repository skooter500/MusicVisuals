package C20401562;

import ie.tudublin.*;

public class Start extends Visual{

    int mode = 0;
    int colour = 0;
    int choice = 0;
    int firstClick = 0;

    int buttonMode = 1;

    boolean isPlaying = false;
    boolean allowToPlay = false;

    float lerpedAverage = 0;
    float average;
    float sum;

    //use for the second visual the smooth the shape
    float[] lerpedBuffer;
    float[] lerpedBuffer2;

    AlexsVisual alex;
    JaycelsVisual jay;
    MendesVisual mende;
    StartMenu startm;
    
    public void settings()
    {
        size(1400, 800, P3D);

        //fullScreen;

        //fullScreen(P3D, SPAN);
    }

    public void keyPressed()
    {
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
        loadAudio("Song.mp3");

        // startListening();

        smooth();

        lerpedBuffer = new float[width];
        lerpedBuffer2 = new float[width];


        colorMode(HSB);
       
    }

    public void draw()
    {

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

        background(30);

        alex = new AlexsVisual(this);
        jay = new JaycelsVisual(this);
        mende = new MendesVisual(this);
        startm = new StartMenu(this);


        if(allowToPlay){
            switch (mode) 
            {
			case 0:
                break;
            case 1:
                jay.render();
                startm.lowerMenu();
                break;
            case 2:
                alex.render();
                startm.lowerMenu();
                break;
            case 3:
                mende.render();
                startm.lowerMenu();
                break;
            case 4:
                break;

            }
        }else{
            startm.render();
        }
    }

    public void mouseClicked()
    {   

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
        }else{
        }

        // System.out.println("Mode set to " + mode);


        if(firstClick != 0 && mouseX <= startm.playButtonX + 50 && mouseX >= startm.playButtonX - 50 && mouseY >= startm.playButtonY - 50 && mouseY <= startm.playButtonY + 50){
            allowToPlay = true;
            ap.play();
            isPlaying = true;
        }else if(isPlaying == false){
            allowToPlay = false;
        }

        if(allowToPlay){
            if(mouseX <= startm.pressButtonX + startm.ButtoRadius && mouseX >= startm.pressButtonX - startm.ButtoRadius && mouseY >= startm.ButtonY - startm.ButtoRadius &&  mouseY <= startm.ButtonY + startm.ButtoRadius){
                if(ap.isPlaying()){
                    ap.pause();
                    buttonMode = 0;
                }else{
                    buttonMode = 1;
                    ap.play();
                }
            }

            if(mouseX <= startm.loopButtonX + startm.ButtoRadius && mouseX >= startm.loopButtonX - startm.ButtoRadius && mouseY >= startm.ButtonY - startm.ButtoRadius &&  mouseY <= startm.ButtonY + startm.ButtoRadius){
                ap.loop();
                buttonMode = 1;
            }
        }

        // System.out.println("Allowed play = " + allowToPlay);

    }
    
}
