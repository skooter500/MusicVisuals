package C19307776;
import C19307776.Scenes.*;

public class Animation extends Visuals {
	SceneManager scenes;

	Animation() {
		
	}

	public void settings() {
		//Creates a fullscreen window
		fullScreen(P3D);
	}
	
	public void setup() {
		background(0);
		//Inits the scene manager
		scenes = new SceneManager();
		//Adds scenes to the animation
		scenes.addScene(new EarthSystem(this));
		scenes.addScene(new MarsSystem(this));
		scenes.setCurrentScene(0);
	}

	public void draw() {
		background(0);
		//Draws the current sceme
		scenes.animateScene();
	}
}
