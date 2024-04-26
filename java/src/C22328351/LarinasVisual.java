package C22328351;

import ie.tudublin.VisualException;
import ie.tudublin.Heartbeat;
import java.util.Random;

public class LarinasVisual {

    static Heartbeat HB;
    float rotX, rotY;

    float radius = 200;
    int points = 5;

    static float outerHue;
    static float innerHue;

    static int numSparkles = 100;
    static float sparkleSize = 5;

    public LarinasVisual(Heartbeat HB) {
        this.HB = HB;
        HB.colorMode(HB.HSB, 360, 100, 100);
        rotX = rotY = 0;
    }

    public void draw() {
        HB.background(0);
        HB.pushMatrix();
        HB.lights();

        HB.translate(HB.width / 2, HB.height / 2);

        HB.rotateX(rotX);
        HB.rotateY(rotY);

        float rotationSpeedX = HB.map(HB.getAmplitude(), 0, 1, 0.02f, 0.15f);
        float rotationSpeedY = HB.map(HB.getAmplitude(), 0, 1, 0.02f, 0.3f);

        rotX += rotationSpeedX;
        rotY += rotationSpeedY;

        HB.calculateAverageAmplitude();

        innerHue = HB.map(HB.getAmplitude(), 0, 1, 0, 360);
        outerHue = HB.map(HB.getAmplitude(), 0, 1, 0, 360);

        HB.fill(innerHue, 100, 360);
        HB.stroke(innerHue, 90, 90, 150);
        drawStar(radius, points, innerHue, outerHue);
        HB.strokeWeight(4);

        HB.fill(outerHue, 200, 360);
        HB.stroke(innerHue, 90, 90, 150);
        drawStar(radius, points, innerHue, outerHue);
        HB.strokeWeight(4);

        //Gets the amplitude of the audio and maps it to the star
        //float amplitude = HB.getAmplitude();
        //float radius = this.radius + (amplitude * 200);

        float starSize = radius + HB.sin(HB.frameCount * 0.5f) * 500 * HB.getAmplitude();

        drawStar(starSize, points, outerHue, innerHue);

        drawStar(starSize * 0.5f, points, outerHue, innerHue);

        drawSparkles();
        HB.popMatrix();
        HB.noLights();
    }

    public void drawSparkles() {
        HB.noStroke();
        HB.fill(255);
        float amplitude = HB.getAmplitude();
        int numSparkles = (int) HB.map(amplitude, 0, 1, 100, 10);
        Random random = new Random();
        for (int i = 0; i < numSparkles; i++) {
            float x = random.nextFloat() * HB.width;
            float y = random.nextFloat() * HB.height;
            float sparkleSize = random.nextFloat() * 5;
            float sparkleBrightness = HB.random(200, 255);
            HB.fill(255, sparkleBrightness);
            HB.ellipse(x, y, sparkleSize, sparkleSize);

        }
    }

    public void drawStar(float radius, int points, float innerHue, float outerHue) {
        float angle = HB.TWO_PI / points;
        float starAngle = angle / 2;

        HB.beginShape();
        for (float a = 0; a < HB.TWO_PI; a += angle) {
            float x = HB.cos(a) * radius;
            float y = HB.sin(a) * radius;
            float z1 = radius / 2;
            float z2 = -radius / 2;
            HB.stroke(outerHue, 100, 360); // Use vibrant color with maximum saturation and brightness
            HB.vertex(x, y, z1);
            HB.vertex(0, 0, z2);
            float sx = HB.cos(a + starAngle) * radius / 2;
            float sy = HB.sin(a + starAngle) * radius / 2;
            HB.vertex(sx, sy, z1);
        }
        HB.endShape(HB.CLOSE);

        float innerRadius = radius * 0.06f;
        HB.beginShape();
        for (float a = 0; a < HB.TWO_PI; a += angle) {
            float x = HB.cos(a) * innerRadius;
            float y = HB.sin(a) * innerRadius;
            float z1 = radius / 2;
            float z2 = -radius / 2;
            HB.stroke(innerHue, 100, 100);
            HB.vertex(x, y, z1);
            HB.vertex(0, 0, z2);
            float sx = HB.cos(a + starAngle) * innerRadius / 2;
            float sy = HB.sin(a + starAngle) * innerRadius / 2;
        }
        HB.endShape(HB.CLOSE);

    }

}
