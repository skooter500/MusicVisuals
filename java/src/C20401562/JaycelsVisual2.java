package C20401562;

import ie.tudublin.*;
import processing.core.PVector;

class Star extends Visual {

    Start s;
    float x,y,z;
    float pz;
    float speed;

    Star(Start start) {

        this.s = start;

        x = random(-width/2, width/2);
        y = random(-height/2, height/2);
        z = random(width/2);

        pz = z;
    } //end of star
    void update() {
        z = z - speed;
        if(z < 1) {
            z = width / 2;
            x = random(-width/2, width/2);
            y = random(-height/2, height/2);
            pz = z;
        }
    }

    void show() {
        s.fill(255);
        s.noStroke();


        float sx = map(x / z, 0, 1, 0, s.width/2);
        float sy = map(y / z, 0, 1, 0, height/2);

        float r = map(z, 0, width/2, 16, 0);
        s.ellipse(sx, sy, r, r);

        float px = map(x / pz, 0, 1, 0, width/2);
        float py = map(y / pz, 0, 1, 0, height/2);

        pz = z;

        s.stroke(255);
        s.line(px, py, sx, sy);

    }
}


public class JaycelsVisual2 extends Visual{

    Start start;
    Star Star;
    Star[] stars = new Star[800];

    float speed;

    public JaycelsVisual2(Start start) 
    {
        this.start = start;
    }

    public void setup(){

        for (int i = 0; i < stars.length; i++) {
           // stars[i] = new Star();
          }
    }

    public void render() {

        // Call this is you want to use frequency bands
        calculateFrequencyBands(); 

        // Call this is you want to get the average amplitude
        calculateAverageAmplitude();

        // i link the value of the speed variable to the mouse position.
        speed = map(mouseX, 0, width, 0, 50);

        start.background(0);
        // I shift the entire composition,
        // moving its center from the top left corner to the center of the canvas.
        start.translate(width/2, height/2);
        // I draw each star, running the "update" method to update its position and
        // the "show" method to show it on the canvas.
        for (int i = 0; i < stars.length; i++) {
            stars[i].update();
            stars[i].show();
        }
    }

}
