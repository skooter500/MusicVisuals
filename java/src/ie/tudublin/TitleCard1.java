package ie.tudublin;

import processing.core.PConstants;

public class TitleCard1 {
    SongVisuals sv;
    float x;
    float y;
    public TitleCard1 (SongVisuals sv){
        this.sv = sv;
        x = this.sv.width;
        y = this.sv.height;
        sv.createFont("font.otf", 24);
    }
    public void render(){
        sv.textSize(48);
        sv.textAlign(PConstants.CENTER, PConstants.CENTER);
        sv.text("MGMT", x/2, y/2);
        
    }
}
