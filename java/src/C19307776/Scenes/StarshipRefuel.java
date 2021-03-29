package C19307776.Scenes;
import C19307776.Scene;
import C19307776.ShapeAnimatable;
import C19307776.TextAnimatable;
import C19307776.Visuals;
import C19307776.ImageAnimatable;
import java.util.Map;
import C19307776.Properties;
import C19307776.utils.*;

public class StarshipRefuel extends Scene {
	ImageAnimatable stars;
	ImageAnimatable starship1;
	ImageAnimatable starship2;
	ShapeAnimatable fuelIndicator1;
	ShapeAnimatable fuelIndicator1_1;
	ShapeAnimatable fuelIndicator2;
	ShapeAnimatable fuelIndicator2_1;
	TextAnimatable fuel;

	public StarshipRefuel(Visuals v) {

		this.sceneLength = 1620;

		VHVW d = new VHVW(v.width, v.height);

		stars = new ImageAnimatable(v, "assets/stars.png", d.vw(50f), d.vh(50f), Map.of("prop", (float) d.vw(90f)));
		starship1 = new ImageAnimatable(v, "assets/starship_upperstage.png", d.vw(25f), d.vh(50f), Map.of("prop", (float) d.vw(32f), "r", -88f, "parallax", 0.003f));
		starship2 = new ImageAnimatable(v, "assets/starship_upperstage.png", d.vw(62f), d.vh(50f), Map.of("prop", (float) d.vw(32f), "r", 90f, "parallax", 0.003f));
		fuelIndicator1 = new ShapeAnimatable(v, new int[]{0, 160, 0}, d.vw(35f), d.vh(40f), Map.of("w", (float) d.vw(20f), "h", (float) d.vh(4f), "parallax", 0.003f));
		fuelIndicator1_1 = new ShapeAnimatable(v, new int[]{255, 0, 0}, d.vw(35f), d.vh(40f), Map.of("w", (float) d.vw(20f), "h", (float) d.vh(4f), "parallax", 0.003f));
		fuelIndicator2 = new ShapeAnimatable(v, new int[]{0, 160, 0}, d.vw(50f), d.vh(40f), Map.of("w", (float) d.vw(5f), "h", (float) d.vh(4f), "parallax", 0.003f));
		fuelIndicator2_1 = new ShapeAnimatable(v, new int[]{255, 0, 0}, d.vw(57.5f), d.vh(40f), Map.of("w", (float) d.vw(20f), "h", (float) d.vh(4f), "parallax", 0.003f));
		fuel = new TextAnimatable(v, "Transferring Propellant", d.vw(2f), new int[]{255, 255, 255}, d.vw(45f), d.vh(41f), 0, 0.003f);

		stars.setDuration(1620);
		starship1.setDuration(1620);
		starship2.setDuration(1620);
		fuelIndicator1.setDuration(540, 600);
		fuelIndicator1_1.setDuration(540, 600);
		fuelIndicator2.setDuration(540, 600);
		fuelIndicator2_1.setDuration(540, 600);
		fuel.setDuration(540, 600);

		starship1.animateProperty(Map.of("property", Properties.XPOS.getValue(), "to", (int) d.vw(31.75f), "startTime", 0, "duration", 510), 0);

		fuelIndicator1.animateProperty(Map.of("property", Properties.WIDTH.getValue(), "to", (int) d.vw(5f), "startTime", 0, "duration", 510), 0);
		fuelIndicator1.animateProperty(Map.of("property", Properties.XPOS.getValue(), "to", (int) d.vw(27.5f), "startTime", 0, "duration", 510), 0);
		fuelIndicator2.animateProperty(Map.of("property", Properties.WIDTH.getValue(), "to", (int) d.vw(20f), "startTime", 0, "duration", 510), 0);
		fuelIndicator2.animateProperty(Map.of("property", Properties.XPOS.getValue(), "to", (int) d.vw(57.5f), "startTime", 0, "duration", 510), 0);

		starship1.animateProperty(Map.of("property", Properties.ROTATION.getValue(), "to", (int) -100f, "startTime", 1140, "duration", 510), 0);
		starship1.animateProperty(Map.of("property", Properties.XPOS.getValue(), "to", (int) d.vw(15f), "startTime", 1140, "duration", 510), 0);
		starship1.animateProperty(Map.of("property", Properties.YPOS.getValue(), "to", (int) d.vh(100f), "startTime", 1140, "duration", 510), 0);

		this.addToScene(stars);
		this.addToScene(starship1);
		this.addToScene(starship2);
		this.addToScene(fuelIndicator1_1);
		this.addToScene(fuelIndicator1);
		this.addToScene(fuelIndicator2_1);
		this.addToScene(fuelIndicator2);
		this.addToScene(fuel);
	}
}
