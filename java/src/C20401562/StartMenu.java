package C20401562;

public class StartMenu {

    Start s;
    String string = "Select One Of The Renders And Press Play";

    float space = 0;
    int heightOfBox = 0;

    //Variables To Return Back to Start.java

    float alexBoxX;
    float alexBox2X;
    
    float BoxY;
    float BoxWidth;
    float BoxHeight;

    float jayBoxX;
    float jayBox2X;

    float mendeBoxX;

    float playButtonX;
    float playButtonY;
    float playRadius;

    float loopButtonX;
    float pressButtonX;
    float ButtonY;
    float ButtoRadius;

    float rightbuttonX;
    float rightbutton2X;
    float leftbuttonX;
    float leftbutton2X;
    float nextButtonY;
    float nextButtonWidth;
    float nextButtonHeight;

    int index1;
    int index2;
    int index3;
    int index4;


    //_____________Constructor 
    public StartMenu(Start start) 
    {
        this.s = start;

    }

    public void render(){


        s.pushMatrix();
            s.translate(s.width/2, s.height/2);

            //Top Text
            s.fill(220);
            s.textSize(40);
            s.text(string, -380, -180);

            //Play Button
            s.fill(90, 300, 360);
            s.circle(0,0, 100);
            s.fill(0);
            s.noStroke();
            s.triangle(-15, 28, -15, -28, 30, 0);

        s.popMatrix();


        space = s.width/10;
        heightOfBox = 60;

        s.pushMatrix();
            s.translate(s.width/2, s.height/2 + 140 , 0);

            //Get choice from Start, when rectangle is clicked then turn that border green
            if(s.choice == 1){//Jay1
                s.noFill();
                s.stroke(90, s.colour, 360);
                s.strokeWeight(3);
                s.rect(-space * 3.5f, 0, space, heightOfBox, 10);
            }else if(s.choice == 2){//Jay2
                s.noFill();
                s.stroke(90, s.colour, 360);
                s.strokeWeight(3);
                s.rect(-space * 2, 0, space, heightOfBox, 10);
            }else if(s.choice == 3){//Alex
                s.noFill();
                s.stroke(90, s.colour, 360);
                s.strokeWeight(3);
                s.rect(-space/2, 0, space, heightOfBox, 10);
            }else if(s.choice == 4){//Alex2
                s.noFill();
                s.stroke(90, s.colour, 360);
                s.strokeWeight(3);
                s.rect(space, 0, space, heightOfBox, 10);
            }else if(s.choice == 5){//Mende
                s.noFill();
                s.stroke(90, s.colour, 360);
                s.strokeWeight(3);
                s.rect(space * 2.5f, 0, space, heightOfBox, 10);
            }else{
                s.noStroke();
                s.noFill();
            }

            s.fill(230);
            s.noStroke();

            //Each Persons Rectangle and Respectful name

            //Jay
            s.rect(-space * 2, 0, space, heightOfBox, 10);
            s.rect(-space * 3.5f, 0, space, heightOfBox, 10);
            //Alex
            s.rect(-space/2, 0, space, heightOfBox, 10);
            s.rect(space, 0, space, heightOfBox, 10);
            //Mende
            s.rect(space * 2.5f, 0, space, heightOfBox, 10);

            s.fill(0);

            s.textSize(40);
            s.text("Jay", -space - space*2 - 35, heightOfBox/1.3f);
            s.text("Jay 2", -space -space/2 - 35, heightOfBox/1.3f);
            s.text("Alex", -40 ,heightOfBox/1.3f);
            s.text("Alex 2", space + space/2 -heightOfBox + 5, heightOfBox/1.3f);
            s.text("Mende", space + space * 2 -heightOfBox, heightOfBox/1.3f);

        s.popMatrix();


        //Setting Variables for Start
        BoxY = s.height/2 + 140;
        BoxWidth = space;
        BoxHeight = heightOfBox;

        alexBoxX = s.width/2 -space/2;
        alexBox2X = s.width/2 + space;

        jayBoxX = s.width/2 -space * 3.5f;
        jayBox2X = s.width/2 -space * 2;

        mendeBoxX = s.width/2 + space * 2.5f;

        playButtonX = s.width/2;
        playButtonY = s.height/2;
        playRadius = 100;

    }
    //_____________End Of Render

    public void lowerMenu()
    {

        //Bar
        // s.fill(20);
        // s.noStroke();
        // s.rect(0, s.height - 60, s.width, 60);

        //Play/Pause Button
        s.fill(255);
        s.circle(s.width/2 - 40, s.height - 30, 40);

        //Loop Button
        s.stroke(255);
        s.noFill();
        s.circle(s.width/2 + 40, s.height - 30, 40);
        
        s.stroke(255);
        s.fill(255);
        s.triangle(s.width/2 + 50, s.height - 20, s.width/2 + 70, s.height - 20, s.width/2 + 60, s.height - 30);


        //Grabs if the song is playing or not and changed the button mode from puase to play
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

        //Creating two rectangles so user can switch through to other graphics
        s.stroke(255);
        //RightSide
        s.rect(s.width/2 + 100, s.height - 50, 100, 40);
        s.rect(s.width/2 + 250, s.height - 50, 100, 40);
        //Left Side
        s.rect(s.width/2 - 200, s.height - 50, 100, 40);
        s.rect(s.width/2 - 350, s.height - 50, 100, 40);

        //Loops through array from Start to know which text to put were
        s.fill(255);
        s.textSize(25);
        int leftbox = 0;
        int left2box = 0;
        int rightbox = 0;
        int right2box = 0;

        for(int i = 1; i < s.name.length; i++){
            //If the index of the array is not the current one playing populate rectangles
            if(s.mode != i && leftbox == 0){
                s.text(s.name[i],s.width/2 - 180, s.height - 22);
                index3 = i;
                leftbox = 1;
            }else if(s.mode != i && rightbox == 0){
                s.text(s.name[i],s.width/2 + 110, s.height - 22);
                index1 = i;
                rightbox = 1;
            }else if(s.mode != i && right2box == 0){
                s.text(s.name[i],s.width/2 + 260, s.height - 22);
                index2 = i;
                right2box = 1;
            }else if(s.mode != i && left2box == 0){
                s.text(s.name[i],s.width/2 - 340, s.height - 22);
                index4 = i;
                left2box = 1;
            }
        }


        //Variables for Start
        loopButtonX = s.width/2 + 40;

        pressButtonX = s.width/2 - 40;
        ButtonY = s.height - 30;
        ButtoRadius = 20;


        rightbuttonX = s.width/2 + 100;
        rightbutton2X = s.width/2 + 250;
        leftbuttonX = s.width/2 - 200;
        leftbutton2X = s.width/2 - 350;

        nextButtonHeight = 40;
        nextButtonWidth = 100;
        nextButtonY = s.height - 50;


    }

    //___________________End Lower Menu
    
}
