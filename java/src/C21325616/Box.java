package C21325616;

import processing.core.PVector;

import java.util.List;

import example.MyVisual;
import ie.tudublin.Heartbeat;

import java.util.*; 

import processing.core.PApplet;

public class Box {

    public PVector position;
    public PVector dimensions;

    public void setDimension(PVector dimension)
    {
        this.dimensions = dimension;
    }

    public void setPosition(PVector position)
    {
        this.position = position;
    }

    public void render(Heartbeat mv)
    {
        mv.pushMatrix();
        mv.translate(position.x, position.y, position.z); 
        mv.fill(220);
        mv.noStroke();
        mv.box(dimensions.x, dimensions.y, dimensions.z);
        mv.popMatrix();
    }
    
}
