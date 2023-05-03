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

    public void settings() {
        size(800, 600, P3D);
    }

    public void setup() {
        smooth();
        camPos = new PVector(0, 0, orbitRadius);
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
        stroke(random(0, 255) % (t * 10), random(0, 255) % (t * 10), random(0, 255) % (t * 10));
        smooth();
        rotateY(t * 0.0005f);
        box(renderBox);

        t++;
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
