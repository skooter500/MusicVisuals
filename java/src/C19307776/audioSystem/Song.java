package C19307776.audioSystem;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Song {
	//Store current position
	Long currentFrame;
	Clip clip;

	//status of clip
	String status;

	AudioInputStream audioInputStream;

	//Constructor to init streams and clip - takes file path as a parameter
	public Song(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		//create AudioInputStream object
		audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

		//creates reference to clip
		clip = AudioSystem.getClip();
		//this opens the audioInputStream to the clip
		clip.open(audioInputStream);
		//This loops the clip infinitely
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	//Plays the audio clip
	public void play() {
		clip.start();
	}
}
