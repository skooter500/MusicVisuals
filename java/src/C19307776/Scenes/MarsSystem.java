package C19307776.Scenes;
import C19307776.Scene;
import C19307776.Visuals;
import C19307776.Animatable;
import C19307776.Properties;
import java.util.Map;

public class MarsSystem extends Scene{
	Animatable mars;
	float marsSize;

	public MarsSystem(Visuals v) {
		this.sceneLength = 700;

		marsSize = v.height*0.424f;
		mars = new Animatable(v, "assets/mars.png", -marsSize, 400, Map.of("prop", marsSize));

		v.background(0);
		mars.setDuration(700);
		mars.animateProperty(Map.of("property", Properties.XPOS.getValue(), "to", 1500, "startTime", 0, "duration", 600), 0);
		mars.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", 0, "startTime", 0, "duration", 600), 0);

		
		this.addToScene(mars);
	}
}
