package ie.tudublin;

import OOP2023.*;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Start());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startUI();			
	}
}