package C22410766;

import ie.tudublin.Visual;

public class start {
    Visual visual;

    public start(Visual visual) {
        this.visual = visual;


    }
    public void draw(){
        visual.background(0);

        float centerX = visual.width / 2;
        float centerY = visual.height / 2;
        float angle = 0;5

        angle += 0.01;
    
        // Set the rotation angle
        visual.rotate(angle);
        
        visual.textSize(50);
        visual.text("OOP Visual assignment", centerX-200, centerY);
        visual.textSize(30);
        visual.text("by Aaron, Alex, Sergei", centerX-200, centerY+50);
        
        
    }
}
