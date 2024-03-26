package ie.tudublin;

import example.CubeVisual;
import example.MyVisual;
import example.RotatingAudioBands;
import C21374751.RoxanaVisual1;
import C22308773.AmyVisual1;
import C22787471.CiaraVisual1;
import C22790201.AleenaVisual1;

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