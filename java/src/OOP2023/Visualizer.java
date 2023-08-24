package OOP2023;

import ie.tudublin.Visual;

public class Anne extends Visual {
    private Start anne;

    // Constants
    private static final int PETAL_COUNT = 5;
    private static final int PETAL_ANGLE = 72;

    public Anne(Start anne) {
        this.anne = anne;
    }

    public float getAmplitude() {
        float[] flowers = getAudioBuffer().toArray();
        float sum = 0;
        for (float flower : flowers) {
            sum += flower * flower;
        }
        return (float) Math.sqrt(sum / flowers.length);
    }

    public void drawCloud(int a, int b, int c, float d) {
        anne.noStroke();
        anne.fill(255);

        int[] ellipseOffsetsX = {530, 510, 460, 480, 470, 510, 490};
        int[] ellipseOffsetsY = {295, 305, 295, 305, 285, 285, 280};
        int[] ellipseWidths = {60, 40, 40, 40, 40, 40, 40};
        int[] ellipseHeights = {30, 30, 30, 30, 30, 30, 30};

        for (int i = 0; i < ellipseOffsetsX.length; i++) {
            anne.ellipse(a + ellipseOffsetsX[i], b + ellipseOffsetsY[i], c + ellipseWidths[i], d + ellipseHeights[i]);
        }
    }

    public void drawGround() {
        anne.stroke(114, 180, 58);
        anne.fill(65, 101, 34);
        anne.rect(0, 850, 2000, 500);
    }

    public void drawSun() {
        anne.pushMatrix();
        anne.smooth();
        anne.noSmooth();
        anne.fill(245, 187, 87);
        anne.ellipse(224, 184, 220, 220);
        anne.popMatrix();
    }

    private void drawFlower(int translateX, int translateY, float rotateFactor, int[] flowerColor, int[] flowerCenterColor) {
        float amplitude = getAmplitude();
        anne.pushMatrix();
        anne.smooth();
        anne.noStroke();
        anne.translate(anne.width / 2, anne.height / 2);
        anne.translate(translateX, translateY);
        anne.rotate(radians(amplitude * rotateFactor));

        anne.fill(flowerColor[0], flowerColor[1], flowerColor[2]);
        for (int i = 0; i < PETAL_COUNT; i++) {
            anne.ellipse(0, -40, 50, 50);
            anne.rotate(radians(PETAL_ANGLE));
        }

        anne.pushMatrix();
        anne.rotate(-radians(amplitude * rotateFactor));
        anne.stroke(41, 63, 22);
        anne.strokeWeight(3);
        anne.line(0, 44, 0, 255);
        anne.popMatrix();

        anne.stroke(flowerCenterColor[0], flowerCenterColor[1], flowerCenterColor[2]);
        anne.fill(flowerCenterColor[0], flowerCenterColor[1], flowerCenterColor[2]);
        anne.ellipse(0, 0, 50, 50);

        anne.popMatrix();
    }

    public void drawFlowers() {
        drawFlower(-100, 100, 180, new int[]{191, 155, 48}, new int[]{141, 85, 36});
        drawFlower(-200, -100, 360, new int[]{141, 158, 199}, new int[]{195, 227, 220});
        drawFlower(200, 200, 60, new int[]{246, 234, 219}, new int[]{255, 200, 109});
        drawFlower(-400, 50, 250, new int[]{149, 125, 173}, new int[]{255, 223, 211});
    }

    public void render() {
        float avg = 0;
        for (int i = 0; i < ab.size(); i++) {
            avg += Math.abs(ab.get(i));
        }
        avg /= ab.size();
        float smoothedavg = lerp(0, avg, 0.01f);

        anne.colorMode(RGB);
        drawSun();
        drawFlowers();
        drawGround();

        anne.fill(0, 10);
        anne.fill(255);
        anne.noStroke();

        if (anne.frameCount % 30 == 0) {
            anne.ellipse(anne.width, anne.height, smoothedavg, smoothedavg);
        }

        anne.translate(anne.width / 2, anne.height / 2, 0);

        // Cloud rendering calls
        drawCloud(-600, -544, 10, 30 * smoothedavg * 200);
        drawCloud(-800, -644, 10, 30 * smoothedavg * 200);
        drawCloud(-1000, -644, 10, 30 * smoothedavg * 200);
        drawCloud(-100, -544, 10, 30 * smoothedavg * 200);
        drawCloud(-300, -600, 10, 30 * smoothedavg * 200);
        drawCloud(0, -644, 10, 30 * smoothedavg * 200);

    }
}
