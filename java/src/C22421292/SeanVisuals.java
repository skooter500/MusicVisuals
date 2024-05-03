package c22421292;

import ie.tudublin.*;
import processing.core.PVector;
import processing.core.PApplet;
import java.util.ArrayList;

public class SeanVisuals extends Visual{
    private PApplet parent;  //Reference to PApplet app
    private float angle = 0.0f;  //Used for cube spinning
    public int scene = 0; //Used for keeping track of which scene is active
    public float cameraX, cameraY; //Camera position
    public int sunColor, moonColor; //Color of sun and moon spheres
    public int bgHue, bgBrightness = 0; //Used for color of background
    public int bandColor = 0; //Color of the audiobands also used for stars fill
    private boolean spamMode = false; //Boolean for whether spam is on or off
    private ArrayList<PVector> stars;  //Store positions of stars

    public void setParent(PApplet parent) {
        this.parent = parent;
        this.g = parent.g;
        this.width = parent.width;
        this.height = parent.height;
        stars = new ArrayList<>();
        addStars(500); //Add stars in background
    }

    public void sceneChange() {
        if (scene < 3) {
            scene++; //Next scene if scene is less than 3
        } else {
            scene = 1; //if scene is == 3 set it back to 1 to let it cycle through
        }
    }

    public void spamMode() {
        if (spamMode == true) {
            spamMode = false; //if spamMode is on, turn it off
        }
        else {
            spamMode = true; //if spamMode is off, turn it on
        }
    }

    private void drawSingleCube(float xPosition, float yPosition, float sizeMod, int cubeColor) {
        float amplitude = ((Visual)parent).getSmoothedAmplitude(); //Get amplitude
    
        parent.pushMatrix();
        parent.translate(xPosition, yPosition, -200); //Position cube
        parent.rotateX(PApplet.radians(-15)); //Tilt cubes downwards
        parent.rotateY(-angle); //Rotate anti-clockwise
        float size = 1000 * (0.5f + 1.1f * amplitude); //Cube size based on amplitude
        parent.fill(cubeColor); //Fill cubes with color
        parent.box(size*sizeMod); //Draw the cube
        parent.popMatrix();
    }
    

    private void drawCube() {
        int colorBottomCube, colorTopLeftCube, colorTopRightCube; //Variables to hold color values
    
        switch(scene) {
            case 1:
                colorBottomCube = parent.color(180, 155, 255);
                colorTopLeftCube = parent.color(300, 155, 255);
                colorTopRightCube = parent.color(60, 190, 255);
                sunColor = parent.color(300, 155, 255);
                moonColor = parent.color(60, 190, 255);
                bandColor = parent.color(180, 155, 255);
                break;
            case 2:
                colorBottomCube = parent.color(60, 190, 255);
                colorTopLeftCube = parent.color(180, 155, 255);
                colorTopRightCube = parent.color(300, 155, 255);
                sunColor = parent.color(180, 155, 255);
                moonColor = parent.color(300, 155, 255);
                bandColor = parent.color(60, 190, 255);
                break;
            case 3:
                colorBottomCube = parent.color(300, 155, 255);
                colorTopLeftCube = parent.color(60, 190, 255);
                colorTopRightCube = parent.color(180, 155, 255);
                sunColor = parent.color(60, 190, 255);
                moonColor = parent.color(180, 155, 255);
                bandColor = parent.color(300, 155, 255);
                break;
            default:
                colorBottomCube = colorTopLeftCube = colorTopRightCube = parent.color(0, 0, 255); //Give cubes a color value. Wont show up
        }
    
        if (parent instanceof Visual) {
            ((Visual)parent).calculateAverageAmplitude(); //get average amplitude
        }
        float amplitude = ((Visual)parent).getSmoothedAmplitude(); //Smooth amplitude
        float baseY = map(amplitude, 0, 1, parent.height * 0.9f, parent.height * 0.1f);
    
        parent.lights();
        parent.noStroke();
    
        float sizeMod = 1.0f; //Change size of cubes certain cubes
    
        //Coordinates and drawing for the bottom cube
        float bottomX = parent.width * 0.5f;
        float bottomY = baseY;
        drawSingleCube(bottomX, bottomY, 1.45f, colorBottomCube);
    
        //Coordinates and drawing for the top left and top right cubes
        float topXOffset = 1800;
        float topY = bottomY - (1000 * (0.5f + 1.1f * amplitude) * sizeMod * 0.8f);
        drawSingleCube(bottomX - topXOffset, topY, 1, colorTopLeftCube);
        drawSingleCube(bottomX + topXOffset, topY, 1, colorTopRightCube);
    
        angle += 0.05; //Rotate cubes
    }

