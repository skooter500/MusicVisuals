package c21430484;

import processing.core.PApplet;
import processing.core.PShape;
import java.util.*;

public class CrossVisual 
{
    BensVisual mv;

    PShape cross; 
    PShape innerCross; 

    float objScale; 

    public CrossVisual(BensVisual mv)
    {
        this.mv = mv; 
        innerCross = mv.loadShape("innerCross.obj");
        cross = mv.loadShape("cross.obj");
        // cross.translate(mv.width / 2, mv.height / 2);
        objScale = 0.8f; 

        cross.rotateX(+260);
        cross.scale(objScale);

        innerCross.rotateX(+260);
        innerCross.scale(objScale);

    }

    
    public void changeScale()
    {
        float testScale2 = 0.8f;  
        float test = (mv.getSmoothedBands()[0] * 0.35f); 
        float testScale1 = PApplet.map(test, 0f, 40f, 0.8f, 1.2f);
        testScale2 = PApplet.lerp(testScale2, testScale1, 0.8f);


        if(objScale > testScale2)
        {   
            while(objScale > testScale2)
            {
                objScale = objScale * 0.999f;
                cross.scale(0.999f);
                innerCross.scale(0.999f);
            }
        }
        else 
        {
            while(objScale < testScale2)
            {
                objScale = objScale * 1.001f;
                cross.scale(1.001f);
                innerCross.scale(1.001f);
            }
        }
    }

    public void render()
    {
        cross.setFill(mv.color(208, 152, 3));
        mv.camera(0f, 0f, mv.height * .86602f, 0f, 0f, 0f, 0f, 1f, 0f);
        mv.lights();
        mv.shape(cross);
        
        innerCross.setFill(mv.color(0));
        mv.shape(innerCross);

        // cross.rotateX(0.005f);

        if(mv.startTime != -1 && System.currentTimeMillis() - mv.startTime > 5000)
        {
            cross.rotateY(0.005f);
            cross.rotateZ(0.005f);    

            innerCross.rotateY(0.005f);
            innerCross.rotateZ(0.005f);    
        }

        changeScale();
    }
}
