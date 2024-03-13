package c22371846;

import example.CubeVisual;
import example.MyVisual;
import example.RotatingAudioBands;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;




public class PatricksVisuals extends PApplet
{
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;

    public void settings()
    {
        size(1024, 1000);
    }

    public void setup()
    {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        //ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        //ab = ai.mix; 
        //ap = minim.loadFile("heroplanet.mp3", 1024);
        //ap.play();
        ab = ai.mix;
    }

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





    public void draw()
	{
		switch (mode)
		{
			case 0:
			{
				int ver_bar = height - 50;
				float w = width / months.length;
				background(225);
				text("Rainfall Bar Chart", 200, 15);

				//bar ticks
				for (int i = 0; i < months.length; i++) 
				{
					line(50, height - (50 * i), 30, height - (50 * i));
				}

				//bars
				for (int i = 1; i < ab.size(); i++) 
				{
					float num = 50;
					float x = map(num * i, width - 450, width, num, width);
					rect(x, height - 50, w, ab.size());
					fill(map(i, 0, months.length, 0, 255), 255, 255);
					textAlign(CENTER, CENTER);
					/*Months text*/text(months[i], (i * 40) + 50, 475);
				}

				for (int i = 1; i < months.length; i++) 
				{
					text(i * 10, 30, height - (i*50));
				}

				//graph bars
				line(50, 50, 50, ver_bar);
				line(50, ver_bar, width, ver_bar);
				break;
			}
        }
    }
}

