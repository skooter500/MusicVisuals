package ie.tudublin;

public class Main
{

	

	public static void audio1()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Audio1());
    }
	public static void audio2()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new ParasiteEveVisualizer());
    }
	public static void audio3()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new ParasiteEveFlowerVisualizer());
    }
	public static void audio4()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new MoltenBallVisualizer());
    }
	public static void main(String[] args)
	{
		audio4();
	}
}