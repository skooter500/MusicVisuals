package ie.tudublin;

import C21423244.Monitor;
import example.CubeVisual;
import example.Test;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Monitor());		
	}

	public void testUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Test());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startUI();			
	}
}