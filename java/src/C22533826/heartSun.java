package C22533826;

import ie.tudublin.Heartbeat;
import ie.tudublin.VisualException;

public class heartSun {

    Heartbeat HB;

    public heartSun(Heartbeat HB) {
        // Initialize variables.
        this.HB = HB;
    }

    public void render() {
        // Render the heart sun.
        HB.calculateAverageAmplitude();

        // Get the amplitude and calculate the size and position of the heart.
        float amplitude = HB.getSmoothedAmplitude();
        float heartSize = HB.map(amplitude, 0, 1, 100, 150);
        float heartX = HB.width / 2 + 15;
        float heartY = HB.height / 4;

        HB.pushMatrix();
        HB.translate(heartX, heartY);

        // determines the size of the heart based on the amplitude and framecount for
        // speed.
        float pulsatingSize = heartSize + HB.sin((float) (HB.frameCount * 0.025)) * 40;

        // Calculate color based on volume
        float volume = HB.getSmoothedAmplitude();
        float hue = HB.map(volume, 0, 1, 0, 255);
        float saturation = 255;
        float brightness = 255;
        HB.colorMode(HB.HSB);
        HB.fill(hue, saturation, brightness);

        // Draw the heart shape.
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
    }
}
