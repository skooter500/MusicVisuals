package C19307776;

import java.util.ArrayList;

public class SceneManager {
	private static ArrayList<Scene> scenes = new ArrayList<Scene>();
	private static Scene currentScene;
	private static int sceneNumber = 0;
	private static int frames = 0;

	public void addScene(Scene scene) {
		scenes.add(scene);
	}

	public void setCurrentScene(int i) {
		currentScene = scenes.get(i);
		sceneNumber = i;
	}
	public void nextScene() {
		sceneNumber = sceneNumber < scenes.size()-1 ? sceneNumber+1 : 0;
		currentScene = scenes.get(sceneNumber);
		frames = 0;
	}

	public void animateScene() {
		ArrayList<Animatable> objects = currentScene.objects;
		for(int i = 0; i < objects.size(); i++) {
			if(objects.get(i).getStartPoint() <= frames) {
				objects.get(i).animate();
			}
		}
		if(frames == currentScene.getSceneLength()) {
			nextScene();
		}else {
			frames++;
		}
	}

	public Scene getCurrentScene() {
		return currentScene;
	}
}
