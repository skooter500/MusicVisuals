package c22485282;

import processing.core.PApplet;

public class GradientShape {
    MyVisual mv;

    int dim; // Size of the gradient circles
    int height, width; // Dimensions of the canvas
    float audioAmplitude; // Stores the audio amplitude

    public GradientShape(MyVisual mv) {
        this.mv = mv;
        this.height = mv.height;
        this.width = mv.width;
        dim = width / 2;
    } // End public GradientShape()

    public void render() {
        mv.background(0);

        // Get the audio amplitude from the MyVisual instance
        audioAmplitude = mv.getAudioBuffer().get(0);

        // Draw the gradient circles based on audio amplitude
        for (int x = 0; x <= width; x += dim) {
            drawGradient(x, height / 2, audioAmplitude);
        } // End for

    } // End render()

    private void drawGradient(float x, float y, float amplitude) {
        int radius = dim / 2;

           // Calculate the initial hue based on amplitude
           float h = PApplet.map(amplitude, -1, 1, 0, 360);

           // Draw each circle with a smoothly changing hue
           for (int r = radius; r > 0; --r) {
               // Adjust saturation and brightness for a more vivid color
               float saturation = PApplet.map(r, 0, radius, 90, 100);
               float brightness = PApplet.map(r, 0, radius, 90, 100);

                // Set fill color using HSB color mode
                mv.fill(h, saturation, brightness);
                mv.ellipse(x, y, r, r);

                 // Gradually change the hue for the next circle
                 h = (h + 1) % 360;

           } // End for


    } // End  drawGradient()


    
} // End public class GradientShape
