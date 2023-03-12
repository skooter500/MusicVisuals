package example;

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
    }


    public void draw()
    {
        drawComputer();
        windowsLogo();
        
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

    public void windowsLogo() 
    {
        // Red Square
        fill(255,0,0);
        quad(500,500, 700,513, 688,713, 488,700);

        // Green Square
        fill(0,255,0);
        quad(725,513, 925,525, 913,725, 713,713);

        // Yellow Square
        fill(255, 255, 0);
        quad(488,725, 688,738, 676,938, 476,925);

        // Blue Square
        fill(0,0,255);
        quad(713,738, 913,750, 900,950, 700,938);
    }
}
