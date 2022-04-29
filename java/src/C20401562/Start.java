package C20401562;

import ie.tudublin.*;

public class Start extends Visual{

    int mode = 1;
    int options = 4;
    int playing = 1;

    public void settings()
    {
        size(1400, 800);
        //fullScreen;

        //fullScreen(P3D, SPAN);
    }


    public void keyPressed()
    {
        if (key == ' ')
        {
            if(playing == 1){
                as.stop();
                // noLoop();
                playing = 0;
            }else{
                as.trigger();
                // loop();
                playing = 1;
            }
            
            
        }

        for(int i = 1; i <= options; i++){

            int asscii = 48 + i;
            
            if(key == (char)asscii){
                mode = i;
            }
        }
    }

    public void setup()
    {
        startMinim();
        loadAudio("Song.mp3");

        // startListening();

        as.trigger();

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


        AlexsVisual alex = new AlexsVisual(this);
        JaycelsVisual jay = new JaycelsVisual(this);
        MendesVisual mende = new MendesVisual(this);

        switch (mode) 
        {
			case 0:
                break;
            case 1:
                alex.render();
                break;
            case 2:
                jay.render();
                break;
            case 3:
                mende.render();
                break;
            case 4:
                break;

        }

    }
}
