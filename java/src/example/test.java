package example;

import ddf.minim.Minim;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;

import ie.tudublin.Visual;

public class test extends Visual
{
    Minim minim; 
    AudioInput ai;
    AudioPlayer ap;
    AudioBuffer ab;

    int frameSize = 1024;

    public void settings()
    {   
        size(500, 500);
    }
    
    public void setup()
    {
        colorMode(HSB);
        background(0);
         
        minim = new Minim(this);
        ai = minim.getLineIn(Minim.MONO, frameSize, 44100, 16);
        ab = ai.mix; 

        smooth();
    }

    public void draw()
    {
        background(0);
        stroke(255);
        float cgap = 255 / (float)ab.size();
        float half = height/2;

        float sum = 0;

        for(int i = 0; i < ab.size(); i++)
        {
            stroke(cgap * i, 255, 255);
            line(i, half, i, half + (ab.get(i) * half));
            sum += abs(ab.get(i));
        }

        float average = sum / ab.size();
        // System.out.printf("%.2f\n", average);

        noFill(); 
        circle(width / 2, half, 100 + (average * 100));

        

    }

} 