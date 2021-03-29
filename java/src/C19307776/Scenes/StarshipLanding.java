package C19307776.Scenes;
import C19307776.ImageAnimatable;
import C19307776.Scene;
import C19307776.Visuals;
import C19307776.Properties;
import java.util.Map;
import C19307776.utils.*;

public class StarshipLanding extends Scene {

	ImageAnimatable background;
	ImageAnimatable launchPad;
	ImageAnimatable engines1;
	ImageAnimatable engines2;
	ImageAnimatable engines3;
	ImageAnimatable[] exhaustClouds = new ImageAnimatable[22];

	public StarshipLanding(Visuals v) {
		this.sceneLength = 720;

		//Intialise viewport units
		VHVW d = new VHVW(v.width, v.height);

		background = new ImageAnimatable(v, "assets/ocean.png", d.vw(50f), d.vh(50f), Map.of("prop", (float) d.vw(90f), "parallax", 0.001f));
		launchPad = new ImageAnimatable(v, "assets/launch_platform.png", d.vw(40f), d.vh(50f), Map.of("prop", (float) d.vh(50f), "parallax", 0.001f));
		engines1 = new ImageAnimatable(v, "assets/landing1engine.png", d.vw(65f), -d.vh(5f), Map.of("prop", (float) d.vw(20), "r", -90f, "parallax", 0.001f));
		engines2 = new ImageAnimatable(v, "assets/landing2engine.png", d.vw(62f), d.vh(4f), Map.of("prop", (float) d.vw(20), "r", -90f, "parallax", 0.001f));
		engines3 = new ImageAnimatable(v, "assets/landing3engine.png", d.vw(61.25f), d.vh(8.5f), Map.of("prop", (float) d.vw(20), "r", -90f, "parallax", 0.001f));

		//Sets the duration of that sprites appear on the screen
		background.setDuration(720);
		launchPad.setDuration(720);
		engines1.setDuration(60);
		engines2.setDuration(60, 30);
		engines3.setDuration(90, 630);

		//Sprite animations
		engines1.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", (int) d.vh(4f), "startTime", 0, "duration", 60), 0);
		engines1.animateProperty(Map.of("property", Properties.XPOS.getValue(), "to", (int) d.vw(62f), "startTime", 0, "duration", 60), 0);

		engines2.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", (int) d.vh(8.5f), "startTime", 0, "duration", 30), 0);
		engines2.animateProperty(Map.of("property", Properties.XPOS.getValue(), "to", (int) d.vw(61.25f), "startTime", 0, "duration", 30), 0);
		
		engines3.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", (int) d.vh(50f), "startTime", 0, "duration", 210), 0);
		engines3.animateProperty(Map.of("property", Properties.XPOS.getValue(), "to", (int) d.vw(50f), "startTime", 0, "duration", 210), 0);

		engines3.animateProperty(Map.of("property", Properties.ROTATION.getValue(), "to", (int) 10f, "startTime", 0, "duration", 120), 0);

		engines3.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", (int) d.vh(65f), "startTime", 210, "duration", 180), 0);

		engines3.animateProperty(Map.of("property", Properties.ROTATION.getValue(), "to", (int) 0f, "startTime", 210, "duration", 60), 0);
		//starship.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", (int) -d.vh(10f), "startTime", 900, "duration", 300), 0);
		//flame.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", (int) d.vh(71.82f), "startTime", 300, "duration", 600), -d.vh(0.0003f));
		//flame.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", (int) -d.vh(40f), "startTime", 900, "duration", 600), 0);

		//Adding sprites to the scene
		this.addToScene(background);
		this.addToScene(launchPad);
		this.addToScene(engines1);
		this.addToScene(engines2);
		this.addToScene(engines3);

		for(int i = 0; i < 11; i++) {
			exhaustClouds[i] = new ImageAnimatable(v, "assets/launch_cloud/frame"+i+".png", d.vw(50f), d.vh(67.5f), Map.of("prop", (float) d.vw(3f), "parallax", 0.001f));
			exhaustClouds[i].setDuration(240+(i*15), 15);
			this.addToScene(exhaustClouds[i]);
		}
		for(int i = 0; i < 11; i++) {
			exhaustClouds[i+10] = new ImageAnimatable(v, "assets/launch_cloud/frame"+(10-i)+".png", d.vw(50f), d.vh(67.5f), Map.of("prop", (float) d.vw(3f), "parallax", 0.001f));
			exhaustClouds[i+10].setDuration(240+((i+10)*15), 15);
			this.addToScene(exhaustClouds[i+10]);
		}
	}
}
