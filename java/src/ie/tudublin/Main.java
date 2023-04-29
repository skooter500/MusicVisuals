package ie.tudublin;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Experiance());		
	}
	public void discoBallUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new DiscoBall());		
	}

	public void music_noteUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new music_note());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		//main.startUI();	
		main.discoBallUI();	
		 	
	}
}