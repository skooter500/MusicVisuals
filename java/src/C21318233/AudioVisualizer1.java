package C21318233;

import ie.tudublin.*;

public class AudioVisualizer1 extends Visual {

    public void settings()
    {
        fullScreen();
    }
    
    public void setup()
    {
        startMinim();
        loadAudio("heathens.mp3");
        getAudioPlayer().play();
    }



    public void draw()
    {
        background(0);
    }
}
