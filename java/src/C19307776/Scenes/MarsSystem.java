package C19307776.Scenes;
import C19307776.Scene;
import C19307776.Visuals;
import C19307776.Animatable;
import C19307776.Properties;

public class MarsSystem extends Scene{
	Animatable mars;
	float marsSize;

	public MarsSystem(Visuals v) {
		super();
		this.v = v;
		this.sceneLength = 700;

		marsSize = v.height*0.424f;
		mars = new Animatable(v, "assets/mars.png", -marsSize, 400, marsSize, marsSize, 0);

		v.background(0);
		mars.setDuration(700);
		mars.animateProperty(Properties.XPOS.getValue(), 1500, 0, 600);
		mars.animateProperty(Properties.YPOS.getValue(), 0, 0, 600);
		this.addToScene(mars);
	}
}
