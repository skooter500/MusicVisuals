package ie.tudublin;

// import C21725659.JaredAudioVisualiser;
import C21468162.WarpedSpace;
import example.CubeVisual;
import example.MyVisual;
import example.RotatingAudioBands;
import processing.core.PApplet;

public class Main {

    public void startUI() {
        String[] a = { "MAIN" };
        PApplet.runSketch(a, new WarpedSpace());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startUI();
    }
}