// package C21468162;

// import processing.core.PApplet;

// public class WarpedSpace extends PApplet {

//   int numParticles = 100; // Number of particles
//   float maxSpeed = 2.5f; // Maximum speed of particles
//   float maxRadius = 10; // Maximum radius of particles
//   Particle[] particles; // Array to store particles

//   public void settings() {
//       size(800, 600); // Set the size of the canvas
//   }

//   public void setup() {
//       particles = new Particle[numParticles]; // Initialize particle array

//       // Create particles with random positions, velocities, and radius
//       for (int i = 0; i < numParticles; i++) {
//           particles[i] = new Particle(random(width), random(height), 
//                                        random(-maxSpeed, maxSpeed), 
//                                        random(-maxSpeed, maxSpeed), 
//                                        random(1, maxRadius));
//       }

//       background(0); // Set the background color to black
//       smooth(); // Enable anti-aliasing for smoother rendering
//   }

//   public void draw() {
//       // Draw a transparent black rectangle to create a fading trail effect
//       fill(0, 15);
//       rect(0, 0, width, height);

//       // Update and draw particles
//       for (int i = 0; i < numParticles; i++) {
//           particles[i].update();
//           particles[i].draw();
//       }
//   }

//   // Particle class
//   class Particle {
//       float x, y, vx, vy, r;

//       Particle(float x, float y, float vx, float vy, float r) {
//           this.x = x;
//           this.y = y;
//           this.vx = vx;
//           this.vy = vy;
//           this.r = r;
//       }

//       void update() {
//           // Update particle position
//           x += vx;
//           y += vy;

//           // Wrap particle around the edges of the canvas
//           if (x < -r) {
//               x = width + r;
//           } else if (x > width + r) {
//               x = -r;
//           }
//           if (y < -r) {
//               y = height + r;
//           } else if (y > height + r) {
//               y = -r;
//           }
//       }

//       void draw() {
//           // Draw particle as a circle with random color and transparency
//           noStroke();
//           fill(random(255), random(255), random(255), random(255));
//           ellipse(x, y, r, r);
//       }
//   }

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
		speed = 4;
		stars = new Star[1500];
		for (int i = 0; i < 1500; i++) stars[i] = new Star();
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
			strokeWeight(z);
			beginShape();
			vertex(x, y);
			vertex(px, py);
			endShape();
		}
	}
}
