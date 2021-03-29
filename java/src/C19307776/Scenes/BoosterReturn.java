package C19307776.Scenes;
import C19307776.Scene;
import C19307776.ImageAnimatable;
import C19307776.Visuals;
import java.util.Map;
import C19307776.Properties;
import java.util.Random;
import C19307776.utils.*;

public class BoosterReturn extends Scene {
	ImageAnimatable superheavy;
	ImageAnimatable background;

	public BoosterReturn(Visuals v) {
		this.sceneLength = 300;

		VHVW d = new VHVW(v.width, v.height);

		background = new ImageAnimatable(v, "assets/sky.png", d.vw(50f), d.vh(50f), Map.of("prop", (float) d.vw(90f), "parallax", 0.001f));
		superheavy = new ImageAnimatable(v, "assets/superheavy_booster.png", d.vw(50f), d.vh(50f), Map.of("prop", (float) d.vh(80f), "r", 5f, "parallax", 0.003f));
		//flame = new ImageAnimatable(v, "assets/flames.png", d.vw(50f), d.vh(90f), Map.of("prop", (float) d.vw(5f), "parallax", 0.001f));

		background.setDuration(300);
		superheavy.setDuration(300);

		this.addToScene(background);
		
		Random r = new Random();
		for(int i = 0; i < 5; i++) {
			float xPos = r.nextFloat()*100;
			float yPos = 100 + r.nextFloat()*100;
			ImageAnimatable cloud = new ImageAnimatable(v, "assets/cloud1.png", d.vw(xPos), d.vh(yPos), Map.of("prop", (float) d.vw(15f), "parallax", 0.001f));
			cloud.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", (int) d.vh(yPos-600), "startTime", 0, "duration", 300), 0);
			cloud.setDuration(300);
			this.addToScene(cloud);
		}

		this.addToScene(superheavy);

		for(int i = 0; i < 5; i++) {
			float xPos = r.nextFloat()*100;
			float yPos = 200 + r.nextFloat()*100;
			ImageAnimatable cloud = new ImageAnimatable(v, "assets/cloud2.png", d.vw(xPos), d.vh(yPos), Map.of("prop", (float) d.vw(20f), "parallax", 0.005f));
			cloud.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", (int) d.vh(yPos-600), "startTime", 0, "duration", 300), 0);
			cloud.setDuration(300);
			this.addToScene(cloud);
		}
	}
}
