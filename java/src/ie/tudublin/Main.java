package ie.tudublin;

import c22371846.PatricksVisuals;
import example.*;
import C22533826.NoelsVisual;
import example.CubeVisual;
import example.CubeVisual1;
import example.MyVisual;
import example.RotatingAudioBands;

// Test - Michael 

public class Main {

    public void startUI() {
        String[] a = { "MAIN" };
        // processing.core.PApplet.runSketch(a, new PatricksVisuals());
        processing.core.PApplet.runSketch(a, new Heartbeat());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startUI();
    }
}
