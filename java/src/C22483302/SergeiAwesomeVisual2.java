package C22483302;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;

public class SergeiAwesomeVisual2 {

    Visual visual;
    float amplitude;

    // center coordinates and size of the pyramid base
    float baseSize = 200; 

    // angle variables for triangle pyramid positions
    float angle1 = 0;
    float angle2 = Visual.TWO_PI / 3; 
    float angle3 = 2 * Visual.TWO_PI / 3; 

    // eye variables
    float eyeSize = 50;
    float eyeX;
    float eyeY;

    public SergeiAwesomeVisual2(Visual visual) {
        this.visual = visual;
        visual.startMinim();
    }

    public void draw() {
        visual.background(0); 
    
        try {
            visual.calculateFFT();
            visual.calculateFrequencyBands();
            visual.calculateAverageAmplitude();
        } catch (VisualException e) {
            e.printStackTrace();
        }
    
        amplitude = visual.getSmoothedAmplitude();
    
        // update the angles based on amplitude to create rotation effect
        float rotateSpeed = visual.map(amplitude, 0, 1, 0.01f, 0.1f);
        angle1 += rotateSpeed;
        angle2 += rotateSpeed;
        angle3 += rotateSpeed;
    
        // center of screen
        float centerX = visual.width / 2;
        float centerY = visual.height / 2;
    
        // define vertices of pyramid base
        float baseY = centerY + baseSize / 2; // Base of the pyramid is centered horizontally
        float apexY = centerY - baseSize / 2; // Apex of the pyramid is above the base
    
        float x0 = centerX;
        float y0 = apexY; 
        float z0 = 0;
        
        float x1 = centerX + baseSize * visual.cos(angle1);
        float y1 = baseY;
        float z1 = -baseSize; // base vertex 1
        
        float x2 = centerX + baseSize * visual.cos(angle2);
        float y2 = baseY;
        float z2 = -baseSize; // base vertex 2
        
        float x3 = centerX + baseSize * visual.cos(angle3);
        float y3 = baseY;
        float z3 = -baseSize; // base vertex 3

        // eye position above pyramid
        eyeX = visual.width / 2;
        eyeY = centerY - baseSize / 2 - eyeSize; 
    
        float[] bands = visual.getSmoothedBands();
    
        float hueWavyLandscape = visual.map(amplitude, 0.15f, 0.3f, 100, 255);

        visual.stroke(100);
        visual.strokeWeight(3);

        // draw triangular faces of the pyramid
        drawTriangle(x0, y0, z0, x1, y1, z1, x2, y2, z2); // Triangle 1
        drawTriangle(x0, y0, z0, x2, y2, z2, x3, y3, z3); // Triangle 2
        drawTriangle(x0, y0, z0, x3, y3, z3, x1, y1, z1); // Triangle 3
        drawTriangle(x1, y1, z1, x2, y2, z2, x3, y3, z3); // Base triangle

        visual.stroke(hueWavyLandscape, 255, 255);

        drawWavyLandscape();

        visual.stroke(150);
        drawEye();
    }
    
    private void drawWavyLandscape() {
        float stepSize = 10;
        float waveHeight = 500;
    
        // loop through horizontal sections of the screen
        for (float x = 0; x < visual.width; x += stepSize) {
            visual.beginShape(Visual.TRIANGLE_STRIP);
    
            float startY = visual.height * 0.7f;
            float endY = visual.height + waveHeight;
    
            for (float y = startY; y <= endY; y += stepSize) {
                // calculate yOffset based on amplitude
                float amplitudeMapped = visual.map(amplitude, 0, 1, 0, 1);
                float noiseValue = visual.noise(x * 0.01f, y * 0.01f);
                float yOffset = visual.map(noiseValue, 0, 1, -waveHeight, waveHeight);
                yOffset *= amplitudeMapped; // scale wave height based on amplitude
    
                float clippedY = visual.constrain(y + yOffset, 0, visual.height + waveHeight);
    
                visual.vertex(x, clippedY); 
                visual.vertex(x + stepSize, clippedY); 
            }
    
            visual.endShape();
        }
    }

    private void drawEye() {
    
        // draw eye
        visual.ellipseMode(Visual.CENTER);
        visual.ellipse(eyeX, eyeY, eyeSize, eyeSize);
    
        // calculate pupil size based on amplitude
        float basePupilSize = eyeSize * 0.6f; // Base size of the pupil
        float amplitudeMapped = visual.map(amplitude, 0, 1, 0.5f, 2.0f); // Map amplitude to range for pupil size
        float pupilSize = basePupilSize * amplitudeMapped; // Scaled pupil size based on amplitude
    
        // calculate pupil position based on mouse cursor
        float pupilX = eyeX + visual.map(visual.mouseX, 0, visual.width, -pupilSize * 0.25f, pupilSize * 0.25f);
        float pupilY = eyeY + visual.map(visual.mouseY, 0, visual.height, -pupilSize * 0.25f, pupilSize * 0.25f);
    
        // draw pupil
        visual.ellipse(pupilX, pupilY, pupilSize, pupilSize);
    }
    
    
    
    private void drawTriangle(float x1, float y1, float z1, 
                              float x2, float y2, float z2, 
                              float x3, float y3, float z3) 
    {
        // draw a triangle with specified vertices
        visual.beginShape();
        visual.vertex(x1, y1, z1);
        visual.vertex(x2, y2, z2);
        visual.vertex(x3, y3, z3);
        visual.endShape(Visual.CLOSE); // close the triangle
    }
}
