package ie.tudublin;

// Dependencies
import processing.core.PApplet;

public class Utils extends PApplet {



    // Wait for X amount of Seconds
    public static void waitFor(long milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }// End void waitFor(float milliSeconds)
} // End class Utils
