package OOP2023;

import ie.tudublin.Visual;

public class Start extends Visual {
    Anne anne = new Anne(this);

     //For menu buttons
     int mode = 0;



     public void keyPressed()
     {
         if(key == '0')
         {
             mode = 0;
         }
 
         if(key == '1')
         {
             mode = 1;
         }
    }

    public void setup()
    {
        startMinim();
        loadAudio("rave.mp3");
        getAudioPlayer().play();
        
        colorMode(RGB);
    }

    public void settings()
    {
        size(600, 600, P3D);
    }

    public void draw() {
        switch (mode) {
            // For the main menu
            case 0:
                background(50, 50, 70); // Dark Blue-Gray background
                fill(210, 210, 230); // Light Blue-Gray for text
                textAlign(CENTER);
                textSize(width / 20.0f);

                // Title with a bit of shadow for a modern touch
                text("Ecstatic song", width / 2.0f, height / 4.0f);
                textSize(width / 30.0f);
                fill(220, 220, 240); // Slightly lighter shade for options

                text("Press 0: MENU", width / 2.0f, (height / 2.0f));
                text("Press 1: Nature", width / 2.0f, (height / 2.0f) + 50);

                break;

                // Audio
                case 1:
                background(153, 204, 255);
                anne.render();

        }

                





    }

}