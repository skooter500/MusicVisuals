package C19307776;
import processing.core.*;
import java.util.Map;

public class ImageAnimatable extends Animatable {
	PImage img; //Sprite
	String src; // sprite image src

	boolean scale = false;

	public ImageAnimatable(Visuals v, String src, float x, float y, Map<String, Float> props) {
		this.v = v;
		this.src = src;
		img = v.loadImage(src);

		//Checks if the width and height property are present
		if(props.containsKey("w") && props.containsKey("h")) {
			//Sets the width and height variables
			this.lastWidth = this.w = props.get("w");
			this.lastHeight = this.h = props.get("h");
		}else if(props.containsKey("prop")) {
			//If width / height not present, scale the image proportionally
			//scale = true;
			//this.lastScale = scle = (int) (float) props.get("prop");
			//imageRatio = img.height/img.width;
			//img.resize(0, scle);
			this.imageRatio = (float) img.width/img.height;
			this.w = this.h = props.get("prop");
		}

		this.lastX = this.x = x;
		this.lastY = this.y = y;
		if(props.containsKey("r")) {
			this.lastRotation = this.rotation = props.get("r");
		}
		if(props.containsKey("parallax")) {
			this.parallax = props.get("parallax");
		}
		v.imageMode(v.CENTER);
	}

	public int getStartPoint() {
		return this.startPoint;
	}

	public void animate() {
		if(this.animateFrame()) {
			v.pushMatrix();
			v.translate(this.x+(v.mouseX*parallax), this.y+(v.mouseY*parallax));
			//v.translate(0, 0);
			//Checks if rotation = 0
			if(this.rotation != 0) {
				//Rotates the sprite
				v.rotate(v.radians(this.rotation));
			}
			//Draws the shape at new dimensions / coordinates
			v.image(this.img, 0, 0, this.w*imageRatio, this.h);
			v.popMatrix();
			//Increments frame counter
		}
	}
}