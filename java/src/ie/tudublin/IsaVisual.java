package ie.tudublin;

import ddf.minim.*;
import processing.core.*;
import ddf.minim.analysis.*;

public class IsaVisual extends Visual {

    //String[] visualizers = {"IsaVisual", "CerenVisual", "MountainLandscape"};
    //int currentVisualizer = 0;
    

    Minim minim;
    AudioPlayer ap;
    FFT fft;

    Branch branch1;
    Branch branch2;

    int counter = 0;
    int branchCounter = 0;

    
    public void settings() {
        size(1400, 800);
        //size(1024, 500);
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("Parasite.mp3");
        ap.play();
        fft = new FFT(ap.bufferSize(), ap.sampleRate());
        colorMode(HSB);
        ellipseMode(RADIUS);
    }

    /*void drawButtons() {
        textAlign(RIGHT);
        textSize(15);
        fill(255);
        text(visualizers[currentVisualizer], width - 20, 30);
        rectMode(CORNER);
        rect(width - 80, 10, 70, 30);
        rect(width - 160, 10, 70, 30);
        rect(width - 240, 10, 70, 30);
        fill(0);
        textAlign(CENTER);
        text("Isa", width - 45, 30);
        text("Ceren", width - 115, 30);
        text("Rana", width - 185, 30);
    }

    public void mousePressed() {
        if (mouseY < 40) {
          if (mouseX > width - 80) {
            currentVisualizer = 0;
          } else if (mouseX > width - 160) {
            currentVisualizer = 1;
          } else if (mouseX > width - 240) {
            currentVisualizer = 2;
          }
          String visualizerClassName = "ie.tudublin." + visualizers[currentVisualizer];
          PApplet.main(visualizerClassName);
        }
    }*/
      
      

    class Branch {
        
        private IsaVisual iv;
        private float start;
        private float amplitude;
        private float angle = 0;

        private Branch[] branches;

        Branch(IsaVisual iv, float start, float amplitude, float angle, int branches) {
            this.iv = iv;
            this.start = start;
            this.amplitude = amplitude;
            this.angle = angle;
            
            branch(branches);
        }

        void display() {
            
            iv.rotate(this.angle);
            iv.line(0, 0, 0, amplitude);

            if (branches != null) {
                iv.translate(0, amplitude);
                iv.pushMatrix();
                branches[0].display();
                iv.popMatrix();
                branches[1].display();
                
            }
        }

        void branch(int numOfBranches) {
            
            if (numOfBranches > 0) {
                branches = new Branch[2];
                iv.branchCounter++;

                float angle = IsaVisual.map(iv.smothedAmplitude, 0, 1, 3.14f / 5f, 3.14f / 2f);

                branches[0] = new Branch(iv, start - amplitude, amplitude / 1.5f, angle, numOfBranches - 2);
                branches[1] = new Branch(iv, start - amplitude, amplitude / 1.5f, -angle, numOfBranches - 2);
            }
        }
    }   
      
    public void draw() {
        
        //background(0);
        //drawButtons();

        strokeWeight(0.7f);
        fill(0, 70);
        rect(-1, -1, width + 1, height + 1);
        fft.forward(ap.mix);
        smothedAmplitude = lerp(smothedAmplitude, fft.getBand(512), 0.01f);
        counter++;

        for (int i = 0; i < 8; i++) {
            
            resetMatrix();
            
            translate(width / 2, height / 2);
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

    public static void main(String[] args) {
        PApplet.main("ie.tudublin.IsaVisual");
    }
}
