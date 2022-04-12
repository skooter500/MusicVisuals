package c20474596;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class Runner extends Visual{

    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    Star[] stars = new Star[1250];
    float px;
    float py;
    int mode = 0;
    float smoothedBoxSize = 0;
    float angle = 0;

    public void setup(){
        for(int i = 0; i < stars.length; i++){
            stars[i] = new Star();
        }
        startMinim();
        loadAudio("tevvez.mp3");
        colorMode(HSB);
    }

    public void keyPressed(){
        if (key >= '0' && key <= '9') {
			mode = key - '0';
		}
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
    }

    public void settings(){
        size(800,800,P3D);
        fullScreen(P3D, SPAN);
    }

    public void draw(){
        background(0);
        //translate(width/2, height/2);

        switch(mode){
            case 1:
            {
                background(0);
                translate(width/2, height/2);
                for(int i = 0; i<stars.length;i++){ //Displays star(s) on screen
                    fill(255);
                    strokeWeight(1);
                    
                    float sx = map(stars[i].x/stars[i].z,0,1,0,width);
                    float sy = map(stars[i].y/stars[i].z,0,1,0,height);
                    float r = map(stars[i].z,0,width,16,0);
                    ellipse(sx,sy,r,r);
                
                    float px = map(stars[i].x/stars[i].pz,0,1,0,width);
                    float py = map(stars[i].y/stars[i].pz,0,1,0,height);
                
                    stroke(255);
                    line(px,py,sx,sy);
                }

                for(int i = 0;i<stars.length;i++){ //updates star(s) position on screen once it disappears
                    stars[i].z = stars[i].z-12;
                    if(stars[i].z < 1){
                        stars[i].z = width;
                        stars[i].x = random(-width,width);
                        stars[i].y = random(-height,height);
                        stars[i].pz = stars[i].z;
                    }
                }
            }

            case 2:
            {
                calculateAverageAmplitude();
                stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
                strokeWeight(7);
                noFill();
                lights();
                pushMatrix();
                camera(0, 0, 0, 0, 0, -1, 0, 1, 0);
                translate(0, 0, -200);
                rotateX(angle);
                rotateZ(angle);       
                float boxSize = 50 + (200 * getSmoothedAmplitude()); 
                box(boxSize);   
                popMatrix();
                angle += 0.01f;  
            }
        }
        
    }
}
 