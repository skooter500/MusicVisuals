package C20401562;

import ie.tudublin.*;
import processing.core.*;

public class AlexsVisual extends Visual{

    Start s;
    float start = 0;

    //_____________Constructor 

    public AlexsVisual(Start start) 
    {
        this.s = start;
    }

    public void render()
    {
        float amp = s.getSmoothedAmplitude();
        

        s.fill(map(amp, 0, 1, 0, 255), 255, 255);
        s.noStroke();

        //Creating Random dots on the scren as soon as beat is detected
        if(s.beat.isOnset()){
            for(int i = 0; i < 50; i++){
                s.ellipse(random(s.width), random(s.height), 6, 6);
            }
        }

        //______________Center Matrix
        s.pushMatrix();
            //Translating to middle of the screen
            s.translate(s.width/2, s.height/2);


                //_________________Center Circle
                s.fill(15);
                s.circle(0, 0, 500);

                //_________________Center Graphic
                for(int i = 0 ; i <= 180 ; i++)
                {

                    s.strokeWeight(2);
                    float index = PApplet.map(i, 0 , 180 , 0, s.getAudioBuffer().size() - 1);
                    s.stroke(PApplet.map(index, 0, s.getAudioBuffer().size(), 0, 255), 255,255);

                    //Setting the length mapped to the index of the current audioBuffer
                    float lenght = PApplet.map(s.getAudioBuffer().get((int)index) /2, -1 , 1, 0, 250);

                    //Setting X and Y
                    float x = lenght * -sin(i);
                    float y = lenght * cos(i);

                    //Multiple Lines created to give the effect
                    s.line(x, y,x *1.25f,y);
                    s.line(y, x,y *1.25f ,x);
                    s.line(x, y * 1.25f,x,y);

                }


                //_______________Devil Face

                s.stroke(map(amp, 0, 1, 0, 255), 255, 255);
                s.fill(0);
                s.strokeWeight(6);

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

                //Smile
                s.strokeWeight(3);
                s.curve(-30, -180, -30, 10, 30, 10, 30, -180);


                //_________________Outer Circle Waves

                float space = 0.1f;
                s.noStroke();

                for(float i = 0f; i < 360; i += space){

                    float xoff = PApplet.map(cos(i), -1, 1, 0 ,3);
                    float yoff = PApplet.map(sin(i), -1, 1, 0 ,3);

                    float n = noise(xoff + start, yoff + start);

                    //Height of rectangles
                    float h = PApplet.map(n, 0, 1, (amp*250) * -1 ,amp*500);

                    //Colour to change with song Amp
                    s.fill(map(amp, 0, 1, 0, 255), 255, 255);

                    //Rotate each rect around the circle
                    s.rotate(space);

                    s.rect(250,0,h,3);

                }
                //Increment starting value
                start += 0.1f;

        s.popMatrix();

        //___________End Of Center Matrix



        //____________Side Graphics
        
        s.stroke(map(amp/2, 0, 1, 0, 255), 255, 255);
        s.strokeWeight(1);

        float rot = 0;
        rot += amp / 8.0f;
        float r = PApplet.map(amp, 0, 1, 10, 100);
        
        s.pushMatrix();
            //Right Side
            s.translate(s.width/2 + 500, s.height/2);

            for (int i = 0; i < 360; i++){
                    float x = r * cos(i);
                    float y = r * sin(i);

                    s.rotateY(rot);
                    s.triangle(x, y, x *1.5f, y * 1.5f, x * 2.75f, x * 2.75f);
            }

        s.popMatrix();

        s.pushMatrix();
            //Left Side
            s.translate(s.width/2 - 500, s.height/2);

            for (int i = 0; i < 360; i++) {
                float x = r * cos(i);
                float y = r * sin(i);

                s.rotateY(rot);
                s.triangle(x, y, x *1.5f, y * 1.5f, x * 2.75f, x * 2.75f);
            }

        s.popMatrix();


    
    }
    //________________End Render
    
}
