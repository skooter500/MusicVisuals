package ie.tudublin;

public class Main
{

	

	public static void audio1()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Audio1());
    }
	public static void audio2() {
		String[] a = {"MAIN"};
		processing.core.PApplet.runSketch(a, new ParasiteEveVisualizer());
	}
	
	public static void audio3()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new AudioWireframeLandscape());
    }
	public static void audio4()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new MoltenBallVisualizer());
    }
	public static void audio5()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Visualizer());
    }
	public static void audio6()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new AudioVisual());
    }
	public static void audio7()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new CerensSongVisualizer());
    }
	public static void main(String[] args)
	{
		audio7();
	}
}