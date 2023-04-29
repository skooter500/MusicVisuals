package ie.tudublin;

import ie.tudublin.stars;


import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class Experiance extends PApplet{

    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    stars star;

    FFT fft;

    int mode = 0;

<<<<<<< HEAD
    float f = 0;

    int numShapes = 50;
        float[] x = new float[numShapes];
        float[] y = new float[numShapes];
        float[] size = new float[numShapes];
        float[] speed = new float[numShapes];
        float[] colorHue = new float[numShapes];
        float[] colorBrightness = new float[numShapes];
        float[] colorAlpha = new float[numShapes];
    
=======
    float y = 0;
>>>>>>> 57d5b3a23a69e6c808ff618a41e24176b8c7a131

    public void keyPressed() {
		if (key >= '0' && key <= '9') {
			mode = key - '0';
		}
		if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
	}
    
    public void settings()
    {
        size(700, 700);
    }

    public void setup()
    {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix; 

        // And comment the next two lines out
        ap = minim.loadFile("MusicVisuals/java/data/Victoria_Mon_t_ft_Khalid_-_Experience.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB,360, 100, 100, 1);

        for (int i = 0; i < numShapes; i++) 
        {
            x[i] = random(width);
            y[i] = random(height);
            size[i] = random(10, 50);
            speed[i] = random(1, 3);
            colorHue[i] = random(360);
            colorBrightness[i] = random(30, 70);
            colorAlpha[i] = random(0.2, 0.5);
          }


    }

    public void draw()
    {
        switch (mode) {
			case 0://first to play (grace)

          
          
                
                break;
        case 1://When you press key 1 (hadassah)
            background(0);
                    
            break;

        case 2:// when you press key 2 (cece)
<<<<<<< HEAD
            
=======
      
    
>>>>>>> 57d5b3a23a69e6c808ff618a41e24176b8c7a131


                
        

<<<<<<< HEAD

        
=======
            break;

        case 4://when you press key 4 (aisha)

            break;
        }
>>>>>>> 57d5b3a23a69e6c808ff618a41e24176b8c7a131
    }
     
}





   




