package example;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;

public class RotatingAudioBands extends Visual {


    public void settings()
    { 
        size(800, 800, P3D);
        println("CWD: " + System.getProperty("user.dir"));
        //fullScreen(P3D, SPAN);
    }

    public void keyPressed()
    { //space key to pause and unpause 
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
            
        }
 
    }

    public void setup()
    {   //HSB color code, frameSize at 256, 
        colorMode(HSB);
        noCursor();
        
        setFrameSize(256);
        //starting audio library
        startMinim();
        loadAudio("heroplanet.mp3"); //loading song
        getAudioPlayer().play(); //start playing song immediately on load
        //startListening(); 
        
    }
    //radius the squares sit in 
    float radius = 200;

    float smoothedBoxSize = 0; //boxsize Default 

    //rotation
    float rot = 0;

    public void draw()
    {
        //calc average amplitude. Gets size of audio buffer, builds a total value and divides by buffer size
        calculateAverageAmplitude(); 
        try
        {
            calculateFFT(); //acquire frequency 
        }
        catch(VisualException e)
        {
            e.printStackTrace();
        }
        calculateFrequencyBands(); //gets the bands of frequency and calculates their size
        background(0);
        noFill();
        stroke(255);
        lights(); //adds lighting to the amo,atopm
        stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        camera(0, -500, 500, 0, 0, 0, 0, 1, 0); //set camera position
        //translate(0, 0, -250);
        //rotation speed increase in speed alongside amplitude gain
        rot += getAmplitude() / 8.0f;

        //Y rotates
        rotateY(rot);
        //Smoothed bads is the % increase/decrease rather then snappy moves when audio increases
        float[] bands = getSmoothedBands();
        //for i through the audio bands
        for(int i = 0 ; i < bands.length ; i ++)
        {
            //creating the boxes to the spun and changed

            //this is mapping the positions for the boxes to be generated in, 2 PI creates a circle
            float theta = map(i, 0, bands.length, 0, TWO_PI);

            //seperates the boxes into seperate colors depending on band length
            stroke(map(i, 0, bands.length, 0, 255), 255, 255);
            //giving x value coordinates
            float x = sin(theta) * radius;
            //z value coordinates
            //both needed to form the circle
            float z = cos(theta) * radius;
            //changes height depending on i inside of bands
            float h = bands[i];
            //begin 3d box creation
            pushMatrix();
            //keeps them on 1 plane and is needed for being placed in a circle
            translate(x, - h / 2 , z);
            rotateY(theta); //rotate in speed with bands
            box(50, h, 50); //placing boxes
            popMatrix();
        }

    }
    float angle = 0;

}