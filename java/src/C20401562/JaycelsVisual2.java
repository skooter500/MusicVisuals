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

    //____________Render

    public void render() {

        s.noFill();

        s.pushMatrix();

            s.translate(s.width / 2, s.height / 2);
            s.strokeWeight(4);
        
            //Setting the rotation to the song playing
            if(s.ap.isPlaying()){
                s.angle += 0.01f + s.getSmoothedAmplitude() / 8;
                s.angle1 += 0.02f + s.getSmoothedAmplitude() / 8;
                s.angle2 += 0.03f + s.getSmoothedAmplitude() / 8;

                s.rotateX(s.angle);
                s.rotateY(s.angle1);
                s.rotateZ(s.angle2);
        
            }else{//If paused slow rotaion
                s.rotateX(1);
                s.rotateY(1);
                s.rotateZ(1);
            }
            
            //Center Sphere
            s.stroke(255);
            s.sphere(40);

        s.popMatrix();

        //Creating Sin waves on the border
        for (int i = 0; i < ab.size(); i++) {

            float c = PApplet.map(i, 0, ab.size(), 0, 255);
            s.stroke(c, 255, 255);
            s.lerpedBuffer[i] = lerp(s.lerpedBuffer[i], ab.get(i), 0.1f);    
            
            //Waves
            s.line(0, i * 2f, s.lerpedBuffer[i] * s.height, i * 2f);
            s.line(s.width, i * 2f, s.width - (s.lerpedBuffer[i] * s.height), i  * 2);
            s.line(i * 3, 0, i * 3, s.lerpedBuffer[i] * s.height);
        }     

        float average = 0;
        float sum = 0;
       
        //Average of the buffer
        for(int i =0; i < ab.size(); i++)
        {
            sum += abs(ab.get(i));
        }

        average = sum / ab.size();

        //Calculate the average amplitutde
        lerpedAverage = lerp(lerpedAverage,average, 0.1f);

        //Shading
        s.lights();
        s.strokeWeight(2);

        float c = PApplet.map(lerpedAverage * 10, 0, 1, 0, 255);
        s.stroke(c, 255, 255);
        s.noFill();

        //Set cubiod rotation to the song
        if(s.ap.isPlaying()){
            s.angle += 0.01f + s.getSmoothedAmplitude() / 5;
        }

        float f = 100 + (100 * lerpedAverage * 10);
 
        //Side Boxes
        //Left
        s.pushMatrix();
            s.translate(s.width / 4 - 120, s.height / 2);

            s.rotateY(s.angle);
            s.rotateX(s.angle);
            s.box(f);
        s.popMatrix();

        //Right
        s.pushMatrix();
            s.translate(s.width * 0.75f + 120, s.height / 2);

            s.rotateY(s.angle);
            s.rotateX(s.angle);
            s.box(f);
        s.popMatrix();

        //____________Center Cubiod Graphic
        
        float size = 100;

        s.pushMatrix();

            s.translate(s.width/2, s.height/2);
            
            //Rotation Of Cubiod

            s.rotateX(s.angle);
            s.rotateY(s.angle); 
            s.rotateZ(s.angle);
            
            for (int xo = (int)-size; xo <= size; xo += 30) { //Spacing between each box
                for (int yo = (int)-size; yo <= size; yo += 30) {
                    for (int zo = (int)-size; zo <= size; zo += 30) {
                        s.pushMatrix();
                            s.translate(xo, yo, zo); //Positions of each cube

                            //Rotates boxes
                            s.rotateX(s.angle);
                            s.rotateY(s.angle);
                            s.rotateZ(s.angle);
                            s.noStroke();

                            s.strokeWeight(4);
                            s.fill(c); //Filling the inside of the boxes white

                            //Changing cubiod colour on beat detection
                            if(s.beat.isOnset()){
                                s.fill(c, 255, 255);
                            }

                            s.box((float) (15 + (Math.sin(s.angle1)) * 10));
                        s.popMatrix();
                    }
                s.angle += 0.00005f; //Speed of rotation
                s.angle1 += 0.00005f; 
                }
            }

      s.popMatrix();

      }



      




}
