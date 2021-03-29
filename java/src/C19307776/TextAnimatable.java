package C19307776;
import java.util.Map;

public class TextAnimatable extends Animatable {

	boolean scale = false;

	int[] colour = new int[3];
	float fonstSize;
	String text;

	public TextAnimatable(Visuals v, String txt, float fs, int[] colour, float x, float y, float r, float parallax) {
		this.v = v;

		this.colour = colour;
		this.text = txt;
		this.fonstSize = fs;

		this.lastX = this.x = x;
		this.lastY = this.y = y;
		this.lastRotation = this.rotation = r;
		this.parallax = parallax;

		v.textAlign(v.CENTER);
	}

	public int getStartPoint() {
		return this.startPoint;
	}

	public void animate() {
		if(this.animateFrame()) {
			v.fill(colour[0], colour[1], colour[2]);
			v.textSize(this.fonstSize);
			v.pushMatrix();
			v.translate(this.x+(v.mouseX*parallax), this.y+(v.mouseY*parallax));

			//Checks if rotation = 0
			if(this.rotation != 0) {
				//Rotates the sprite
				v.rotate(v.radians(this.rotation));
			}
			//Draws the text at correct X/Y coordinates
			v.rect(0, 0, this.w, this.h);
			v.text(this.text, 0, 0);

			v.popMatrix();
			//Increments frame counter
		}
	}
}