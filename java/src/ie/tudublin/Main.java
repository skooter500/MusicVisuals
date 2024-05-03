package ie.tudublin;

import Mercury.BohemianRhapsody;



public class Main {

    public void startUI() {
        String[] a = { "MAIN" };
        processing.core.PApplet.runSketch(a, new BohemianRhapsody());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startUI();
    }
}