package C19326126;

import processing.core.PApplet;

public class StarSystem extends PApplet{
    float rotation;
    float rotation2;
    float rotation3;

    Sun Sun1;
    Sun Sun2;
    Sun Sun3;
    Sun Sun4;
    Sun Sun5;
    Sun Sun6;

    public void settings(){
        fullScreen(P3D, SPAN);
        //color, position X/Y, size
        Sun1 = new Sun(color(255, 255, 0), 0, 0, 50);//Sun
        Sun2 = new Sun(color(0, 255, 0), 10, 0, 20);//Earth
        Sun3 = new Sun(color(0, 0, 255), 10, 0, 5);//Moon
        Sun4 = new Sun(color(255, 0, 50), 10, 0, 18);
        Sun5 = new Sun(color(255, 0, 50), 10, 0, 4);
        Sun6 = new Sun(color(255, 0, 50), 10, 0, 6);
    }


    public void setup(){


    }

    public void draw(){
        
        background(0);
        lights();


        pushMatrix();//Sun
        translate(width/2, height/2);
        rotate(radians(rotation));
        Sun1.display();
            pushMatrix();//Earth
            rotate(radians(rotation2));
            translate(400, 0);
            Sun2.display();
            rotate(radians(rotation2));
                pushMatrix(); //Moon
                    translate(40, 0);
                    Sun3.display();
                popMatrix(); //End Moon
            popMatrix(); //End earth

        pushMatrix();
        translate(650,0);
        rotate(radians(rotation));
        Sun4.display();//Mars
            pushMatrix(); //Mars Moons
                rotate(radians(rotation3));
                translate(29, 0);
                Sun5.display();
            popMatrix();
            pushMatrix();
                translate(-53, 0);
                Sun6.display();
            popMatrix();
            pushMatrix();
                rotate(radians(rotation));
                translate(40, 15);
                Sun5.display();
            popMatrix(); //End Mars Moon
        popMatrix(); //End Mars

        popMatrix(); //end Sun

        rotation += .15;
        rotation2 += .85;
        rotation3 += 1.5;
    }

    public class Sun{
        int c;
        int s;
        float xpos = 0;
        float ypos = 0;

        Sun(int tempC, float tempXpos, float tempYpos, int size){
            c = tempC;
            xpos= tempXpos;
            ypos= tempYpos;
            s = size;
        }

        public void display(){
            //For loops for music and changing stroke and size
            stroke(c);
            fill(c);
            sphere(s);
        }
    }
    
}
