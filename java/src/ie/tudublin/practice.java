package ie.tudublin;

// import processing.core.*;
import processing.core.PApplet;
import ddf.minim.*;
import ddf.minim.analysis.FFT;

public class practice extends Visual
{
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    float colour = 0;
    int mode = 0;

    FFT fft;

    Star[] stars;
    

    public void settings()
    {
        size(1024,1024, P3D);
        smooth();
    }

    public void setup()
    {
        colorMode(HSB);
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

    public void keyPressed() {
		if (key >= '0' && key <= '9') {
			mode = key - '0';
		}
		if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
	}

    public void draw()
    {
        background(0);
        noFill();
        noStroke();
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

        for (int i = 0; i < stars.length; i++) {
            Star star = stars[i];
            star.update();
            float size = map(amplitude, 0, 1, 20, 200);
            star.size = size;
            star.display(bands);
        }

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
    }

    
    void update() 
    {
        y += speed;
        rotate_Star += 0.01;
        
        if (y > height) {
            y = 0;
            x = p.random(width);
            size = p.random(20, 200);
            speed = p.random(1, 4);
        } else if (y < 0) {
            y = height;
        }

    }

    int colorIndex = (int) random(3);
    int[] colors = { color(230, 0, 255), color(255, 232, 31), color(255, 209, 220) };
    int frameCount = 0;
    int colorChangeInterval = 20; // Change color every 30 frames
    
    void display(float[] bands) 
    {
        p.pushMatrix();
        p.translate(x, y);
        p.rotate(rotate_Star);
        p.scale(size/300);
        p.noStroke();


        // Only change color every colorChangeInterval frames
        if (frameCount % colorChangeInterval == 0) {
            colorIndex = (colorIndex + 1) % colors.length;
        }
        frameCount++;

        p.fill(colors[colorIndex]);

        p.beginShape();

        num_point = (int) map(bands[4], 0, 255, 5, 10);
        for (int i = 0; i < num_point; i++) {
            float angle = TWO_PI * i / num_point;
            float x = cos(angle) * 100;
            float y = sin(angle) * 100;
            p.vertex(x, y);
            angle += TWO_PI / (num_point * 2);
            x = cos(angle) * 50;
            y = sin(angle) * 50;
            p.vertex(x, y);
        }
        p.endShape();
        p.popMatrix();
    } 

}