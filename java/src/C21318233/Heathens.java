package C21318233;

import ie.tudublin.*;

public class Heathens extends Visual {
    public void settings()
    {
        fullScreen();
        size(1000,1000);
    }
    
    public void setup()
    {
        startMinim();
        loadAudio("heathens.mp3");
        getAudioPlayer().play();
    }

    public void draw()
    {
        calculateAverageAmplitude();
        circleGroup();
    }

    public void circleGroup()
    {
        float halfheight = height / 2;
        float halfwidth = width / 2;

        background(0);
        stroke(255, 0, 0);
        strokeWeight(3);
        fill(0);
        circle(halfwidth, halfheight, getSmoothedAmplitude() * 2000);
    }
}
