package ie.tudublin;

public class Main
{

	

	public static void audio1()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Audio1());
    }
	public static void visualizer()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Visualizer());
    }
	public static void main(String[] args)
	{
		visualizer();
	}
}