package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public abstract class DrawObjectAbstractClass {
    // Private Variables
    protected PApplet pApplet;
    protected int windowWidth;
    protected int windowHeight;
    protected AudioBuffer audioBuffer;
    protected FFT fft;

    public void render() {};
}
