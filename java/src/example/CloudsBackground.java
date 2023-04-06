package example;

import processing.core.*;

import java.util.ArrayList;

import java.util.List;


public class CloudsBackground {

    private MyVisual mv;

    private float cx, cy;
    
    private List<PVector> points;

    private float speed;

    private float direction;


    public CloudsBackground(MyVisual mv) {

        this.mv = mv;

        this.cx = mv.width / 2f;

        this.cy = mv.height / 2f;

        this.points = new ArrayList<>();

        this.speed = 12;

        // random value between 0 and 2pi
        this.direction = mv.random(PConstants.TWO_PI);

    }


    public void render() {

        mv.colorMode(PApplet.RGB);

        // add new points to list with random position
        int numNewPoints = PApplet.floor(mv.randomGaussian() * 5) + 10;

        // adding variation to direction to make it make it move
        for (int i = 0; i < numNewPoints; i++) 
        {
            // making point generate randomly
            // increasing the divisor makes points more compact
            float x = (float)(cx + mv.randomGaussian() * mv.width / 20);

            float y = (float)(cy + mv.randomGaussian() * mv.height / 20);

            // adding points to list
            points.add(new PVector(x, y));

        }


        // display points in list
        for (PVector point : points)
        {
            mv.strokeWeight(10);

            mv.stroke(mv.color(255));

            mv.fill(mv.color(255));

            // drawing the point
            mv.point(point.x, point.y);

        }


        // update the direction and position of the points
        direction += mv.randomGaussian() * 0.1f;

        cx += PApplet.cos(direction) * speed;

        cy += PApplet.sin(direction) * speed;


        // screen wrap the cluster of points when going off screen
        if (cx < 0)
        {
            cx += mv.width;

        }
        if(cx > mv.width)
        {
            cx -= mv.width;

        }
        if (cy < 0)
        {
            cy += mv.height;

        }
        if(cy > mv.height)
        {
            cy -= mv.height;

        }


        // removes points from list if there are too many
        if (points.size() > 20)
        {
            points.subList(0, 8).clear();

        }
    }
}