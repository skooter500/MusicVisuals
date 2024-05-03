package C22483302;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;

public class SergeiAwesomeVisual{

    Visual visual;

    public SergeiAwesomeVisual(Visual visual) {
        this.visual = visual;
        visual.startMinim();
    }

    float b = 0;                 // angle of rotation
    float offset = (float) (visual.PI/24.0);  // angle offset between boxes
    int num = 60;

public void draw() {
    visual.background(0);

    try {
        visual.calculateFFT();
    } catch (VisualException e) {
        e.printStackTrace();
    }

    visual.calculateFrequencyBands();
    visual.calculateAverageAmplitude();
    float[] bands = visual.getSmoothedBands();
    float amplitude = visual.getSmoothedAmplitude();

    float centerX = visual.width / 2;
    float centerY = visual.height / 2;

    float rotateSpeed = visual.map(amplitude, 0.1f, 0.5f, 0.02f, 0.2f);

    visual.lights();
    visual.translate(centerX, centerY);

    // adjust overall rotation angle
    float orbitAngle = visual.map(amplitude, 0.1f, 0.5f, 0.02f, 0.2f);
    b += orbitAngle;

    for (int i = 0; i < num; i++) {
        float angle = visual.map(i, 0, num, 0, visual.TWO_PI);
        float x = visual.width / 4 * visual.cos(angle);
        float y = visual.height / 4 * visual.sin(angle);

        float zOffset = visual.map(bands[i % bands.length], 0, 255, -200, 200);
        float shapeSize = visual.map(bands[i % bands.length], 0, 255, 50, 200);

        float hue = visual.map(i, 0, num, 0, 255);

        visual.pushMatrix();

        // translate and rotate the entire group
        visual.translate(x, y, zOffset);
        visual.rotateX(b + angle * rotateSpeed);
        visual.rotateY(b + angle * rotateSpeed);

        // varying stroke color based on position
        visual.stroke(hue, 255, 255);

        // draw different shapes based on amplitude
        if (i % 3 == 0) {
            visual.box(shapeSize); // draw box
        } else if (i % 3 == 1) {
            visual.sphere(shapeSize / 2); // draw sphere
        } else {
            // draw cylinder
            int segments = 20;
            float radius = shapeSize / 2;
            float segmentHeight = shapeSize / segments;
            for (int j = 0; j < segments; j++) {
                visual.ellipse(0, 0, radius * 2, radius * 2); 
                visual.translate(0, 0, segmentHeight); 
            }
        }
    
        visual.popMatrix();
        }
    
        if (b > visual.TWO_PI) {
            b = 0;
        }
    }   
}
