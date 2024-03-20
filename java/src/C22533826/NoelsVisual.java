package C22533826;

// Draft ideas.

import ie.tudublin.Visual;
import ie.tudublin.VisualException;

public class NoelsVisual extends Visual {

    public void settings() {
        size(800, 800, P3D);
        println("CWD: " + System.getProperty("user.dir"));
        // fullScreen(P3D, SPAN);
    }

    public void setup() {
        colorMode(HSB);
        // noCursor();
        setFrameSize(256);
        startMinim();
        loadAudio("Heartbeat.mp3");
        getAudioPlayer().play();
        // startListening();

    }

    float radius = 200;
    float smoothedBoxSize = 0;
    float rot = 0;

    public void draw() {
        calculateAverageAmplitude();
        try {
            calculateFFT();
        } catch (VisualException e) {
            e.printStackTrace();
        }
        calculateFrequencyBands();
        background(0);

        float amplitude = getSmoothedAmplitude();

        // Draw rotating 3D box
        pushMatrix();
        translate(width / 2, height / 2, 0);
        rotateY(rot);
        rotateX(rot);
        rotateZ(rot);
        float boxSize = 200 + amplitude * 200;
        float heartSize = map(amplitude, 0, 1, 100, 300);
        if (boxSize < heartSize) {
            boxSize = heartSize + 10;
        }
        noFill(); // Make the box transparent
        stroke(255); // Set the stroke color to white
        box(boxSize);
        popMatrix();

        // Draw heart inside the box
        float heartX = width / 2;
        float heartY = height / 2;

        pushMatrix();
        translate(heartX, heartY);

        // determines the size of the heart based on the amplitude and framecount for
        // speed.
        float pulsatingSize = heartSize + sin((float) (frameCount * 0.025)) * 50;

        // Calculate color based on volume
        float volume = getSmoothedAmplitude();
        float hue = map(volume, 0, 1, 0, 255);
        float saturation = 255;
        float brightness = 255;

        colorMode(HSB);
        fill(hue, saturation, brightness);

        beginShape();
        for (float angle = 0; angle < TWO_PI; angle += 0.01) {
            float xCoord = 16 * pow(sin(angle), 3);
            float yCoord = -13 * cos(angle) + 5 * cos(2 * angle) + 2 * cos(3 * angle) + cos(4 * angle);
            xCoord *= pulsatingSize / 10;
            yCoord *= pulsatingSize / 10;
            vertex(xCoord, yCoord);
        }
        endShape(CLOSE);
        popMatrix();

        rot += 0.01; // Rotate the box
    }
}
