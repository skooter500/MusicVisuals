package C19307776.Scenes;
import C19307776.Scene;
import C19307776.Visuals;
import C19307776.Animatable;
import java.util.Map;
import C19307776.Properties;
import C19307776.utils.*;

public class StarshipRefuel extends Scene {
	Animatable stars;
	Animatable starship1;
	Animatable starship2;

	public StarshipRefuel(Visuals v) {

		this.sceneLength = 1620;

		VHVW d = new VHVW(v.width, v.height);

		stars = new Animatable(v, "assets/stars.png", d.vw(50f), d.vh(50f), Map.of("prop", (float) d.vw(90f)));
		starship1 = new Animatable(v, "assets/starship_upperstage.png", d.vw(25f), d.vh(50f), Map.of("prop", (float) d.vw(32f), "r", -88f, "parallax", 0.003f));
		starship2 = new Animatable(v, "assets/starship_upperstage.png", d.vw(62f), d.vh(50f), Map.of("prop", (float) d.vw(32f), "r", 90f, "parallax", 0.003f));

		stars.setDuration(1620);
		starship1.setDuration(1620);
		starship2.setDuration(1620);

		starship1.animateProperty(Map.of("property", Properties.XPOS.getValue(), "to", (int) d.vw(31.5f), "startTime", 0, "duration", 510), 0);

		//starship2.animateProperty(Map.of("property", Properties.XPOS.getValue(), "to", (int) d.vw(80f), "startTime", 60, "duration", 940), 0);
		//starship1.animateProperty(Map.of("property", Properties.XPOS.getValue(), "to", (int) d.vw(25), "startTime", 120, "duration", 800), 0);
		//starship1.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", (int) d.vh(55), "startTime", 120, "duration", 800), 0);
		//starship1.animateProperty(Map.of("property", Properties.ROTATION.getValue(), "to", (int) -35f, "startTime", 120, "duration", 880), 0);

		this.addToScene(stars);
		this.addToScene(starship1);
		this.addToScene(starship2);
	}
}
