package c20474596;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;

public class Runner extends Visual{
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    Star[] stars = new Star[900];
    float px;
    float py;
    int mode = 0;
    float smoothedBoxSize = 0;
    float angle = 0;
    //sean edits
    int c = 16;
    float t = 1;
    int nprime = 1;
    float hue;
    FFT fft; // Object that performs Fast Fourier Transform (FFT)
    int OFF_MAX= 300;
    drop d;
    drop[] drops= new drop[500];
    float radius = 200;
    float rot = 0;

    public void setup(){
        for(int i = 0; i < stars.length; i++){
            stars[i] = new Star();
        }
        minim=new Minim(this);
        startMinim();
        getFFT();
        loadAudio("light.mp3");
        colorMode(HSB,255);   
        hue = random(255);
        //sean edits
        noStroke();
        for(int i =0;i<drops.length; i++){
            drops[i]= new drop();
        }
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
        if(key == '1' || key == '2' || key == ' '){ //ensure everything is centered in visual 1 and 2
            translate(width/2, height/2);
        } 
        switch(mode){
            case 1: //stars on screen visual
            {
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
    
                for(int i = 0;i<stars.length;i++){ //updates star(s) position on screen once they disappear
                    stars[i].z = stars[i].z-25; //z - 25, 25 = speed of stars
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
                getFFT();
                calculateFrequencyBands();
                calculateAverageAmplitude();
                getAmplitude();
                float boxSize = 200 + (700 * getSmoothedAmplitude()); 
                stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
                fill(10,10,10);
                box(boxSize);
            }

           case 3:
           {
                if (key == '3') //if statement to make sure this visual is exclusive
                {
                
                    t = (float) (pow(t,(float) 1.00001) + .1);
                    calculateAverageAmplitude();
                    stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
                    nprime++;
                    translate(width/2,height/2);
                    rotate(PI*sin(t/50));
                    fill(255,30,50,30);
                    rect(-width/2,-height/2,width,height);
                    circles();
                }
            }

            case 4:
            {
                if (key == '4') //if statement to make sure this visual is exclusive
                {
                    
                getFFT();
                getMinim();
                calculateFrequencyBands();
                calculateAverageAmplitude();
                stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
                rot += getAmplitude() / 8.0f;
                
                translate(width / 2, height / 2, OFF_MAX);
                rotateX((float) (frameCount * .01));
                rotateY((float) (frameCount * .01));
                rotateZ((float) (frameCount * .01));
                
                for (int xo = -OFF_MAX; xo <= OFF_MAX; xo += 50) {
                    for (int yo = -OFF_MAX; yo <= OFF_MAX; yo += 50) {
                        for (int zo = -OFF_MAX; zo <= OFF_MAX; zo += 50) {
                            pushMatrix();
                            translate(xo, yo, zo);
                            rotateX((float) (frameCount * .02));
                            rotateY((float) (frameCount * .02));
                            rotateZ((float) (frameCount * .02));
                            rotate(rot);
                            fill(colorFromOffset(xo), colorFromOffset(yo), 
                            colorFromOffset(zo));
                            box((float) (20 + (Math.sin(frameCount / 20.0)) * 15));
                            popMatrix();
                        }
                    }
                }
            }
        }  
    }
        
    }
            
    private void circles() {
        calculateAverageAmplitude();
        getFFT();
        calculateFrequencyBands();//SEAN
        for (int n = 1; n < nprime*3; n++) {
            pushMatrix();
            rot += getAmplitude() / 10.0f;
            rotate(rot);
            float r = c*sqrt(n);
            float radius = 50;
            float theta = n*PI*(3-sqrt(10));
            stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
            fill(255,map(r/2,1,width,0,500),28,40);
            float pulse = pow(sin(t*PI/3-n*PI/(t%100)),(float) 1.5);
           
            ellipse(r*cos(theta)/2,r*sin(theta)/2,pulse*radius+10,pulse*radius+6);
            popMatrix();
            pulse= 50 + (200 * getSmoothedAmplitude()); 
        }
            
    }

    int colorFromOffset(int offset) {
        return (int) ((offset + OFF_MAX) / (2.0 * OFF_MAX) * 255);}
}
 