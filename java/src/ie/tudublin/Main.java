package ie.tudublin;

import assaigment.DiscoBall;
import assaigment.music_note;
import assaigment.stars;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new stars(null));		
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

	public void experianceUI()
	{
		String[] a = {"MAIN"};
		processing.core.PApplet.runSketch( a, new Experiance());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();	
		main.startUI();		
	}
}