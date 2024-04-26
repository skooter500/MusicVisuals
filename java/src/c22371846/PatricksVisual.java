package c22371846;

import ie.tudublin.VisualException;
import ie.tudublin.Heartbeat;

public class PatricksVisual {

    Heartbeat HB;
    Graph graphVisual;
    Animation animationVisual;

    int width, height;

    public PatricksVisual(Heartbeat HB) {
        this.HB = HB;
        this.graphVisual = new Graph(HB);
        this.animationVisual = new Animation(HB);

        this.width = HB.width;
        this.height = HB.height;
    }

    public void renderGraph() {
        graphVisual.render();
    }

    public void renderAnimation() {
        animationVisual.render();
    }
}

// package c22371846;

// import ddf.minim.AudioBuffer;
// import ddf.minim.AudioInput;
// import ddf.minim.AudioPlayer;
// import ddf.minim.Minim;
// import processing.core.PApplet;
// import ddf.minim.analysis.FFT;

// public class PatricksVisuals extends PApplet {
// Minim minim;
// AudioPlayer ap;
// AudioInput ai;
// AudioBuffer ab;
// FFT fft;

// int mode = 0;
// float[] lerpedBuffer;

// public void settings() {
// size(1024, 800, P3D);
// // fullScreen(P3D, SPAN);
// }

// public void setup() {
// colorMode(HSB);
// background(0);
// minim = new Minim(this);
// // Microphone
// // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
// // ab = ai.mix;

// // Music
// ap = minim.loadFile("Heartbeat.mp3", 1024);
// ap.play();
// ab = ap.mix;
// fft = new FFT(width, 44100);

// lerpedBuffer = new float[width];
// }

// public void keyPressed() {
// if (key >= '0' && key <= '9') {
// mode = key - '0';
// }
// if (keyCode == ' ') {
// if (ap.isPlaying()) {
// ap.pause();
// } else {
// ap.rewind();
// ap.play();
// }
// }
// }

// public void draw() {
// float separated = 10;
// float sum = 0;
// float off = 0;
// float average = 0;
// float smoothedAmplitude = 0;
// int bar_values = (int) (mouseX / 20.0f);
// float w = width / (float) bar_values;
// float h = height / separated;
// float H = height / 2;
// float W = width / 2;

// for (int i = 0; i < ab.size(); i++) {
// sum += abs(ab.get(i));
// lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
// }
// average = sum / (float) ab.size();

// smoothedAmplitude = lerp(smoothedAmplitude, average, 0.75f);

// fft.forward(ab);

// int maxFFTIndex = 0;
// for (int i = 0; i < fft.specSize() / 2; i++) {
// if (fft.getBand(i) > fft.getBand(maxFFTIndex)) {
// maxFFTIndex = i;
// }
// }
// float freq = fft.indexToFreq(maxFFTIndex);

// switch (mode) {
// case 0: {
// off += 10;
// background(0);
// text("Frequencies: " + freq, width - 300, height - 50);

// // bar values
// for (int i = 1; i < ab.size(); i++) {
// float num = 50;
// float f = lerpedBuffer[i] * H * 4.0f;
// float x = map(num * i, width - 450, width, num, width);
// float c = map((off), 0, 200, 0, 255);
// float cc = c * i;
// fill(cc % 256, 255, 255);
// rect(x, 0, w, f * h * 0.15F);
// noStroke();
// }

// for (int i = 0; i < 20; i++) {
// text(i * 50, 25, (i * 50) + 10);
// }
// break;
// }
// case 1: {
// off += 10;
// background((off) % 256, mouseX);
// for (int i = 0; i < fft.specSize() / 2; i++) {
// float c = map(i, 0, ab.size(), 0, 255);
// float cc = c * i;
// translate(100, 0, 0);
// rotateY(angle);
// rotateX(angle);
// if (keyCode == LEFT) {
// square(W, H, lerp(0, smoothedAmplitude, (fft.getBand(i) * 100)));
// fill((cc + off) % 256, mouseX, 255);
// }

// if (keyCode == RIGHT) {
// triangle(W, H, H, H, i, (fft.getBand(i)));
// fill((cc + off) % 256, mouseX, 255);
// }

// if (keyCode == UP) {
// circle(W, H, (fft.getBand(i) * 6));
// fill((cc + off) % 256, mouseX, 255);

// }

// if (keyCode == DOWN) {
// box((fft.getBand(i) * 3));
// fill((cc + off) % 256, mouseX, 255);
// }
// }
// angle += 0.01f;
// break;
// }
// }
// }

// float angle = 0;
// }