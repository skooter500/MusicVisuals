package C19307776;
import C19307776.Animation;

public class Main {

    void startAnimation() {
        String[] a = {"MAIN"};
        processing.core.PApplet.runSketch(a, new Animation());
    }
    public static void main(String[] args) {
        Main main = new Main();
        main.startAnimation();
    }
}
