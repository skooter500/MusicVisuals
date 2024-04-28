package C21374751;

import ie.tudublin.OurVisual;
import processing.core.*;

public class RoxanaVisual1
{
    OurVisual ov;
    PImage frogImage;
    float xPos; // Horizontal position of the frog image
    float yPos; // Vertical position of the frog image
    float hopSpeed; // Horizontal speed of the frog
    float angle; // Angle for rainbow colors
    int numGrassLines; // Number of grass lines
    float grassSpacing; // Spacing between grass lines

    public RoxanaVisual1(OurVisual ov)
    {
        this.ov = ov;
        frogImage = ov.loadImage("images/frog.png"); // Load the frog image from the "images" folder
        frogImage.resize(120, 0); // Resize the image width to 120 pixels, keep aspect ratio (slightly bigger)
        xPos = frogImage.width / 2; // Start the frog at the left edge of the screen
        yPos = ov.height - frogImage.height / 2; // Start the frog at the bottom of the screen
        hopSpeed = 5; // Horizontal speed of the frog
        angle = 0; // Initialize angle for rainbow colors
        numGrassLines = 30; // Number of grass lines
        grassSpacing = ov.width / numGrassLines; // Calculate spacing between grass lines
    }

    public void render()
    {
        // Calculate background color based on time
        float bgBrightness = PApplet.abs(PApplet.sin((float)(ov.frameCount * 0.02))) * 255;
        ov.background(0, 0, bgBrightness); // Set background to fade from black to blue and back to black
        
        // Update frog position
        xPos += hopSpeed;
        
        // Check if frog reaches edges of the screen
        if (xPos > ov.width - frogImage.width / 2 || xPos < frogImage.width / 2) {
            // Reverse direction when frog reaches the right or left wall
            hopSpeed = -hopSpeed;
        }
        
        // Draw rainbow aura around the frog
        ov.noFill();
        for (int i = 0; i < 6; i++) {
            ov.stroke((i * 60) % 255, 255, 255); // Rainbow colors
            float auraSize = 50 + 20 * PApplet.sin(angle + i * PApplet.TWO_PI / 6);
            ov.ellipse(xPos, yPos, frogImage.width + auraSize, frogImage.height + auraSize);
        }
        
        // Calculate squish factor based on frog's vertical position relative to the bottom of the screen
        float squishFactor = 1.0f - (yPos / (ov.height - frogImage.height / 2));
        float squishedHeight = frogImage.height * squishFactor;
        
        // Draw frog image
        ov.imageMode(PApplet.CENTER); // Set image mode to center
        ov.image(frogImage, xPos, yPos, frogImage.width, squishedHeight); // Display frog image with squish effect

        // Make the frog jump higher
        float hopAmount = 20 * PApplet.sin((float)(ov.frameCount * 0.1)); // Adjust the hop amount as needed
        yPos = ov.constrain(yPos + hopAmount, 0, ov.height - frogImage.height / 2); // Ensure the frog stays within the screen
        
        angle += 0.05; // Increment angle for rainbow colors
        
        

        
        // Draw grass lines at the bottom of the screen
        for (int i = 0; i < numGrassLines; i++) {
            float grassX = i * grassSpacing; // Calculate x position for each grass line
            float grassHeight = ov.random(40, 100); // Randomize grass height
            ov.strokeWeight(10); // Set grass line thickness
            ov.stroke((i * 60) % 225, 255, 225); // Rainbow colors // Set grass color to green
            ov.line(grassX, ov.height, grassX, ov.height - grassHeight); // Draw a vertical grass line
        }
    }
}
