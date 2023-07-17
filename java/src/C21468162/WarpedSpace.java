package C21468162;

import processing.core.PApplet;
import processing.event.MouseEvent;

public class WarpedSpace extends PApplet {
	
	double speed;
	Star[] stars;
	
	public void settings() {
		size(800,600);  //screen width and height
	}
	
	public void setup() {
		speed = 10;	//increase speed to make the particles appear longer
		stars = new Star[2500];
		for (int i = 0; i < 2500; i++) stars[i] = new Star();
		translate(width/2, height/2);
	}
	
	public void draw() {
		background(0, 0, 0, 10);
		translate(width / 2, height / 2);
		for (Star s : stars) {
			s.update();
			s.show();
		}
	}
	
	//on mouse scroll changes speed and color
	public void mouseWheel(MouseEvent event) {
		stroke(random(255), random(255), random(255));
		if (event.getCount() < 0) speed *= 1.1;
		else speed *= 0.9;
		if (speed < 0.01) speed = 0.01;
		else if (speed > 0.1) speed = 0.1;
	}
	
	public class Star {
		float x, y, px, py, z;
		
		public Star() {
			//initializing
			x = random(width)-width/2;  //random x
			y = random(height)-height/2; //random y
			z = random(4); //random z
		}
		
		public void update() {
			//stores previous x, y and z and generates new coordinates
			px = x;
			py = y;
			z += speed;
			x += x*(speed*0.2)*z;
			y += y*(speed*0.2)*z;
			if (x > width/2+50 || x < -width/2-50 ||
			    y > height/2+50 || y < -height/2-50) {
				x = random(width)-width/2;
				y = random(height)-height/2;
				px = x;
				py = y;
				z = 0;
			}
		}
		
		//draws line from x,y to px,py
		public void show() {
			strokeWeight((float) (z * 0.8));
			line(x, y, x + (x - px) * z, y + (y - py) * z);
		}
	}
}
