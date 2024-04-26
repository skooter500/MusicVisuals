package C22533826;

import ie.tudublin.Heartbeat;
import ie.tudublin.VisualException;

public class menuScreen {

    Heartbeat HB;

    public menuScreen(Heartbeat HB) {
        // Initialize variables.
        this.HB = HB;
    }

    public void renderMenu() {
        HB.background(0);
        HB.textSize(32);
        HB.fill(255);
        HB.text("Press 1 for Noel's Visual", HB.width / 2, HB.height / 2 - 100);
        HB.text("Press 2 for Patrick's Visual: Each arrow key displays a new visual.", HB.width / 2,
                HB.height / 2 - 50);
        HB.text("For Michael's Visual press 3 for Cube visual and 4 for Particle visual.", HB.width / 2, HB.height / 2);
        HB.text("Press 5 for Larina's Visual", HB.width / 2, HB.height / 2 + 50);
    }
}
