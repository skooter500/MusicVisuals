package example; //declare package example
import processing.core.PApplet; //import the core Processing library

public class tricar extends poly {

    public tricar(MyVisual mv)
    { 
        super(mv);
    }

    @Override
    public void render() { 
            mv.background(0); //set background to black
            mv.lights(); //enable processing lighting for 3D shapes

            // Map colour based on lerpedAvg
            float hue2 = PApplet.map(mv.lerpedAvg, 0, 1, 0, 255); //dynamic colour changes based on audio
            mv.stroke(hue2, 255, 255); //set outline colour
            mv.fill(hue2, 255, 255); //set fill colour

            mv.translate(0, 0, 200); //move forward in Z-axis to draw other shapes
            
            //parameters for small triangles
            float triangleSize1 = 80;
            float spacing = 120;

            // nested loop for grid of triangles
            for (int x = 0; x <= mv.width; x += spacing) {
                for (int y = 0; y <= mv.height; y += spacing) {

                int row = y / 60; 

                //skip the middle three rows to avoid overlap with sportsCar.obj
                if (row >= 8 && row <= mv.height/60 - 7) {
                    continue; //iterate loop
                }

                mv.pushMatrix();
                mv.translate(x, y, 0); //centre each triangle


                //audio-reactive rotation
                float angle1 = PApplet.radians(mv.frameCount + x + y) + mv.lerpedAvg * PApplet.TWO_PI; 

                float xx1 = PApplet.cos(angle1) * triangleSize1; //calculate X coordinates of the first triangle vertex
                float yy1 = PApplet.sin(angle1) * triangleSize1; //calculate Y coordinates of the first triangle vertex
                //same calculations for vertex 2 + 3
                float xx2 = PApplet.cos(angle1 + PApplet.TWO_PI / 3) * triangleSize1;
                float yy2 = PApplet.sin(angle1 + PApplet.TWO_PI / 3) * triangleSize1;
                float xx3 = PApplet.cos(angle1 + 2 * PApplet.TWO_PI / 3) * triangleSize1;
                float yy3 = PApplet.sin(angle1 + 2 * PApplet.TWO_PI / 3) * triangleSize1;

                mv.triangle(xx1, yy1, xx2, yy2, xx3, yy3); //draws the triangles using the calculated coordinates 
                mv.popMatrix();
                }
            }

            // Bounce Logic
            float bounceVelocity = 0.0f; // Initialise to 0
            float bounceFactor = 5.0f; //amplify the effect of audio on bounce
            float gravity = 0.2f; //downwards force    

            //update bounce velocity based on audio and gravity
            bounceVelocity += mv.smoothedAmplitude131 * bounceFactor; //velocity based on audio amplitude
            bounceVelocity -= gravity; //constant pull of gravity

            //calculate vertical bounce position 
            float bounceY = mv.height/2 + 100 + bounceVelocity * 50; 

            mv.translate(mv.width/2, bounceY, -200); //centre horizontally, apply bounceY, move back in Z
            mv.rotateZ(PApplet.PI); //rotate 180D around Z-axis
            mv.rotateY(mv.ry); //existing rotation around Y-axis
            mv.scale(250.5f); //set scale of car
            mv.shape(mv.car); //draw the car .obj file

            mv.ry += 0.02; //increment rotation around Y-axis
    }

    
}
