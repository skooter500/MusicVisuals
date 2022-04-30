package C20401562;

import ie.tudublin.*;
import java.util.Random; 

public class JaycelsVisual extends Visual{

    Start start;

    float rot = 0;

    float x;
    float y;
    float z;
    float colour;
    int rainlength = 80;

    
    float lerpedAverage = 0;

    
    float average;
    float sum;

    public JaycelsVisual(Start start) 
    {
        this.start = start;

        x = RandomNumber();
        y = RandomNumber();
        z = y + rainlength;
       
    }
    public void update()
    {
        y += 9;
        z += 9;

        if(y > start.height / 2)
        {
            y = 0;
            z = y + rainlength;
        }
    }

    public int RandomNumber()
    {
        Random rand = new Random();
        return rand.nextInt(start.width);
    }

    public void render()
    {

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

        start.background(0);

        start.translate(start.width/2, start.height/2); //translate to the center of the screen
        start.getFFT().forward(start.as.mix); //load the audio to the fft
        start.pushMatrix();
        start.setBands(start.getFFT().getSpectrumReal()); //load the fft to bands

        //Circle
        float angle = 0;
        float cir = 180; // radius

        for (int i = 0; i < 360; i++) {
            float color = start.getBands()[i] / 2; //get the current fft used for color
            start.noStroke();
            start.fill(start.random(color, 200), start.random(color, 255), 200);
            start.lerpedBuffer[i] = start.lerp(start.lerpedBuffer[i], start.getBands()[i], 0.08f); //smooth the circle
            start.ellipse(start.sin(angle) * ( start.lerpedBuffer[i]), -start.cos(angle) * (cir + start.lerpedBuffer[i]), start.abs(start.lerpedBuffer[i] * 4), start.abs(start.lerpedBuffer[i] * 4));
            angle += start.PI / 20; //draw 20 circles on the screen to form a big circle
        }

        //Ring
        float ang = 0;
        int stick = 15; //the length of the stick

        for (int j = 0; j < 360; j++) {
            float color = start.getBands()[j]; //get the current fft used for color
            start.stroke(start.random(color, 200), start.random(color, 150), 200, 180);
            start.strokeWeight(3);
            start.lerpedBuffer2[j] = start.lerp(start.lerpedBuffer2[j], start.getBands()[j], 0.5f); //smooth the circle
            start.line(-start.cos(ang) * (start.lerpedBuffer2[j] + stick), start.sin(ang) * (cir + start.lerpedBuffer2[j] + stick), -start.cos(ang) * (cir - start.lerpedBuffer2[j] - stick), start.sin(ang) * (cir - start.lerpedBuffer2[j] - stick));
            ang += start.PI / 40; //draw 40 lines on the screen to form a big circle
        }

        //top line
        for (int x = 0; x < 60; x += 2) {
            float color = start.getBands()[x]; //get the current fft us for color
            float position = start.random(color, start.width); //find a random position to draw the line
            start.stroke(start.random(color, 250), start.random(color, 50), 250);
            start.strokeWeight(5);
            start.line(position - start.width / 2, -(start.height / 2), position - start.width / 2, color * 2 + 40 - start.height / 2);
        }

        //bottom line
        for (int x = 0; x < 60; x += 2) {
            float color = start.getBands()[x]; //get the current fft us for color
            float position = start.random(color, start.width); //find a random position to draw the line
            start.stroke(start.random(color, 250), start.random(color, 50), 250);
            start.strokeWeight(5);
            start.line(position - start.width / 2, start.height / 2, position - start.width / 2, -(color * 2) - 40 + start.height / 2);
        }
        start.popMatrix();
       
        // flower dots
        start.rotate(radians(rot));
        for(int i = 0; i < 360; i++) {
            float color = start.getBands()[i] / 2; //get the current fft used for color
            start.stroke(start.random(color, 200), start.random(color, 150), 200, 180);
            
            float r = 50 * cos(4 * i);
            float x = r * cos(i);
            float y = r * sin(i);

            start.point(10 *(x - 40 * lerpedAverage * 0.1f), 10 * (y - 40 * lerpedAverage * 0.1f)); // get lerp avg

            rot += 1;
        }

        // random triangle

        colour = map(RandomNumber(), 0, start.width / 2, 0, 255);

        start.stroke(colour, 150, 255);
        start.strokeWeight(2);
        start.triangle(x, y, x, z, -10, -10);
    }
}
