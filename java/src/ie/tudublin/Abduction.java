package ie.tudublin;

import c19444404.RyansVisual;
import processing.core.PApplet;

public class Abduction extends Vision {

    public Abduction (RyansVisual rv)
    {
        super(rv);
    }

    float[] lerpedBuffer = new float[512];

    public void render()// cow abduction 
    {
        
    rv.background(0);


    for(int i=0;i< rv.getAudioBuffer().size(); i++)
    {
        float c = PApplet.map(i,0,rv.getAudioBuffer().size(),0,255);
        rv.stroke(c,255,255);
        lerpedBuffer[i] = PApplet.lerp(lerpedBuffer[i], rv.getAudioBuffer().get(i), 0.1f);
    }

        float r = 1f;
        int numPoints = 2;
        float thetaInc = PApplet.TWO_PI / (float) numPoints;
        rv.strokeWeight(1);                
        float lastX = rv.width /4 , lastY = rv.height/4 ;
        for(int i = 0 ; i < 100 ; i ++) // creating rays for the ufo
        {
            rv.noFill();
            float c = PApplet.map(i, 0, 300, 0, 255) % 255.0f; //mapping colours
            rv.stroke(c, 255, 255, 100);
            float theta = i * (thetaInc + rv.getSmoothedAmplitude() * 5);
            float x = rv.width / 2 + PApplet.sin(theta) * r;// the rotations
            float y = rv.height / 2 - PApplet.cos(theta) * r;
            r += 0.5f + rv.getSmoothedAmplitude();
            rv.line(lastX, lastY, x, y);
            
        }
        for(int i = 0 ; i < 100 ; i ++)// creating of the second ray 
        {
            float c = PApplet.map(i, 0, 300, 0, 255) % 255.0f;
            rv.stroke(c, 255, 255, 100);
            float theta = i * (thetaInc + rv.getSmoothedAmplitude() * 5);
            float x = rv.width / 4 + PApplet.sin(theta) * r;
            float y = rv.height / 4 - PApplet.cos(theta) * r;
            r += 0.5f + rv.getSmoothedAmplitude();
            rv.line(lastX, lastY, x, y);
            
        }
       
            rv.noFill();

            rv.stroke(255); // design of the cow and of the ufo
            rv.fill(255);
            rv.ellipse(rv.width/2,rv.height/2,140,60);
            rv.ellipse(rv.width/2+70,rv.height/2-30,40,20);
            rv.rect(rv.width/2-35,rv.height/2+25,15,30);
            rv.rect(rv.width/2+30,rv.height/2+20,15,35);
    
            rv.fill(0);
            rv.noStroke();
            rv.ellipse(rv.width/2-20,rv.height/2+20,25,18);//spot one
            rv.ellipse(rv.width/2-25,rv.height/2+15,30,18);//spot one
            rv.ellipse(rv.width/2-45,rv.height/2,25,18);//spot one
    
    
    
    
           rv. ellipse(rv.width/2-35,rv.height/2-8,30,12);//spot two
           rv. ellipse(rv.width/2-20,rv.height/2+10,20,12);
    
            rv.ellipse(rv.width/2+40,rv.height/2,20,25);//spot three
            rv.ellipse(rv.width/2+30,rv.height/2+13,30,20);//spot three
    
            
            rv.ellipse(rv.width/2+5,rv.height/2-10,20,25);//spot three
            rv.ellipse(rv.width/2,rv.height/2-13,30,20);//spot three

            rv.fill(120);
            rv.ellipse(rv.width/4, rv.height/4,150,70);
            rv.fill(120);
            rv.ellipse(rv.width/4, rv.height/4 -30,80,50);
    }
    
}
