package C19307776.Scenes;
import C19307776.Animatable;
import C19307776.Scene;
import C19307776.Visuals;
import C19307776.Properties;
import java.util.Map;
import C19307776.utils.*;

public class EarthSystem extends Scene{
	Animatable stars;
	Animatable earth;
	Animatable starship;

	public EarthSystem(Visuals v) {
		this.sceneLength = 420;

		VHVW d = new VHVW(v.width, v.height);

		stars = new Animatable(v, "assets/stars.png", d.vw(50f), d.vh(50f), Map.of("prop", (float) d.vw(90f)));
		earth = new Animatable(v, "assets/earth.png", d.vw(50f), d.vh(50f), Map.of("prop",d.vh(75f), "parallax", 0.003f));
		starship = new Animatable(v, "assets/starship_upperstage.png", d.vw(50f), d.vh(50f), Map.of("prop", d.vh(25f), "r", 90f, "parallax", 0.01f));

		v.background(0);
		stars.setDuration(420);
		earth.setDuration(420);
		starship.setDuration(420);
		//earth.animateProperty(Properties.XPOS.getValue(), 1500, 0, 600);
		//earth.animateProperty(Properties.YPOS.getValue(), 0, 0, 600);
		starship.animateProperty(Map.of("property", Properties.XPOS.getValue(), "to", (int) d.vw(60f), "startTime", 0, "duration", 420), 0);
		//starship.animateProperty(Properties.ROTATION.getValue(), 36000, 0, 120);
		
		this.addToScene(stars);
		this.addToScene(earth);
		this.addToScene(starship);
	}
}
