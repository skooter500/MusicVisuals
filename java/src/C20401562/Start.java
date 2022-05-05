package C20401562;

import ddf.minim.analysis.*;
import ie.tudublin.*;
import processing.core.PApplet;

public class Start extends Visual{

    //For Beat Detection
    BeatDetect beat;
    Particals[] partical = new Particals[300];
    public boolean fourCircles = false;
    public boolean fourCubes = false;
    float h = height / 2;



    int mode = 0;
    int colour = 0;
    int choice = 0;
    int firstClick = 0;
    int particals = 300;

    int buttonMode = 1;

    //Booleans for Menu
    boolean isPlaying = false;
    boolean allowToPlay = false;

    float lerpedAverage = 0;
    float average;
    float sum;

    //Smooth the shapes In Visuals
    float[] lerpedBuffer;
    float[] lerpedBuffer2;


    String[] name = {"","Jay", "Jay 2", "Alex", "Alex 2","Mende"};


    //____Initializing Visuals
    AlexsVisual alex;
    AlexsVisual2 alex2;
    JaycelsVisual jay;
    JaycelsVisual2 jay2;
    MendesVisual mende;
    StartMenu startm;
    
    public void settings()
    {
        size(1400, 800, PApplet.P3D);

        noSmooth();

    }

    public void keyPressed()
    {
        //Pause or Play Song

        if (key == ' ')
        {
            if(ap.isPlaying()){
                ap.pause();

                buttonMode = 0;
                isPlaying = false;
            }else if(allowToPlay){
                ap.play();

                buttonMode = 1;
                isPlaying = true;
            }
            
        }
    }

    public void setup()
    {

        startMinim();
        loadAudio("Song.wav");

        // startListening();

        beat = new BeatDetect();

        colorMode(HSB);

        for(int i = 0; i < particals; i++)
        {
            partical[i] = new Particals(this); 
         
        }

            
        lerpedBuffer = new float[width];
        lerpedBuffer2 = new float[width];

       
    }

