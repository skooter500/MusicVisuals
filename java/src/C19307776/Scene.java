package C19307776;
import java.util.ArrayList;

public class Scene extends Visuals {
	protected Visuals v;
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
