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

            float rotationX = 0;
            float rotationY = 0;
            float rotationZ = 0;
            float sphereRadius = 250;
            int lineCount = 30;
            int squareSize = 15;
            int colorChangeRate = 20;
            int colorTime = 0;

            background(0);
            translate(width/2, height/2, 0);
            rotateX(rotationX);
            rotateY(rotationY);
            rotateZ(rotationZ);
            colorTime++;
            if (colorTime % colorChangeRate == 0) {
            stroke(random(255), random(255), random(255));
            }
            for (int i = 0; i < lineCount; i++) {
            for (int j = 0; j < lineCount; j++) {
                pushMatrix();
                float xPos = map(i, 0, lineCount, -sphereRadius, sphereRadius);
                float yPos = map(j, 0, lineCount, -sphereRadius, sphereRadius);
                translate(xPos, yPos, 0);
                float dist = sqrt(sq(xPos) + sq(yPos));
                float angle = atan2(yPos, xPos);
                float zPos = sqrt(sq(sphereRadius) - sq(dist));
                translate(0, 0, zPos);
                float rotAngle = map(zPos, 0, sphereRadius, 0, PI);
                rotateX(rotAngle);
                rotateY(angle + radians(frameCount));
                for (int k = 0; k < 4; k++) {
                line(-squareSize/2, -squareSize/2, -squareSize/2, squareSize/2);
                line(-squareSize/2, squareSize/2, squareSize/2, squareSize/2);
                line(squareSize/2, squareSize/2, squareSize/2, -squareSize/2);
                line(squareSize/2, -squareSize/2, -squareSize/2, -squareSize/2);
                translate(0, 0, squareSize/2);
                rotateX(PI/2);
                }
                popMatrix();
            }
            }
            rotationX += 0.01;
            rotationY += 0.02;
            rotationZ += 0.03;
        
            strokeWeight(2);
          
                
                break;
        case 1://When you press key 1 (hadassah)
               
                    
            break;

        case 2:// when you press key 2 (cece)
       
        star.render();
    

            break;

        case 3://when you press key 3 (molly)

            break;

        case 4://when you press key 4 (aisha)

            break;
        }

        
    }

    
}