package C19307776.Scenes;
import java.util.Map;

import C19307776.Animatable;
import C19307776.Visuals;
import C19307776.Scene;
import C19307776.utils.VHVW;
import C19307776.Properties;

public class Curvature extends Scene {
	Animatable earth;
	Animatable starship;
	Animatable flame;

	public Curvature(Visuals v) {
		this.sceneLength = 900;

		VHVW d = new VHVW(v.width, v.height);

		earth = new Animatable(v, "assets/earth.png", d.vw(50f) ,  d.vh(250f), Map.of("prop", d.vw(200f), "parallax", 0.001f));
		starship = new Animatable(v, "assets/full_starship.png", d.vw(50f), d.vh(70f), Map.of("prop", (float) d.vh(5f), "parallax", 0.003f));
		flame = new Animatable(v, "assets/flames.png", d.vw(58.5f), d.vh(40f), Map.of("prop", (float) d.vw(0.3f), "r", 90f, "parallax", 0.003f));

		starship.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", (int) d.vh(40f), "startTime", 0, "duration", 300), 0);
		starship.animateProperty(Map.of("property", Properties.XPOS.getValue(), "to", (int) d.vw(60f), "startTime", 0, "duration", 300), 0);

		
		starship.animateProperty(Map.of("property", Properties.ROTATION.getValue(), "to", (int) 90f, "startTime", 0, "duration", 300), 0);
		starship.animateProperty(Map.of("property", Properties.SCALE.getValue(), "to", (int)  d.vh(90f), "startTime", 300, "duration", 300), 0);
		starship.animateProperty(Map.of("property", Properties.XPOS.getValue(), "to", (int) d.vw(45f), "startTime", 300, "duration", 300), 0);

		flame.animateProperty(Map.of("property", Properties.XPOS.getValue(), "to", (int) d.vw(26f), "startTime", 0, "duration", 300), 0);
		flame.animateProperty(Map.of("property", Properties.SCALE.getValue(), "to", (int) d.vw(5f), "startTime", 0, "duration", 300), 0);

		earth.animateProperty(Map.of("property", Properties.SCALE.getValue(), "to", (int) d.vw(100f), "startTime", 300, "duration", 300), 0);
		earth.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", (int) d.vh(300f), "startTime", 300, "duration", 300), 0);

		earth.setDuration(900);
		starship.setDuration(900);
		flame.setDuration(300, 900);

		this.addToScene(earth);
		this.addToScene(starship);
		this.addToScene(flame);
	}
}
