package C19307776;
import processing.core.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class Animatable {
	Visuals v; //Processing
	PImage img; //Sprite
	float w; //width
	float h; //height
	float x; //x position
	float y; //y position
	float rotation = 0; //sprite's rotation
	String src; // sprite image src

	boolean scale = false;

	//current frame of sprite animation
	int currentFrame = 0;
	//maximum amount of frames sprite will be on screen
	int maxFrames;
	//The point that the sprite displays on the screen
	int startPoint = 0;

	//The dimensions of the sprite at beginning of animations
	float lastWidth;
	float lastHeight;
	float lastX;
	float speedX = 1f;
	float lastY;
	float speedY = 1f;
	float lastRotation;

	float parallax = 0;

	//List of animation frames
	HashMap<Integer, ArrayList<ArrayList<Float>>> animations = new HashMap<Integer, ArrayList<ArrayList<Float>>>();

	public Animatable(Visuals v, String src, float x, float y, Map<String, Float> props) {
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
			scale = true;
			img.resize(0, ((int) (float) props.get("prop")));
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

	//Set amount of frames that sprite will be visible for
	public void setDuration(int duration) {
		this.maxFrames = duration;
	}
	public void setDuration(int start, int duration) {
		//Set start point of the graph
		this.startPoint = start;
		this.maxFrames = duration;
	}

	//add an animation to be run on the sprite
	public void animateProperty(Map<String, Integer> props, float acceleration) {
		//int property, float to, int startTime, int duration
		float val = 0;
		//determine which property is being run
		if(props.get("property") == Properties.WIDTH.getValue()) {
			val = lastWidth;
		}else if(props.get("property") == Properties.HEIGHT.getValue()) {
			val = lastHeight;
		}else if(props.get("property") == Properties.XPOS.getValue()) {
			val = lastX;
			if(acceleration > 0 || acceleration < 0) {
				speedX = acceleration;
			}
		}else if(props.get("property") == Properties.YPOS.getValue()) {
			val = lastY;
			if(acceleration > 0 || acceleration < 0) {
				speedY = acceleration;
			}
		}else if(props.get("property") == Properties.ROTATION.getValue()) {
			val = lastRotation;
		}
		//The list of animatation properties
		ArrayList<Float> arr = new ArrayList<Float>();
		arr.add((float) props.get("duration"));
		arr.add((float) props.get("property"));
		arr.add((props.get("to")-val)/props.get("duration"));
		if(acceleration > 0 || acceleration < 0) {
			System.out.println(acceleration);
			arr.add((float) acceleration);
		}
		//Check there is already animation starting at start time
		//Creates arraylist of animations if not
		if(!animations.containsKey(props.get("startTime"))) {
			animations.put(props.get("startTime"), new ArrayList());
		}
		//Adds animation to the list of animations
		animations.get(props.get("startTime")).add(arr);
	}

	public int getStartPoint() {
		return this.startPoint;
	}

	public void animate() {
		//Checks if current frame < frames in full animation
		if(currentFrame < maxFrames) {
			//Loops through animations and sees if there is an animation starting at this frame
			for(HashMap.Entry<Integer, ArrayList<ArrayList<Float>>> animation: animations.entrySet()) {
				//animation.getKey() == starting time for animation
				if(animation.getKey() <= currentFrame) {
					//List of animations starting at this time
					ArrayList<ArrayList<Float>> p = animation.getValue();
					//Loops through animations
					for(int i = 0; i < p.size(); i++) {
						//References animation properties
						ArrayList<Float> props = p.get(i);
						//Checks if sprite dimensions should change
						if(currentFrame < animation.getKey()+props.get(0)) {
							//Changes sprite dimensions
							if(props.get(1) == Properties.WIDTH.getValue()) {
								this.w+=props.get(2);
							}else if(props.get(1) == Properties.HEIGHT.getValue()) {
								this.h+=props.get(2);
							}else if(props.get(1) == Properties.XPOS.getValue()) {
								//this.x+=props.get(2);
								if(props.size() == 4) {
									speedX+=props.get(3);
									this.x+=speedX;
								}else {
									this.x+=props.get(2);
								}
							}else if(props.get(1) == Properties.YPOS.getValue()) {
								if(props.size() == 4) {
									speedY+=props.get(3);
									this.y+=speedY;
								}else {
									this.y+=props.get(2);
								}
							}else if(props.get(1) == Properties.ROTATION.getValue()) {
								this.rotation+=props.get(2);
								//System.out.println(this.rotation);
							}
						}else {
							//Removes animation if completed
							p.get(i).remove(p.get(i));
						}
					}
				}
			}
			v.pushMatrix();
			v.translate(this.x+(v.mouseX*parallax), this.y+(v.mouseY*parallax));
			//v.translate(0, 0);
			//Checks if rotation = 0
			if(this.rotation != 0) {
				//Rotates the sprite
				v.rotate(v.radians(this.rotation));
			}
			//Draws the shape at new dimensions / coordinates
			if(!scale) {
				v.image(this.img, 0, 0, this.w, this.h);
			}else {
				v.image(this.img, 0, 0);
			}
			v.popMatrix();
			//Increments frame counter
			currentFrame++;
		}
	}
}
