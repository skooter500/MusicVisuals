package c22368271;

import ddf.minim.*;
import ddf.minim.analysis.*;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class moonVisual extends PApplet {

    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    FFT fft;

    PImage bg;
    PImage flower;
    float speedX, speedY;

    int mode = 0;

    float[] lerpedBuffer;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    float flowerX, flowerY; // flower pos
    PVector flowerPos;

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
    }

    public void settings() {
        size(1024, 600, P3D);
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("Squidward's Tiki Land     Psy-Trance Remix.mp3", 1024);
        ap.play();
        ab = ap.mix;

        fft = new FFT(ap.bufferSize(), ap.sampleRate());

        bg = loadImage("spongeBG.png");
        flower = loadImage("spongeFlower.png");

        lerpedBuffer = new float[width];

        colorMode(HSB);
    }

    public void flowers(float amplitude) {
        int numflowers = 7;
        float angleIn = TWO_PI / numflowers;

        float flowerSize = map(amplitude, 0, 1, 10, 100);

        for (int i = 0; i < numflowers; i++) {
            float angle = i * angleIn;
            float radius = flowerSize * (i + 10);
            float flowerPosX = width / 2 + cos(angle) * radius-100;
            float flowerPosY = height / 2 + sin(angle) * radius-100;
            image(flower, flowerPosX, flowerPosY, flower.width / 6, flower.height / 6);
        }
    }

    void drawStrobeLights() {
        int numLights = 20; // Number of strobe lights
        float angleStep = TWO_PI / numLights; // Angle step between each light
        float radius = min(width, height) * 0.4f; // Radius of the circle containing the lights
        float centerX = width / 2; // X-coordinate of the center of the circle
        float centerY = height / 2; // Y-coordinate of the center of the circle
    
        for (int i = 0; i < numLights; i++) {
            // Calculate position of each strobe light around the circle
            float x = centerX + cos(angleStep * i) * radius;
            float y = centerY + sin(angleStep * i) * radius;
    
            // Calculate size of the strobe light based on the amplitude
            float lightSize = map(smoothedAmplitude, 0, 1, 10, 50);
    
            // Calculate color of the strobe light based on time
            int lightColor = color((frameCount + i * 10) % 255, 255, 255);
    
            // Set fill and stroke colors
            fill(lightColor);
            stroke(lightColor);
    
            // Draw the strobe light
            ellipse(x, y, lightSize, lightSize);
        }
    }
    
    

    public void draw() {
        image(bg, 0, 0, width, height);

        float average = 0;
        float sum = 0;
        for (int i = 0; i < ab.size(); i++) {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
        }
        average = sum / (float) ab.size();

        flowers(smoothedAmplitude);

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
        float squareSize = map(smoothedAmplitude, 0, 1, 10, 400);
        float squareX = width / 2 - squareSize / 2;
        float squareY = height / 2 - squareSize / 2;
        int rainbowColor = color((frameCount / 2) % 255, (frameCount / 3) % 255, (frameCount / 4) % 255);
        stroke(rainbowColor);
        fill(0);
        rect(squareX, squareY, squareSize, squareSize);

        float waveAmplitude = map(smoothedAmplitude, 0, 1, 0, squareSize / 2);
        fft.forward(ap.mix);
        for (int i = 0; i < fft.specSize(); i++) {
            float x = map(i, 0, fft.specSize(), squareX, squareX + squareSize);
            float y = squareY + squareSize / 2 + fft.getBand(i) * waveAmplitude;
            point(x, y);
        }

        drawStrobeLights();
    }
}
