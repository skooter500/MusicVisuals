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

	public void addScene(Scene scene) {
		scenes.add(scene);
	}

	public void setCurrentScene(int i) {
		//Sets the current scene to the one at index i
		currentScene = scenes.get(i);
		sceneNumber = i;
	}
	public void nextScene() {
		//Checks if playing last scene and changes scene to next
		sceneNumber = sceneNumber < scenes.size()-1 ? sceneNumber+1 : 0;
		currentScene = scenes.get(sceneNumber);
		frames = 0;
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
	//Returns the currently animating scene
	public Scene getCurrentScene() {
		return currentScene;
	}
}