    public void draw()
    {
        //Creat Beat Detection On Ap
        beat.detect(ap.mix);

        background(30);

        calculateAverageAmplitude();   

        alex = new AlexsVisual(this);
        alex2 = new AlexsVisual2(this);
        jay = new JaycelsVisual(this);
        jay2 = new JaycelsVisual2(this);
        mende = new MendesVisual(this);
        startm = new StartMenu(this);

        //_______________Switch Statment For Visuals

        if(allowToPlay){
            switch (mode) 
            {
			case 0:
                break;
            case 1:
                //First Visual
                jay.render();
                startm.lowerMenu();
                break;
            case 2:
                //Second Visual
                jay2.render();
                startm.lowerMenu();
                break;
            case 3:
                //Third Visual
                for(int i = 0; i < particals; i++)
                {
                    partical[i].display(); 
                    partical[i].update();
                
                }
                alex.render();
                startm.lowerMenu();
                break;
            case 4:
                //Fourth Visual
                float average = 0;
                float sum = 0;
        
                // Calculates average of the sound buffer
                for (int j = 0; j < ab.size(); j++)
                {
                    sum += abs(ab.get(j));
                }
                average = sum / ab.size();
                lerpedAverage = lerp(lerpedAverage, average, 0.1f);
        
                //variables for circles
                float x1 = 333.33f;
                float y1 = 333.33f;
        
                //variables for squars
                float angle = 0;
                angle += 0.01f;
                float s = 50 + (50 * lerpedAverage * 10);
        
                for (int i = 0; i < ab.size(); i++) {
        
                    float c = map(i, 0, ab.size(), 0, 255);
        
                    stroke(c, 255, 255);
        
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.3f); 
                    //line 1       
                    line(width - x1, i, width - x1 - (lerpedBuffer[i] * h * 10), i);
        
                    //line 2
                    line(width - (x1 * 2), i, width - (x1 * 2) - (lerpedBuffer[i] * h * 10), i);
        
                    //line 3
                    line(i, 0 + y1, i, y1 - lerpedBuffer[i] * h * 10);
        
                    //line 4
                    line(i, height - y1, i, (height - y1) - (lerpedBuffer[i] * h * 10));
                }      
                
                float c = map(average, 0, 1, 0, 255);
                fill(c, 255, 255);
                int gap = 25;
        
                
        
                if(! fourCircles)
                {
                    ellipse(width - (x1 / 2), height - (y1 / 2), 50 + (lerpedAverage * 500), 50 + (lerpedAverage * 500));
                    ellipse(width - (x1 / 2), height - (y1 / 2) * 5, 50 + (lerpedAverage * 500), 50 + (lerpedAverage * 500));
                    ellipse(width - (x1 / 2) * 5, height - (y1 / 2), 50 + (lerpedAverage * 500), 50 + (lerpedAverage * 500));
                    ellipse(width - (x1 / 2) * 5, height - (y1 / 2) * 5, 50 + (lerpedAverage * 500), 50 + (lerpedAverage * 500));
                }
                else
                {
                    ellipse(width / 2 + (x1 / 4) - gap, height / 2 + (y1 / 4) - gap, 50 + (lerpedAverage * 500), 50 + (lerpedAverage * 500));
                    ellipse(width / 2 + (x1 / 4) - gap, height / 2 - (y1 / 4) + gap, 50 + (lerpedAverage * 500), 50 + (lerpedAverage * 500));
                    ellipse(width / 2 - (x1 / 4) + gap, height / 2 + (y1 / 4) - gap, 50 + (lerpedAverage * 500), 50 + (lerpedAverage * 500));
                    ellipse(width / 2 - (x1 / 4) + gap, height / 2 - (y1 / 4) + gap, 50 + (lerpedAverage * 500), 50 + (lerpedAverage * 500));
        
                    triangle(width - (x1 / 2) / 2, 0 + (y1 / 2) / 2, width - (x1 / 2) * 3, 0 + (y1 / 2) / 2, width - (x1 / 2), 0 + (y1 / 2) / 2);
                }
        
                
        
                float b = map(average * 2, 0, 1, 0, 255);
                fill(b, 255, 255);
                if (! fourCubes)
                {
                    translate(width / 2, height / 2, 0);
                    rotateY(angle);
                    rotateX(angle);
                    box(s);
                }
                else
                {
                    pushMatrix();
                    translate(width - (x1 / 2) * 5, height / 2, 0);
                    rotateY(angle);
                    rotateX(angle);
                    box(s);
                    popMatrix();
        
                    pushMatrix();
                    translate(width - (x1 / 2), height / 2, 0);
                    rotateY(angle);
                    rotateX(angle);
                    box(s);
                    popMatrix();
        
                    pushMatrix();
                    translate(width / 2, height - (y1 / 2), 0);
                    rotateY(angle);
                    rotateX(angle);
                    box(s);
                    popMatrix();
        
                    pushMatrix();
                    translate(width / 2, height - (y1 / 2) * 5, 0);
                    rotateY(angle);
                    rotateX(angle);
                    box(s);
                    popMatrix();
                
                
                
                
        
             
            }
                alex2.render();
                startm.lowerMenu();
                break;
            case 5:
                //Fifth Visual
                mende.render();
                startm.lowerMenu();
                break;
            case 6:
                break;

            }
        }else{
            //At the start when song isnt playing show menu
            startm.render();
        }
    }
    //___________________End Draw Method()


    public void mouseClicked()
    {   
        //If statments to check the cordinates of the mouse and which box it is selecting

        if(isPlaying == false && mouseX >= startm.jayBoxX && mouseX <= startm.jayBoxX + startm.BoxWidth && mouseY >= startm.BoxY && mouseY <= startm.BoxY + startm.BoxHeight){
            
            firstClick = 1;
            mode = 1;
            choice = 1;
            colour = 200;

        }else if(isPlaying == false && mouseX >= startm.jayBox2X && mouseX <= startm.jayBox2X + startm.BoxWidth && mouseY >= startm.BoxY && mouseY <= startm.BoxY + startm.BoxHeight){
            
            firstClick = 1;
            mode = 2;
            choice = 2;
            colour = 200;

        }else if(isPlaying == false && mouseX >= startm.alexBoxX && mouseX <= startm.alexBoxX + startm.BoxWidth && mouseY >= startm.BoxY && mouseY <= startm.BoxY + startm.BoxHeight){
            
            firstClick = 1;
            mode = 3;
            choice = 3;
            colour = 200;

        }else if(isPlaying == false && mouseX >= startm.alexBox2X && mouseX <= startm.alexBox2X + startm.BoxWidth && mouseY >= startm.BoxY && mouseY <= startm.BoxY + startm.BoxHeight){
            
            firstClick = 1;
            mode = 4;
            choice = 4;
            colour = 200;

        }else if(isPlaying == false && mouseX >= startm.mendeBoxX && mouseX <= startm.mendeBoxX + startm.BoxWidth && mouseY >= startm.BoxY && mouseY <= startm.BoxY + startm.BoxHeight){
            
            firstClick = 1;
            mode = 5;
            choice = 5;
            colour = 200;

        }

        //Mouse coridnates for the play button, first checks if the first click was made
        if(firstClick != 0 && mouseX <= startm.playButtonX + 50 && mouseX >= startm.playButtonX - 50 && mouseY >= startm.playButtonY - 50 && mouseY <= startm.playButtonY + 50){
            
            allowToPlay = true;
            ap.play();
            isPlaying = true;

        }
        
        //If the Song is Playing
        if(allowToPlay){

            //Lower Menu Button to pause and play the song
            if(mouseX <= startm.pressButtonX + startm.ButtoRadius && mouseX >= startm.pressButtonX - startm.ButtoRadius && mouseY >= startm.ButtonY - startm.ButtoRadius &&  mouseY <= startm.ButtonY + startm.ButtoRadius){
                if(ap.isPlaying()){

                    ap.pause();
                    buttonMode = 0;

                }else{

                    buttonMode = 1;
                    ap.play();
                    
                }
            }

            //Lower Menu Loop button
            if(mouseX <= startm.loopButtonX + startm.ButtoRadius && mouseX >= startm.loopButtonX - startm.ButtoRadius && mouseY >= startm.ButtonY - startm.ButtoRadius &&  mouseY <= startm.ButtonY + startm.ButtoRadius){
               
                ap.loop();
                buttonMode = 1;

            }

            //Scrolling through the renders in the lower menu

            if(mouseX <= startm.rightbuttonX + startm.nextButtonWidth && mouseX >= startm.rightbuttonX && mouseY >= startm.nextButtonY && mouseY <= startm.nextButtonY + startm.nextButtonHeight){
                mode = startm.index1;
            }

            if(mouseX <= startm.rightbutton2X + startm.nextButtonWidth && mouseX >= startm.rightbutton2X && mouseY >= startm.nextButtonY && mouseY <= startm.nextButtonY + startm.nextButtonHeight){
                mode = startm.index2;
            }
            
            if(mouseX <= startm.leftbuttonX + startm.nextButtonWidth && mouseX >= startm.leftbuttonX && mouseY >= startm.nextButtonY && mouseY <= startm.nextButtonY + startm.nextButtonHeight){
                mode = startm.index3;
            }

            if(mouseX <= startm.leftbutton2X + startm.nextButtonWidth && mouseX >= startm.leftbutton2X && mouseY >= startm.nextButtonY && mouseY <= startm.nextButtonY + startm.nextButtonHeight){
                mode = startm.index4;
            }

        }

    }
    //___________________End Mouse Click Method
    
}
