package C19307776;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class Animatable {
	protected Visuals v; //Processing
	protected float w; //width
	protected float h; //height
	protected float x; //x position
	protected float y; //y position
	protected float rotation = 0; //sprite's rotation

	protected boolean scale = false;

	//current frame of sprite animation
	protected int currentFrame = 0;
	//maximum amount of frames sprite will be on screen
	protected int maxFrames;
	//The point that the sprite displays on the screen
	protected int startPoint = 0;

	//The dimensions of the sprite at beginning of animations
	protected float lastWidth;
	protected float lastHeight;
	protected float lastX;
	protected float speedX = 1f;
	protected float lastY;
	protected float speedY = 1f;
	protected float lastRotation;
	protected int lastScale;
	protected float imageRatio = 1;

	float parallax = 0;

	//List of animation frames
	HashMap<Integer, ArrayList<ArrayList<Float>>> animations = new HashMap<Integer, ArrayList<ArrayList<Float>>>();

	public Animatable() {

	}

	//add an animation to be run on the sprite
	public void animateProperty(Map<String, Integer> props, float acceleration) {
		//int property, float to, int startTime, int duration
		float val = 0;
		//determine which property is being run
		if(props.get("property") == Properties.WIDTH.getValue()) {
			val = lastWidth;
			lastWidth = props.get("to");
		}else if(props.get("property") == Properties.HEIGHT.getValue()) {
			val = lastHeight;
			lastHeight = props.get("to");
		}else if(props.get("property") == Properties.XPOS.getValue()) {
			val = lastX;
			if(acceleration > 0 || acceleration < 0) {
				speedX = acceleration;
			}
			lastX = props.get("to");
		}else if(props.get("property") == Properties.YPOS.getValue()) {
			val = lastY;
			System.out.println(val);
			if(acceleration > 0 || acceleration < 0) {
				speedY = acceleration;
			}
			lastY = props.get("to");
		}else if(props.get("property") == Properties.ROTATION.getValue()) {
			val = lastRotation;
			lastRotation = props.get("to");
		}else if(props.get("property") == Properties.SCALE.getValue()) {
			val = lastScale;
			lastScale = props.get("to");
		}
		//The list of animatation properties
		ArrayList<Float> arr = new ArrayList<Float>();
		arr.add((float) props.get("duration"));
		arr.add((float) props.get("property"));
		if(props.get("property") == 3) {
			System.out.println(Properties.YPOS.getValue()+" "+props.get("property")+" "+props.get("to")+" "+val);
		}
		arr.add((props.get("to")-val)/props.get("duration"));
		if(acceleration > 0 || acceleration < 0) {
			System.out.println(acceleration);
			arr.add((float) acceleration);
		}
		//Check there is already animation starting at start time
		//Creates arraylist of animations if not
		if(!animations.containsKey(props.get("startTime"))) {
			animations.put(props.get("startTime"), new ArrayList<ArrayList<Float>>());
		}
		//Adds animation to the list of animations
		animations.get(props.get("startTime")).add(arr);
	}

	public boolean animateFrame() {
		//Checks if current frame < frames in full animation
		if(this.currentFrame < this.maxFrames) {
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
							}else if(props.get(1) == Properties.SCALE.getValue()) {
								this.w = this.h+=props.get(2);
								//System.out.println(this.rotation);
							}
						}else {
							//Removes animation if completed
							p.get(i).remove(p.get(i));
						}
					}
				}
			}
			currentFrame++;
			return true;
		}else {
			return false;
		}
	}

	public void animate() {}

	//Set amount of frames that sprite will be visible for
	public void setDuration(int duration) {
		this.maxFrames = duration;
	}
	public void setDuration(int start, int duration) {
		//Set start point of the graph
		this.startPoint = start;
		this.maxFrames = duration;
	}

	public int getStartPoint() {
		return this.startPoint;
	}
}