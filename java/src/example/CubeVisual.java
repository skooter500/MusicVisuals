package example;

import ie.tudublin.Visual;

public class CubeVisual extends Visual
{
    public void settings()
    {
        size(800, 800, P3D);
        
    }

    public void setup()
    {
        colorMode(HSB);
        startMinim();
        //loadAudio("heroplanet.mp3");
        startListening(); 
        
        //as.trigger();
        //startListening();
    }

    public void draw()
    {
        calculateAverageAmplitude();
        background(0);
        noFill();
        lights();
        stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);

        camera(0, 0, 0, 0, 0, -1, 0, 1, 0);
        pushMatrix();
        translate(0, 0, -250);
        rotateX(angle);
        rotateY(angle);
        strokeWeight(5);        
        box(50 + (getSmoothedAmplitude() * 500));
        strokeWeight(1);        
        sphere(20 + (getSmoothedAmplitude() * 200));
        angle += 0.01f;
        popMatrix();
    }
    float angle = 0;
}