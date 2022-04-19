package ie.tudublin;

/*
Two lines intersecting at the same time
*/

public class LineIntro {
    SongVisuals sv;
    float x;
    float y;
    float i = 0;
    float j = 0;
    public LineIntro (SongVisuals sv){
        this.sv = sv;
        x = this.sv.width;
        y = this.sv.height;
    }

    public void render(){
        //i += 1;
        
        sv.stroke(255);
        sv.strokeWeight(2);
        sv.line(x, y/8, x-i, y / 8);
        sv.line(0, y - y / 8, 0 + j, y - y / 8);
        //sv.line(x/8, y, x/8, y-j);
        
        if (i < x){
            i = i + (0.003f*x);
        }
        /*
        if (j < y){
            j = j + (0.003f*y);
        }
        */
        if (j < x){
            j = j + (0.003f*x);
        }
    }

}