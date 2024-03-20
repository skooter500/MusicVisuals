package c22371846;

import example.CubeVisual;
import example.MyVisual;
import example.RotatingAudioBands;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;




public class PatricksVisuals extends PApplet
{
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    RotatingAudioBands RAB;


    int mode = 0;
    float separated = 12;
    float lerpedAvg = 0;
    float H = height / 2;
    float W = width / 2;

    public void settings()
    {
        size(500, 500);
    }

    public void setup()
    {
        colorMode(HSB);
        background(0);
        minim = new Minim(this);
        // Microphone
        //ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        //ab = ai.mix; 

        //Music
        ap = minim.loadFile("heroplanet.mp3", 1024);
        ap.play();
        ab = ap.mix;
    }

    public void keyPressed() {
		if (key >= '0' && key <= '9') 
        {
			mode = key - '0';
		}
		if (keyCode == ' ') {
            if (ap.isPlaying()) 
            {
                ap.pause();
            } 
            else 
            {
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
				float w = width / separated;
                float h = height / separated;
                float total = 0;
                float ticks = 15;
				background(0);

                //graph bars
				line(50, 50, 50, height);
				line(50, ver_bar - 400, width, ver_bar - 400);

				//bar ticks
				for (int i = 0; i < ticks; i++) 
				{
					line(50, height - (50 * i), 30, height - (50 * i));
				}
                text("Frequencies", 225, 25);

                //Get the frequency values
                for(int i = 0 ; i < ab.size() ; i ++)
                {
                    total += abs(ab.get(i));
                }

                float average = total / ab.size();
                lerpedAvg = lerp(lerpedAvg, average, 0.1f);

                //bar values
                text('0', 25, 50);
				for (int i = 1; i < ab.size(); i++) 
				{
					float num = 50;
					float x = map(num * i, width - 450, width, num, width);
                    //float hue_color = map(i, 0, ab.size() , 0, 256);

                    rect(x, (height - 450), w, lerpedAvg * h * 50);
                    //stroke(hue_color, 105, 255);
                    //fill(map(i, 0, ab.size(), 0, 255), 255, 255);
				}

                for (int i = 0; i < separated; i++) 
                {
                    text(i * 50, 25, (i * 50) + 50);
                }
				break;
			}
            case 1:
            {
                background(0); 
                float w = width / 15;
                float bars = 20;
                float total = 0;

                for (int i = 0; i < bars; i++) 
                {
                    rect(0, i * w, width, 5);
                    rect(0, i * w, height, 5);
                }
				
                //Get the frequency values
                for(int i = 0 ; i < ab.size() ; i ++)
                {
                    total += abs(ab.get(i));
                }

                float average = total / ab.size();
                lerpedAvg = lerp(lerpedAvg, average, 0.1f);
				break;
            }
        }
    }
}
