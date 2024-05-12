package c22485282;

import java.util.ArrayList;
import java.lang.Math; // import the Math class

public class Snow {
    MyVisual mv;
    ArrayList<Snowflake> snowflakes;
    float angle =  (float) Math.toRadians(20); // use Math.toRadians() instead

    public Snow(MyVisual mv) {
        this.mv = mv;
        snowflakes = new ArrayList<>();
    }

    public void render() {
        // mv.background(0, 0, 139); // Set background color to dark blue

        // Simulate falling snow by adding new snowflakes
        addSnowflakes(8);

        // Update and display all snowflakes
        for (int i = snowflakes.size() - 1; i >= 0; i--) {
            Snowflake flake = snowflakes.get(i);
            flake.update();
            flake.display();
            if (flake.isOffscreen()) {
                snowflakes.remove(i); // Remove snowflake if it's offscreen
            }
        }
    }

    
    void addSnowflakes(int num) {
        // Add new snowflakes at the top of the screen
        for (int i = 0; i < num; i++) {
            float x = mv.random(mv.width); // Random x position within canvas width
            float y = mv.random(-50, -10); // Random y position above the screen
            float diameter = mv.random(5, 15); // Random diameter of snowflake

            snowflakes.add(new Snowflake(x, y, diameter)); // Add new snowflake to the list
        }
    }

    
    class Snowflake {
        float x, y; 
        float diameter; // Snowflake size
        float speed; // Falling speed

        Snowflake(float x, float y, float diameter) {
            this.x = x;
            this.y = y;
            this.diameter = diameter;
            this.speed = mv.random(1, 3); // Random falling speed
        }

        
        void update() {
            y += speed; // Snowflake move down
            x += mv.random(-1, 1); // Horizontal drift  simulating wind effect
        }

        void display() {
            mv.noStroke(); 
            mv.fill(255);
            mv.ellipse(x, y, diameter, diameter); 
        }

        boolean isOffscreen() {
            // Check if snowflake is completely off the screen
            return y > mv.height + diameter;
        }

    }




} // End Snow()