package C22454222; // Package declaration

import ie.tudublin.*; // Importing necessary classes from the ie.tudublin package
import ddf.minim.AudioPlayer;
// MainVisual class extending Visual class
public class MainVisual extends Visual 
{
    AudioPlayer ap; // Reference to AudioPlayer class
    int visualSwap = 0; // Variable to control visual swapping
    int playTune = 0; // Variable to control playing tune
    int stopPlay = 0; // Variable to control stopping playback
    public float eRadius = 20;
    /*
    AimeeVisual aimee;
    NeilVisual neil;
    JadeVisual jade;
    JasonVisual jason;
    */
    ChrisVisual chris;
    AudioBandsVisual audioBandsVisual; // Instance of AudioBandsVisual class

    public float fCounter = 0; // Variable to store a floating-point counter value
    public int chrisOption = 1; // Variable to store an option for Chris's visual

    // Method to set initial settings
    public void settings() 
    {
        // Setting window size
        fullScreen();
        // fullScreen(P3D,SPAN);
    }

    // Method to perform initial setup
    public void setup() 
    {
        startMinim(); // Starting Minim audio library
        loadAudio("Project.wav"); // Loading audio file
        BeatDetect(); // Initializing beat detection
        colorMode(HSB); // Setting color mode
        /*
        aimee = new AimeeVisual(this);
        neil = new NeilVisual(this);
        jade = new JadeVisual(this);
        jason = new JasonVisual(this);
        */
        chris = new ChrisVisual(this); // Initializing ChrisVisual instance with appropriate constructor
        audioBandsVisual = new AudioBandsVisual(this); // Initializing AudioBandsVisual instance
    }

    // Method to handle key pressed events
    public void keyPressed() 
    {
        if (key == ' ') 
        { // If space bar is pressed
            as.stop();
            as.trigger();
        }
        // If numeric keys 1 to 3 are pressed
        if (keyCode >= '1' && keyCode <= '3') 
        {
            chrisOption = keyCode - '0'; // Update chrisOption with the corresponding numeric value
        }
    }

    // Method to draw visuals
    public void draw() 
    {
        if (playTune == 1) 
        { // If playTune flag is set
            as.stop(); // Stop audio playback
            as.trigger(); // Trigger audio playback
            playTune = 0; // Reset playTune flag
        }

        try 
        {
            calculateFFT(); // Calculate Fast Fourier Transform
        }

        catch (VisualException e) 
        { // Catch any VisualExceptions
            e.printStackTrace(); // Print stack trace of the exception
        }

        calculateFrequencyBands(); // Calculate frequency bands
        calculateAverageAmplitude(); // Calculate average amplitude

        // Switch statement to determine which visual to render
        switch (visualSwap) 
        {
            case 0:
                // aimee.render();
                break;
            case 1:
                // neil.render();
                break;
            case 2:
                // jade.render();
                break;
            case 3:
                // jason.render();
                break;
            case 4:
                chris.render();
                break;
            case 5:
                audioBandsVisual.render(); // Render AudioBandsVisual
                break;
            default:
                background(0); // Set background color to black by default
                break;
        }
    }
}
