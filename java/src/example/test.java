package example;


import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ie.tudublin.Visual;
import ie.tudublin.VisualException;
import processing.core.PShape;


public class test extends Visual
{
    Minim minim; 
    AudioInput ai;
    AudioBuffer ab;
    AudioBuffer abRight;
    AudioBuffer abLeft;
    AudioPlayer ap; 
    FFT fft; 
    PShape cube; 

    int frameSize = 1024;
    
    public void settings()
    {   
        size(512 , 512, P3D);
        // fullScreen(P3D, SPAN); 
    }
    
    public void setup()
    {
        colorMode(HSB);
        background(0);

        
        smooth();
        minim = new Minim(this);
        ai = minim.getLineIn(Minim.MONO, frameSize, 44100, 16);
        
        ap = minim.loadFile("Genesis.mp3", 512);
        
        
        ab = ap.mix; 
        abRight = ap.right;
        abLeft = ap.left; 
        
        ap.play(); 
        fft = new FFT(width, 44100);

        cube = loadShape("t.obj");
        cube.width = 1000;
        cube.height = 1000;
        // cube.translate(width/2, height/2, 0);

        cube.rotateX(radians(+140));

        // cube.translate(width/2, height/2, 0);
        cube.rotateX(radians(10));
        // cube.setFill(color(46, 100, 50));

        System.out.println(cube.width + " " + cube.height);

    }

    float lerpedBuffer[] = new float[512];
    float zAxis = 1; 
    int test = 1; 
    float average;
    float cubeScale = 1; 

    public void grow()
    {
        if(cubeScale >= 1.1)
            return; 

        cubeScale = cubeScale * 1.1f;
        cube.scale(1.1f); 
    }

    public void shrink()
    {
        if(cubeScale <= 0.9)
            return; 

        cubeScale = cubeScale * 0.9f;
        cube.scale(0.9f); 
    }

    boolean polarity = true; 
    public void pulse()
    {
        if(polarity)
        {
            polarity = !polarity;
            while(cubeScale < 1.1)
                grow(); 
        }
        else 
        {
            polarity = !polarity;
            while(cubeScale > 1)
                shrink(); 

        }
    }

    float radius = 200;

    float smoothedBoxSize = 0;

    float rot = 0;


    public void draw()
    {



        float[] bands = getSmoothedBands();
        for(int i = 0 ; i < bands.length ; i ++)
        {
            float theta = map(i, 0, bands.length, 0, TWO_PI);

            stroke(map(i, 0, bands.length, 0, 255), 255, 255);
            float x = sin(theta) * radius;
            float z = cos(theta) * radius;
            float h = bands[i];
            pushMatrix();
            translate(x, - h / 2 , z);
            rotateY(theta);
            box(50, h, 50);
            popMatrix();
        }
        
        smooth();

        background(0);
        lights();
        shape(cube);
        lights();



        camera(0f, 0f, height * .86602f, 0f, 0f, 0f, 0f, 1f, 0f);
        // cube.translate(0, 0, 10);
        cube.rotateX(radians(0.5f));
        // cube.rotateY(radians(0.25f));
        // cube.translate(0, 0, -1f);


        // float cgap = 255 / (float)ab.size();
        // float half = height/2;

        // float sum = 0;
        
        // for(int i = 0; i < ab.size(); i++)
        // {
        //     // if(ab.get(i) < 0.1 && ab.get(i) > -0.1)
        //     //     lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
        //     // else 
        //     lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.25f);
        //     sum += ab.get(i);
        // }       

        // average = lerp(average, sum / ab.size(), 0.05f);
            

        // for(int i = 0; i < ab.size(); i++)
        // {
            
        //     stroke(cgap * i, 255, 255);
        //     float f = lerpedBuffer[i] * half * (1 + (i / 100)); 

        //     line(i - ab.size() / 2, half + f, i - ab.size() / 2, half - f);

        //     sum += abs(ab.get(i));

        //     // line(i / 4, half / 2, half / 2 + test, i / 2);
        //     // line(width-(i / 4), half / 2, half / 2 + test, i / 2);


        // }

        // int heighestIndex = 0;
        // fft.forward(ab);
        // for(int i = 0; i < fft.specSize(); i++)
        // {
        //     line(i, height, i, height - fft.getBand(i));

        //     if(fft.getBand(i) > fft.getBand(heighestIndex))
        //         heighestIndex = i;
            
        // }


        // float freq = fft.indexToFreq(heighestIndex);
        // fill(255);
        // text("Freq: " + freq, 10, 50);
        // if(freq > 200)
        //     pulse();




        // fill(100);
        // if(freq < 50
        // )
        //     circle(100, 100, 100);


        // float average = sum / ab.size();
        // // System.out.printf("%.2f\n", average);

        // noFill(); 

        // float radius = average * 1000; 
        // float lerpedR = 100; 
        // lerpedR = lerp(lerpedR, radius, 0.25f);

        // circle(width / 2, half, lerpedR);




    }
}