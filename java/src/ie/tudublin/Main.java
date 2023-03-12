package ie.tudublin;

//import example.CubeVisual;
import example.Monitor;
//import example.RotatingAudioBands;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Monitor());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startUI();			
	}
}