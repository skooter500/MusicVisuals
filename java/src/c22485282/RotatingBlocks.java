package c22485282;

import c22485282.MyVisual;
import processing.core.PApplet;
import processing.core.PVector;

public class RotatingBlocks {
    MyVisual mv;
    int lastColorChange = 0;
    int colorChangeInterval = 1500; // Change colors every 1.5 seconds
    float[] blockColors; // Stores the hue of each block
    float a = 0.0f; // Angle of rotation
    float offset = (float) (mv.PI / 24.0); // Angle offset between boxes
    int num = 12; // num of boxes
    PVector[] boxPositions; // Stores the position of each box

 public RotatingBlocks(MyVisual mv) {
    this.mv = mv; // Initialize the MyVisual reference
    blockColors = new float[num]; // Initializes array to store block colors
    boxPositions = new PVector[num]; // Initializes array to store box positions
    initializeBlockColors(); // Call method to initialize block colors
    initializeBoxPositions(); // Call method to initialize box positions
    } // End  RotatingShape(M)

    void initializeBlockColors() {
        // Initialize block colors with random hues
        for (int i = 0; i < num; i++) {
            blockColors[i] = mv.random(360); // A random hue initialized to each block
        }
    } // End initializeBlockColors() 

    void initializeBoxPositions() {
        float radius = 325; // Radius of the circular arrangement
        float angle = 0; // Initial angle
        // Positions of each box around a circle is calculated
        for (int i = 0; i < num; i++) {
            float x = radius * (float) Math.cos(angle); // X-coordinate of box position
            float y = radius * (float) Math.sin(angle); // Y-coordinate of box position
            boxPositions[i] = new PVector(x, y, 0); // Box position stored in the array
            angle += mv.TWO_PI / num; // Angle incremented for the next box
        }
    } // End initializeBoxPositions()

    public void render() {
        mv.stroke(0);
        mv.strokeWeight(4);
        float amt = (float) (mv.millis() - lastColorChange) / colorChangeInterval; // Color change amount calculated
        int audioIndex = (int) (mv.random(30, 512)); // Random audio buffer index
        float audioValue = mv.getAudioBuffer().get(audioIndex); // Get audio value from the buffer
        mv.pushMatrix(); // Current transformation matrix saved
        mv.colorMode(mv.HSB, 360, 100, 100); // Color mode set to HSB

        mv.translate(mv.width / 2, mv.height / 2 - 150); // Translate to the center 

        if (amt >= 1.0) {
            initializeBlockColors(); // Block colors after interval are to be reset
            lastColorChange = mv.millis(); // Last color change timestamp updated
        }
        // Render each rotating box
        for (int i = 0; i < num; i++) {
            float hue = mv.lerp(blockColors[i], mv.lerp(0, 360, audioValue), amt); // Interpolate hue based on audio value
            mv.fill(hue, 100, 100); // Fill color  set using HSB
            mv.pushMatrix(); // Current transformation matrix saved
            mv.rotateY(a + offset * i); // Rotate around Y-axis
            mv.rotateX(a / 2 + offset * i); // Rotate around X-axis
            mv.translate(boxPositions[i].x, boxPositions[i].y, 0); // Translate to box position
            mv.box(50); // Draw a box
            mv.popMatrix(); // Previous transformation matrix restored
        }
        a += 0.01; // Increment rotation angle
        mv.popMatrix(); // Restore the original transformation matrix
    } // End render()

    }
 // End rotatingBlocks()



