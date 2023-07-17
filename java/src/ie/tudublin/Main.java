package ie.tudublin;

import C21725659.Check;
import C21725659.DemiAudioVisualiser;
import C21725659.RippleEcho;
import C21725659.TestingSubtitles;
import example.CubeVisual;
import example.MyVisual;
import example.RotatingAudioBands;

public class Main {

	public void startUI() {
		String[] a = { "MAIN" };
		processing.core.PApplet.runSketch(a, new RippleEcho());
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.startUI();
	}
}

