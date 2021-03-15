package C19307776.Scenes;
import C19307776.Animatable;
import C19307776.Scene;
import C19307776.Visuals;
import C19307776.Properties;
import java.util.Map;

public class EarthSystem extends Scene{
	float earthSize;
	Animatable earth;
	Animatable starship;

	public EarthSystem(Visuals v) {
		super();
		this.v = v;
		this.sceneLength = 2800;
		earthSize = v.height*0.80f;
		earth = new Animatable(v, "assets/earth.png", v.width/2, v.height/2, Map.of("prop",v.height*0.75f, "parallax", 0.003f));
		starship = new Animatable(v, "assets/starship_bellyflop.png", 1000, v.height/2, Map.of("prop",earthSize, "parallax", 0.01f));

		v.background(0);
		earth.setDuration(2800);
		starship.setDuration(2800);
		//earth.animateProperty(Properties.XPOS.getValue(), 1500, 0, 600);
		//earth.animateProperty(Properties.YPOS.getValue(), 0, 0, 600);
		//starship.animateProperty(Map.of("property", Properties.XPOS.getValue(), "to", 2000, "startTime", 0, "duration", 2800), -0.001f);
		//starship.animateProperty(Properties.ROTATION.getValue(), 36000, 0, 120);
		this.addToScene(earth);
		this.addToScene(starship);
	}
}
