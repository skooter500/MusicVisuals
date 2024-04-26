package c22380473;

import processing.core.PApplet;
import ie.tudublin.CombinedVisual;

public class JeniedVisual1 extends PApplet {

    CombinedVisual cv;

    int maxDots = 2000; 
    float[] dotX = new float[maxDots]; 
    float[] dotY = new float[maxDots]; 
    float[] dotSizes = new float[maxDots];
    float maxDotSize = 15; 
    float minDotSize = 5;
    float maxSpread = 700; 
    float minSpread = 700; 
    float sphereSize = 0;
    float prevAmplitude = 0;
    float angle = 0;
    

    // Constructor that takes a parameter of type CombinedVisual
    public JeniedVisual1(CombinedVisual cv) {
        this.cv = cv;
    }

    public void settings() {
        println("CWD: " + System.getProperty("user.dir"));
        cv.fullScreen(P3D, SPAN); 
    }

    public void setup() {
        cv.colorMode(HSB);
        cv.setFrameSize(256);
        cv.startMinim(); 
    }

  
    public void render() {
        cv.calculateAverageAmplitude(); 
        cv.background(0);
        cv.colorMode(HSB, 360, 100, 100);
    
        float hue = cv.frameCount % 360;
        cv.noStroke();
        cv.fill(0, 100); 

        // Calculate the number of dots based on the amplitude
        float amplitude = cv.getSmoothedAmplitude(); 
        int newDots = (int) map(amplitude, 0, 1, 0, maxDots);

        // Drop new dots based on the audio
        for (int i = 0; i < newDots; i++) {
            // Map dot spread based on amplitude
            float spread = map(amplitude, 0, 1, minSpread, maxSpread);
            // Randomly position the dots based on the spread
            dotX[i] = cv.random(-spread, spread);
            dotY[i] = cv.random(-spread, spread);
        }

        // Draw all dots
        for (int i = 0; i < newDots; i++) {
            // Map dot size based on amplitude
            float size = map(amplitude, 0, 1, minDotSize, maxDotSize);
            float brightness = map(i, 0, newDots, 50, 100); 
            cv.fill(hue, 80, brightness);
            cv.ellipse(cv.width / 2 + dotX[i], cv.height / 2 + dotY[i], size, size); 
        }

        prevAmplitude = amplitude;

        // Draw the sphere
        drawSphere();
    }

    void drawSphere() {
        // Calculate the amplitude of the audio signal
        float amplitude = cv.getSmoothedAmplitude(); 

        sphereSize = constrain(sphereSize, 190, 200); 

        // Rotate the centre sphere based on the amplitude 
        float rotationSpeed = map(amplitude, 0, 1, 0, TWO_PI);
        rotationSpeed *= -0.1; 
        angle += rotationSpeed; 

        // Draw the centre sphere in middle of canvas
        cv.pushMatrix(); 
        cv.translate(cv.width / 2, cv.height / 2, 0); 
        cv.rotateY(angle);
        cv.noFill();
        cv.stroke(cv.frameCount % 360, 80, 100); 

        // Draw a ring to 2nd planet
        float mainRingRadius = 450; 
        float mainRingThickness = 8; 
        cv.rotateX(angle); 
        cv.ellipse(0, 0, mainRingRadius * 2, mainRingRadius * 2); 

        // Draw a ring to 3rd planet
        float mainRingRadius2 = 312; 
        float mainRingThickness2 = 8; 
        cv.rotateX(angle); 
        cv.ellipse(0, 0, mainRingRadius2 * 2, mainRingRadius2 * 2); 

        // Draw the main sphere
        cv.sphere(sphereSize); 

        // Draw beat visualiser
        float smallSphereSize = map(amplitude, 0, 1, 0, 800); // Map amplitude to small sphere size
        cv.fill(cv.frameCount % 360, 80, 100); 
        cv.noStroke(); 
        cv.sphere(smallSphereSize); 

        // Calculate the position of the 2nd sphere
        float circleRadius = 300; 
        float circleX1 = cv.width / 5; 
        float circleY1 = cv.height / 2 - 500; 
        float circleZ1 = 0;
 
        // Draw the 2nd sphere
        cv.pushMatrix(); 
        cv.translate(circleX1, circleY1, circleZ1); 
        cv.rotateX(angle); 
        cv.sphere(50); 

        // Calculate the position of the spinning sphere relative to the centre sphere
        float spinRadius = 150;
        float spinX = spinRadius; 
        float spinY = 0; 

        // Draw the 3rd spinning sphere
        cv.pushMatrix(); 
        cv.translate(spinX, spinY, 0); 
        cv.rotateX(angle); 
        cv.sphere(30); 
        
        // Draw a ring around the last sphere
        float ringRadius = 40; 
        float ringThickness = 300;
        cv.ellipse(0, 0, ringRadius * 2, ringRadius * 2); 
        cv.stroke(100); 

        cv.popMatrix(); 
        cv.popMatrix(); 
        cv.popMatrix(); 
    }
}
