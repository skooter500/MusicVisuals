package C21725659;

import processing.core.PApplet;
import processing.core.PVector;

public class textSprites {
    private PVector position;
    private PVector velocity;
    private String text;
    private int textSize;
    private int color;

    public textSprites(PVector position, PVector velocity, String text, int textSize, int color) {
        this.position = position;
        this.velocity = velocity;
        this.text = text;
        this.textSize = textSize;
        this.color = color;
    }

    public void update() {
        position.add(velocity);
    }

    public void display(PApplet p) {
        p.pushStyle();
        p.textAlign(PApplet.CENTER, PApplet.CENTER);
        p.textSize(textSize);
        p.fill(color);
        p.text(text, position.x, position.y);
        p.popStyle();
    }
}
