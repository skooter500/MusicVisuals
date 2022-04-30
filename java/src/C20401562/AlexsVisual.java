package C20401562;

import ie.tudublin.*;
import ie.tudublin.Visual;
import processing.core.*;

public class AlexsVisual extends Visual{

    Start s;
    float start = 0;
    float x,y;



    //Constructor 
    public AlexsVisual(Start start) 
    {
        this.s = start;
    }
    public void setup()
    {
        noiseDetail(2, 1);
        s.smooth();

    }

    public void render()
    {
        s.colorMode(PApplet.HSB);
        
        s.fill(0);
        s.strokeWeight(5);
        s.stroke(0, 0, 100);

        // for(int i = 0; i < s.width ;i++){
        //     float index = PApplet.map(i, 0 , s.width, 0, s.getAudioBuffer().size());
        //     float y = s.getAudioBuffer().get((int)index) * 50 + s.height / 2;

        //     s.stroke(PApplet.map(index, 0, s.getAudioBuffer().size()*2, 0, 255), 255, 255);

        //     float cy = 0;
        //     cy = this.s.height / 2;

        //     s.line(i, y, i, y + y* s.getAudioBuffer().get((int)index));

        // }

        s.pushMatrix();
        s.translate(s.width/2, s.height/2);


        s.noStroke();
        s.circle(0, 0, 500);

        for(int i = 0 ; i <= 180 ; i++)
        {

            s.strokeWeight(2);
            float index = PApplet.map(i, 0 , 180 , 0, s.getAudioBuffer().size() - 1);
            s.stroke(PApplet.map(index, 0, s.getAudioBuffer().size(), 0, 255), 255,255);

            float r = PApplet.map(s.getAudioBuffer().get((int)index) /2, -1 , 1, 0, 250);

            float x = r * -sin(i);
            float y = r * cos(i);

            s.line(x, y,x *1.25f,y);
            s.line(y, x,y * 1.25f,x);

            // s.line(x, y,y,x);

        }

        s.stroke(map(s.getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        s.fill(0);

        s.strokeWeight(8);

        //Right horn
        s.line(-15,-45,-35,-65);
        s.line(-30,-40,-35,-65);

        //Left horn
        s.line(15,-45,35,-65);
        s.line(30,-40,35,-65);

        s.strokeWeight(6);

        s.circle(0, 0, 100);

        //EyeLids
        s.curve(-10, 10, 8, -10, 28, -20, 30, 0);
        s.curve(10, 10, -8, -10, -28, -20, -30, 0);

        //Eyes
        s.ellipse(-15, 0, 5, 15);
        s.ellipse(15, 0, 5, 15);
        // s.noFill();

        //smile
        s.strokeWeight(3);
        s.curve(-30, -180, -30, 10, 30, 10, 30, -180);



        float space = 0.1f;
        s.noStroke();

        for(float i = 0f; i < 360; i += space){

            float xoff = PApplet.map(cos(i), -1, 1, 0 ,3);
            float yoff = PApplet.map(sin(i), -1, 1, 0 ,3);

            float n = noise(xoff + start, yoff + start);

            float h = PApplet.map(n, 0, 1, 0 ,s.getAmplitude()*100);

            s.fill(map(s.getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);

            s.rotate(space);
            s.rect(250,0,h,1);

        }
        start += 0.1f;

        s.popMatrix();
    }


    
}
