package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public abstract class VisualAbstractClass extends PApplet {
    // Private Variables
    protected int windowWidth;
    protected int windowHeight;
    protected PApplet pApplet;
    protected AudioPlayer audioPlayer;
    protected AudioBuffer audioBuffer;
    protected FFT fft;

    public void drawVisual() {};
    // private void loadRenderObjects() {};
}
