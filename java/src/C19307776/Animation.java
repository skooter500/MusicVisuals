package C19307776;
import C19307776.Scenes.*;
import C19307776.audioSystem.*;

public class Animation extends Visuals {
	SceneManager scenes;

	Animation() {
		
	}

	public void settings() {
		//Creates a fullscreen window
		fullScreen(P3D);
		//size(500, 300);
	}
	
	public void setup() {
		hint(DISABLE_TEXTURE_MIPMAPS);
		background(0);
		//Inits the scene manager
		scenes = new SceneManager();
		//Adds scenes to the animation
		scenes.addScene(new Takeoff(this));
		scenes.addScene(new EarthSystem(this));
		scenes.addScene(new MarsSystem(this));
		scenes.setCurrentScene(0);

		//Loads and plays the music
		try {
			Song donau = new Song("assets/donau.wav");
			donau.play();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void draw() {
		background(0);
		//Draws the current sceme
		scenes.animateScene();
	}
}
