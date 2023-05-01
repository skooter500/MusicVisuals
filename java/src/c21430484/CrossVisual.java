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
    float scaler;
    float abScale;

    long currentTime;

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
        float scaler = 0.8f;  

        abScale = (mv.getSmoothedBands()[0] * 0.8f); 
        abScale = PApplet.map(abScale, 0f, 40f, 0.8f, 1.1f);
        scaler = PApplet.lerp(scaler, abScale, 0.85f);

        if(objScale > scaler)
        {   
            while(objScale > scaler)
            {
                objScale = objScale * 0.999f;
                cross.scale(0.999f);
                innerCross.scale(0.999f);
            }
        }
        else 
        {
            while(objScale < scaler)
            {
                objScale = objScale * 1.001f;
                cross.scale(1.001f);
                innerCross.scale(1.001f);
            }
        }
    }


    public void render(int phase)
    {
        cross.setFill(mv.color(208, 152, 3));
        mv.camera(0f, 0f, mv.height * .86602f, 0f, 0f, 0f, 0f, 1f, 0f);
        mv.lights();
        mv.shape(cross);
        
        innerCross.setFill(mv.color(0));
        mv.shape(innerCross);

        currentTime = System.currentTimeMillis();
        // cross.rotateX(0.005f);

        if(phase == 1)
            renderPhase1();
        else if(phase == 2)
            renderPhase2(); 
    }

    public void renderPhase1()
    {
        if(mv.startTime != -1 && currentTime - mv.startTime > 5000)
        {
            cross.rotateY(0.005f);
            cross.rotateZ(0.005f);    

            innerCross.rotateY(0.005f);
            innerCross.rotateZ(0.005f);    
        }

        if(mv.startTime != -1 && currentTime - mv.startTime > 38700)
        {
            changeScale();

            cross.rotateX(-0.0005f);   
            innerCross.rotateX(-0.0005f);
        }    

        if(currentTime - mv.startTime > 125000 && currentTime - mv.startTime < 137500)
        {
            if(currentTime - mv.startTime < 131250)
            {
                cross.rotateY(0.02f);
                cross.rotateZ(0.02f);    
    
                innerCross.rotateY(0.02f);
                innerCross.rotateZ(0.02f);  
    
                cross.rotateX(-0.02f);   
                innerCross.rotateX(-0.02f);
            }
            else 
            {
                cross.rotateY(-0.02f);
                cross.rotateZ(-0.02f);    
    
                innerCross.rotateY(-0.02f);
                innerCross.rotateZ(-0.02f);  
    
                cross.rotateX(0.04f);   
                innerCross.rotateX(0.04f);
            }
        }
    }

    public void renderPhase2()
    {
        cross.rotateZ(0.01f);    
        innerCross.rotateZ(0.01f);  
    }
}
