package C19326126;

import example.AudioBandsVisual;
import example.CubeVisual;
import example.MyVisual;
import example.RotatingAudioBands;

public class Main
{	

	public void StarSystem()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new StarSystem());		
	}

    public void Example()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Example());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.StarSystem();			
	}
}