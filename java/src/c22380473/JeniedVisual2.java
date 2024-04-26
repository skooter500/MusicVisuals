package c22380473;

import ie.tudublin.CombinedVisual;
import processing.core.PApplet;

public class JeniedVisual2 extends PApplet {

    CombinedVisual cv;

    float rotationSpeed = 0.02f;
    float eyeWidth = 800; 
    float eyeHeight = 500; 

    // Constructor that takes a parameter of type CombinedVisual
    public JeniedVisual2(CombinedVisual cv) {
        this.cv = cv;
    }

    public void settings() {
        cv.fullScreen(P3D, SPAN);
    }

    public void setup() {
        cv.colorMode(HSB);
        cv.setFrameSize(256);
        cv.startMinim();
    }

    public void render() {
        cv.calculateAverageAmplitude();
        cv.background(0); 
        cv.colorMode(HSB, 360, 100, 100);

        float amplitude = cv.getSmoothedAmplitude();

        drawBackgroundPattern(amplitude);

        drawEyeOutline();

        drawEyeball(amplitude);

        drawAudioBands(amplitude);
    }

    void drawBackgroundPattern(float amplitude) {
        int cols = 10; 
        int rows = 10; 

        // Calculate the size of each square
        float squareSize = cv.width / cols;

        // Calculate rotation speed based on amplitude
        float rotationSpeed = map(amplitude, 0f, 1f, 0.01f, 0.1f);

        // Loop through each column and row
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                // Calculate the position of the square
                float xPos = x * squareSize + squareSize / 2;
                float yPos = y * squareSize + squareSize / 2;

                // Calculate the size of the square based on amplitude
                float size = map(amplitude, 0, 1, 10, 50);

                // Calculate the hue based on position and amplitude
                float hue = map(x * y + amplitude * 100, 0, cols * rows + 100, 0, 360);

                // Set the fill color
                cv.fill(hue, 80, 100);

                // Draw the square
                cv.pushMatrix();
                cv.translate(xPos, yPos, -size / 2); // Move to the center of the square
                cv.rotateX(frameCount * rotationSpeed);
                cv.rotateY(frameCount * rotationSpeed);
                cv.rectMode(CENTER);
                cv.rect(0, 0, size, size);
                cv.popMatrix();
            }
        }

        // Draw another set of squares based on beat amplitude
        int beatSquares = 5; // Number of beat squares
        float beatSize = map(amplitude, 0, 1, 10, 50); // Size based on amplitude
        for (int i = 0; i < beatSquares; i++) {
            float x = random(cv.width); 
            float y = random(cv.height); 
            float beatHue = random(360);
            cv.fill(beatHue, 80, 100);
            cv.rectMode(CENTER);
            cv.rect(x, y, beatSize, beatSize);
        }
    }

    void drawEyeball(float amplitude) {
        // Calculate the size of the eyeball based on amplitude
        float eyeballSize = map(amplitude, 0, 1, 200, 300);

        // Draw the black eyeball
        cv.fill(0); // Set fill color to black
        cv.ellipse(cv.width / 2, cv.height / 2, eyeballSize, eyeballSize);

        // Calculate the size of the heart based on amplitude
        float heartSize = map(amplitude, 0, 1, 20, 60);

        // Calculate the hue for the heart based on amplitude
        float heartHue = map(amplitude, 0, 1, 0, 360);

        // Draw the white heart inside the eyeball
        cv.fill(heartHue, 80, 100); // Set fill color to heartHue
        cv.noStroke();
        cv.beginShape();
        float yOffsetHeart = 20; 
        cv.vertex(cv.width / 2, cv.height / 2 - eyeballSize / 4 + yOffsetHeart);
        cv.bezierVertex(cv.width / 2 + heartSize * 2, cv.height / 2 - eyeballSize / 4 - heartSize * 1.5f + yOffsetHeart,
                cv.width / 2 + heartSize * 4, cv.height / 2 - eyeballSize / 4 - heartSize * 0.5f + yOffsetHeart,
                cv.width / 2, cv.height / 2 + heartSize + yOffsetHeart);
        cv.bezierVertex(cv.width / 2 - heartSize * 4, cv.height / 2 - eyeballSize / 4 - heartSize * 0.5f + yOffsetHeart,
                cv.width / 2 - heartSize * 2, cv.height / 2 - eyeballSize / 4 - heartSize * 1.5f + yOffsetHeart,
                cv.width / 2, cv.height / 2 - eyeballSize / 4 + yOffsetHeart);
        cv.endShape(CLOSE);

    }

    void drawAudioBands(float amplitude) {
        // Number of bands
        int bands = 16;

        // Calculate the radius based on the eyeball size
        float radius = map(amplitude, 0, 1, 115, 215);

        // Loop through each band
        for (int i = 0; i < bands; i++) {
            // Calculate the angle
            float angle = map(i, 0, bands, 0, TWO_PI) + cv.frameCount * rotationSpeed;

            // Calculate the position of the point on the circle
            float x = cv.width / 2 + radius * cos(angle);
            float y = cv.height / 2 + radius * sin(angle);

            // Calculate the size of the band
            float bandSize = map(amplitude, 0, 1, 10, 50);

            // Set the fill color
            float hue = map(i, 0, bands, 0, 360);
            cv.fill(hue, 80, 100);
            cv.noStroke();
            // Draw the filled circle
            cv.ellipse(x, y, bandSize, bandSize);
        }
    }

    void drawEyeOutline() {
        cv.fill(255);
        cv.stroke(255); 
        cv.strokeWeight(3); 
        cv.ellipse(cv.width / 2, cv.height / 2, eyeWidth, eyeHeight); 

        // Draw audio waveform inside the eye outline
        cv.noFill(); 
        cv.stroke(255, 0, 0); 
        cv.strokeWeight(2); 

        // Number of points in the waveform
        int numPoints = 1000;

        // Calculate waveform parameters based on eye outline size
        float waveHeight = eyeHeight / 4; // Height of the waveform
        float waveLength = eyeWidth; // Length of the waveform
        float xStep = waveLength / numPoints; // Step between x values

        // Start drawing the waveform
        cv.beginShape();
        for (int i = 0; i < numPoints; i++) {
            float x = cv.width / 2 - eyeWidth / 2 + i * xStep;
            float angle = map(i, 0, numPoints, 0, TWO_PI);
            float yOffset = waveHeight * sin(angle * 2 + frameCount * 0.05f); // Adjust frequency with frameCount
            float amplitude = cv.getSmoothedAmplitude(); // Get the current amplitude
            yOffset *= amplitude; // Modulate the height of the waveform based on amplitude
            float y = cv.height / 2 + yOffset;
            cv.vertex(x, y);
        }
        cv.endShape();
    }

}
