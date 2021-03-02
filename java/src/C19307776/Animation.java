package C19307776;
import C19307776.Scenes.*;

public class Animation extends Visuals {
	SceneManager scenes;

	Animation() {
		
	}

	public void settings() {
		fullScreen(P3D);
	}
	
	public void setup() {
		background(0);
		scenes = new SceneManager();
		scenes.addScene(new EarthSystem(this));
		scenes.addScene(new MarsSystem(this));
		scenes.setCurrentScene(0);
	}

	public void keyPressed() {
		if(keyCode == ' ') {
			scenes.nextScene();
		}
	}

	public void draw() {
		background(0);
		scenes.animateScene();
		//scenes.getCurrentScene().animateScene();
	}
}
