package C21325616;

import java.util.Random;

import example.MyVisual;
import ie.tudublin.Heartbeat;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import processing.event.KeyEvent;

public class MichaelsVisuals {

    Heartbeat mv;

    BoxPlane[] cube;
    PointCube pc;
    ParticleSystem ps;

    Random rand = new Random();

    int width;
    int height;

    float lastX;
    float lastY;

    int State = 1;
    boolean first = true;

    float rotation=0;

    public MichaelsVisuals(Heartbeat mv)
    {
        this.mv = mv; 

        width = mv.displayWidth;
        height = mv.displayHeight;

        initializeVisualOne();
        initializeVisualTwo();
    }

    private void initializeVisualOne()
    {

        cube = new BoxPlane[6];

        for(int i = 0; i < 6; i++)
        {
            cube[i] = new BoxPlane();
        }

        cube[0].init(Side.PositiveX);
        cube[1].init(Side.PositiveY);
        cube[2].init(Side.PositiveZ);
        cube[3].init(Side.NegativeX);
        cube[4].init(Side.NegativeY);
        cube[5].init(Side.NegativeZ);

    }

    private void initializeVisualTwo()
    {
        pc = new PointCube(10);
        ps = new ParticleSystem(300);
    }

    private void renderVisualOne()
    {
        mv.beginCamera();
        mv.noFill();
        mv.background(0);
        //camera.setCamera(mv);
        mv.camera(-150, -400 * PApplet.cos(PApplet.radians(rotation)), -400 * PApplet.sin(PApplet.radians(rotation)), 100, 100, 100, 0.0f, 1.0f, 0.0f);
        mv.directionalLight(100, 0, 300, -1, 1, -1);
        mv.pointLight(0, 50, 300, 100 , 0, -100);
        mv.pointLight(0, 50, 300, -100, 0, 100);
        //mv.rotate(PApplet.atan2(dir.y, dir.x));
        //rotate2D(dir, PApplet.radians(3));
        /* 
        mv.translate(50, 50, 0);
        mv.rotateX(-PConstants.PI/6);
        mv.rotateY(PConstants.PI/3);
        */
        //mv.box(45);
        //ico.render(mv);
        if(mv.frameCount % 5 == 0)
        {
            for(int i = 0; i < 6; i++)
            {
                cube[i].update(mv);
            }
        }
        for(int i = 0; i < 6; i++)
        {
            cube[i].render(mv);
        }
        mv.endCamera();
        rotation++;
    }

    public void setState(int state)
    {
        this.State = state;
    }

    private void renderVisualTwo()
    {
        mv.beginCamera();
        mv.noFill();
        mv.background(0);
        //camera.setCamera(mv);
        mv.camera(-100, -100  * PApplet.cos(PApplet.radians(rotation)), -100  * PApplet.sin(PApplet.radians(rotation)), 100, 100, 100, 0.0f, 1.0f, 0.0f);
        pc.update(mv);
        pc.render(mv);
        ps.render(mv);
        ps.update();

        mv.pushMatrix();
        mv.noStroke();
        //mv.lights();
        mv.fill(255);
        mv.translate(50, 50, 50);
        mv.calculateAverageAmplitude();
        System.out.println(mv.getAmplitude());
        mv.sphere(100 * mv.getAmplitude());
        mv.popMatrix();

        mv.endCamera();
        rotation++;
    }

    public void render()
    {
        if(State == 0)
            renderVisualOne();

        if(State == 1)
            renderVisualTwo();
    }
    
}