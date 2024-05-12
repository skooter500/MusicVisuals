package ie.tudublin;

import c22485282.CubeVisual;
import c22485282.MyVisual;
import c22485282.RotatingAudioBands;

public class Main {

    public void startUI() {
        String[] a = { "MAIN" };
        processing.core.PApplet.runSketch(a, new MyVisual());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startUI();
    }
}