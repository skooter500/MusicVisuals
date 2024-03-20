package ie.tudublin;

import c22371846.PatricksVisuals;
import example.*;

// Test - Michael 

public class Main {

    public void startUI() {
        String[] a = { "MAIN" };
        processing.core.PApplet.runSketch(a, new PatricksVisuals());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startUI();
    }
}