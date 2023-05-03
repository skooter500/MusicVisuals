// Rana's combined code modified

package ie.tudublin;

import ddf.minim.*;
import ddf.minim.analysis.*;
import processing.core.PApplet;
import processing.core.PVector;
import java.util.ArrayList;

public class MainVisual extends PApplet {

    int currentVisual = 1; // 1 for MountainLandscape, 2 for MusicVisualizer

    // MountainLandscape variables
    Minim minim;
    AudioPlayer player;
    FFT fft;

    int cols, rows;
    int scl = 20;
    float w = 2000;
    float h = 1500;
    float[][] terrain;

    float flyover = 0;
    PVector camera = new PVector(0, -100, 500);
    PVector target = new PVector(0, 0, 0);
    float theta = 0;

    // MusicVisualizer variables
    BeatDetect beat;
    float angle = 0;
    float size = 50;
    float posX, posY;
    float speedX, speedY;
    ArrayList<Particle> particles;

    //Isa Visual Variables
    
    Branch branch1;
    Branch branch2;
    int counter = 0;
    int branchCounter = 0;
    float smothedAmplitude = 0;

    //CerensSong vvariables
    AudioPlayer ap; // Audio player object
    AudioBuffer ab; // Audio buffer object
    private static final int NUM_PARTICLES = 5000; // Number of particles
    private ArrayList<CerensParticle> Cerensparticles;

    private float[] waveform; // Array to store the waveform data

    private float rotationAngle = 0; // Angle for rotation
    private float rotationSpeed = 0.01f; // Speed of rotations




    public void settings() {
        size(1280, 720, P3D);
        //fullScreen();
    }

    
    public void setup() {
        minim = new Minim(this);
        player = minim.loadFile("Parasite.mp3", 1024);
        fft = new FFT(player.bufferSize(), player.sampleRate());
        beat = new BeatDetect();

        // Start audio processing in a separate thread
        new Thread(() -> player.play()).start();

        cols = (int) (w / scl);
        rows = (int) (h / scl);
        terrain = new float[cols][rows];
        particles = new ArrayList<>(); // Initialize the list

        posX = width / 2;
        posY = height / 2;
        speedX = random(-2, 2);
        speedY = random(-2, 2);
        ellipseMode(RADIUS);
        colorMode(HSB);

        Cerensparticles = new ArrayList<CerensParticle>();
        minim = new Minim(this); // Initialize the minim object
        ap = minim.loadFile("java/data/Parasite.mp3", 1024); // Load the audio file
        ap.play(); // Start playing the audio
        ab = ap.mix; // Get the audio buffer
        colorMode(HSB, 360, 100, 100); // Set the color mode
    
        waveform = ab.toArray(); // Convert audio buffer to an array

    
        for (int i = 0; i < NUM_PARTICLES; i++) {
            float angle = random(TWO_PI); // Random angle
            float radius = random(width * 0.4f); // Random radius within a range
            float speed = random(0.5f, 2.0f); // Random speed within a range
            float size = random(1, 4); // Random size within a range
    
            Cerensparticles.add(new CerensParticle(angle, radius, speed, size));
        }
    
        smooth(); // Enable smoothing of edges
    }
        

    public void draw() {
        if (currentVisual == 1) {
            drawMountainLandscape();
        } else if (currentVisual == 2) {
            drawMusicVisualizer();
        } else if (currentVisual == 3) {
            drawIsaVisual();
        } else if (currentVisual == 4) {
            drawCerensSongVisualizer();
        }
    }

    public void keyPressed() {
        if (key == '1') {
            currentVisual = 1;
        } else if (key == '2') {
            currentVisual = 2;
        } else if (key == '3') {
            currentVisual = 3;
        } else if (key == '4') {
            currentVisual = 4;
        }
    }

