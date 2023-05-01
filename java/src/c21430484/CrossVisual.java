package c21430484;

import processing.core.PShape;

public class CrossVisual 
{
    BensVisual mv; 
    PShape cross; 

    public CrossVisual(BensVisual mv)
    {
        this.mv = mv; 
        cross = mv.loadShape("cross.obj");
        // cross.translate(mv.width / 2, mv.height / 2);
        cross.rotateX(+260);
        cross.scale(0.8f);

    }

    public void render()
    {
        cross.setFill(mv.color(208, 152, 3));
        mv.camera(0f, 0f, mv.height * .86602f, 0f, 0f, 0f, 0f, 1f, 0f);
        mv.lights();
        mv.shape(cross);
        // cross.rotateX(0.005f);
        cross.rotateY(0.005f);
        cross.rotateZ(0.005f);
    }
}
