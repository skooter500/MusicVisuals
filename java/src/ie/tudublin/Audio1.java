package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Audio1 extends PApplet
{
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;

    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;
    float[] spectrum = new float[512];
    Planet[] planets = new Planet[4];

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
        size(1024, 1000);        //fullScreen(P3D, SPAN);
    }

    public void setup()
    {
        minim = new Minim(this);
         //Uncomment this to use the microphone
        //ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        //ab = ai.mix; 

        // And comment the next two lines out
        ap = minim.loadFile("java/data/horizon.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;  

        for (int i = 0; i < planets.length; i++ ) {
            planets[i] = new Planet(64 + i*32,24);
          }
        }
        
       
    

    float off = 0;

    float lerpedBuffer[] = new float[1024];

    public void draw()
    {
        //background(0);
        float halfH = height / 2;
        float average = 0;
        float sum = 0;
        off += 1;


        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
        }
        average= sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
        
        //float cx = width / 2;
        //float cy = height / 2;

        
        switch (mode) {
			case 0:
                background(0);
                for(int i = 0 ; i < ab.size() ; i ++)
                {
                    //float c = map(ab.get(i), -1, 1, 0, 255);
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    float f = lerpedBuffer[i] * halfH * 4.0f;
                    line(i, halfH + f, i, halfH - f);                    
                }
                break;
        case 1:
            background(0);
    int from = color(255, 0, 0);
    int to = color(0, 255);
    int c1 = lerpColor(from, to, (float) 0.33);
    int c2 = lerpColor(from, to, (float) 0.66);
    for (int i = 0; i < 15; i++) {
      fill(from);
      quad(
        random(-40, 220), random(height),
        random(-40, 220), random(height),
        random(-40, 220), random(height),
        random(-40, 220), random(height)
      );
      fill(c1);
      quad(
        random(140, 380), random(height),
        random(140, 380), random(height),
        random(140, 380), random(height),
        random(140, 380), random(height)
      );
      fill(c2);
      quad(
        random(320, 580), random(height),
        random(320, 580), random(height),
        random(320, 580), random(height),
        random(320, 580), random(height)
      );
      fill(to);
      quad(
        random(500, 760), random(height),
        random(500, 760), random(height),
        random(500, 760), random(height),
        random(500, 760), random(height)
      );
    frameRate(5);
  }
  
            break;      
        case 2:
            background(0);
            for(int i = 0 ; i < ab.size() ; i ++)
            {
                float c = map(i, 0, ab.size(), mouseX /2, mouseY/ 2);
                stroke(c, 255, 255);
                float f = lerpedBuffer[i] * halfH * 4.0f;
                line(0, i, f, i);              
                line(width, i, width - f, i);              
                line(i, 0, i, f);          
                line(i, height, i, height - f);              
            }
            break;   
            
            case 3:
            background(0);
            // Drawing the Sun
            pushMatrix();
            translate(width/2,height/2);
            stroke(0);
            fill(255);
            ellipse(0,0,64,64);
            
            // Drawing all Planets
            for (int i = 0; i < planets.length; i++ ) {
              planets[i].update();
              planets[i].display();
            }
            popMatrix();
            break;
          
        }
        
        // Other examples we made in the class
        /*
        stroke(255);
        fill(100, 255, 255);        
        
        circle(width / 2, halfH, lerpedA * 100);

        circle(100, y, 50);
        y += random(-10, 10);
        smoothedY = lerp(smoothedY, y, 0.1f);        
        circle(200, smoothedY, 50);
        */

    }        
}
