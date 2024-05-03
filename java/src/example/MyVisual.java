package example;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PShape;
import ie.tudublin.*;
import ddf.minim.analysis.*;
import processing.core.PVector;


public class MyVisual extends Visual {
    WaveForm wf;
    AudioBandsVisual abv;
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    poly play;

    float smooth = 0;

    // variables used in alannahs code
    FFT fft;
    float smoothedAmplitude = 4;
    //cube related code
    int nbCubes;
    double specLow = 0.03; // 3%
    double specMid = 0.125; // 12.5%
    double specHi = 0.20;   // 20%
    Cube[] cubes;

    //line related code
    int x = width;
    int y = height;
    int x0 = 10; int y0 = 10;
    int lineSegments = 150; 
    float[] lineOffsetsLeft;
    float[] lineOffsetsRight;
    
    //eye related code
    PVector[] eyePositions;
    int[] eyeSizes;

    // FIONAN
    int mode = 0;

    float[] lerpedBuffer;

    float[] lerpedBuffer131;
    float y131 = 0;
    float smoothedY = 0;
    float smoothedAmplitude131 = 0;

    PShape car;
    float ry;

    //defining camera positions
    float camW_X = 500, camW_Y = 500, camW_Z = 500;
    float camA_X = -200, camA_Y = 500, camA_Z = 500;
    float camS_X = 500, camS_Y = -200, camS_Z = 500;
    float camD_X = 800, camD_Y = 500, camD_Z = 500;

    public void settings() {
        //size(1024, 500);

        // Use this to make fullscreen
        // fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        fullScreen(P3D, SPAN);
    }

    public void setup() {
        startMinim();
        minim = new Minim(this);
        colorMode(HSB,360,255,255);
        rectMode(CENTER);
        // Call loadAudio to load an audio file to process
        ap = minim.loadFile("Bicep - Glue (Original Mix)_q5rliCxX8xc.mp3", 1024);
        ap.play();
        ab = ap.mix;
        loadAudio("Bicep - Glue (Original Mix)_q5rliCxX8xc.mp3");
        // Call this instead to read audio from the microphone
        //startListening();
        

        wf = new WaveForm(this);
        abv = new AudioBandsVisual(this);
        lerpedBuffer = new float[width];

        y131 = height / 2;
        smoothedY = y131;

        lerpedBuffer131 = new float[width];

        car = loadShape("java/data/sportsCar.obj");
    }

    float off = 0;
    float lerpedAvg = 0;

    public void keyPressed() {
        if (key >= '0' && key <= '9') {
			mode = key - '0';
		}
		if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
        if (key == 'g'){
            ap.close();
            ap = minim.loadFile("Vengaboys - We like to Party! (The Vengabus).mp3", 1024);
            ap.play();
            ab = ap.mix;
            loadAudio("Vengaboys - We like to Party! (The Vengabus).mp3");
        }
        if (key == 'h'){
            ap.close();
            ap = minim.loadFile("Bicep - Glue (Original Mix)_q5rliCxX8xc.mp3", 1024);
            ap.play();
            ab = ap.mix;
            loadAudio("Bicep - Glue (Original Mix)_q5rliCxX8xc.mp3");
        }
    }

