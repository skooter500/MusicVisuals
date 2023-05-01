package ie.tudublin;

import ddf.minim.AudioBuffer;
// import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class notes extends PApplet{

    Minim m;
    AudioInput ai;
    AudioPlayer ap;
    AudioBuffer ab;

    FFT fft;

    float[] frequencies = {293.66f, 329.63f, 369.99f,
    392.00f, 440.00f, 493.88f, 554.37f, 587.33f
    , 659.25f, 739.99f, 783.99f, 880.00f, 987.77f, 1108.73f, 1174.66f};
    String[] spellings = {"D,", "E,", "F,", "G,", "A,", "B,", "C", 
    "D", "E", "F", "G", "A", "B","c", "d", "e", 
    "f", "g", "a", "b", "c'", "d'", "e'", "f'", "g'", "a'", "b'", "c''", "d''"};

    public void settings()
    {
        size(1024, 1024);
    }

    public void setup()
    {
        m = new Minim(this);
        //ai = m.getLineIn(Minim.MONO, width, 44100, 16);
        //ab = ai.mix;
        
        ap = m.loadFile("strawberry_fields_forever.mp3", 1024); //CHANGE
        ap.play();
        ab = ap.mix;
        colorMode(HSB);
    
        fft = new FFT(width, ab.size());
    }

    
    public void bubble(float x, float y, float size) 
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
    public void draw()
    {
        background(0);
        colorMode(HSB);
        stroke(255);

        float x = 500;
        float y = 600;
        float size = 100;
        float f;

        float half = height / 2;
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            background(0);
            stroke(map(i, 0, ab.size(), 0, 255), 255, 255);
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
            f = abs(lerpedBuffer[i] * half * 5.0f);
            //line(i, half + f, i, half - f);
            bubble(x,(y-f), size);
            
        }

        fft.forward(ab);
        stroke(255);

        int highestIndex = 0;
        for(int i = 0 ;i < fft.specSize() / 2 ; i ++)
        {
            line(i * 2.0f, height, i * 2.0f, height - (fft.getBand(i) * 100) * 0.50f);

            if (fft.getBand(i) > fft.getBand(highestIndex))
            {
                highestIndex = i;
            }
            
        }

        float freq = fft.indexToFreq(highestIndex);
        freq = freq * 10;
        //System.out.println(highestIndex);

        for (int i = 0; i > 15; i++){
            if (ab.get(i) == frequencies[i]){
                //System.out.println(spellings);
            }

        }

        fill(255);
        textSize(20);
        text("Freq: " + freq, 100, 100);

        
        //bubble(x,y, size);

        //println(map(5, 2, 10, 1000, 2000));
        //println(map1(5, 2, 10, 1000, 2000));
    }

    float lerpedY = 0;
}
