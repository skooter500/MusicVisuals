package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class vines extends PApplet
{
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    FFT fft;

    int mode = 0;

    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;
    float halfH = height / 2;
    float halfW = width/2;

    public void settings() {
        size(1024, 1000);
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("strawberry_fields_forever.mp3", 1024); //CHANGE
        ap.play();
        ab = ap.mix;
        colorMode(HSB);
        

        y = height / 2;

        fft = new FFT(width, 1024);
    }

    public void vine(float x, float y) 
    {
        float y1, y2, y3, y4;
        float size1;
        float size2;
        float size3;
        
        y1 = 800;
        //x2 = x-200;
        y2 = 750;
        y3 = y2-70;
        y4 = y3-60;
        size1 = 80;
        size2 = 60;
        size3 = 30;

        //stroke(80, 190, 150); // green

        // trunk
        stroke(20, 100, 120);
        strokeWeight(30);
        line(x, y1, x, y2-y);
 
        // leaves
        strokeWeight(10);
        stroke(80, 220, 100);
        fill(80, 220, 100);
        triangle(x-size1, y2-y, x+size1, y2-y,x, y2-size1-y);
        stroke(80, 220, 110);
        triangle(x-size2, y3-y, x+size2, y3-y,x, y3-size2-y);
        stroke(80, 220, 120);
        triangle(x-size3, y4-y, x+size3, y4-y,x, y4-size3-y);

        //triangle(x2-size2, y2-y, x2+size2, y2-y,x2, y2-size2-y);

    }

    public void clouds() 
    {
        float x1;
        float y1;

        float x2;
        float y2;

        x1 = 100;
        y1 = 700;

        x2 = 100;
        y2 = 500;
                
        strokeWeight(4);
        //stroke(130, 200, 200);
        stroke(130, 60, 255);
        fill(130, 100, 250);
        circle(x, y, size);
        stroke(130, 60, 255);
        fill(130, 60, 255);
        circle(x+20, y-20, size/5);
        circle(x+30, y-5, size/10);
        
    }

    float lerpedBuffer[] = new float[1024];
    float totalX = 100;
    float totalY = 0;

    public void draw()
    {
        float average = 0;
        float sum = 0;
        int highestIndex = 0;

        background(150,120,160);


        for(int i = 0 ;i < fft.specSize() / 2 ; i ++)
        {
            line(i * 2.0f, height, i * 2.0f, height - fft.getBand(i) * 5.0f);

            if (fft.getBand(i) > fft.getBand(highestIndex))
            {
                highestIndex = i;
            }
        }

        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);

        }
        average= sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
        float freq = fft.indexToFreq((int)(smoothedAmplitude * 100000.0f));
        System.out.println(freq);
        

        freq = freq / 100;
        System.out.println(freq);


        // if the song gets louder

        if (freq > 100)
        {
            // increase the second coordinate of y
            totalY++;
        }

        // if it dips below a certain level and isn't at starting height
        if (freq < 40 && totalY>0)
        {
            // shrinks the tree
            totalY--;
        }


        // generates trees along ground
        vine(totalX,totalY); 
        vine(totalX+200,totalY-10); 
        vine(totalX+400,totalY+10); 
        vine(totalX+600,totalY-12); 
        vine(totalX+800,totalY+20); 
        
        }
    }        

