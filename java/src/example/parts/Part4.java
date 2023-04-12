package example.parts;

import example.MyVisual;
import ie.tudublin.Visual;
import processing.core.PApplet;

public class Part4 {
    MyVisual mv;
    float ballX, ballY;
    float ballSpeedX, ballSpeedY;
    float ballSize;
    float angle;

    public Part4(MyVisual mv) {
        this.mv = mv;
        ballX = mv.random(mv.width);
        ballY = mv.random(mv.height);
        ballSpeedX = mv.random(-1, 1);
        ballSpeedY = mv.random(-1, 1);
        ballSize = 100;
        angle = mv.random(PApplet.TWO_PI);
    }

    public void render() {
        mv.colorMode(PApplet.HSB);
        mv.fill(mv.random(0, 255), mv.random(0, 255), mv.random(0, 255));
        float speed = mv.getAmplitude() * 1000;
    
        ballX += speed * PApplet.cos(angle);
        ballY += speed * PApplet.sin(angle);
    
        if (ballX < ballSize / 2) {
            ballX = ballSize / 2;
            angle = PApplet.PI - angle;
            changeColour(ballX, ballY);
        } else if (ballX > mv.width - ballSize / 2) {
            changeColour(ballX, ballY);
            ballX = mv.width - ballSize / 2;
            angle = PApplet.PI - angle;
        }
    
        if (ballY < ballSize / 2) {
            ballY = ballSize / 2;
            angle = -angle;
            changeColour(ballX, ballY);
        } else if (ballY > mv.height - ballSize / 2) {
            changeColour(ballX, ballY);
            ballY = mv.height - ballSize / 2;
            angle = -angle;
        }
    
        mv.ellipse(ballX, ballY, ballSize, ballSize);
    }
    
    void changeColour(float x, float y) {
        mv.fill(mv.random(0, 255), mv.random(0, 255), mv.random(0, 255));
    }
}