package C19307776;
import java.util.ArrayList;
/*
	This class is the parent class for each scene.
	It handles stuff such as scene duration and the
	objects in the scene.
*/
public class Scene extends Visuals {
	protected int sceneLength = 0;


	protected ArrayList<Animatable> objects = new ArrayList<Animatable>();

	public void setup() {
	}

	public void addToScene(Animatable object) {
		objects.add(object);
	}

	public int getSceneLength() {
		return sceneLength;
	}
	
	protected void animateScene(){};
}
