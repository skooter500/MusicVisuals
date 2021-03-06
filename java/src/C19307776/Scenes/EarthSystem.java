package C19307776.Scenes;
import C19307776.Animatable;
import C19307776.Scene;
import C19307776.Visuals;
import C19307776.Properties;

public class EarthSystem extends Scene{
	float earthSize;
	Animatable earth;
	Animatable starship;

	public EarthSystem(Visuals v) {
		super();
		this.v = v;
		this.sceneLength = 700;
		earthSize = v.height*0.80f;

		earth = new Animatable(v, "assets/earth.png", v.width/2, v.height/2, earthSize, earthSize, 0);
		starship = new Animatable(v, "assets/starship_rising.png", -100, v.height/2, 0, 0, 90);

		v.background(0);
		earth.setDuration(700);
		starship.setDuration(700);
		//earth.animateProperty(Properties.XPOS.getValue(), 1500, 0, 600);
		//earth.animateProperty(Properties.YPOS.getValue(), 0, 0, 600);
		starship.animateProperty(Properties.XPOS.getValue(), 2000, 0, 700);
		starship.animateProperty(Properties.ROTATION.getValue(), 36000, 0, 120);
		this.addToScene(earth);
		this.addToScene(starship);
	}
}
