package C22739165;

import ie.tudublin.Visual;
import processing.core.PApplet;

class RotatingCircles {
    Visual visual;
    float a = 0; // Rotation control
    float numCircles;
    float[] bands;

    RotatingCircles(Visual visual, int numCircles) {
        this.visual = visual;
        this.numCircles = numCircles;
        this.bands = new float[numCircles];
    }

    public void update(float[] bands) {
        System.arraycopy(bands, 0, this.bands, 0, Math.min(this.bands.length, bands.length));
    }

    public void draw() {
        visual.lights();
        visual.pushMatrix();
        visual.translate(visual.width / 2, visual.height / 2);

        for (int i = 0; i < numCircles; i++) {
            float bandValue = bands[i % bands.length];
            // Adjust the base size and dynamic range, and limit the maximum size of the circles
            float circleSize = PApplet.map(bandValue, 0, 1, 20, 50); // Circle size now ranges from 20 to 50 based on bandValue

            float hue = PApplet.map(i, 0, numCircles, 0, 255); // Color mapped across the spectrum

            visual.pushMatrix();
            visual.stroke(hue, 204, 255); // Vibrant colors
            visual.fill(hue, 204, 255, 127); // Semi-transparent fill for a cleaner look
            visual.rotateY(PApplet.radians(a + i));
            visual.rotateX(PApplet.radians(a / 2 + i));
            visual.circle(0, 0, circleSize);
            visual.popMatrix();
        }

        visual.popMatrix();
        a += 0.05; // Maintain rotation speed for smoothness
    }
}
