package ie.tudublin;

import example.CubeVisual;
import example.MyVisual;
import example.RotatingAudioBands;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new stars());		
	}
	public void discoBallUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new DiscoBall());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		//main.startUI();	
		main.discoBallUI();		
	}
}