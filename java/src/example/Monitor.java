package example;

import java.util.concurrent.TimeUnit;

import ie.tudublin.*;

public class Monitor extends Visual
{   
    

    public void settings()
    {
        fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        //fullScreen(P3D, SPAN); 
    }

    public void setup()
    {
        drawComputer();
        startMinim();
        loadAudio("fanBackground.mp3");
        getAudioPlayer().play();
        
    }
    int count = 0;

    public void draw()
    {
        
        loadingMode();

        // Plays audio once, needs fix
        if(count == 0) 
        {
            startMinim();
            loadAudio("startupSound.mp3");
            getAudioPlayer().play();
            count++;
        }
        
        
    }

    public void drawComputer() 
    {
        float MonitorX = 480;
        float MonitorY = 35;
        float monitorSize =  (float) (width/1.9);
        float bezelSize = 45;

        float screenX = MonitorX+bezelSize;
        float screenY = 80;
        float screenSize =  (float) (monitorSize/1.1);

        float standX = (float) (screenX * 1.25);
        float standY = height-MonitorY;
        float standSize = (float) (screenX * 1.25);

        //background
        fill(176, 176, 176);
        square(0,0,width);
   
        // Table
        fill(130, 107, 82);
        quad(280, 1000, 1660, 1000, 1720, 1080,200, 1080);


        // Monitor
        fill(222, 221, 213);
        noStroke();
        rect(MonitorX,MonitorY,monitorSize,monitorSize,10);


        // Stand
        strokeWeight(2);
        stroke(0);
        fill(181, 178, 165);
        rect(standX,standY,standSize,standY);
    

        // Screen
        fill(0);
        noStroke();
        rect(screenX,screenY,screenSize,screenSize);
    }
    

    public void windowsLogo(float x,float y,float extent) 
    {
        float size = 20 * extent;
        float offset = 1* extent;

        // Red Square
        fill(255,0,0);
        quad(x,y, x+size,y+offset, x+size-offset,y+size+offset, x-offset,y+size);

        // Green Square
        fill(0,255,0);
        quad(x+size+(offset*2), y+offset,   x+(size*2)+(offset*2), y+(offset*2),    x+(size*2)+offset, y+size+(offset*2),    x+size+offset, y+size+offset);

        // Yellow Square
        fill(255, 255, 0);
        quad(x-offset, y+size+(offset*2),   x+size-offset ,y+size+(offset*3),   x+size-(offset*2), y+(size*2)+(offset*3),   x-(offset*2),y+(size*2)+(offset*2));

        // Blue Square
        fill(0,0,255);
        quad(x+size+offset, y+size+(offset*3),   x+(size*2)+offset, y+size+(offset*4),   x+(size*2), y+(size*2)+(offset*4),   x+size, y+(size*2)+(offset*3));
    }


    public void loadingMode() 
    {
        //Times out loading screen
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        windowsLogo(850, 200,6);
        fill(255);
        textSize(80);
        text("Loading...", 800,800);
    }
}
