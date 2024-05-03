package C22339066;


import ie.tudublin.Visual;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import ddf.minim.analysis.*;

public class Alannahsvisual extends Visual {

    Minim minim;
    AudioPlayer song;
    AudioBuffer ab;
    FFT fft;

    
    int mode = 0;
    float smoothedAmplitude = 4;
    int nbCubes;
    double specLow = 0.03; // 3%
    double specMid = 0.125; // 12.5%
    double specHi = 0.20;   // 20%

    Cube[] cubes;
    

    public void settings() {
        size(1024, 500);

        // Use this to make fullscreen
        // fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        // fullScreen(P3D, SPAN);
    }

    public void setup() {
        startMinim();
        minim = new Minim(this);

        // Call loadAudio to load an audio file to process
        //song = minim.loadAudio("Bicep - Glue (Original Mix)_q5rliCxX8xc.mp3");
        song.play();
        ab = song.mix;
        fft = new FFT(ab.size(), 44100);

        // Display across the whole screen
        nbCubes = (int)(fft.specSize()*specHi);

        cubes = new Cube[nbCubes];

        // Create cube objects
        for (int i = 0; i < nbCubes; i++) {
            cubes[i] = new Cube();
        }

        
        // Start the song
        song.play(0);
    }

    public void draw() {
        background(0);
        /*try {
            // Call this if you want to use FFT data
            calculateFFT();
        } catch (VisualException e) {
            e.printStackTrace();
        }
        // Call this is you want to use frequency bands
        calculateFrequencyBands();

        // Call this is you want to get the average amplitude
        calculateAverageAmplitude();*/
        background(0);
        switch (mode) {
            case 0:
                fft.forward(ab);
                //smoothedAmplitude=smoothedAmplitude*0.9f+fft.mix.level*0.1f;
                // Display cubes
                for (int i = 0; i < nbCubes; i++) {
                    cubes[i].display((float) specLow, (float) specMid, (float) specHi, smoothedAmplitude, 0);
                }
                break;
            }

    }

    class Cube {
        float x, y, z; // Position
        float rotX, rotY, rotZ; // Rotation
        float sumRotX, sumRotY, sumRotZ; // Cumulative rotation
        float size; // Size of the cube
        float speed; // Speed of the cube
    
        Cube() {
            x = random(width);
            y = random(height);
            z = random(-10000, 1000);
            rotX = random(TWO_PI);
            rotY = random(TWO_PI);
            rotZ = random(TWO_PI);
            size = random(50, 200);
            speed = 1.0f; // Set default speed
        }
    
        void display(float scoreLow, float scoreMid, float scoreHi, float intensity, float scoreGlobal) {
            pushMatrix(); // Save current transformation matrix
            translate(x, y, z); // Move to the position of the cube
            rotateX(rotX); // Apply rotation around x-axis
            rotateY(rotY); // Apply rotation around y-axis
            rotateZ(rotZ); // Apply rotation around z-axis
            // Draw the cube
            fill(0, 255, 0);
            box(size); // Draw a cube with the given size
            popMatrix();
    
            // Update cube position
            updateSpeed();
            z += speed; // Move the cube forward
        }
        void updateSpeed(){
            float minAmplitude = 0.0f; // Minimum amplitude threshold
            float maxAmplitude = 1.0f; // Maximum amplitude threshold
            float minSpeed = 0.5f; // Minimum speed
            float maxSpeed = 5.0f; // Maximum speed
            // Map the smoothed amplitude to the speed range
            speed = map(smoothedAmplitude, minAmplitude, maxAmplitude, minSpeed, maxSpeed);
    
        }
    }
    
   
        
        void display(float scoreLow, float scoreMid, float scoreHi, float intensity, float scoreGlobal) {
            pushMatrix(); // Save current transformation matrix

            
            fill(255); // Set fill color to white
            
            
        }
    
    public static void main(String[] args) {
        PApplet.main("ie.tudublin.MyVisual");
    }


}
