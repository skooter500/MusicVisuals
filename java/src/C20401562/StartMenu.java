package C20401562;

public class StartMenu {

    Start s;
    String string = "Select One Of The Renders And Press Play";

    float space = 0;
    int heightOfBox = 0;

    //Variables To Return Back to Start.java

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

    float rightbuttonX;
    float leftbuttonX;
    float nextButtonY;
    float nextButtonWidth;
    float nextButtonHeight;

    int rightIndex;
    int leftIndex;


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
            if(s.choice == 1){
                s.noFill();
                s.stroke(90, s.colour, 360);
                s.strokeWeight(3);
                s.rect(-space * 2, 0, space, heightOfBox, 10);
            }else if(s.choice == 2){
                s.noFill();
                s.stroke(90, s.colour, 360);
                s.strokeWeight(3);
                s.rect(-space/2, 0, space, heightOfBox, 10);
            }else if(s.choice == 3){
                s.noFill();
                s.stroke(90, s.colour, 360);
                s.strokeWeight(3);
                s.rect(space, 0, space, heightOfBox, 10);
            }else{
                s.noStroke();
                s.noFill();
            }

            s.fill(230);
            s.noStroke();

            //Each Persons Rectangle and Respectful name

            s.rect(-space * 2, 0, space, heightOfBox, 10);
            s.rect(-space/2, 0, space, heightOfBox, 10);
            s.rect(space, 0, space, heightOfBox, 10);

            s.fill(0);

            s.textSize(40);
            s.text("Alex", -40 ,heightOfBox/1.3f);
            s.text("Jay", -space - space/2 - 30, heightOfBox/1.3f);
            s.text("Mende", space + space/2 -heightOfBox, heightOfBox/1.3f);

        s.popMatrix();


        //Setting Variables for Start
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
    //_____________End Of Render

    public void lowerMenu()
    {

        //Bar
        s.fill(20);
        s.noStroke();
        s.rect(0, s.height - 60, s.width, 60);

        //Play/Pause Button
        s.fill(255);
        s.circle(s.width/2 - 40, s.height - 30, 40);

        //Loop Button
        s.stroke(255);
        s.fill(20);
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
        s.rect(s.width/2 + 100, s.height - 50, 100, 40);
        s.rect(s.width/2 - 200, s.height - 50, 100, 40);

        //Loops through array from Start to know which text to put were
        s.fill(255);
        s.textSize(25);
        int leftbox = 0;
        int rightbox = 0;

        for(int i = 1; i < s.name.length; i++){
            //If the index of the array is not the current one playing populate rectangles
            if(s.mode != i && leftbox == 0){
                s.text(s.name[i],s.width/2 - 180, s.height - 22);
                leftIndex = i;
                leftbox = 1;
            }else if(s.mode != i && rightbox == 0){
                s.text(s.name[i],s.width/2 + 110, s.height - 22);
                rightIndex = i;
                rightbox = 1;
            }
        }


        //Variables for Start
        loopButtonX = s.width/2 + 40;

        pressButtonX = s.width/2 - 40;
        ButtonY = s.height - 30;
        ButtoRadius = 40;


        rightbuttonX = s.width/2 + 100;
        leftbuttonX = s.width/2 - 200;

        nextButtonHeight = 40;
        nextButtonWidth = 100;
        nextButtonY = s.height - 50;


    }

    //___________________End Lower Menu
    
}