    public void drawMountainLandscape() {
        // ... MountainLandscape draw() code here ...
            background(0);
    
            fft.forward(player.mix);
            float smoothedAmplitude = 0;
            for (int i = 0; i < player.bufferSize(); i++) {
                smoothedAmplitude += abs(player.mix.get(i));
            }
            smoothedAmplitude /= player.bufferSize();
            smoothedAmplitude = lerp(smoothedAmplitude, 0, 0.1f);
        
            flyover += map(smoothedAmplitude, 0, 1, 0.2f, 1);
        
            camera.z = flyover;
            camera.y = -100 + map(smoothedAmplitude, 0, 1, -10, 10);
            target.x = sin(theta);
            target.z = cos(theta);
        
            translate(width / 2, height / 2, 0);
            rotateX(PI / 3);
            translate(-w / 2, -h / 2);
        
            // Set up the lighting
            ambientLight(100, 100, 100);
            directionalLight(255, 255, 255, 1, 1, -1);
        
            hint(DISABLE_DEPTH_TEST);
            noFill();
            stroke(255);
        
            for (int x = 0; x < cols; x++) {
                for (int y = 0; y < rows; y++) {
                    terrain[x][y] = map(noise(x * 0.1f, y * 0.1f, flyover * 0.01f), 0, 1, -100, 100) * smoothedAmplitude * 10;
                }
            }
        
            for (int y = 0; y < rows - 1; y++) {
                beginShape(TRIANGLE_STRIP);
                for (int x = 0; x < cols; x++) {
                    float r = map(y, 0, rows, 50, 255);
                    float g = map(x, 0, cols, 50, 255);
                    float b = map(terrain[x][y], -50, 50, 100, 200);
                    stroke(r, g, b);
                    vertex(x * scl, y * scl, terrain[x][y]);
                    vertex(x * scl, (y + 1) * scl, terrain[x][y + 1]);
                }
                endShape();
            }
        
            theta += map(smoothedAmplitude, 0, 1, 0, 0.05f);
        
        
    }

    public void drawMusicVisualizer() {
        // ... MusicVisualizer draw() code here ...
        background(0); // Set the background color

    // Analyze the audio for beats
    beat.detect(player.mix);

    // If a beat is detected, change the visual effect
    if (beat.isOnset()) {
      changeVisualEffect();
    }

    // Update the position of the design element
    posX += speedX;
    posY += speedY;

    // Check boundaries and change direction if necessary
    if (posX < 0 || posX > width) {
      speedX *= -1;
    }
    if (posY < 0 || posY > height) {
      speedY *= -1;
    }

    // Create particles based on the beat
    if (beat.isOnset()) {
      for (int i = 0; i < 10; i++) {
        Particle p = new Particle(posX, posY, speedX, speedY);
        particles.add(p);
      }
    }

    // Update and draw particles
    for (int i = particles.size() - 1; i >= 0; i--) {
      Particle p = particles.get(i);
      p.update();
      p.display();
      if (p.isDead()) {
        particles.remove(i);
      }
    }

    // Draw the current visual effect
    drawVisualEffect();

    // Calculate the FFT to get frequency data
    fft.forward(player.mix);

    }

    // ... MusicVisualizer methods and Particle class here ...
    void changeVisualEffect() {
        // Modify the design element based on the beat
        size = random(10, 100); // Randomize the size of the design element
        speedX = random(-5, 5); // Randomize the horizontal speed of the design element
        speedY = random(-5, 5); // Randomize the vertical speed of the design element
        background(random(255), random(255), random(255)); // Randomize the background color
      }
      
      void drawVisualEffect() {
        // Draw the design element in all four corners
        for (int i = 0; i < 4; i++) {
          float cornerX = i % 2 == 0 ? posX : width - posX;
          float cornerY = i < 2 ? posY : height - posY;
      
          pushMatrix();
          translate(cornerX, cornerY);
          rotate(angle);
          rectMode(CENTER);
          fill(random(255), random(255), random(255)); // Randomize the fill color of the design element
          rect(0, 0, size, size);
          popMatrix();
      
          // Create particles based on the beat
          if (beat.isOnset()) {
            for (int j = 0; j < 10; j++) {
              Particle p = new Particle(cornerX, cornerY, speedX, speedY);
              particles.add(p);
            }
          }
        }
      
        // Rotate the design element gradually
        angle += 0.05;
      }     
      
      
      class Particle {
        PVector position;
        PVector velocity;
        PVector acceleration;
        float lifespan;
        float size;
        float hue;
      
