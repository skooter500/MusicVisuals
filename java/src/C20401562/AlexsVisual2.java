package C20401562;

import ie.tudublin.Visual;
import processing.core.*;

public class AlexsVisual2 extends Visual{

    Start s;

    float[] triangle = {145, 105, 65, 25};


    float lerpedAverage = 0;


    //_____________Constructor 

    public AlexsVisual2(Start start) 
    {
        this.s = start;
    }

    public void render()
    {

        float average = 0;
        float sum = 0;
       
        // average of the buffer
        for(int i =0; i < ab.size(); i++)
        {
            sum += abs(ab.get(i));
        }

        average = sum / ab.size();

        // calculate the average amplitutde
        lerpedAverage = lerp(lerpedAverage,average, 0.1f);

        s.stroke(0);
        s.line(0,s.height/2,s.width,s.height/2);
        s.line(300,0,300,s.height);
        s.line(1100,0,1100,s.height);


        for (int i = 0; i < ab.size(); i++) {

            s.lerpedBuffer[i] = lerp(s.lerpedBuffer[i], ab.get(i), 0.1f);        
            s.line(i*3, s.height/2 - s.lerpedBuffer[i] * s.height, i*3, s.height/2 + s.lerpedBuffer[i] * s.height);

            s.line(300 - s.lerpedBuffer[i] * 350, i * 3,300 + s.lerpedBuffer[i] * 350, i * 3);

            s.line(1100 - s.lerpedBuffer[i] * 350, i * 3,1100 + s.lerpedBuffer[i] * 350, i * 3);
        }        

        s.pushMatrix();
        s.beginShape();
        s.translate(s.width/2, s.height/2); //Positioning to the centre

        s.noFill();
        
        if(s.ap.isPlaying()){
            s.angle += 0.01f + s.getSmoothedAmplitude() / 8;
            s.angle1 += 0.02f + s.getSmoothedAmplitude() / 8;
            s.angle2 += 0.03f + s.getSmoothedAmplitude() / 8;
        }

        s.rotateX(s.angle);
        s.rotateY(s.angle1);
        s.rotateZ(s.angle2);



        s.beginShape();
        for(int i = 0; i <= 3; i++){
            
            if(i == 3){
                s.stroke(255);
            }else{
                s.stroke(PApplet.map(s.getSmoothedAmplitude()*2*i, 0, 1, 0, 255), 255, 255);  //React to the colors
            }

            s.vertex(-triangle[i], -triangle[i], -triangle[i]);
            s.vertex( triangle[i], -triangle[i], -triangle[i]);
            s.vertex( 0, 0, triangle[i]);
    
            s.vertex( triangle[i], -triangle[i], -triangle[i]);
            s.vertex( triangle[i], triangle[i], -triangle[i]);
            s.vertex( 0, 0, triangle[i]);
        
            s.vertex( triangle[i], triangle[i], -triangle[i]);
            s.vertex(-triangle[i], triangle[i], -triangle[i]);
            s.vertex( 0, 0, triangle[i]);
        
            s.vertex(-triangle[i], triangle[i], -triangle[i]);
            s.vertex(-triangle[i], -triangle[i], -triangle[i]);
            s.vertex( 0, 0, triangle[i]);

            s.angle += 0.01f;

        }

        s.endShape();




        s.stroke(255);
        s.strokeWeight(1);

        s.translate(200, 200, 0);
        s.sphere(30 * s.getSmoothedAmplitude() * 7);

        s.translate(-100, -100, 0);
        s.sphere(30 * s.getSmoothedAmplitude() * 7);

        s.translate(-200, -200, 0);
        s.sphere(30 * s.getSmoothedAmplitude() * 7);

        s.translate(-100, -100, 0);
        s.sphere(30 * s.getSmoothedAmplitude() * 7);


        s.popMatrix();


    
    }
    //________________End Render
    
}
