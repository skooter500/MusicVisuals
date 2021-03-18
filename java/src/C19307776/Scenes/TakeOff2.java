package C19307776.Scenes;
import C19307776.Scene;
import C19307776.Animatable;
import C19307776.Visuals;
import java.util.Map;
import C19307776.utils.VHVW;
import C19307776.Properties;
import java.util.Random;

public class TakeOff2 extends Scene {
	Animatable starship;
	Animatable flame;
	Animatable background;

	public TakeOff2(Visuals v) {
		this.sceneLength = 300;

		VHVW d = new VHVW(v.width, v.height);

		background = new Animatable(v, "assets/sky.png", d.vw(50f), d.vh(50f), Map.of("prop", (float) d.vw(90f), "parallax", 0.001f));
		starship = new Animatable(v, "assets/full_starship.png", d.vw(50f), d.vh(50f), Map.of("prop", (float) d.vh(80f), "parallax", 0.003f));
		flame = new Animatable(v, "assets/flames.png", d.vw(50f), d.vh(90f), Map.of("prop", (float) d.vw(5f), "parallax", 0.001f));

		background.setDuration(300);
		starship.setDuration(300);
		flame.setDuration(300);

		this.addToScene(background);
		
		Random r = new Random();
		for(int i = 0; i < 5; i++) {
			float xPos = r.nextFloat()*100;
			float yPos = -50 + r.nextFloat()*100;
			Animatable cloud = new Animatable(v, "assets/cloud1.png", d.vw(xPos), d.vh(yPos), Map.of("prop", (float) d.vw(15f), "parallax", 0.001f));
			cloud.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", (int) d.vh(yPos+100), "startTime", 0, "duration", 300), 0);
			cloud.setDuration(300);
			this.addToScene(cloud);
		}

		this.addToScene(flame);
		this.addToScene(starship);

		for(int i = 0; i < 5; i++) {
			float xPos = r.nextFloat()*100;
			float yPos = r.nextFloat()*100;
			Animatable cloud = new Animatable(v, "assets/cloud2.png", d.vw(xPos), d.vh(yPos), Map.of("prop", (float) d.vw(20f), "parallax", 0.005f));
			cloud.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", (int) d.vh(yPos+100), "startTime", 0, "duration", 300), 0);
			cloud.setDuration(300);
			this.addToScene(cloud);
		}
	}
}
