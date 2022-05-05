package C20401562;

import ie.tudublin.*;
import processing.core.PApplet;

import java.util.Random; 

public class JaycelsVisual extends Visual{

    Start start;

    float rot = 0;

    float x;
    float y;
    float z;
    float colour;
    int beamLength = 80;

    float lerpedAverage = 0;
    
    float average;
    float sum;

    public JaycelsVisual(Start start) 
    {
        this.start = start;

        x = RandomNumber();
        y = RandomNumber();
        z = y + beamLength;
       
    }
    public void update()
    {
        y += 9;
        z += 9;

        if(y > start.height / 2)
        {
            y = 0;
            z = y + beamLength;
        }
    }

    public int RandomNumber()
    {
        Random rand = new Random();
        return rand.nextInt(start.width);
    }

    public void render()
    {
        
        start.background(0);

        average = 0;
        sum = 0;

        // Calculate the average of the buffer
        for (int i = 0; i < start.getAudioBuffer().size(); i ++)
        {
            sum += abs(start.getAudioBuffer().get(i));
        }
        average = sum / start.getAudioBuffer().size();
        // Move lerpedAverage 10% closer to average every frame
        lerpedAverage = lerp(lerpedAverage, average, 0.1f);


        //start.getFFT().forward(start.ap.mix); //load the audio to the fft
        start.pushMatrix();
        start.translate(start.width/2, start.height/2); //translate to the center of the screen
        start.setBands(start.getFFT().getSpectrumReal()); //load the fft to bands

        //Circle
        float angle = 0;
        float cir = 180; // radius

        for (int i = 0; i < 360; i++) {
            float color = start.getBands()[i] / 2; //get the current fft used for color
            start.noStroke();
            start.fill(start.random(color, 400), start.random(color, 300), 280);
            start.lerpedBuffer[i] = PApplet.lerp(start.getAmplitude()*20, start.getBands()[i], 0.08f); //smooth the circle
            start.ellipse(sin(angle) * ( start.lerpedBuffer[i]), -cos(angle) * (cir + start.lerpedBuffer[i]), PApplet.abs(start.lerpedBuffer[i] * 4), PApplet.abs(start.lerpedBuffer[i] * 4));
            angle += PI / 20; //draw 20 circles on the screen to form a big circle
        }

        //Ring
        float ang = 0;
        int stick = 15; //the length of the stick

        for (int j = 0; j < 360; j++) {
            float color = start.getBands()[j]; //get the current fft used for color
            start.stroke(start.random(color, 400), start.random(color, 300), 300, 200);
            start.strokeWeight(3);
            start.lerpedBuffer2[j] = PApplet.lerp(start.getAmplitude()*40, start.getBands()[j], 0.5f); //smooth the circle
            start.line(-cos(ang) * (start.lerpedBuffer2[j] + stick), sin(ang) * (cir + start.lerpedBuffer2[j] + stick), -cos(ang) * (cir - start.lerpedBuffer2[j] - stick), sin(ang) * (cir - start.lerpedBuffer2[j] - stick));
            ang += PI / 40; //draw 40 lines on the screen to form a big circle
        }

        if(start.ap.isPlaying()) {
            //top line
            for (int x = 0; x < 60; x += 2) {
                float color = start.getBands()[x]; //get the current fft us for color
                float position = start.random(color, start.width); //find a random position to draw the line
                start.stroke(start.random(color, 400), start.random(color, 300), 300);
                start.strokeWeight(5);
                start.line(position - start.width / 2, -(start.height / 2), position - start.width / 2, color * 2 + 40 - start.height / 2);
                //start.line(position - (start.lerpedBuffer2[x] - start.width / 2, -(start.height / 2) ), position - start.lerpedBuffer2[x] - start.width / 2,color * 2 + 40 - start.height / 2);
            }

            //bottom line
            for (int x = 0; x < 60; x += 2) {
                float color = start.getBands()[x]; //get the current fft us for color
                float position = start.random(color, start.width); //find a random position to draw the line
                start.stroke(start.random(color, 400), start.random(color, 300), 300);
                start.strokeWeight(5);
                start.line(position - start.width / 2, start.height / 2 - 60 , position - start.width / 2 , -(color * 2) - 40 + start.height /2 - 60);
            }

              // random triangle
            colour = map(RandomNumber(), 0, start.width / 2, 0, 255);

            start.stroke(colour, 150, 255);
            start.strokeWeight(2);
            start.triangle(x, y, x, z, -10, -10);
        }

        
        // flower dots
        start.rotate(radians(rot));

        for(int i = 0; i < 360; i++) {
            float color = start.getBands()[i] / 2; //get the current fft used for color
            start.stroke(start.random(color, 400), start.random(color, 300), 300, 200);
            
            float r = 30 * cos(6 * i);
            float x = r * cos(i);
            float y = r * sin(i);

            start.point(10 *(x - 40 * lerpedAverage * 2f), 10 * (y - 40 * lerpedAverage * 2f)); // get lerp avg

            rot += 1;
        }
        
        start.popMatrix();

        // // Building
        // for (int i = 0; i < 360; i++) {
        //     float color = start.getBands()[i] / 2; //get the current fft used for color
        //     start.noStroke();
        //     start.fill(start.random(color, 400), start.random(color, 300), 280);
        //     start.rect(560, 80, -220, 220);
            
        // }

         // CENTRE OF FLOWER BLUE PART
         float r = 1f;
            float thetaInc = PApplet.TWO_PI / (float) 3;

            
 
         // ----- light blue inner circle --------
         start.pushMatrix();
 
         for (int i = 0; i < 75; i++) {
             start.strokeWeight(2);
             start.fill(map(start.getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);

             float theta = i * (thetaInc + start.getSmoothedAmplitude() * 5);
             start.pushMatrix();
             x = 50 + PApplet.sin(theta) * r;
             y = 50 - PApplet.cos(theta) * r;
             r += 2f + start.getSmoothedAmplitude();
 
             start.fill(0);
             start.translate(start.width / 2 - 500, start.height / 2);
             start.ellipse(0, 0, x, y);
             start.translate(1000, 0);
             start.ellipse(0, 0, x, y);


 
             start.popMatrix();
 
         } /// end loop
         start.popMatrix();

        

    }
}
