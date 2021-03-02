package C19307776;
import processing.core.*;
import java.util.HashMap;
import java.util.ArrayList;

public class Animatable {
	Visuals v;
	PImage img;
	float w;
	float h;
	float x;
	float y;
	String src;

	int currentFrame = 0;
	int maxFrames;
	int startPoint = 0;

	float lastWidth;
	float lastHeight;
	float lastX;
	float lastY;

	HashMap<Integer, ArrayList<ArrayList<Float>>> animations = new HashMap<Integer, ArrayList<ArrayList<Float>>>();

	public Animatable(Visuals v, String src, float x, float y, float w, float h) {
		this.v = v;
		this.lastWidth = this.w = w;
		this.lastHeight = this.h = h;
		this.lastX = this.x = x;
		this.lastY = this.y = y;
		this.src = src;
		img = v.loadImage(src);
	}

	public void setDuration(int duration) {
		this.maxFrames = duration;
	}
	public void setDuration(int start, int duration) {
		this.startPoint = start;
		this.maxFrames = duration;
	}

	public void animateProperty(int property, float to, int startTime, int duration) {
		float val = 0;
		if(property == Properties.WIDTH.getValue()) {
			val = lastWidth;
		}else if(property == Properties.HEIGHT.getValue()) {
			val = lastHeight;
		}else if(property == Properties.XPOS.getValue()) {
			val = lastX;
		}else if(property == Properties.YPOS.getValue()) {
			val = lastY;
		}
		ArrayList<Float> arr = new ArrayList<Float>();
		arr.add((float) duration);
		arr.add((float) property);
		arr.add((to-val)/duration);
		if(!animations.containsKey(startTime)) {
			//Start time, duration, property (wdith/height etc), animation starting width, animation ending width
			//float[][] props = {{duration, property, val, to}};
			animations.put(startTime, new ArrayList());
		}
		animations.get(startTime).add(arr);
	}

	public int getStartPoint() {
		return this.startPoint;
	}

	public void animate() {
		if(currentFrame < maxFrames) {
			//HashMap<Integer, ArrayList<ArrayList<Float>>> rem = new HashMap<Integer, ArrayList<ArrayList<Float>>>();
			for(HashMap.Entry<Integer, ArrayList<ArrayList<Float>>> animation: animations.entrySet()) {
				if(animation.getKey() <= currentFrame) {
					ArrayList<ArrayList<Float>> p = animation.getValue();
					for(int i = 0; i < p.size(); i++) {
						ArrayList<Float> props = p.get(i);
						if(currentFrame < animation.getKey()+props.get(0)) {
							if(props.get(1) == Properties.WIDTH.getValue()) {
								this.w+=props.get(2);
							}else if(props.get(1) == Properties.HEIGHT.getValue()) {
								this.h+=props.get(2);
							}else if(props.get(1) == Properties.XPOS.getValue()) {
								this.x+=props.get(2);
							}else if(props.get(1) == Properties.YPOS.getValue()) {
								this.y+=props.get(2);
							}
						}else {
							p.get(i).remove(p.get(i));
						}
					}
				}
			}
			
			v.image(this.img, this.x+(v.mouseX/100), this.y+(v.mouseY/100), this.w, this.h);
			currentFrame++;
		}
	}
}
