package C19307776.Scenes;
import C19307776.ImageAnimatable;
import C19307776.Scene;
import C19307776.Visuals;
import C19307776.Properties;
import java.util.Map;
import C19307776.utils.*;

public class Takeoff extends Scene {

	ImageAnimatable background;
	ImageAnimatable launchPad;
	ImageAnimatable starship;
	ImageAnimatable flame;
	ImageAnimatable[] exhaustClouds = new ImageAnimatable[22];

	public Takeoff(Visuals v) {
		this.sceneLength = 1100;

		//Intialise viewport units
		VHVW d = new VHVW(v.width, v.height);

		background = new ImageAnimatable(v, "assets/ocean.png", d.vw(50f), d.vh(50f), Map.of("prop", (float) d.vw(90f), "parallax", 0.001f));
		launchPad = new ImageAnimatable(v, "assets/launch_platform.png", d.vw(40f), d.vh(50f), Map.of("prop", (float) d.vh(50f), "parallax", 0.001f));
		starship = new ImageAnimatable(v, "assets/full_starship.png", d.vw(47f), d.vh(47f), Map.of("prop", (float) d.vw(26f), "parallax", 0.001f));
		flame = new ImageAnimatable(v, "assets/flames.png", d.vw(47f), d.vh(72f), Map.of("prop", (float) d.vw(3f), "parallax", 0.001f));

		//Sets the duration of that sprites appear on the screen
		background.setDuration(1100);
		launchPad.setDuration(1100);
		flame.setDuration(1100);
		starship.setDuration(1100);

		//Sprite animations
		starship.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", (int) d.vh(46.82f), "startTime", 300, "duration", 600), -d.vh(0.0003f));
		starship.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", (int) -d.vh(10f), "startTime", 900, "duration", 300), 0);
		flame.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", (int) d.vh(71.82f), "startTime", 300, "duration", 600), -d.vh(0.0003f));
		flame.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", (int) -d.vh(40f), "startTime", 900, "duration", 600), 0);

		//Adding sprites to the scene
		this.addToScene(background);
		this.addToScene(flame);
		this.addToScene(launchPad);
		this.addToScene(starship);

		for(int i = 0; i < 11; i++) {
			exhaustClouds[i] = new ImageAnimatable(v, "assets/launch_cloud/frame"+i+".png", d.vw(47f), d.vh(67.5f), Map.of("prop", (float) d.vw(3f), "parallax", 0.001f));
			exhaustClouds[i].setDuration(300+(i*15), 15);
			this.addToScene(exhaustClouds[i]);
		}
		for(int i = 0; i < 11; i++) {
			exhaustClouds[i+10] = new ImageAnimatable(v, "assets/launch_cloud/frame"+(10-i)+".png", d.vw(47f), d.vh(67.5f), Map.of("prop", (float) d.vw(3f), "parallax", 0.001f));
			exhaustClouds[i+10].setDuration(300+((i+10)*15), 15);
			this.addToScene(exhaustClouds[i+10]);
		}
	}
}
