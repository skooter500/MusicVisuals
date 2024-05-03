package example;
import processing.core.PApplet;
public class circlevertex extends poly
{
    public circlevertex(MyVisual mv)
    { 
        super(mv);
    }

    @Override
    public void render(){
        mv.strokeWeight(1);
        mv.colorMode(PApplet.HSB);
        float hue2 = mv.frameCount % 330;
        mv.background(hue2, 255, 255);
        mv.translate(mv.width/2, mv.height/2);
        for(int i = 0; i < 2000;i+=65)
        {
            float hue = PApplet.map(i, 0, mv.ab.size(), 0, 256);
            float radius = 100+i;
            //rotate entire shape
            mv.rotate(PApplet.radians(mv.frameCount));
                    
            mv.beginShape();
            for(float a = 0; a < PApplet.TWO_PI; a+=PApplet.TWO_PI/8)
            {
                float x = radius * PApplet.cos(a);
                float y =  radius * PApplet.sin(a);
                //changes stroke weight on beat
                mv.noFill();
                mv.stroke(hue, 150, 200);
	            mv.vertex(x * mv.smooth, y * mv.smooth);
                //Vertex responds to beat through smooth variable
                //Create circles in the shape of a vertex and distance them using i and j
                for (int j = 0; j < mv.height; j += 25) {
                    float hue1 = PApplet.map(i, 0, mv.ab.size() , 0, 256);
                    mv.fill(hue1, 255, 255);
                    mv.stroke(15);
                    //Circles respond to beat through smooth variable
                    mv.circle((i*3)/4, (j*3)/4, ((mv.width/2) * mv.smooth) / 2 );
                }
            }
            mv.endShape();
        }
    }
}