package C19307776;

import java.util.ArrayList;

/*
	This class handles the loading and switching of scenes
	in the animation.
*/

public class SceneManager {
	//List of scenes
	private static ArrayList<Scene> scenes = new ArrayList<Scene>();
	//The current scene
	private static Scene currentScene;
	//The current number of the Scene
	private static int sceneNumber = 0;
	//The current frame of the scene
	private static int frames = 0;

	protected boolean paused = false;

	public void addScene(Scene scene) {
		scenes.add(scene);
	}

	public void setCurrentScene(int i) {
		//Sets the current scene to the one at index i
		currentScene = scenes.get(i);
		sceneNumber = i;
	}
	public int getCurrentScene() {
		return sceneNumber;
	}
	public void nextScene() {
		//Checks if playing last scene and changes scene to next
		if(sceneNumber < scenes.size()-1) {
			sceneNumber++;
			currentScene = scenes.get(sceneNumber);
			frames = 0;
		}
	}
	public void previousScene() {
		//Checks if playing last scene and changes scene to next
		if(sceneNumber > 0) {
			sceneNumber--;
			currentScene = scenes.get(sceneNumber);
			frames = 0;
		}
	}

	public void animateScene() {
		//Gets list of objects in scene
		ArrayList<Animatable> objects = currentScene.objects;
		//Loops through objects in the scene
		for(int i = 0; i < objects.size(); i++) {
			//animates / renders the object if it is supposed
			// to be on the screen at the given frame
			if(objects.get(i).getStartPoint() <= frames) {
				objects.get(i).animate();
			}
		}
		//Checks if the current scene frame equals the length of the scene
		//Then either switches the scene or increments the frame counter
		if(frames == currentScene.getSceneLength()) {
			nextScene();
		}else {
			frames++;
		}
	}

	public int animationLength() {
		int animLength = 0;
		for(Scene scene: scenes) {
			animLength+=scene.getSceneLength();
		}
		return animLength;
	}

	public int sceneStartPosition() {
		int scenesLength = 0;
		for(int i = 0; i < scenes.size(); i++) {
			if(scenes.get(i) != scenes.get(sceneNumber-1)) {
				scenesLength+=scenes.get(i).sceneLength;
			}else {
				break;
			}
		}
		return scenesLength/60;
	}

	public int sceneEndPosition() {
		int scenesLength = 0;
		for(Scene scene: scenes) {
			if(scene != currentScene) {
				scenesLength+=scene.sceneLength;
			}else {
				scenesLength+=scene.sceneLength;
				break;
			}
		}
		return scenesLength/60;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	/*//Returns the currently animating scene
	public Scene getCurrentScene() {
		return currentScene;
	}*/
}
