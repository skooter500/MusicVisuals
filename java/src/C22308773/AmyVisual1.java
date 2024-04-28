package C22308773;

import ddf.minim.AudioBuffer;
import ie.tudublin.OurVisual;
import processing.core.*;

public class AmyVisual1
{
    OurVisual ov;
    float cy = 0;
    float cx = 0;
    float hue = 0f;

    //cube variables
    float rotation;   
    float numCubes = 24f;                         
    float offset = (float)((float)ov.PI/(numCubes * 2));  

    //lines from centre visual variables
    int numLines = 30;            
    float angleStep = ov.TWO_PI / numLines; //spacing of the lines

    //square pattern variables
    Square[] squares;
    int numSquares = 12;
    

    public AmyVisual1(OurVisual ov)
    {
        this.ov = ov;
        cy = this.ov.height / 2;
        cx = this.ov.width / 2;


        squares = new Square[numSquares];
        for (int i = 0; i < numSquares; i++) 
        {
            squares[i] = new Square(); 
            squares[i].rotation = i * 24;
            squares[i].size = i * 25;
            squares[i].squareNum = i; 

        }
        
    }


    public void render()
    {

        ov.colorMode(PApplet.HSB);
        ov.lights();
        ov.background(0);

        //calling square pattern
        for (Square square : squares)
        {
            square.display();
        }


        //calling lines from centre visual
        for (int i = 0; i < numLines; i++) 
        {
            ov.noFill();

            hue = ov.map(ov.getSmoothedAmplitude(), 0, 1, 0, 256);
            ov.stroke(hue, 175, 255);
            float angle = i * angleStep;
            float x1 = cx + ov.cos(angle) * (ov.getSmoothedAmplitude() * (ov.height/2) * 1.5f);
            float y1 = cy + ov.sin(angle) * (ov.getSmoothedAmplitude() * (ov.height/2) * 1.5f);

            ov.line(cx, cy, x1, y1);

        }

        ov.translate(ov.width/2, ov.height/2); 
        
        //calling cube centre visual
        for(int i = 0; i < numCubes; i++) 
        {
            float hue = ov.map(ov.getSmoothedAmplitude(), 0, 1, 0, 256);
            ov.fill(hue, 175, 255);
            ov.stroke(hue, 100, 100);
            ov.pushMatrix();
            ov.rotateY(rotation + offset*i);
            ov.rotateX(rotation/2 + offset*i);
            ov.box(200 * (ov.getSmoothedAmplitude() * 3));
            ov.popMatrix();
        }
        rotation += 0.01f;    
        

    }


    class Square
    {
        float side = 100;
        float rotation = 0;
        float halfH = ov.height / 2;
        int size = 0;    
        int squareNum;

        
        public void display()
        {
            hue = ov.map(ov.getSmoothedAmplitude()+(squareNum/100), 0, 1, 0, 256);
            side = (ov.getSmoothedAmplitude() * halfH * 3) + size;

            ov.stroke(hue, 175, 255);
            ov.noFill();
            ov.strokeWeight(3);

            ov.pushMatrix();
            ov.translate(cx, cy);
            ov.rotate(rotation);
            ov.rectMode(ov.CENTER);
            ov.rect(0, 0, side, side, 25);
            ov.popMatrix();

            rotation += 0.01f;   
        }
    }
}
    
