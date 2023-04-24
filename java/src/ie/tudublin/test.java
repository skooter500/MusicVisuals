package ie.tudublin;


//import ddf.minim.AudioBuffer;
//import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PVector;
import ddf.minim.analysis.*;
//import ddf.minim.*;
//import ddf.minim.signals.*;
 

public class test extends PApplet
{
    Minim m;
    AudioPlayer player;
    FFT fft;
    
    Node[] nodes = new Node[1200];

    class Node {
        //GLOBAL VARS
        PVector loc;
        PVector velocity = new PVector(random(-2, 2), random(-2, 2));
        float size = 10;
        
        //CONSTUCTOR
        Node(float x, float y){
          this.loc = new PVector(x, y);
        }

    //Runs all necessary funcitions
  void run(){
    this.display();
    this.move();
    this.bounce();
  }
  
  //Displays node
  void display(){
    point(loc.x, loc.y);
  }

  //Moves node according to velocity
  void move(){
    this.loc.add(velocity);
  }

  //Checks whether the node has reached a wall and if so node "bounces" off
  void bounce(){
    if ((this.loc.x > width) || (this.loc.x < 0)) {
      velocity.x = velocity.x * -1;
    }
    if ((this.loc.y > height) || (this.loc.y < 0)) {
      velocity.y = velocity.y * -1;
    }    
   }
   
}
        
    public void settings()
    {
        size(1024, 1000); 
       //noStroke();
    }

    public void setup()
    {
        //fullScreen();
        smooth();

        //Initialize Nodes
        for(int i = 0; i < nodes.length; i++){
             nodes[i] = new Node(random(width), random(height));
        }
  
        //Initialize Sound
        m = new Minim(this);
        player = m.loadFile("java/data/horizon.mp3", 1024);
        fft = new FFT(player.bufferSize(), player.sampleRate());
        player.play();
    }
        
    
    public void draw(){
        noStroke();
        noCursor();
        background(20);

        // Play Music
  fft.forward(player.mix);
  
  for(int i = 0; i < nodes.length; i++){
    float freq = fft.getFreq((float) (dist(nodes[i].loc.x, nodes[i].loc.y, width/2, height/2)*2.2)); // Multiplier = 2.2 higher to show more frequencies
    
    //Draw Lines
    strokeWeight(freq/15);
    stroke((1-nodes[i].loc.y/800)*255, 0, (nodes[i].loc.y/800)*255);
    for(int j = i + 1; j < nodes.length; j++){
      Node other = nodes[j];
      float dist = nodes[i].loc.dist(other.loc);
      if (dist > 0 && dist < 45){
        line(nodes[i].loc.x, nodes[i].loc.y, other.loc.x, other.loc.y);
      }
    }
    stroke(255);
    nodes[i].run();
}

    }          
    }

    




