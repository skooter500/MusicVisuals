
package c22485282;

import ie.tudublin.*;

public class MyVisual extends Visual {
    WaveForm wf;
    AudioBandsVisual abv;
    Worm worm;
    RotatingBlocks rotatingBlocks;
    Balls balls;
    GradientShape gradientShape;
    Snow snow;


    public void settings() {
        size(1024, 500);

        // Use this to make fullscreen
        // fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        fullScreen(P3D, SPAN);
    }

    public void setup() {
        startMinim();

        // Call loadAudio to load an audio file to process
        loadAudio("music/tagmp3_MCLuigiCircuit.mp3");
        // Call this instead to read audio from the microphone
        // startListening();

        getAudioPlayer().cue(0);
        getAudioPlayer().play();

        wf = new WaveForm(this);
        abv = new AudioBandsVisual(this);
        worm = new Worm(this);
        rotatingBlocks = new RotatingBlocks(this);
        balls = new Balls(this);
        gradientShape= new GradientShape(this);
        snow = new  Snow(this);
     
    }

    int current = 0;

    public void keyPressed() {
        if (key == '0') {
            
            wf.render();
            abv.render();
            current = 0;
        }
        else if (key == '1') {
            worm.render();
            current = 1;
        } else if (key == '2') {
            rotatingBlocks.render();
            current = 2; 
        } else if (key == '3') {
                balls.render();
                current = 3;
        } else if (key == '4') {
           gradientShape.render();
           wf.render();
           abv.render();
            current = 4;
        }
    

    }  // End keyPressed()
    

    public void draw() {
        background(20,50,90);
       

      
        if (current == 0) {
            snow.render();
            wf.render();
            abv.render();
            
        }
        else if (current == 1) {
            snow.render();
            worm.render();
        }
        else if (current == 2) {
            snow.render();
            rotatingBlocks.render();
        }
        else if(current == 3)
        {
            // snow.render();
            balls.render();
        }
        else if(current == 4)
        {
            gradientShape.render();
            //  snow.render();
        }
 
    //    wf.render();
    //    abv.render();
    } // End draw()
}