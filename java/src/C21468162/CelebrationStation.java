package C21468162;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;
import java.util.ArrayList;

public class CelebrationStation extends PApplet {

    ArrayList<Firework> fireworks = new ArrayList<>();
    float renderBox = 800;
    float t = 0;
    float camSpeed = 0.005f;
    float camAngle = 0;
    float orbitRadius = 1300;
    PVector camPos;
    float prevX, prevY;

    public void settings() {
        size(800, 600, P3D);
    }

    public void setup() {
        smooth();
        camPos = new PVector(0, 0, orbitRadius);
    }

    public int getGradientColor(float t) {
        // Calculate red, green, and blue values as functions of time
        int r = (int)(127 * (sin(0.1f*t + 0) + 1));
        int g = (int)(127 * (sin(0.1f*t + 2) + 1));
        int b = (int)(127 * (sin(0.1f*t + 4) + 1));
      
        // Combine red, green, and blue values into an RGB color
        return color(r, g, b);
      }

    public void draw() {
        background(20);
        lights();

        // Create new firework randomly
        if (random(1) < 0.05) {
			fireworks.add(new Firework(this, new PVector(random(-renderBox/2, renderBox/2), random(-renderBox/2, renderBox/2), random(-renderBox/2, renderBox/2))));
        }

        // Update and show fireworks
        for (int i = fireworks.size() - 1; i >= 0; i--) {
            Firework fw = fireworks.get(i);
            fw.applyForce(new PVector(0, 0.05f, 0));
            fw.update();
            if (fw.isDone()) {
                fireworks.remove(i);
            } else {
                fw.show();
            }
        }

        // Move camera around the origin
        camPos.x = orbitRadius * cos(camAngle);
        camPos.z = orbitRadius * sin(camAngle);
        camera(camPos.x, camPos.y, camPos.z, 0, 0, 0, 0, 1, 0);
        camAngle += camSpeed;


        // Draw a rotating wireframe box
        noFill();
        stroke(getGradientColor(t * 0.05f));
        smooth();
        rotateY(t * 0.0005f);
        box(renderBox);

        t++;

        if (keyCode == LEFT) {
            // Change box to triangle
            beginShape();
            vertex(0, -renderBox/2, 0);
            vertex(-renderBox/2, renderBox/2, 0);
            vertex(renderBox/2, renderBox/2, 0);
            endShape(CLOSE);
        } else if (keyCode == RIGHT) {
            // Change box to circle
            ellipse(0, 0, renderBox, renderBox);
        }
    }

    public void mouseWheel(MouseEvent event) {
        orbitRadius += event.getCount() * 10;
    }

    public void keyPressed() {
        if (keyCode == DOWN) {
            camSpeed -= 0.001f;
        }
        if (keyCode == UP) {
            camSpeed += 0.001f;
        }
    }

}