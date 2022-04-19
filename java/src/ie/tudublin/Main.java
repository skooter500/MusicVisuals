package ie.tudublin;

import example.CubeVisual;
import example.CubeVisual1;
import c20362766.HabeebsVisuals;
import c20362766.WaveForm4;
import example.MyVisual;
import example.RotatingAudioBands;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        //processing.core.PApplet.runSketch( a, new MyVisual());		
		processing.core.PApplet.runSketch( a, new HabeebsVisuals());
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startUI();			
	}
}