package C20401562;

import ie.tudublin.Visual;

public class Start extends Visual{

    int mode = 1;
    int options = 4;
    int playing = 1;

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
            if(playing == 1){
                as.stop();
                playing = 0;
            }else{
                as.trigger();
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
