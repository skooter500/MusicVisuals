package ie.tudublin;


// Dependencies
import processing.core.PApplet;


import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.analysis.FFT;

public class Utils extends PApplet {
    // Frequency Functions
    public static float getHighestFrequency(FFT fft) {
        int highestIndex = 0;
        for(int i = 0; i < fft.specSize() / 2; i++) {
            if(fft.getBand(i) > fft.getBand(highestIndex)) {
                highestIndex = i;
            } // End if
        } // End for

        return fft.indexToFreq(highestIndex);
    } // End float getHighestFrequency()

    public static int[] getHighestFrequencyIndex(FFT fft, AudioBuffer audioBuffer) {
        int[] loudestFrequencies = {0, 0, 0};
        fft.forward(audioBuffer);

        // Draw Bass
        // 20 Hz - 261 Hz
        for(int i = 0; i < 12; i++) {
            // pApplet.line(i * scale, windowHeight, i * scale, windowHeight - fft.getBand(i) * 5.0f);
            if(fft.getBand(i) > 60) {
                if(fft.getBand(i) > fft.getBand(loudestFrequencies[0])) {
                    loudestFrequencies[0] = i;
                } // End if
            } // End if

        } // End for

        // Draw Mids
        // 261 Hz - 1046
        for(int i = 12; i < 49; i++) {
            // pApplet.line(i * scale, windowHeight, i * scale, windowHeight - fft.getBand(i) * 5.0f);
            if(fft.getBand(i) > 90) {
                if(fft.getBand(i) > fft.getBand(loudestFrequencies[1])) {
                    loudestFrequencies[1] = i;
                } // End if
            }
        } // End for
    
        // Draw Highs
        for(int i = 49; i < 390; i++) {
            // pApplet.line(i * scale, windowHeight, i * scale, windowHeight - fft.getBand(i) * 5.0f);
            if(fft.getBand(i) > 5) {
                if(fft.getBand(i) > fft.getBand(loudestFrequencies[2])) {
                    loudestFrequencies[2] = i;
                } // End if
            }
        } // End for

        return loudestFrequencies;
    } // End float getHighestFrequency()


    // Wait for X amount of Seconds
    public static void waitFor(long milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }// End void waitFor(float milliSeconds)

    public static void skipSecondsSong(AudioPlayer audioPlayer, float skipSeconds) {
        int milliSeconds = (int) (skipSeconds * 1000);
        audioPlayer.skip(milliSeconds);
    } // End void skipSecondsSongs
} // End class Utils
