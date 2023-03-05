package ie.tudublin;


public class Main
{	
	public static void main(String[] args)
	{
		startMusicVisualizer();
	}

	public static void startMusicVisualizer()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new MusicVisualizer());		
	}
}