package ie.tudublin;
import ddf.minim.analysis.BeatDetect;

/*
To create a visual to add to the song:
Create a separate class
Pass the SongVisuals class as a parameter to the constructor
Create an instance of the class in SongVisuals
Call the render function in the draw funciton


*/
public class SongVisuals extends Visual 
{
    // Intro
    AMWaveForm wf;
    LineIntro li;
    TitleCard1 tc1;
    TitleCard2 tc2;
    WaveIntro wi;
    Mountains mt;
    SineWave sw;
    Timer timer;
    BeatDetect beat;


    public void settings()
    {
        size(1024, 500, P3D);
        
        
        // Use this to make fullscreen
        //fullScreen();
    }

    public void setup()
    {
        startMinim();
        loadAudio("Kids.mp3");

        // Controls when visuals appear and disappear according to the time
        timer = new Timer(this);

        wf = new AMWaveForm(this);
        li = new LineIntro(this);
        tc1 = new TitleCard1(this);
        tc2 = new TitleCard2(this);
        wi = new WaveIntro(this);
        mt = new Mountains(this);
        sw = new SineWave(this);
        beat = new BeatDetect();

        beat.setSensitivity(500);
        
    }

    public void keyPressed()
    {
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
            timer.start();
        }
    }

    public void draw()
    {
        background(0);

        try
        {
            // Call this if you want to use FFT data
            calculateFFT(); 
        }
        catch(VisualException e)
        {
            e.printStackTrace();
        }
        // Call this is you want to use frequency bands
       // calculateFrequencyBands(); 

        // Call this is you want to get the average amplitude
        calculateAverageAmplitude();
        /*
        wf.render();
        li.render();
        tc.render();
        wi.render();
        mt.render();
        sw.render();
        */

        // 6 refers to the number of seconds
        if(timer.running && timer.seconds() < 6){
            wi.render();
        }

        if (timer.running && timer.seconds() >= 6 && timer.seconds() + 0.5f < 21){
            
            
        }

        if (timer.running && timer.seconds() >= 6 && timer.seconds() <= 13){
            sw.render();
            tc1.render();
        }

        if (timer.running && timer.seconds() > 13 && timer.seconds() + 0.5f < 21){
            
            li.render();
            wf.render();
            tc2.render();
        }

        if (timer.running && timer.seconds() + 0.5f > 22 && timer.seconds() <= 53){
            mt.render();
            
        }
    }
    
}
