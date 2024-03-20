package c22371846;

import example.CubeVisual;
import example.MyVisual;
import example.RotatingAudioBands;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import ie.tudublin.Heartbeat;




public class PatricksVisuals 
{
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    RotatingAudioBands RAB;
    Heartbeat HB;

    int mode = 0;
    float separated = 12;
    float lerpedAvg = 0;
    float H = HB.height / 2;
    float W = HB.width / 2;

    public void settings()
    {
        HB.size(500, 500, HB.P3D);
        //fullScreen(P3D, SPAN);
    }

    public void setup()
    {
        HB.colorMode(HB.HSB);
        HB.background(0);
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
		if (HB.key >= '0' && HB.key <= '9') 
        {
			mode = HB.key - '0';
		}
		if (HB.keyCode == ' ') {
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





    public void render(Heartbeat HB)
	{
		switch (mode)
		{
			case 0:
			{
				int ver_bar = HB.height - 50;
				float w = HB.width / separated;
                float h = HB.height / separated;
                float total = 0;
                float ticks = 15;
				HB.background(0);

                //graph bars
				HB.line(50, 50, 50, HB.height);
				HB.line(50, ver_bar - 400, HB.width, ver_bar - 400);

				//bar ticks
				for (int i = 0; i < ticks; i++) 
				{
					HB.line(50, HB.height - (50 * i), 30, HB.height - (50 * i));
				}
                HB.text("Frequencies", 225, 25);

                //Get the frequency values
                for(int i = 0 ; i < ab.size() ; i ++)
                {
                    total += ab.get(i);
                }

                float average = total / ab.size();
                lerpedAvg = HB.lerp(lerpedAvg, average, 0.1f);

                //bar values
                HB.text('0', 25, 50);
				for (int i = 1; i < ab.size(); i++) 
				{
					float num = 50;
					float x = HB.map(num * i, HB.width - 450, HB.width, num, HB.width);
                    //float hue_color = map(i, 0, ab.size() , 0, 256);

                    HB.rect(x, (HB.height - 450), w, lerpedAvg * h * 50);
                    //stroke(hue_color, 105, 255);
                    //fill(map(i, 0, ab.size(), 0, 255), 255, 255);
				}

                for (int i = 0; i < separated; i++) 
                {
                    HB.text(i * 50, 25, (i * 50) + 50);
                }
				break;
			}
            case 1:
            {
                HB.background(0); 
                float total = 0;
                float bars = 22;
                
				
                //Get the frequency values
                for(int i = 0 ; i < ab.size() ; i ++)
                {
                    total += (ab.get(i));
                }

                float average = total / ab.size();
                lerpedAvg = HB.lerp(lerpedAvg, average, 0.1f);

                for (int i = 0; i < bars; i++) 
                {
                    for (int j = 0; j < ab.size(); j++) 
                    {
                        float hue_color = HB.map(i, 0, ab.size() , 0, 256);
                        HB.rect(j, i, 50, 20);
                        HB.stroke(hue_color, 105, 255);
                        HB.fill(hue_color, 255, 255);

                        //rect();
                    }
                }
				break;
            }
        }
    }
}
