package ie.tudublin;

import c20362766.*;
import example.MyVisual;

public class Main {

	public void startUI() {
		String[] a = { "MAIN" };
		//processing.core.PApplet.runSketch( a, new MyVisual());
		processing.core.PApplet.runSketch(a, new HabeebsVisuals());
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.startUI();
	}
}