package C20401562;


public class StartMenu {

    Start s;

    String string = "Select One Of The Render And Press Play";

    float space = 0;
    int heightOfBox = 0;

    float alexBoxX;
    
    float BoxY;
    float BoxWidth;
    float BoxHeight;

    float jayBoxX;

    float mendeBoxX;

    float playButtonX;
    float playButtonY;
    float playRadius;

    float loopButtonX;
    float pressButtonX;
    float ButtonY;
    float ButtoRadius;


    //Constructor 
    public StartMenu(Start start) 
    {
        this.s = start;
    }

    public void render(){

        // s.rect(, b, c, d, r);

        s.pushMatrix();
        s.translate(s.width/2, s.height/2);

        s.fill(220);
        s.text(string, -380, -180);

        s.fill(90, 220, 360);
        if(s.allowToPlay){
            s.stroke(255);
        }else{

            s.noStroke();
        }

        s.circle(0,0, 100);
        s.fill(0);
        s.noStroke();
        s.triangle(-15, 28, -15, -28, 30, 0);
        s.popMatrix();


        space = s.width/10;
        heightOfBox = 60;
        s.pushMatrix();
        s.translate(s.width/2, s.height/2 + 140);


        if(s.choice == 1){
            s.noFill();
            s.stroke(90, s.colour, 360);
            s.strokeWeight(5);
            s.rect(-space * 2, 0, space, heightOfBox, 10);
        }else if(s.choice == 2){
            s.noFill();
            s.stroke(90, s.colour, 360);
            s.strokeWeight(5);
            s.rect(-space/2, 0, space, heightOfBox, 10);

        }else if(s.choice == 3){
            s.noFill();
            s.stroke(90, s.colour, 360);
            s.strokeWeight(5);
            s.rect(space, 0, space, heightOfBox, 10);
        }else{
            s.noStroke();
            s.noFill();
        }
        s.fill(230);
        s.noStroke();

        s.rect(-space * 2, 0, space, heightOfBox, 10);
        s.rect(-space/2, 0, space, heightOfBox, 10);
        s.rect(space, 0, space, heightOfBox, 10);

        s.fill(0);

        s.textSize(40);
        s.text("Alex", -40 ,heightOfBox/1.3f);
        s.text("Jay", -space - space/2 - 30, heightOfBox/1.3f);
        s.text("Mende", space + space/2 -heightOfBox, heightOfBox/1.3f);

        s.popMatrix();


        alexBoxX = s.width/2 -space/2;
        BoxY = s.height/2 + 140;
        BoxWidth = space;
        BoxHeight = heightOfBox;

        jayBoxX = s.width/2 -space * 2;

        mendeBoxX = s.width/2 + space;

        playButtonX = s.width/2;
        playButtonY = s.height/2;
        playRadius = 100;




    }

    public void lowerMenu(){

        s.fill(20);
        s.noStroke();

        s.rect(0, s.height - 60, s.width, 60);

        s.fill(255);
        s.circle(s.width/2 - 40, s.height - 30, 40);

        s.noFill();
        s.stroke(255);
        s.circle(s.width/2 + 40, s.height - 30, 40);

        s.fill(0);
        s.triangle(s.width/2 + 50, s.height - 20, s.width/2 + 70, s.height - 20, s.width/2 + 60, s.height - 30);

        s.stroke(20);
        s.fill(20);
        s.rect(s.width/2 + 45, s.height - 37f, 20,5);

        if(s.buttonMode == 1){
            s.fill(20);
            s.noStroke();
            s.rect(s.width/2 - 38.25f, s.height - 42.5f, 8, 25,2);
            s.rect(s.width/2 - 50, s.height - 42.5f, 8, 25,2);

            s.noFill();
            s.stroke(90, 220, 360);
            s.circle(s.width/2 - 40, s.height - 30, 40);
        }else{
            s.noFill();
            s.stroke(0, 220, 360);
            s.circle(s.width/2 - 40, s.height - 30, 40);

            s.fill(20);
            s.stroke(40);
            s.triangle(s.width/2 - 48,  s.height - 42.5f, s.width/2 - 48,  s.height - 16.5f, s.width/2 - 25.25f,  s.height - 29.5f);
        }

        s.rect(s.width/2 + 100, s.height - 50, 60, 40);

        loopButtonX = s.width/2 + 40;

        pressButtonX = s.width/2 - 40;
        ButtonY = s.height - 30;
        ButtoRadius = 40;


    }
    
}
