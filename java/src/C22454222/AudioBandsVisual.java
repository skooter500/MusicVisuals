package C22454222; // Package declaration
import ie.tudublin.*; // Importing necessary classes from the ie.tudublin package
import ddf.minim.AudioBuffer; // Importing AudioBuffer class from the ddf.minim package

// This is an example of a visual that uses the audio bands
public class AudioBandsVisual extends Visual // Class declaration, extending Visual class
{
    MainVisual mv; // Reference to MainVisual class
    AudioBuffer ab; // Reference to AudioBuffer class
    float[] lerpedBuffer; // Array to store lerped audio buffer values
    float smoothedAmplitude = 0; // Variable to store smoothed amplitude value
    
    // Constructor for AudioBandsVisual class
    public AudioBandsVisual(MainVisual mv)
    {
        this.mv = mv; // Initializing MainVisual reference
        ab = mv.getAudioBuffer(); // Getting audio buffer from MainVisual
        lerpedBuffer = new float[ab.size()]; // Initializing lerpedBuffer with size of audio buffer
    }

    // Method to render the visual
    public void render()
    {
        mv.beat.detect(mv.as.mix); // Detecting beat using MainVisual's AudioSum
        mv.beat.detectMode(0); // Setting beat detection mode
        mv.background(0); // Setting background color to black
        float average = 0; // Variable to store average value
        float sum = 0; // Variable to store sum of audio buffer values

        // Calculating sum of absolute values of audio buffer
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            sum += abs(ab.get(i)); // Adding absolute value of each audio buffer element to sum
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f); // Applying linear interpolation to smooth audio buffer
        }
        
        average = sum / (float) ab.size(); // Calculating average value
        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f); // Applying linear interpolation to smooth amplitude
        
        // Draw the visual using lerpedBuffer
        for(int i = 0; i < ab.size(); i ++)
        {
            float c = map(i, 0, ab.size(), 0, 255); // Mapping index to color value
            mv.stroke(c, 255, 255); // Setting stroke color based on mapped value
            float f = lerpedBuffer[i] * mv.height / 2 * 4.0f; // Scaling lerped buffer value
            mv.line(i, mv.height / 2 + f, i, mv.height / 2 - f); // Drawing line based on scaled value
        }
    }
}