        Particle(float x, float y, float speedX, float speedY) {
          position = new PVector(x, y);
          velocity = new PVector(speedX, speedY).add(PVector.random2D().mult(random(1, 5))); // Randomize the initial velocity of particles
          acceleration = new PVector(0, 0.05f); // Set the constant acceleration for particles
          lifespan = 255;
          size = random(4, 16); // Randomize the size of particles
          hue = random(360); // Randomize the hue value of particles
        }
      
        void update() {
          velocity.add(acceleration); // Update the velocity of particles
          position.add(velocity); // Update the position of particles based on velocity
          lifespan -= 2; // Decrease the lifespan of particles
        }
      
        void display() {
          colorMode(HSB);
          noStroke();
          fill(hue, 255, 255, lifespan); // Set the fill color of particles with transparency based on lifespan
          ellipse(position.x, position.y, size, size); // Draw particles as ellipses
        }
      
        boolean isDead() {
          return lifespan <= 0; // Check if the lifespan of particles has reached zero
        }
      }
      
      public void stop() {
        player.close();
        minim.stop();
        super.stop();
      }
    public void drawIsaVisual() {
        background(0);
        //drawButtons();

        strokeWeight(0.7f);
        fill(0, 70);
        rect(-1, -1, width + 1, height + 1);
        fft.forward(player.mix);
        smothedAmplitude = lerp(smothedAmplitude, fft.getBand(512), 0.01f);
        counter++;
    
        for (int i = 0; i < 8; i++) {
            
            resetMatrix();
            
            perspective(PI / 1.5f, (float)width/(float)height, 0.1f, 1000.0f);
            translate(width/2 - 640, height/2 - 360, -300);
            
            branchCounter = 0;
    
            rotate(map(counter % 360, 0, 360, 0, PI * 1.5f));
            rotate(map((float) i, 0f, 6f, 0f, PI*1.5f));
    
            branch1 = new Branch(this, 0f, map(smothedAmplitude*2f, 0, .6f, -height / 30f, -height / 3.5f), 0, 14);
            branch2 = new Branch(this, 0f, map(smothedAmplitude*2f, 0, .6f, -height / 15f, -height / 3.5f), 0, 16);
    
    
            fill((counter) % 255);
            stroke((counter) % random(255));
            branch1.display();
    
            fill((counter / 2) % 255);
            stroke((counter / 2) % 255, 255, 255);
            branch2.display();
    
        }
    }
    
    
    class Branch {
        
        private MainVisual cv;
        private float start;
        private float amplitude;
        private float angle = 0;

        private Branch[] branches;

        Branch(MainVisual mainVisual, float start, float amplitude, float angle, int branches) {
            this.cv = mainVisual;
            this.start = start;
            this.amplitude = amplitude;
            this.angle = angle;
            
            branch(branches);
        }

        void display() {
            
            cv.rotate(this.angle);
            cv.line(0, 0, 0, amplitude);

            if (branches != null) {
                cv.translate(0, amplitude);
                cv.pushMatrix();
                branches[0].display();
                cv.popMatrix();
                branches[1].display();
                
            }
        }

        void branch(int numOfBranches) {
            
            if (numOfBranches > 0) {
                branches = new Branch[2];
                cv.branchCounter++;

                float angle = map(cv.smothedAmplitude, 0, 1, 3.14f / 5f, 3.14f / 2f);

                branches[0] = new Branch(cv, start - amplitude, amplitude / 1.5f, angle, numOfBranches - 2);
                branches[1] = new Branch(cv, start - amplitude, amplitude / 1.5f, -angle, numOfBranches - 2);
            }
        }
    }   
    
    
    public void drawCerensSongVisualizer() {
            // ... CerensSongVisualizer draw() code
            background(0);
            for (CerensParticle particle : Cerensparticles) {
                particle.update();
                particle.display();
            }
            
            drawWaveform(); // Draw the waveform
            rotateStar(); // Rotate the star
        }
        
        private void drawWaveform() {
            float waveformHeight = height / 2; // Height of the waveform
            noFill(); // Don't fill shapes
            strokeWeight(1); // Set stroke weight
    
            // Draw waveform graph
            beginShape();
            for (int i = 0; i < waveform.length; i += 10) {
                float waveX = map(i, 0, waveform.length - 1, 0, width); // Map x-coordinate
                float waveY = map(waveform[i], -1, 1, waveformHeight, -waveformHeight); // Map y-coordinate based on
                                                                                        // waveform data
                stroke(i % 360, 100, 100); // Set stroke color based on index
                vertex(waveX, waveY); // Add vertex to the shape
            }
            endShape();
        }
    
