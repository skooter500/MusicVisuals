package C19307776;
import C19307776.Scenes.*;
import C19307776.audioSystem.*;

public class Animation extends Visuals {
	SceneManager scenes;
	Song donau;

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
		scenes.addScene(new Intro(this));
		scenes.addScene(new Takeoff(this));
		scenes.addScene(new TakeOff2(this));
		scenes.addScene(new Curvature(this));
		scenes.addScene(new EarthSystem(this));
		scenes.addScene(new MarsSystem(this));
		scenes.setCurrentScene(0);

		//Loads and plays the music
		try {
			donau = new Song("assets/donau.wav", scenes.animationLength());
			donau.play();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed() {
		if(keyCode == RIGHT) {
			donau.skipTo(scenes.sceneEndPosition());
			scenes.nextScene();
		}
	}

	public void draw() {
		background(0);
		//Draws the current sceme
		scenes.animateScene();
	}
}
