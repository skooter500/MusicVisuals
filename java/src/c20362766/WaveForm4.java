package c20362766;

import ie.tudublin.Visual;
import processing.core.PApplet;


public class WaveForm4 extends PApplet {

    HabeebsVisuals mv;

    public WaveForm4(HabeebsVisuals mv) {
        this.mv = mv;
    }

    public void settings()
	{
		size(800, 600, P3D);
		
	}

    float angle = 0;
    float angle2 = 0;
    float angle3 = 0;

    public void circleBall(){
        int numCircles = 22;
        int Mwidth = 140;

        float c = mv.map(mv.getAmplitude(), 0, 1, 0, 255);
        // circles
        for (int i = 0; i < numCircles; i++) {
            mv.stroke(c * i, 255, 255);
            mv.strokeWeight(2);
            mv.noFill();
            mv.ellipse(mv.width / 2, mv.height / 2, Mwidth + (mv.getSmoothedAmplitude() * 400), Mwidth + (mv.getSmoothedAmplitude() * 400));
            Mwidth += 70;
        }

        mv.lights();
        mv.strokeWeight(2);
        mv.stroke(c, 255, 255);
        mv.noFill();

        angle += 0.01f;
        angle2 -= 0.02f;
        float s = 40 + (100 * mv.getSmoothedAmplitude() * 10);
        float s1 = 20 + (100 * mv.getSmoothedAmplitude() * 10);
        mv.translate(mv.width / 2, mv.height / 2);

        // cubes
        mv.rotateY(angle);
        mv.rotateX(angle);
        mv.box(s);

        mv.rotateY(angle2);
        mv.rotateX(angle2);
        mv.box(s1);


        
        mv.calculateAverageAmplitude();
        mv.stroke(c,25 , 255);
        mv.strokeWeight(3);
        mv.noFill();
        mv.lights();
        mv.pushMatrix();

        
        mv.camera(0, 0, 0, 0, 0, -1* mv.getSmoothedAmplitude(), 0, 1, 0);
        mv.translate(0, 0, -200);
        angle = angle - (angle * mv.getSmoothedAmplitude());
        mv.rotateX(angle = angle + (angle * mv.getSmoothedAmplitude()));
        mv.rotateZ(angle = angle +  (angle * mv.getSmoothedAmplitude()));   
        angle = angle - (angle * mv.getSmoothedAmplitude());



        float boxSize = 25 + (200 * mv.getSmoothedAmplitude()); 
        mv.sphere(boxSize);
        for(int i = 0 ; i < mv.getAudioBuffer().size() ; i ++)
        {
            // changes color to rainbow range
            mv.stroke(PApplet.map(i, 0, mv.getAudioBuffer().size(), 0, 255), 255, 255);
        }
        mv.sphere(boxSize);   
        mv.popMatrix();
        angle3 += 0.01f;
    }
    
    public void render() {
        mv.background(0,0,0);
        
        circleBall();
        

    }
}