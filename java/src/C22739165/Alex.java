package C22739165;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;

import java.util.ArrayList;

public class Alex {
    Visual visual;
    ArrayList<BoxSet> boxSets; // List of BoxSet objects
    RotatingCircles rotatingCircles;


    public Alex(Visual visual) {
        this.visual = visual;
        boxSets = new ArrayList<BoxSet>();

        // Create BoxSet instances in a diamond shape
        // Top
        boxSets.add(new BoxSet(visual, visual.width / 2, visual.height / 4, 10));
        // Right
        boxSets.add(new BoxSet(visual, 3 * visual.width / 4, visual.height / 2, 10));
        // Bottom
        boxSets.add(new BoxSet(visual, visual.width / 2, 3 * visual.height / 4, 10));
        // Left
        boxSets.add(new BoxSet(visual, visual.width / 4, visual.height / 2, 10));

        //circles
        rotatingCircles = new RotatingCircles(visual, 10);
    }

    public void draw() {
        visual.background(0); // Set the background to black
        float intensity = visual.getSmoothedAmplitude();
        visual.background(10 + intensity * 245); // Background changes with amplitude
        try {
            visual.calculateFFT(); // Perform FFT analysis
        } catch (VisualException e) {
            e.printStackTrace();
        }
        visual.calculateFrequencyBands();
        visual.calculateAverageAmplitude();
        float[] bands = visual.getSmoothedBands();
        float amplitude = visual.getSmoothedAmplitude();

        // Update and draw each BoxSet with both amplitude and frequency bands
        for (BoxSet boxSet : boxSets) {
            boxSet.update(amplitude, bands); // Update with the current amplitude and frequency bands
            boxSet.draw(); // Draw the box set
        }

        rotatingCircles.update(visual.getSmoothedBands());
        rotatingCircles.draw(); // This will overlay the rotating circles on top of the boxes
    }
}