    public void addStars(int number) {
        for (int i = 0; i < number; i++) {
            float x = parent.random(-parent.width * 1.5f, parent.width * 1.5f);
            float y = parent.random(-parent.height * 1.5f, parent.height * 1.5f);
            float z = parent.random(-5000, 5000);
            stars.add(new PVector(x, y, z)); //Fill array with random positions for stars to spawn into
        }
    }

    private void drawStars() {
        float amplitude = parent instanceof Visual ? ((Visual) parent).getSmoothedAmplitude() : 0;
        for (PVector star : stars) {
            parent.pushMatrix();
            parent.noStroke();
    
            float bopAmplitude = 30 * amplitude;
            float rate = map(star.x, -parent.width, parent.width, 5.0f, 15.0f);
            float dynamicY = star.y + bopAmplitude * sin(parent.frameCount / rate);
    
            parent.fill(parent.color(bandColor, 128));
            parent.translate(star.x, dynamicY, star.z);
            float size = 10 + 5 * sin(parent.frameCount / 40.0f);
            parent.sphere(size);
    
            parent.popMatrix();
        }
    }

    private void drawSpheres() { //Draw both spheres using single function
        drawLeftSphere();
        drawRightSphere();
    }

    private void drawLeftSphere() {
        if (parent instanceof Visual) {
            ((Visual)parent).calculateAverageAmplitude();
        }
        float amplitude = ((Visual)parent).getSmoothedAmplitude();
        float yPosition = parent.height-15000 * 0.2f + map(amplitude, 0, 1, 50, -50);
    
        float baseX = parent.width * -0.9f;
        float oscillationRange = parent.width * 0.4f * amplitude;
        float xPosition = baseX - oscillationRange * sin(parent.frameCount * 0.05f);
    
        parent.lights();
        parent.noStroke();
        parent.fill(sunColor);
    
        int detail = (int) map(amplitude, 0, 1, 6, 24);
        parent.sphereDetail(detail);
        float size = 300 * (0.5f + 3.0f * amplitude);
    
        parent.pushMatrix();
        parent.translate(xPosition, yPosition, -200);
        parent.sphere(size);
        parent.popMatrix();
    }

    private void drawRightSphere() {
        if (parent instanceof Visual) {
            ((Visual)parent).calculateAverageAmplitude();
        }
        float amplitude = ((Visual)parent).getSmoothedAmplitude();
        float yPosition = parent.height-15000 * 0.2f + map(amplitude, 0, 1, 50, -50);
    
        float baseX = parent.width+2400 * 0.9f;
        float oscillationRange = parent.width * 0.4f * amplitude;
        float xPosition = baseX + oscillationRange * sin(parent.frameCount * 0.05f);
    
        parent.lights();
        parent.noStroke();
        parent.fill(moonColor);
    
        int detail = (int) map(amplitude, 0, 1, 6, 24);
        parent.sphereDetail(detail);
        float size = 300 * (0.5f + 3.0f * amplitude);
    
        parent.pushMatrix();
        parent.translate(xPosition, yPosition, -200);
        parent.sphere(size);
        parent.popMatrix();
    }

    public void draw() {
        float fov = PApplet.PI / 3; //Calculate fov of camera
        float cameraY = parent.height / 2.0f; //Calculate height of camera
        float cameraZ = (cameraY / PApplet.tan(fov / 2.0f)) * 4; //Calculate distance of camera to scene
        parent.perspective(fov, (float) parent.width / (float) parent.height, 1, 10000); //Set fov and distance of camera
        parent.camera(parent.width / 2.0f, cameraY, cameraZ, parent.width / 2.0f, cameraY, 0, 0, 1, 0); //Make camera in set position
        parent.background(bgHue, 255, bgBrightness); //Set background color

        drawStars(); //Draw stars
        drawCube(); //Draw the cubes
        drawSpheres(); //Draw both top left and top right sphere

        if (spamMode) {
            sceneChange(); //Call sceneChange function A LOT
        }
    }
}