package ie.tudublin;

import processing.core.PApplet;
import processing.core.PConstants;

public class Mountains 
{
    SongVisuals sv;
    int cols, rows;
    int scl = 40;
    int w, h = 0;

    float scroll = 0;

    float[][] land;
    public Mountains(SongVisuals sv){
        this.sv = sv;
        w = sv.width*4;
        h = sv.height*2;
        cols = w / scl;
        rows = h / scl;
        land = new float[cols][rows];
        

                            
    }

    public void render(){
        sv.colorMode(PApplet.HSB);
        sv.background(0);
        sv.calculateAverageAmplitude();
        scroll -= 0.2f * sv.getSmoothedAmplitude();
        float offsety = scroll;
        for (int y = 0; y < rows; y++){
            float offsetx = 0;
            for (int x = 0; x < cols; x++){
                land[x][y] = PApplet.map(sv.noise(offsetx, offsety), 0, 1, -100, 100);
                offsetx += 0.1f;
            }
            offsety +=   0.1f;
        }

        
        sv.noFill();
        
        sv.translate(sv.width/4, sv.height/4);
        sv.rotateX(PConstants.PI/3);
        sv.translate(-w/2, -h/2);
            for (int y = 0; y < rows-1; y++){
                sv.beginShape(PApplet.TRIANGLE_STRIP);
                for (int x = 0; x < cols; x++){
                    sv.stroke(PApplet.map(y, 0, rows, 0, 255), 255, 255);
                    sv.vertex(x*scl, y*scl, land[x][y] * sv.getAmplitude()*3); // z value determines warpingk
                    sv.vertex(x*scl, (y+1)*scl, land[x][y] * sv.getAmplitude()*3);
                }
                sv.endShape();
            }
        
    }
}
