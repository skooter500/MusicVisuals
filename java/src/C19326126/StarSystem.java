package C19326126;

import processing.core.PApplet;

public class StarSystem extends PApplet{
    float rotation;
    float rotation2;
    float rotation3;
    float rotation4;
    float rotation5;
    Boolean generation = false;

    //Star Coordinates & Sizes
    float x [] = new float[200];
    float y [] = new float[200]; 
    float z [] = new float[200];
    float size[] = new float[200];
    float x2 [] = new float[200];
    float y2 [] = new float[200]; 
    float z2 [] = new float[200];
    float size2[] = new float[200];

    Sun Sun1, Sun2, Sun3, Sun4, Sun5, Sun6, Sun7, Sun8;
    


    public void settings(){
        fullScreen(P3D, SPAN);
        //color, position X/Y, size
        Sun1 = new Sun(color(255, 255, 0), 0, 0, 50);//Sun
        Sun2 = new Sun(color(0, 255, 0), 10, 0, 20);//Earth
        Sun3 = new Sun(color(0, 0, 255), 10, 0, 5);//Moon
        Sun4 = new Sun(color(255, 0, 50), 10, 0, 18);//Mars
        Sun5 = new Sun(color(255, 0, 50), 10, 0, 4);//Moon2
        Sun6 = new Sun(color(255, 0, 50), 10, 0, 6);//Moon3
        Sun7 = new Sun(color(200, 100, 20), 10, 0, 15);//Mercury
        Sun8 = new Sun(color(200,180,40), 10, 0, 35);//Jupiter
    }


    public void setup(){

    }

    public void draw(){        
        background(0);
        lights();
        camera(mouseX*2, mouseY, (height/2) / tan(PI/5), width/2, height/2, 0, 0, 1, 0);

        pushMatrix();//Sun
            translate(width/2, height/2);
            rotate(radians(rotation));
            Sun1.display();
    
            Mercury();

            Earth();

            Mars();

            Jupiter();

        popMatrix(); //end Sun

        pushMatrix(); //begin star generation

            translate(width/2, height/2);
            StarField();

        popMatrix(); //end star genertaion

        //rotational constants for different planets
        rotation += .15;
        rotation2 += .85;
        rotation3 += 1.5;
        rotation4 += .65;
        rotation5 -= .1;
    }
    private void StarField(){
        if(!generation){
            
        //seperate i's with if statements to be able to make stars of diff audio bands
        //eg if i > 25 &&i < 50 stroke map diffcolor * bands.length


        for(int i = 0; i < 200; i++){
            x[i] = random(-3500,3500);
            y[i] = random(-3000,3000);
            z[i] = random(50,3000);
            size[i] = random(3,18);
        }
        for(int j = 0; j < 200; j++){
            x2[j] = random(-3500,3500);
            y2[j] = random(-3000,3000);
            z2[j] = random(-3000,-50);
            size2[j] = random(3,18);
        }

        
        generation = true;
        }
    for(int i = 0; i < 200; i++){
        //Z values ensure Planets won't strike a star
        pushMatrix();
        translate(x[i], y[i], z[i]);
        fill(120,120,255);
        sphere(size[i]);
        popMatrix();
        }
    for(int j = 0; j < 200; j++){
        pushMatrix();
        translate(x2[j], y2[j], z2[j]);
        fill(120,120,255);
        sphere(size2[j]);
        popMatrix();
        }
    }//end StarField

    private void Mercury() {
        pushMatrix();
        rotate(radians(rotation));
        translate(-150, -10);
        Sun7.display();
        popMatrix();
    }//End Mercury


    private void Earth() {
        pushMatrix();//Earth
            rotate(radians(rotation2));
            
            translate(350, 0);
            Sun2.display();
            rotate(radians(rotation2));
                pushMatrix(); //Moon
                    translate(40, 0);
                    Sun3.display();
                popMatrix(); //End Moon
            popMatrix(); //End earth
    }//End Earth


    void Mars(){
        pushMatrix();
        translate(480,0);
        rotate(radians(rotation4));
        Sun4.display();//Mars
            pushMatrix(); //Mars Moons
                rotate(radians(rotation3));
                translate(29, 0);
                Sun5.display();
            popMatrix();
            pushMatrix();
                rotate(radians(rotation));
                translate(40, 15);
                Sun5.display();
            popMatrix(); //End Mars Moon
        popMatrix(); //End Mars
    }//End Mars

    private void Jupiter() {
        pushMatrix();
        rotate(radians(rotation5));
        translate(-650, 30);
        Sun8.display();
        popMatrix();
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
        }//End Sun

        public void display(){
            //For loops for music and changing stroke and size
            noStroke();
            fill(c);
            sphere(s);
        }//End display
    }
}
