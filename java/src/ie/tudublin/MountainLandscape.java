package ie.tudublin;

import ddf.minim.*;
import ddf.minim.analysis.*;
import processing.core.PApplet;
import processing.core.PVector;

public class MountainLandscape extends PApplet {

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

    public void settings() {
        size(1280, 720, P3D);
    }

    public void setup() {
        minim = new Minim(this);
        player = minim.loadFile("Parasite.mp3", 1024);
        fft = new FFT(player.bufferSize(), player.sampleRate());

        player.play();

        cols = (int) (w / scl);
        rows = (int) (h / scl);
        terrain = new float[cols][rows];
        frameRate(30);
    }

    public void draw() {
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
    
    public static void main(String[] args) {
        PApplet.main("ie.tudublin.MountainLandscape");
    }
}
