package example;
import processing.core.PApplet;
public class vertex extends poly
{
    public vertex(MyVisual mv)
    { 
        super(mv);
    }

    @Override
    public void render(){
        mv.strokeWeight(1);
        mv.colorMode(PApplet.HSB);
        float hue2 = mv.frameCount % 320;
        mv.background(hue2, 255, 255);
        mv.stroke(255);
        mv.translate(mv.width/2, mv.height/2);
        for(int i = 0; i < 2000;i+=60)
        {
            //Map for rainbow colours
            float hue = PApplet.map(i, 0, mv.ab.size() + (mv.ab.size() / 4), 50, 250);
            float radius = 100+i;
            //rotate entire shape
            mv.rotate(PApplet.radians(mv.frameCount));      
            mv.beginShape();
            for(float a = 0; a < PApplet.TWO_PI; a+=PApplet.TWO_PI/8)
            {
                float x = radius * PApplet.cos(a);
                float y =  radius * PApplet.sin(a);
                mv.noFill();
                //Colours for vertex
                mv.stroke(hue, 255, 255);
                //Vertex responds to beat through smooth variable
                mv.vertex(x * mv.smooth, y * mv.smooth);
            }
            mv.endShape();
        }
    }
}