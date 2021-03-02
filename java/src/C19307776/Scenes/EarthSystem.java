package C19307776.Scenes;
import C19307776.Animatable;
import C19307776.Scene;
import C19307776.Visuals;
import C19307776.Properties;

public class EarthSystem extends Scene{
	float earthSize;
	Animatable earth;

	public EarthSystem(Visuals v) {
		super();
		this.v = v;
		this.sceneLength = 700;
		earthSize = v.height*0.80f;

		earth = new Animatable(v, "assets/earth.png", -earthSize, 400, earthSize, earthSize);

		v.background(0);
		earth.setDuration(700);
		earth.animateProperty(Properties.XPOS.getValue(), 1500, 0, 600);
		earth.animateProperty(Properties.YPOS.getValue(), 0, 0, 600);
		this.addToScene(earth);
	}
}
