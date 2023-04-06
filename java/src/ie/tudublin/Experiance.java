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

    float y = 0;
    

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
        colorMode(HSB);
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
            

            break;

        case 3://when you press key 3 (molly)

            break;

        case 4://when you press key 4 (aisha)

            break;
        }

        
    }

    
}