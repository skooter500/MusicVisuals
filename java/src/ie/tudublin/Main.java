package ie.tudublin;

import example.CubeVisual;
import example.MyVisual;
import example.ExampleRotatingAudioBands;
import example.test;
import c21430484.*;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new BensVisual());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startUI();			
	}
}