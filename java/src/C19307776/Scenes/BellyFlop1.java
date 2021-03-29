package C19307776.Scenes;
import C19307776.Scene;
import C19307776.Visuals;
import C19307776.ImageAnimatable;
import java.util.Map;
import C19307776.Properties;
import C19307776.utils.*;

public class BellyFlop1 extends Scene{
	ImageAnimatable starship;
	ImageAnimatable background;

	public BellyFlop1(Visuals v) {
		this.sceneLength = 300;

		VHVW d = new VHVW(v.width, v.height);

		background = new ImageAnimatable(v, "assets/sky.png", d.vw(50f), d.vh(50f), Map.of("prop", (float) d.vw(300f), "parallax", 0.001f));
		starship = new ImageAnimatable(v, "assets/starship_bellyflop3.png", d.vw(50f), d.vh(50f), Map.of("prop", (float) d.vh(10f), "parallax", 0.003f));

		background.setDuration(300);
		starship.setDuration(300);

		background.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", (int) -d.vh(100f), "startTime", 0, "duration", 300), 0);
		starship.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", (int) d.vh(60f), "startTime", 0, "duration", 300), 0);

		this.addToScene(background);
		this.addToScene(starship);
	}
}
