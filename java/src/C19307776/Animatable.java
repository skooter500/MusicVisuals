package C19307776;
import processing.core.*;
import java.util.HashMap;
import java.util.ArrayList;

public class Animatable {
	Visuals v; //Processing
	PImage img; //Sprite
	float w; //width
	float h; //height
	float x; //x position
	float y; //y position
	float rotation = 0; //sprite's rotation
	String src; // sprite image src

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
	float lastY;
	float lastRotation;

	//List of animation frames
	HashMap<Integer, ArrayList<ArrayList<Float>>> animations = new HashMap<Integer, ArrayList<ArrayList<Float>>>();

	public Animatable(Visuals v, String src, float x, float y, float w, float h, float r) {
		this.v = v;
		this.src = src;
		img = v.loadImage(src);
		if(w == 0 && h == 0) {
			this.lastWidth = this.w = img.width/2;
			this.lastHeight = this.h = img.height/2;
		}else {
			this.lastWidth = this.w = w;
			this.lastHeight = this.h = h;
		}
		this.lastX = this.x = x;
		this.lastY = this.y = y;
		this.lastRotation = this.rotation = r;
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
	public void animateProperty(int property, float to, int startTime, int duration) {
		float val = 0;
		//determine which property is being run
		if(property == Properties.WIDTH.getValue()) {
			val = lastWidth;
		}else if(property == Properties.HEIGHT.getValue()) {
			val = lastHeight;
		}else if(property == Properties.XPOS.getValue()) {
			val = lastX;
		}else if(property == Properties.YPOS.getValue()) {
			val = lastY;
		}else if(property == Properties.ROTATION.getValue()) {
			val = lastRotation;
			System.out.println(to-val);
		}
		//The list of animatation properties
		ArrayList<Float> arr = new ArrayList<Float>();
		arr.add((float) duration);
		arr.add((float) property);
		arr.add((to-val)/duration);
		//Check there is already animation starting at start time
		//Creates arraylist of animations if not
		if(!animations.containsKey(startTime)) {
			animations.put(startTime, new ArrayList());
		}
		//Adds animation to the list of animations
		animations.get(startTime).add(arr);
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
								this.x+=props.get(2);
							}else if(props.get(1) == Properties.YPOS.getValue()) {
								this.y+=props.get(2);
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
			v.translate(this.x+(v.mouseX/100), this.y+(v.mouseY/100));
			//v.translate(0, 0);
			//Checks if rotation = 0
			if(this.rotation != 0) {
				//Rotates the sprite
				v.rotate(v.radians(this.rotation));
			}
			//Draws the shape at new dimensions / coordinates
			v.image(this.img, 0, 0, this.w, this.h);
			v.popMatrix();
			//Increments frame counter
			currentFrame++;
		}
	}
}
