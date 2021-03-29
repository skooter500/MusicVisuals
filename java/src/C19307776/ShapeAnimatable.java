package C19307776;
import java.util.Map;

public class ShapeAnimatable extends Animatable {

	boolean scale = false;

	int[] colour = new int[3];

	public ShapeAnimatable(Visuals v, int[] colour, float x, float y, Map<String, Float> props) {
		this.v = v;

		this.colour = colour;

		//Checks if the width and height property are present
		if(props.containsKey("w") && props.containsKey("h")) {
			//Sets the width and height variables
			this.lastWidth = this.w = props.get("w");
			this.lastHeight = this.h = props.get("h");
		}
		
		this.lastX = this.x = x;
		this.lastY = this.y = y;
		if(props.containsKey("r")) {
			this.lastRotation = this.rotation = props.get("r");
		}
		if(props.containsKey("parallax")) {
			this.parallax = props.get("parallax");
		}
		v.rectMode(v.CENTER);
	}

	public int getStartPoint() {
		return this.startPoint;
	}

	public void animate() {
		if(this.animateFrame()) {
			v.fill(colour[0], colour[1], colour[2]);
			v.pushMatrix();
			v.translate(this.x+(v.mouseX*parallax), this.y+(v.mouseY*parallax));
			//v.translate(0, 0);
			//Checks if rotation = 0
			if(this.rotation != 0) {
				//Rotates the sprite
				v.rotate(v.radians(this.rotation));
			}
			//Draws the shape at new dimensions / coordinates
			v.rect(0, 0, this.w, this.h);
			v.popMatrix();
			//Increments frame counter
		}
	}
}