package C19307776;

import java.util.ArrayList;

public class SceneManager {
    private static ArrayList<Scene> scenes = new ArrayList<Scene>();
    private static Scene currentScene;
    private static int sceneNumber = 0;

    public void addScene(Scene scene) {
        scenes.add(scene);
    }

    public void setCurrentScene(int i) {
        currentScene = scenes.get(i);
        sceneNumber = i;
    }
    public void nextScene() {
        //if(sceneNumber < scenes.size())
        sceneNumber = sceneNumber < scenes.size()-1 ? sceneNumber+1 : 0;
        currentScene = scenes.get(sceneNumber);
    }

    public Scene getCurrentScene() {
        return currentScene;
    }
}
