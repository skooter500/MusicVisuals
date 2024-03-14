package ie.tudublin;

import C22410766.AaronVisual;;


public class Main {

    public void startUI() {
        String[] a = { "MAIN" };
        processing.core.PApplet.runSketch(a, new AaronVisual());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startUI();
    }
}