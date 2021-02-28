package C19307776.Scenes;
import C19307776.Scene;
import C19307776.Visuals;
import processing.core.*;

public class MarsSystem extends Scene{
    PImage mars;
    float marsSize;
    float marsX;
    float marsY;

    public MarsSystem(Visuals v) {
        super();
        this.v = v;

        v.background(0);
        marsSize = v.height*0.424f;
        marsX = (v.width/2)-(marsSize/2);
        marsY = (v.height/2)-(marsSize/2);
        mars = v.loadImage("assets/mars.png");
    }

    public void animateScene() {
        v.background(0);
        v.image(this.mars, marsX+(v.mouseX/100), marsY+(v.mouseY/100), marsSize, marsSize);
    }
}
