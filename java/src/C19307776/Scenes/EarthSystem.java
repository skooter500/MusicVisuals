package C19307776.Scenes;
import C19307776.Scene;
import C19307776.Visuals;
import processing.core.*;

public class EarthSystem extends Scene{
    PImage earth;
    float earthSize;
    float earthX;
    float earthY;

    public EarthSystem(Visuals v) {
        super();
        this.v = v;

        v.background(0);
        earthSize = v.height*0.80f;
        earthX = (v.width/2)-(earthSize/2);
        earthY = (v.height/2)-(earthSize/2);
        earth = v.loadImage("assets/earth.png");
    }

    public void animateScene() {
        v.background(0);
        v.image(this.earth, earthX+(v.mouseX/100), earthY+(v.mouseY/100), earthSize, earthSize);
    }
}
