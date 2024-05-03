package C22454222;
import ddf.minim.AudioInput;
import ddf.minim.Minim;
import processing.core.PApplet; 
import ie.tudublin.*;
import ddf.minim.AudioBuffer;

public class ChrisVisual extends Visual 
{
    MainVisual mv;
    Minim minim;
    AudioInput ai;
    AudioBuffer ab;

    float[] lerpedBuffer;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    public ChrisVisual(MainVisual mv) 
    {
        this.mv = mv;
        this.ab = mv.getAudioBuffer();
        this.ai = mv.getAudioInput();
    }

    float[] currentColours = new float[]{random(0,255),random(0,255),random(0,255)};
    float[] previousColours = new float[]{0,0,0};
    int kickCounter = 0;
 
    public float[] replacingColours(float[] currentColours)
    {
        float[] colours = new float[]{random(0,255),random(0,255),random(0,255)};
        for(int i = 0; i < 3; i++)
        {
            currentColours[i] = colours[i];
        }
        return currentColours;
    }

    public void render()
    {
        float radius = 20;
        mv.background(0,0,0);
        mv.beat.detect(mv.as.mix);
        mv.beat.detectMode(0);
        mv.fCounter++;
        mv.translate(mv.width/2, mv.height/2);

        float smooth = mv.getSmoothedAmplitude();

        if (mv.beat.isKick())
        {
            kickCounter++;
        }

        mv.fill(currentColours[0],currentColours[1],currentColours[2], 50);

        radius = 40*kickCounter;
        mv.ellipse(0,0,radius,radius);

        mv.fill(previousColours[0],previousColours[1],previousColours[2], 25);
        
        mv.stroke(map(mv.getAmplitude(), 0, 1, 0,255),255,255);   
        
            radius = 15*kickCounter;
            mv.triangle(-200, -100, -140+(float)0.6*radius, radius, (-260-(float)0.6*radius), radius); 
            mv.triangle(200, -100, 260+(float)0.6*radius, radius, (140-(float)0.6*radius), radius);
            mv.triangle(-400, -100, -340+(float)0.6*radius, radius, (-460-(float)0.6*radius), radius); 
            mv.triangle(400, -100, 460+(float)0.6*radius, radius, (340-(float)0.6*radius), radius);
            mv.triangle(-600, -100, -540+(float)0.6*radius, radius, (-660-(float)0.6*radius), radius); 
            mv.triangle(600, -100, 660+(float)0.6*radius, radius, (540-(float)0.6*radius), radius);
            
            mv.triangle(-200, 100, -140+(float)0.6*radius, -radius, (-260-(float)0.6*radius), -radius); 
            mv.triangle(200, 100, 260+(float)0.6*radius, -radius, (140-(float)0.6*radius), -radius);
            mv.triangle(-400, 100, -340+(float)0.6*radius, -radius, (-460-(float)0.6*radius), -radius); 
            mv.triangle(400, 100, 460+(float)0.6*radius, -radius, (340-(float)0.6*radius), -radius);
            mv.triangle(-600, 100, -540+(float)0.6*radius, -radius, (-660-(float)0.6*radius), -radius); 
            mv.triangle(600, 100, 660+(float)0.6*radius, -radius, (540-(float)0.6*radius), -radius);

            if (radius > (float)mv.width / 2)
            {
                for (int i = 0; i < 3; i++)
                {
                    previousColours[i] = currentColours[i];
                }
                replacingColours(currentColours);
                kickCounter = 0;
                radius = 20;
            }

            for (float i = 0; i < mv.width; i += 0.3)
            {
                float x1 = (PApplet.cos(i) * smooth * i);
                float y1 = (PApplet.sin(i) * smooth * i);
                mv.point(x1, y1);
            }


            for (float i = 0; i < mv.width; i += 0.3)
            {
                float centerX2 = -400; 
                float centerY2 = 0; 
                float x2 = centerX2 + (PApplet.cos(i) * smooth * i);
                float y2 = centerY2 + (PApplet.sin(i) * smooth * i);
                mv.point(x2, y2);
            }

            for (float i = 0; i < mv.width; i += 0.3)
            {
                float centerX3 = +400; 
                float centerY3 = 0; 
                float x3 = centerX3 + (PApplet.cos(i) * smooth * i);
                float y3 = centerY3 + (PApplet.sin(i) * smooth * i);
                mv.point(x3, y3);
            }
    }
}


