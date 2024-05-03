package C22410766;

import ie.tudublin.Visual;

public class Lines {
    float x, y, endX, endY;
    Visual parent;
    float angle, length;
    int strokeColor;
    float strokeWeight;

    public Lines(Visual parent, float centerX, float centerY, float radius, float angle, float length, int strokeColor, float strokeWeight) {
        this.parent = parent;
        this.angle = angle;
        this.length = length;
        this.strokeColor = strokeColor;
        this.strokeWeight = strokeWeight;

        this.x = centerX + radius * parent.cos(angle);
        this.y = centerY + radius * parent.sin(angle);
        this.endX = x + length * parent.cos(angle);
        this.endY = y + length * parent.sin(angle);
    }

    public void display() {
        parent.stroke(strokeColor);
        parent.strokeWeight(strokeWeight);
        parent.line(x, y, endX, endY);
    }
}

