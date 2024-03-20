package C22533826;

// Draft ideas.

import ie.tudublin.Visual;
import ie.tudublin.VisualException;
import processing.core.PApplet;
import ie.tudublin.Heartbeat;

public class NoelsVisual {

    float radius = 200;
    float smoothedBoxSize = 0;
    float rot = 0;

    public void render(Heartbeat HB) {
        HB.calculateAverageAmplitude();
        try {
            HB.calculateFFT();
        } catch (VisualException e) {
            e.printStackTrace();
        }
        HB.calculateFrequencyBands();
        HB.background(0);

        float height = HB.height;
        float width = HB.width;

        float amplitude = HB.getSmoothedAmplitude();

        // Draw rotating 3D box
        HB.pushMatrix();
        HB.translate(width / 2, height / 2, 0);
        HB.rotateY(rot);
        HB.rotateX(rot);
        HB.rotateZ(rot);
        float boxSize = 200 + amplitude * 200;
        float heartSize = HB.map(amplitude, 0, 1, 100, 300);
        if (boxSize < heartSize) {
            boxSize = heartSize + 10;
        }
        HB.noFill(); // Make the box transparent
        HB.stroke(255); // Set the stroke color to white
        HB.box(boxSize);
        HB.popMatrix();

        // Draw heart inside the box
        float heartX = width / 2;
        float heartY = height / 2;

        HB.pushMatrix();
        HB.translate(heartX, heartY);

        // determines the size of the heart based on the amplitude and framecount for
        // speed.
        float pulsatingSize = heartSize + HB.sin((float) (HB.frameCount * 0.025)) * 50;

        // Calculate color based on volume
        float volume = HB.getSmoothedAmplitude();
        float hue = HB.map(volume, 0, 1, 0, 255);
        float saturation = 255;
        float brightness = 255;

        HB.colorMode(HB.HSB);
        HB.fill(hue, saturation, brightness);

        HB.beginShape();
        for (float angle = 0; angle < HB.TWO_PI; angle += 0.01) {
            float xCoord = 16 * HB.pow(HB.sin(angle), 3);
            float yCoord = -13 * HB.cos(angle) + 5 * HB.cos(2 * angle) + 2 * HB.cos(3 * angle) + HB.cos(4 * angle);
            xCoord *= pulsatingSize / 10;
            yCoord *= pulsatingSize / 10;
            HB.vertex(xCoord, yCoord);
        }
        HB.endShape(HB.CLOSE);
        HB.popMatrix();

        rot += 0.01; // Rotate the box
    }
}
