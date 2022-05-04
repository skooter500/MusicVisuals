package c20474596;

import java.util.ArrayList;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PVector;



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
	ArrayList<PVector> circle = new ArrayList<PVector>();
ArrayList<PVector> square = new ArrayList<PVector>();

// An ArrayList for a third set of vertices, the ones we will be drawing
// in the window
ArrayList<PVector> morph = new ArrayList<PVector>();

// This boolean variable will control if we are morphing to a circle or square
boolean state = false;


    
       

    public void setup(){
        for(int i = 0; i < stars.length; i++){
            stars[i] = new Star();
        }
        minim=new Minim(this);
        startMinim();
        getFFT();
        loadAudio("rain.mp3");
        colorMode(HSB,255);   
        hue = random(255);
        //sean edits
        noStroke();
        for(int i =0;i<drops.length; i++){
            drops[i]= new drop();
        }

        // Create a circle using vectors pointing from center
  for (int angle = 0; angle < 360; angle += 9) {
    // Note we are not starting from 0 in order to match the
    // path of a circle.  
    PVector v = PVector.fromAngle(radians(angle-135));
    v.mult(350);
    circle.add(v);
    // Let's fill out morph ArrayList with blank PVectors while we are at it
    morph.add(new PVector());
  }

  // A square is a bunch of vertices along straight lines
  // Top of square
  for (int x = -50; x < 50; x += 10) {
    square.add(new PVector(x, -150));
  }
  // Right side
  for (int y = -50; y < 50; y += 10) {
    square.add(new PVector(150, y));
  }
  // Bottom
  for (int x = 50; x > -50; x -= 10) {
    square.add(new PVector(x, 150));
  }
  // Left side
  for (int y = 50; y > -50; y -= 10) {
    square.add(new PVector(-150, y));
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
           

        switch(mode){




            case 1: //OWN CODE NOT FROM EXAMPLE PACKAGE
            {
                getFFT();
                calculateFrequencyBands();
                calculateAverageAmplitude();
                getAmplitude();
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
                        stars[i].z = stars[i].z-25;
                        if(stars[i].z < 1){
                            stars[i].z = width;
                            stars[i].x = random(-width,width);
                            stars[i].y = random(-height,height);
                            stars[i].pz = stars[i].z;
                        }
                    }
               
               
            }

            case 2: //MUST ALTER - COPIED FROM EXAMPLE.
            {


                              // We will keep how far the vertices are from their target
  float totalDistance = 0;
  
  // Look at each vertex
  for (int i = 0; i < circle.size(); i++) {
    PVector v1;
    // Are we lerping to the circle or square?
    if (state) {
      v1 = circle.get(i);
    }
    else {
      v1 = square.get(i);
    }
    // Get the vertex we will draw
    PVector v2 = morph.get(i);
    // Lerp to the target
    v2.lerp(v1, (float) 0.1);
    // Check how far we are from target
    totalDistance += PVector.dist(v1, v2);
  }
  
  // If all the vertices are close, switch shape
  if (totalDistance < 0.1) {
    state = !state;
  }
  float boxSize = 200 + (700 * getSmoothedAmplitude()); 
    box(boxSize);
  
  // Draw relative to center5
  translate(width/2, height/2);
  strokeWeight(4);
  // Draw a polygon that makes up all the vertices
  beginShape();
  getFFT();
  calculateFrequencyBands();
  calculateAverageAmplitude();
  getAmplitude();
  noFill();
  stroke(255);
  for (PVector v : morph) {
    vertex(v.x, v.y);
  }
  endShape(CLOSE);


                /*getFFT();
                calculateFrequencyBands();
                calculateAverageAmplitude();
                stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
                strokeWeight(2); //make the cube thicker that the stars to add contrast
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
                angle += 0.01f;  */
            }

           case 3://CODE MUST BE ALTERED
           {
            if (key == '3')
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
            if (key == '4')
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
            
            case 5:{

  
                  
            }

            
        }

    }
            




    private void circles() {
        calculateAverageAmplitude();
        getFFT();
        calculateFrequencyBands();//CODE MUST BE ALTERED-SEAN
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
 