    public void draw() {

        //FROM PROCESSING WEBSITE FOR DEBUGGING

        //camera(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ)	
        
        // Parameters
        // eyeX	(float)	x-coordinate for the eye
        // eyeY	(float)	y-coordinate for the eye
        // eyeZ	(float)	z-coordinate for the eye
        // centerX	(float)	x-coordinate for the center of the scene
        // centerY	(float)	y-coordinate for the center of the scene
        // centerZ	(float)	z-coordinate for the center of the scene
        // upX	(float)	usually 0.0, 1.0, or ‑1.0
        // upY	(float)	usually 0.0, 1.0, or ‑1.0
        // upZ	(float)	usually 0.0, 1.0, or ‑1.0

        //function for swithcing camera angles
        if (key == 'w') {
            camera(camW_X, camW_Y, camW_Z, 0, 0, 0, 0, 1, 0);
        } else if (key == 'a') {
            camera(camA_X, camA_Y, camA_Z, 0, 0, 0, 0, 1, 0);
        } else if (key == 's') {
            camera(camS_X, camS_Y, camS_Z, 0, 0, 0, 0, 1, 0);
        } else if (key == 'd') {
            camera(camD_X, camD_Y, camD_Z, 0, 0, 0, 0, 1, 0);
        }
        //q to reset to default
        else if (key == 'q') {

            camera(100, 0, 0, 0, 0, 0, 0, 1, 0); 
        } else {
            camera();
        }

        if (ab.size() <= 0) {
            System.out.println("Audio buffer is empty or not loaded properly.");
            return; // Exit if audio buffer is empty
        }
        float sum = 0;
        
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
        }
        float average = 0;
        average= sum / (float) ab.size();
        smooth = lerp(smooth, average, 0.1f);
        background(0);
        stroke(200,200,200);
        try {
            // Call this if you want to use FFT data
            calculateFFT();
        } catch (VisualException e) {
            e.printStackTrace();
        }
        // Call this is you want to use frequency bands
        calculateFrequencyBands();

        // Call this is you want to get the average amplitude
        calculateAverageAmplitude();
        wf.render();
        abv.render();

        float average131 = 0;
        float sum131 = 0;
        off += 1;

        for(int i = 0 ; i < ab.size() ; i ++)
        {
            sum131 += abs(ab.get(i));
            lerpedBuffer131[i] = lerp(lerpedBuffer131[i], ab.get(i), 0.05f);
        }

        average131= sum131 / (float) ab.size();

        smoothedAmplitude131 = lerp(smoothedAmplitude131, average131, 0.1f);

