package example;

import java.util.concurrent.TimeUnit;

import ie.tudublin.*;
import processing.core.PFont;
import processing.core.PImage;

public class Test extends Visual
{   
    PImage img;
    PFont font;
    PFont myFont;
    PFont mono;
    

    public void settings()
    {
        fullScreen();
        // Use this to make fullscreen and use P3D for 3D graphics
        //fullScreen(P3D, SPAN); 
    }

    public void setup()
    {
        background(0);
    }



    public void draw()
    {
        clear();
        int m = minute();  // Values from 0 - 59
        int h = hour();    // Values from 0 - 23
        String time =  "";
        
        if(h < 12) 
        {
            time = " PM";
        } else {
            time = " AM";
        }
         
        String myString = Integer.toString(h) + " : " + Integer.toString(m) + time;
        text(h, 10, 28);
        text(m, 40, 28); 
        text(myString, 60, 40); 
        
    }
    
}