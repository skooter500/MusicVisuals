package C19307776.Scenes;
import C19307776.Scene;
import C19307776.Visuals;
import C19307776.ImageAnimatable;
import java.util.Map;
import C19307776.Properties;
import C19307776.utils.*;

public class BoosterSep extends Scene {
	ImageAnimatable stars;
	ImageAnimatable superheavy;
	ImageAnimatable starship;

	public BoosterSep(Visuals v) {

		this.sceneLength = 1000;

		VHVW d = new VHVW(v.width, v.height);

		stars = new ImageAnimatable(v, "assets/stars.png", d.vw(50f), d.vh(50f), Map.of("prop", (float) d.vw(90f)));
		superheavy = new ImageAnimatable(v, "assets/superheavy_booster.png", d.vw(35f), d.vh(50f), Map.of("prop", (float) d.vw(32f), "r", 90f, "parallax", 0.003f));
		starship = new ImageAnimatable(v, "assets/starship_upperstage.png", d.vw(62f), d.vh(50f), Map.of("prop", (float) d.vw(26f), "r", 90f, "parallax", 0.003f));

		stars.setDuration(1000);
		superheavy.setDuration(1000);
		starship.setDuration(1000);

		starship.animateProperty(Map.of("property", Properties.XPOS.getValue(), "to", (int) d.vw(80f), "startTime", 60, "duration", 940), 0);
		superheavy.animateProperty(Map.of("property", Properties.XPOS.getValue(), "to", (int) d.vw(25), "startTime", 120, "duration", 800), 0);
		superheavy.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", (int) d.vh(55), "startTime", 120, "duration", 800), 0);
		superheavy.animateProperty(Map.of("property", Properties.ROTATION.getValue(), "to", (int) -35f, "startTime", 120, "duration", 880), 0);

		this.addToScene(stars);
		this.addToScene(superheavy);
		this.addToScene(starship);
	}
}