        float tot = 0;
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            tot += abs(ab.get(i));
        }

        float avg = tot / ab.size();

        lerpedAvg = lerp(lerpedAvg, avg, 0.1f);

        switch(mode)
        {
            case 0:
            { 
                float hue = frameCount % 360;
                colorMode(HSB);
                background(hue, 255, 255);
                textAlign(CENTER, CENTER); 
                textSize(40);
                fill(255); 
                
                // Display a welcome message 
                text("Welcome to OOP 2024 assignment!", width / 2, height / 4);
                
                // Display mode selection 
                textSize(30); // Adjust text size
                text("Press 1 for Éadaoin's Circle Maze", width / 2, height / 2 - 50);
                text("Press 2 for Fionán's reactive triangle and 3D Matrix", width / 2, height / 2);
                text("Press 3 for Alannahs eye visual", width / 2, height / 2 + 50);
                text("Press 4 for Alannahs cube visual", width / 2, height / 2 + 100);
                text("Press 5 for Éadaoin's Kaleidoscope", width / 2, height / 2 + 150);
                text("Press 6 for Éadaoin's Circular Kaleidoscope", width / 2, height / 2 + 200);
                text("Press 7 for Fionán's 3D sports car and reactive triangles", width / 2, height / 2 + 250);
                text("Press 'SPACE' to pause the current audio", width / 2, height -50);

                break;
            }

            //Éadaoin's visual
            case 1:
            {
                play = new circles(this);
                play.render();
                break;
            }

            //Fionán's visual
            case 2:
            {
                play = new trimatrix(this);
                play.render();
                break;
            }

            //Alannah's visual
            case 3:
            {
                eyePositions = new PVector[]{
                    new PVector(width * 0.25f, height * 0.5f), 
                    new PVector(width * 0.75f, height * 0.5f), 
                    new PVector(width * 0.1f, height * 0.2f),  
                    new PVector(width * 0.9f, height * 0.2f),  
                    new PVector(width * 0.5f, height * 0.1f),  
                    new PVector(width * 0.1f, height * 0.8f),  
                    new PVector(width * 0.9f, height * 0.8f),  
                    new PVector(width * 0.5f, height * 0.9f)   
                };
        
                eyeSizes = new int[]{
                    300, 
                    300, 
                    200, 
                    200, 
                    200, 
                    200, 
                    200, 
                    200  
                };
                
                displayBackgroundEffect();
                drawEyes();
                break;   
            }

            case 4:
            {
                //create a new fft instance
                fft = new FFT(ab.size(), 44100);
                stroke(255);
                noFill();

                // Display across the whole screen
                nbCubes = (int) (fft.specSize() * specHi); // Make sure this calculation is correct

                cubes = new Cube[nbCubes]; //  not null
                
                // Cube initialization
                for (int i = 0; i < nbCubes; i++) {
                    cubes[i] = new Cube(); // Properly initialize Aeach cube
                }

                //smoothedAmplitude=smoothedAmplitude*0.9f+fft.mix.level*0.1f;
                // Display cubes
        
                for (int i = 0; i < nbCubes; i++) {
                    cubes[i].display((float) specLow, (float) specMid, (float) specHi, smoothedAmplitude, 0);
                }
                
                // Initialize arrays for line displacements
                lineOffsetsLeft = new float[lineSegments];
                lineOffsetsRight= new float[lineSegments];

                //process the audio buffer
                fft.forward(ab);

                // Update line offsets based on FFT data
                updateLineOffsets();


                // Draw diagonal lines with wave-like movements
                drawLine(lineOffsetsLeft, 0, 0, width, height);  // Top-left to bottom-right
                drawLine(lineOffsetsRight, width, 0, 0, height);  // Top-right to bottom-left


                break;
            }

            //Both Éadaoin's
            case 5:
            {
                play = new vertex(this);
                play.render();   
                break;
            }
            case 6:
            {
                play = new circlevertex(this);
                play.render();   
                break;
                
            }
            //Fionán's visual
            case 7:
            {
                play = new tricar(this);
                play.render();
                break;
            }
        }
    }

    //functions for alannahs visuals
    private void displayBackgroundEffect() {
        // Parameters for the circular shape
        int numPoints = ab.size(); // Number of points in the circle based on audio buffer size
        float centerX = width / 2; // Circle's center X-coordinate
        float centerY = height / 2; // Circle's center Y-coordinate
        float radius = 300; // Base radius of the circle
        float thetaInc = TWO_PI / numPoints; // Angle increment for each point
    
        // Compute amplitude scaling
        float pulsatingScale = map(smoothedAmplitude, 0, 1, 0.5f, 1.5f); // Scaling based on amplitude
        float waveHeight = sin(frameCount / 10.0f) * pulsatingScale * 50; // Modulate height with a sine wave
    
        // Set up for drawing
        noFill(); 
        stroke(255); // Circle outline color
        strokeWeight(3);
        beginShape(); // Start creating the circular shape
    
        // Loop through the audio buffer to create the circular shape
        for (int i = 0; i < numPoints; i++) {
            float theta = thetaInc * i; // Current angle in radians
            float offset = lerpedBuffer[i] * waveHeight; // Amplitude-based offset for the point
    
            // Calculate x and y based on polar coordinates and offset
            float x = centerX + (cos(theta) * (radius + offset)); // X-coordinate with offset
            float y = centerY + (sin(theta) * (radius + offset)); // Y-coordinate with offset
    
            vertex(x, y); // Add the calculated point to the shape
        }
    
        endShape(CLOSE); // Close the shape to create the circle
    }
    


    private void drawEyes() {
        for (int i = 0; i < eyePositions.length; i++) {
            PVector eye = eyePositions[i];

            PVector mouseVector = new PVector(mouseX, mouseY);

            // Calculate pupil positions
            PVector copyOfEye = eye.copy();
            PVector pupilDifference = mouseVector.copy().sub(eye).setMag(40);

            PVector pupil = copyOfEye.add(pupilDifference);

            // Draw eyes
            fill(255);
            ellipse(eye.x, eye.y, eyeSizes[i], eyeSizes[i]); 

            // Draw pupils
            fill(0);
            ellipse(pupil.x, pupil.y, eyeSizes[i] / 5, eyeSizes[i] / 5); // Proportionally larger pupil
        }
    }

    //functions for Alannahs cubes and lines, case 4
    private void updateLineOffsets() {
        // Loop through each segment in the line
        for (int i = 0; i < lineSegments; i++) {
            // Map the index 'i' to the FFT spectrum size to get a corresponding band
            int fftIndex = PApplet.floor(map(i, 0, lineSegments - 1, 0, fft.specSize()));
            //// Get the FFT amplitude for the band and map it to a displacement range (-100 to 100)
            float displacement = map(fft.getBand(fftIndex), 0, 10, -100, 100);

            lineOffsetsLeft[i] = displacement;  // For the diagonal line from top-left to bottom-right
            lineOffsetsRight[i] = displacement; // For the diagonal line from top-right to bottom-left
        }
    }

    // Function to draw 
    private void drawLine(float[] offsets, float startX, float startY, float endX, float endY) {
        beginShape();

        // Map 'i' to a normalized value between 0 and 1
        //  calculate the x coordinate
        //calculate the y coordinate
        for (int i = 0; i < lineSegments; i++) {
            float t = map(i, 0, lineSegments - 1, 0, 1);
            float x = lerp(startX, endX, t);
            float y = lerp(startY, endY, t) + offsets[i];  // Apply the calculated offset

            //Add the calculated point to the shape being drawn
            vertex(x, y);
        }
        endShape();
    }

    class Cube {
        float x, y, z; // Position
        float rotX, rotY, rotZ; // Rotation
        float sumRotX, sumRotY, sumRotZ; // rotation
        float size; // Size of the cube
        float speed; // Speed of the cube
        float angle;
    
        Cube() {
            x = random(width);
            y = random(height);
            z = random(-10000, 1000);
            rotX = random(TWO_PI);
            rotY = random(TWO_PI);
            rotZ = random(TWO_PI);
            size = random(50, 200);
            speed = .5f; // Set default speed
        }
        
        void display(float scoreLow, float scoreMid, float scoreHi, float intensity, float scoreGlobal) {
            pushMatrix(); // Save current transformation matrix
            translate(x, y, z); // Move to the position of the cube
            rotateX(rotX); // Apply rotation around x axis
            rotateY(rotY); // Apply rotation around y axis
            rotateZ(rotZ); // Apply rotation around z axis
            // Draw the cube
            fill(0, 255, 0);
            box(size); // Draw a cube with the given size
            popMatrix();

            z += speed; // Move the cube forward
    
            // Update cube position
            updateSpeed();
            
            float amp = 1 + smoothedAmplitude * 5;
            float waveHeight = sin(angle) * amp * size * -3;
            
            float numPoints = ab.size();
            float thetaInc = TWO_PI / numPoints;
        
            beginShape();
            for (int i = 0; i < numPoints; i++) {
                float px = x + cos(thetaInc * i) * waveHeight; // X coordinate with diagonal offset
                float py = y + sin(thetaInc * i) * waveHeight; // Y coordinate with diagonal offset
                vertex(px, py);
            }
            endShape(CLOSE); // Close the shape to connect the last point with the first
        }

        void updateSpeed() {
            float minAmplitude = 0.0f; 
            float maxAmplitude = 1.0f;
            float minSpeed = 0.1f; 
            float maxSpeed = 1.8f;


            // Map the smoothed amplitude to a corresponding speed value between minSpeed and maxSpeed
            speed = map(smoothedAmplitude, minAmplitude, maxAmplitude, minSpeed, maxSpeed);
        
            if (z > 1000) {
                // Reset position within visible range
                z = random(-10000, -1000); 
                x = random(width);
                y = random(height);
                rotX = random(TWO_PI);
                rotY = random(TWO_PI);
                rotZ = random(TWO_PI);
                size = random(50, 200); 
            }
        }
    }
}
