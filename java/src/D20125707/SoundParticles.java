package D20125707;
//MUSIC  
import ddf.minim.*;
import ddf.minim.signals.*;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PVector;
import ddf.minim.analysis.*;


public class SoundParticles extends PApplet {
    Minim minim;
   AudioPlayer mySound;
   

   // pause/play key
   int mode = 0;

   //if space key is pressed pause/play
   public void keyPressed() {
    if (key >= '0' && key <= '9') {
        mode = key - '0';
    }
    if (keyCode == ' ') {
        if (mySound.isPlaying()) {
            mySound.pause();
        } else {
            mySound.rewind();
            mySound.play();
        }
    }
}
 
public void settings()
    {
        size(1024, 1000); 
       //noStroke();
    }
//MAIN SETUP
public void setup () {
  //fullScreen(P3D);
  //noCursor();
  smooth();
  background (0);
  frameRate(24);
 
  //MUSIC | Add mp3 to file and change name of "Murph.mp3" to your song name
  minim = new Minim(this);
  mySound = minim.loadFile("java/data/horizon.mp3", 1024);    
  mySound.play();
}

 
public void draw () {
 
  fill(0,50);  
  noStroke();
  rect(0, 0, width, height);
  translate(width/2, height/2);
 
  float n4 = (float) 0.4;
  float n6 = (float) 0.6;

for (int i = 0; i < mySound.bufferSize() - 1; i++) {
 
    float angle = sin(i+n4)* 10; 
    float angle2 = sin(i+n6)* 300; 
 
    float x = sin(radians(i))*(angle2+30); 
    float y = cos(radians(i))*(angle2+30);
 
    float x3 = sin(radians(i))*(500/angle); 
    float y3 = cos(radians(i))*(500/angle);
 
    fill (255, 255, 0); //yellow
    ellipse(x, y, mySound.left.get(i)*10, mySound.left.get(i)*10);
 
   fill ( 255); //wt
    rect(x3, y3, mySound.left.get(i)*20, mySound.left.get(i)*10);
 
    fill ( 255, 165, 0); //orange
    rect(x, y, mySound.right.get(i)*10, mySound.left.get(i)*10);
 
 
   fill( 255); //wt
    rect(x3, y3, mySound.right.get(i)*10, mySound.right.get(i)*20);
  }
 
  n4 += 0.008;
  n6 += 0.04;
 
}
}