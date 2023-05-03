package assaigment;

import java.util.ArrayList;
import processing.core.PApplet;
import ddf.minim.*;
import ddf.minim.analysis.FFT;
import ie.tudublin.Experiance;

public class music_note extends Visual
{
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    float colour = 0;
    int mode = 0;

    FFT fft;

    Star[] stars;
    
    Experiance pa;

    public void settings()
    {
        size(1024,1024, P3D);
        smooth();
    }

    public void setup()
    {
        colorMode(HSB);

        // create star objects
        stars = new Star[25];
        for (int i = 0; i < stars.length; i++) 
        {
            float x = random(width);
            float y = random(height);
            float size = random(20, 100);
            float speed = random(1, 5);
            stars[i] = new Star(this, x, y, size, speed, height, width, stars);
        }
        startMinim();
        loadAudio("MusicVisuals/java/data/Victoria_Mon_t_ft_Khalid_-_Experience.mp3");
        getAudioPlayer().play();
    }

 
    public void draw()
    {
        background(0);
        float amplitude = 0;
        float[] bands;
        frameRate(50);

        calculateAverageAmplitude();
        amplitude = getSmoothedAmplitude();

        try
        {
            calculateFFT();
        }
        catch(VisualException e)
        {
            e.printStackTrace();
        }
        calculateFrequencyBands();
        bands = getSmoothedBands();
    
        pushMatrix();

        // loop through al stars in the stars array
        for (int i = 0; i < stars.length; i++) 
        {
            Star star = stars[i];
            star.update();
            float size = map(amplitude, 0, 1, 30, 200);
            star.size = size;
            star.display(bands);
        }

        popMatrix();
        drawNotes(bands);
    }

    public void drawNotes(float[] bands)
    {
        pushMatrix();
        stroke(255);
        strokeWeight(4);

        // for drawing the notes
        int staffHeight = 200;
        int staffWidth = width - 300;
        int staffY = height/2 + 50;
        int staffX = width/2 - staffWidth/2;
        int staffSpacing = height / 10;

        int noteSize = width / 25;
        int noteX = staffX + staffWidth/10;
        int noteY = staffY - staffHeight/5;
        int noteDistance = width / 10;

        // for colour of notes
        int frameCount = 0;
        int colourChangeInterval = 20;
        int colour = 0;

        // for drawing the staff on the screen
        strokeWeight(1);
        for (int i = 0; i < 5; i++) {
            int y = staffY - (2 * staffHeight/5) + (i * staffHeight/5);
            line(staffX, y, staffX + staffWidth, y);
        }

    
        // set colour of notes to change 
        if (frameCount % colourChangeInterval == 0) {
            colour = (int) map(bands[2], 0, 255, 0, 255);
        }
        frameCount++;


        smooth();
        strokeWeight(4);
        int noteX2 = noteX + staffSpacing*2;
        int noteY2 = noteY + staffSpacing;

        // Calculate the vertical position of the notes based on the amplitude
        float amplitude = getSmoothedAmplitude();
        float yOffset = map(amplitude, 0, 1, -staffHeight/3, staffHeight/2);

        fill(colour, 255, 255);

        // draw first note
        ellipse(noteX + noteDistance - 10, noteY2 + yOffset, noteSize, noteSize);
        line(noteX + noteDistance + 10, noteY2 + yOffset, noteX + noteDistance, staffY - staffHeight/5 + yOffset);

        ellipse(noteX2 - 10, noteY + (noteDistance/2 + 10) + yOffset, noteSize, noteSize);
        line(noteX2 + 10, noteY + (noteDistance/2 + 10) + yOffset, noteX2, staffY - staffHeight/3 + yOffset);

        // connect note
        line(noteX + noteDistance, noteY2 - staffSpacing + yOffset, noteX2, noteY - staffSpacing/4 + yOffset);

        // another note
        fill(colour, 255, 255);
        
        ellipse((noteX2 - 10) * 2, noteY + (noteDistance/2 + 10) + yOffset, noteSize, noteSize);
        line(noteX2 * 2, noteY + (noteDistance/2 + 10) + yOffset, noteX2 * 2, noteY - staffHeight/15 + yOffset);

        ellipse((noteX + noteDistance - 10) * 2, noteY2 - (noteDistance - 20) + yOffset, noteSize, noteSize);
        line((noteX + noteDistance) * 2, noteY2 - (noteDistance - 20) + yOffset, (noteX + noteDistance) * 2, staffY - staffHeight/2 + yOffset);

        // connect note
        line((noteX + noteDistance) * 2, staffY - staffHeight/2 + yOffset, noteX2 * 2, noteY - staffHeight/15 + yOffset);

        popMatrix();
    }
}

class Star extends PApplet
{

    PApplet p;
    float x;
    float y;
    float size;
    float speed;
    float height;
    float width;
    float rotate_Star;
    int num_point = 5;
    Star[] stars;
    int i = 0;

    int colourIndex = (int) random(3);
    ArrayList<Integer> colours = new ArrayList<Integer>();
    int frameCount = 0;
    int colourChangeInterval = 20; // Change colour every 20 frames

    Star(PApplet p, float x, float y, float size, float speed, float height, float width, Star[] stars) 
    {
        this.p = p;
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;
        this.height = height;
        this.width = width;
        this.rotate_Star = 0;
        this.stars = stars;

        // initialise colours array list
        colours.add(color(230, 0, 255));
        colours.add(color(255, 232, 31));
        colours.add(color(255, 209, 220));
    }

    
    void update() 
    {
        y += speed;
        rotate_Star += 0.01;
        
        // star still on screen, update it
        if (y > height) 
        {
            y = 0;
            x = p.random(width);
            size = p.random(20, 200);
            speed = p.random(1, 4);
        } 
        else if (y < 0) // reset star to fall from the top of the screen again
        {
            y = height;
        }

    }

    void display(float[] bands) 
    {
        p.pushMatrix();
        p.translate(x, y);
        p.rotate(rotate_Star);
        p.scale(size/300);
        p.noStroke();


        // Only change colour every colourChangeInterval frames
        if (frameCount % colourChangeInterval == 0) {
            colourIndex = (colourIndex + 1) % colours.size();
        }
        frameCount++;

        // change colour of stars
        p.fill(colours.get(colourIndex));

        p.beginShape();

        // change number of points for the star based on the fifth audio frequency band value
        num_point = (int) map(bands[4], 0, 255, 5, 10);
        for (int i = 0; i < num_point; i++) 
        {
            // calculate angle of the current point
            float angle = TWO_PI * i / num_point;

            // calculate the angle of first vertex
            float x = cos(angle) * 100;
            float y = sin(angle) * 100;

            // add vertex to shape
            p.vertex(x, y);

            // get next vertex
            angle += TWO_PI / (num_point * 2);
            x = cos(angle) * 50;
            y = sin(angle) * 50;

            // add vertex to shape
            p.vertex(x, y);
        }

        p.endShape();
        p.popMatrix();
    } 

}