        private void rotateStar() {
            translate(width / 2, height / 2); // Translate to the center of the window
            rotate(rotationAngle); // Apply rotation transformation
    
            float amplitude = max(abs(ap.left.get(0)), abs(ap.right.get(0))); // Get the maximum amplitude
            float starSize = map(amplitude, 0, 1, 50, 200); // Map amplitude to star size
    
            noStroke(); // Disable stroke
            int npoints = floor(map(sin(starSize * 0.3f), -1, 1, 1, 12)); // Calculate the number of points for the star
                                                                          // shape
            drawStar(0, 0, starSize, starSize / 2, npoints); // Draw a star shape with variable number of points
            drawStar(0, 0, starSize, starSize / 2, 5); // Draw a 5-pointed star
            rotationAngle += rotationSpeed; // Update the rotation angle
        }
    
        private void drawStar(float x, float y, float radius1, float radius2, int npoints) {
            float amplitude = max(abs(ap.left.get(0)), abs(ap.right.get(0))); // Get the maximum amplitude
            float hueValue = map(amplitude, 0, 1, 0, 360); // Map amplitude to hue value
    
            fill(hueValue, 100, 100); // Set fill color based on hue value
            drawStarShape(x, y, radius1, radius2, npoints); // Draw the star shape
        }
    
        // Helper method to draw a star shape
        private void drawStarShape(float x, float y, float radius1, float radius2, int npoints) {
            float angle = TWO_PI / npoints; // Calculate the angle between each point
            float halfAngle = angle / 2.0f; // Calculate the half angle
    
            beginShape();
            for (float a = 0; a < TWO_PI; a += angle) {
                float sx = x + cos(a) * radius2; // Calculate x-coordinate for outer radius
                float sy = y + sin(a) * radius2; // Calculate y-coordinate for outer radius
                vertex(sx, sy); // Add vertex to the shape
                sx = x + cos(a + halfAngle) * radius1; // Calculate x-coordinate for inner radius
                sy = y + sin(a + halfAngle) * radius1; // Calculate y-coordinate for inner radius
                vertex(sx, sy); // Add vertex to the shape
            }
            endShape(CLOSE); // Close the shape
        }
    
        private class CerensParticle {
            private float angle; // Angle of the particle
            private float radius; // Distance from the center of the screen
            private float speed; // Speed at which the particle moves
            private float size; // Size of the particle
            private float rotation; // Rotation angle of the particle
        
            CerensParticle(float angle, float radius, float speed, float size) {
                this.angle = angle;
                this.radius = radius;
                this.speed = random(2.0f, 1.0f); // Adjust the speed range here
                this.size = size;
                this.rotation = 0;
            }
        
            void update() {
                radius += speed; // Update the radius based on the speed
                if (radius > 400) {
                    radius = 0; // Reset the radius if it exceeds a certain value
                }
            }
        
            void display() {
                float x = width / 2 + cos(angle) * radius; // Calculate the x-coordinate of the particle
                float y = height / 2 + sin(angle) * radius; // Calculate the y-coordinate of the particle
        
                // Get the amplitude from the audio buffer based on the angle
                float amplitude = ab.get((int) (angle * 10)) * 100;
        
                // Vary the hue based on the sum of the angle and amplitude
                float hueValue = (angle + amplitude) % 360;
        
                // Vary the size based on the amplitude
                float sizeValue = size + amplitude * 0.05f;
        
                pushMatrix(); // Save the current transformation matrix
                translate(x, y); // Translate to the particle's position
                rotate(rotation); // Apply rotation transformation
        
                noStroke(); // Disable stroke
                fill(hueValue, 100, 100); // Set fill color based on hue value
                ellipse(0, 0, sizeValue, sizeValue); // Draw the particle as an ellipse
        
                popMatrix(); // Restore the previous transformation matrix
            }
        }
    
        

    

    public static void main(String[] args) {
        PApplet.main("ie.tudublin.MainVisual");
    }
}
