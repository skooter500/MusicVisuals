package ie.tudublin;


public class Main
{
	public static void strawberry()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch(a, new StrawberryBush());
    }

	public static void Tree()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new tree());
    }
	


	public static void Flower()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new flower());
    }

	public static void vines()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new vines());
    }

	public static void notes()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new notes());
    }

	public static void main(String[] args) throws InterruptedException
	{
		System.out.println("Hello world");

    	//Tree();
		//strawberry();
		//Flower();
		vines();
		//notes();
	}
	
	
}