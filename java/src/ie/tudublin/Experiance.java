package ie.tudublin;

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

    FFT fft;

    int mode = 0;

    float f = 0;

    int numShapes = 50;
        float[] x = new float[numShapes];
        float[] y = new float[numShapes];
        float[] size = new float[numShapes];
        float[] speed = new float[numShapes];
        float[] colorHue = new float[numShapes];
        float[] colorBrightness = new float[numShapes];
        float[] colorAlpha = new float[numShapes];
    

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
        size(1024, 1024);
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
        background(0);
    
        






        



                
        


        
    }

    
}