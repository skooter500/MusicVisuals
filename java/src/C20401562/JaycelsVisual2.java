package C20401562;

import ie.tudublin.*;
import processing.core.PApplet;

public class JaycelsVisual2 extends Visual{

    Start s;
    float lerpedAverage = 0;



    


    public JaycelsVisual2(Start start) 
    {
        this.s = start;

        
        circles = 12;
        theta = 1.0f;
        
    }

    float circles;
    float theta;

    public void render() {

        // s.background(0);
        s.noFill();
        s.pushMatrix();
        s.translate(s.width / 2, s.height / 2);
        s.strokeWeight(4);
    

        if(s.ap.isPlaying()){
            s.angle += 0.01f + s.getSmoothedAmplitude() / 8;
            s.angle1 += 0.02f + s.getSmoothedAmplitude() / 8;
            s.angle2 += 0.03f + s.getSmoothedAmplitude() / 8;

            s.rotateX(s.angle);
            s.rotateY(s.angle1);
            s.rotateZ(s.angle2);
    
        }else{

            s.rotateX(1);
            s.rotateY(1);
            s.rotateZ(1);

        }

        
        s.stroke(255);
        s.sphere(40);

        s.popMatrix();

        for (int i = 0; i < ab.size(); i++) {

            float c = PApplet.map(i, 0, ab.size(), 0, 255);
            s.stroke(c, 255, 255);
            s.lerpedBuffer[i] = lerp(s.lerpedBuffer[i], ab.get(i), 0.1f);        
            s.line(0, i * 2f, s.lerpedBuffer[i] * s.height, i * 2f);
            s.line(s.width, i * 2f, s.width - (s.lerpedBuffer[i] * s.height), i  * 2);
            s.line(i * 3, 0, i * 3, s.lerpedBuffer[i] * s.height);
            // s.line(i * 3, s.height - 81, i * 3, (s.height - 81) - (s.height * s.lerpedBuffer[i]) * 2);
        }     

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

        s.lights();
        s.strokeWeight(2);
        float c = PApplet.map(lerpedAverage * 10, 0, 1, 0, 255);
        s.stroke(c, 255, 255);
        s.noFill();
        if(s.ap.isPlaying()){
            s.angle += 0.01f + s.getSmoothedAmplitude() / 5;
        }
        float f = 100 + (100 * lerpedAverage * 10);
        

 
        s.pushMatrix();
        s.translate(s.width / 4 - 120, s.height / 2);
        s.rotateY(s.angle);
        s.rotateX(s.angle);
        s.box(f);
        s.popMatrix();

        s.pushMatrix();
        s.translate(s.width * 0.75f + 120, s.height / 2);
        s.rotateY(s.angle);
        s.rotateX(s.angle);
        s.box(f);
        s.popMatrix();

        float size = 100;

        s.pushMatrix();
        s.translate(s.width/2, s.height/2); //Putting it in the middle of the screen
        s.rotateX(s.angle); //Rotating each box
        s.rotateY(s.angle); 
        s.rotateZ(s.angle);
        
        for (int xo = (int)-size; xo <= size; xo += 30) {  //Make OFF max -300 till it reaches +300, and determing the spacing between each box
            for (int yo = (int)-size; yo <= size; yo += 30) {
            for (int zo = (int)-size; zo <= size; zo += 30) {
                s.pushMatrix();
                s.translate(xo, yo, zo); //Positions of each box when printed
                s.rotateX(s.angle); //Rotates boxes
                s.rotateY(s.angle);
                s.rotateZ(s.angle);
                s.noStroke();
                // s.stroke(PApplet.map(s.getAmplitude()*2, 0, 1, 0,255), 255, 255);  //Outline of each boxs color in relation to the amplitude
                s.strokeWeight(4); //The thickness of the lines
                s.fill(c); //Filling the inside of the boxes white
                if(s.beat.isOnset()){
                    s.fill(c, 255, 255); //Filling the inside of the boxes white

                }
                s.box((float) (15 + (Math.sin(s.angle1)) * 10)); //Printing each box
                s.popMatrix();
            }
            s.angle += 0.00005f; //Declaring the angle to determine what speed it rotates
            s.angle1 += 0.00005f; 

            }
        }
      s.popMatrix();

      }



      




}
