package C19307776.Scenes;
import C19307776.Animatable;
import C19307776.Scene;
import C19307776.Visuals;
import C19307776.Properties;
import java.util.Map;
import C19307776.utils.VHVW;

public class Takeoff extends Scene{

	Animatable background;
	Animatable launchPad;
	Animatable rocket;
	Animatable flame;
	Animatable[] exhaustClouds = new Animatable[22];

	public Takeoff(Visuals v) {
		super();
		this.v = v;
		this.sceneLength = 800;
		//Intialise viewport units
		VHVW d = new VHVW(v.width, v.height);

		background = new Animatable(v, "assets/ocean.png", d.vw(50f), d.vh(50f), Map.of("prop", (float) d.vw(90f), "parallax", 0.001f));
		launchPad = new Animatable(v, "assets/launch_platform.png", d.vw(40f), d.vh(50f), Map.of("prop", (float) d.vh(50f), "parallax", 0.001f));
		rocket = new Animatable(v, "assets/full_starship.png", d.vw(47f), d.vh(47f), Map.of("prop", (float) d.vw(26f), "parallax", 0.001f));
		flame = new Animatable(v, "assets/flames.png", d.vw(47f), d.vh(72f), Map.of("prop", (float) d.vw(3f), "parallax", 0.001f));

		//Sets the duration of that sprites appear on the screen
		background.setDuration(800);
		launchPad.setDuration(800);
		flame.setDuration(800);
		rocket.setDuration(800);

		//Sprite animations
		rocket.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", -300, "startTime", 0, "duration", 600), -d.vh(0.0003f));
		rocket.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", (int) -d.vh(10f), "startTime", 600, "duration", 300), 0);
		flame.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", -300, "startTime", 0, "duration", 600), -d.vh(0.0003f));
		flame.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", (int) -d.vh(40f), "startTime", 600, "duration", 600), 0);

		//Adding sprites to the scene
		this.addToScene(background);
		this.addToScene(flame);
		this.addToScene(launchPad);
		this.addToScene(rocket);

		for(int i = 0; i < 11; i++) {
			exhaustClouds[i] = new Animatable(v, "assets/launch_cloud/frame"+i+".png", d.vw(47f), d.vh(67.5f), Map.of("prop", (float) d.vw(3f), "parallax", 0.001f));
			exhaustClouds[i].setDuration(i*15, 15);
			this.addToScene(exhaustClouds[i]);
		}
		for(int i = 0; i < 11; i++) {
			exhaustClouds[i+10] = new Animatable(v, "assets/launch_cloud/frame"+(10-i)+".png", d.vw(47f), d.vh(67.5f), Map.of("prop", (float) d.vw(3f), "parallax", 0.001f));
			exhaustClouds[i+10].setDuration((i+10)*15, 15);
			this.addToScene(exhaustClouds[i+10]);
		}
		//exhaustClouds[10] = new Animatable(v, "assets/launch_cloud/frame"+10+".png", d.vw(47f), d.vh(67.5f), Map.of("prop", (float) d.vw(3f), "parallax", 0.001f));
		//exhaustClouds[10].setDuration(10*10, 900);
		//this.addToScene(exhaustClouds[10]);
	}
}
