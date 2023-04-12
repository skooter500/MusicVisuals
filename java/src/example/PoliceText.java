package example;

import processing.core.PConstants;

public class PoliceText {

    MyVisual mv;
    boolean showTape = false;
    float textMovement = 0;
    float tapeMovement = 0;

    public PoliceText(MyVisual mv) {
        this.mv = mv;
    }

    public void draw() {
        if (showTape) {
            mv.stroke(255, 255, 0); 
            mv.strokeWeight(50);
            mv.strokeCap(PConstants.SQUARE); // round strokeCap to square
    
            float speed = 2; 
            tapeMovement += speed;
    
            for (int i = -1; i <= 7; i++) {
                float cy = 20 + 100 * i;
                float cx;
                if (i % 2 == 0) { // if index is even = right
                    cx = (tapeMovement + i * mv.width) % mv.width;
                } else { // if index is odd = left
                    float textWidth = mv.textWidth("If you were a vegetable, you'd be a cute-cumber"); // width of the text message
                    cx = ((mv.width - textWidth) / 2) - ((tapeMovement + (i + 1) * mv.width) % mv.width);
                }
                float textWidth = mv.textWidth("If you were a vegetable, you'd be a cute-cumber"); 
                float x_end_text = cx + textWidth; // x_end_text = end of the text message
                if (x_end_text > 0) {
                    mv.line(cx, cy, x_end_text, cy);
                    float textHeight = mv.textAscent() + mv.textDescent(); // text height
                    mv.fill(0);
                    mv.textSize(25); // Bigger text size
                    mv.textFont(mv.createFont("Arial Bold", 25));
                    mv.textAlign(PConstants.LEFT, PConstants.CENTER);
                    mv.text("If you were a vegetable, you'd be a cute-cumber.", cx, cy - textHeight/2);
                }
            }
    
            textMovement += speed;
            if (textMovement > mv.width) {
                textMovement = -mv.textWidth("If you were a vegetable, you'd be a cute-cumber.");
            }
    
        }
    }
    
}
