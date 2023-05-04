package C21725659;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;
import processing.core.PFont;
import processing.core.PVector;
import Global.*;

import java.util.ArrayList;

public class DemiAudioVisualiser extends Visual {

    // Sphere variables
    PVector[][] globe;
    int r = 200;
    int total = 25;
    float angleX = 0;
    float angleY = 0;

    ArrayList<Particle> particles = new ArrayList<Particle>();
    ArrayList<MusicalNoteSprite> noteSprites = new ArrayList<MusicalNoteSprite>();
    ArrayList<textSprites> notes = new ArrayList<textSprites>();
    int currentTime = 0;
    SubtitleHandler subtitleHandler;
    ArrayList<Subtitle> subtitles;
    int currentSubtitleIndex = 0;
    int previousSubtitleIndex = -1;
    PFont subtitleFont;
    float strobeTimer = 0;
    float strobeInterval = 0;
    NoteManager noteManager;
    String words[];

    public void settings() {
        size(1280, 720, P3D);
    }

    float eyeX = 0;
    float eyeY = -500;
    float eyeZ = 500;
    float centerX = 0;
    float centerY = 0;
    float centerZ = 0;
    float upX = 0;
    float upY = 1;
    float upZ = 0;

    boolean[] keys = new boolean[128];

    public void keyPressed() {
        if (key >= 0 && key < keys.length) {
            keys[key] = true;
        }
    }

    public void keyReleased() {
        if (key >= 0 && key < keys.length) {
            keys[key] = false;
        }
    }

    public void updateCamera() {
        float cameraMoveAmount = 5;
        float cameraRotAmount = 0.01f;

        if (keys[UP]) {
            eyeY += cameraMoveAmount;
        }
        if (keys[DOWN]) {
            eyeY -= cameraMoveAmount;
        }
        if (keys[LEFT]) {
            eyeX += cameraMoveAmount;
        }
        if (keys[RIGHT]) {
            eyeX -= cameraMoveAmount;
        }
        if (keys['Q'] || keys['q']) {
            centerX += cameraMoveAmount;
        }
        if (keys['A'] || keys['a']) {
            centerX -= cameraMoveAmount;
        }
        if (keys['W'] || keys['w']) {
            centerY += cameraMoveAmount;
        }
        if (keys['S'] || keys['s']) {
            centerY -= cameraMoveAmount;
        }
        if (keys['E'] || keys['e']) {
            centerZ += cameraMoveAmount;
        }
        if (keys['D'] || keys['d']) {
            centerZ -= cameraMoveAmount;
        }
    }

    int getNoteTypeFromAmplitude(float amplitude) {
        if (amplitude >= 0 && amplitude < 1.0 / 3.0) {
            return 0;
        } else if (amplitude >= 1.0 / 3.0 && amplitude < 2.0 / 3.0) {
            return 1;
        } else {
            return 2;
        }
    }

    public void setup() {
        // Sphere setup
        globe = new PVector[total + 1][total + 1];

        for (int i = 0; i < total + 1; i++) {
            for (int j = 0; j < total + 1; j++) {
                float lat = map(i, 0, total, 0, PI);
                float lon = map(j, 0, total, 0, TWO_PI);
                float x = r * sin(lat) * cos(lon);
                float y = r * sin(lat) * sin(lon);
                float z = r * cos(lat);
                globe[i][j] = new PVector(x, y, z);
            }
        }

        textSize(20);
        subtitleFont = createFont("Arial", 20);
        textFont(subtitleFont);
        colorMode(HSB);
        SubtitleHandler subtitleHandler = new SubtitleHandler(this);
        NoteManager noteManager = new NoteManager(this);
        subtitles = subtitleHandler.parseSrt("InitialD-KillingMyLove.mp3.srt");
        startMinim();
        loadAudio("InitialD-KillingMyLove.mp3");
        getAudioPlayer().play();
        getAudioPlayer().cue(60000);
    }

    float radius = 200;
    float rot = 0;

    public void draw() {
        calculateAverageAmplitude();
        try {
            calculateFFT();
        } catch (VisualException e) {
            e.printStackTrace();
        }
        calculateFrequencyBands();
        // Strobe effect on background
        if (getSmoothedAmplitude() > 0.1) {
            strobeTimer += getSmoothedAmplitude() * 0.1f;
            if (strobeTimer > strobeInterval) {
                strobeTimer = 0;
                strobeInterval = random(0.1f, 0.5f);
                background(255);
            } else {
                background(0);
            }
        } else {
            background(0);
        }

        noFill();
        stroke(255);
        lights();
        stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        updateCamera();
        camera(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        rot += getAmplitude() / 8.0f;
        textAlign(CENTER, CENTER);
        rotateY(rot);

        displayCurrentSubtitle();

        // // Draw fractical tree
        // pushMatrix();
        // drawBranch(200);
        // popMatrix();

        
    // Sphere drawing code
    pushMatrix();
    translate(width / 2, height / 2, 10);
    rotateX(angleX);
    rotateY(angleY);

    for (int i = 0; i < total; i++) {
        beginShape(TRIANGLE_STRIP);
        for (int j = 0; j < total + 1; j++) {
            PVector v1 = globe[i][j];
            vertex(v1.x, v1.y, v1.z);
            PVector v2 = globe[i + 1][j];
            vertex(v2.x, v2.y, v2.z);
        }
        endShape();
    }

    angleX += 0.005;
    angleY += 0.006;
    popMatrix();

    // Frequency bands drawing code (remains the same)
    float bands[] = getSmoothedBands();
    for (int i = 0; i < bands.length; i++) {
        float theta = map(i, 0, bands.length, 0, TWO_PI);
        stroke(map(i, 0, bands.length, 0, 255), 255, 255);
        float x = sin(theta) * radius;
        float z = cos(theta) * radius;
        float h = bands[i];
        pushMatrix();
        translate(x, -h / 2, z);
        rotateY(theta);
        box(50, h, 50);
        popMatrix();
    }

        // Particle system
        if (getAmplitude() > 0.1) {
            PVector position = new PVector(0, 0, 0);
            PVector velocity = PVector.random3D().mult(getAmplitude() * 200);
            float size = map(getAmplitude(), 0, 1, 5, 20);
            int color = color(random(255), random(255), random(255));
            float lifespan = random(20, 100);
            particles.add(new Particle(this, position, velocity, size, color, lifespan));
        }
        // Note system
        if (getAmplitude() > 0.1) {
            PVector position = new PVector(0, 0, 0);
            float size = map(getAmplitude(), 0, 1, 5, 20);
            int noteType = (int) random(0, 2); // Randomly choose between noteType 0 and 1
            float lifetime = random(20, 100);
            noteSprites.add(new MusicalNoteSprite(this, position, size, noteType, lifetime));
        }

        for (int i = particles.size() - 1; i >= 0; i--) {
            Particle p = particles.get(i);
            p.update();
            p.display();

            if (p.isDead()) {
                particles.remove(i);
            }
        }
        for (int i = noteSprites.size() - 1; i >= 0; i--) {
            MusicalNoteSprite noteSprite = noteSprites.get(i);
            noteSprite.display(this);
            boolean expired = noteSprite.update(1); // You can adjust the deltaTime value accordingly

            if (expired) {
                noteSprites.remove(i);
            }
        }

    }

    void drawBranch(float len) {
        float amplitudeScaling = map(getSmoothedAmplitude(), 0, 1, 0.5f, 2);
        len *= amplitudeScaling;
        line(0, 0, 0, -len);
        translate(0, -len);

        if (len > 4) {
            pushMatrix();
            rotate(QUARTER_PI / 2 * amplitudeScaling);
            drawBranch(len * 0.67f);
            popMatrix();

            pushMatrix();
            rotate(-QUARTER_PI / 2 * amplitudeScaling);
            drawBranch(len * 0.67f);
            popMatrix();
        }
    }

    public void displayCurrentSubtitle() {
        int playPosition = getAudioPlayer().position();
        int totalTimeInSeconds = playPosition / 1000;
        int subtitleIndex = -1;

        for (int i = 0; i < subtitles.size(); i++) {
            Subtitle subtitle = subtitles.get(i);
            if (totalTimeInSeconds >= subtitle.getStart() / 1000.0f
                    && totalTimeInSeconds <= subtitle.getEnd() / 1000.0f) {
                subtitleIndex = i;
                break;
            }
        }

        if (subtitleIndex != -1 && subtitleIndex != currentSubtitleIndex) {
            currentSubtitleIndex = subtitleIndex;

            // This is the code in the middle
            Subtitle currentSubtitle = subtitles.get(currentSubtitleIndex);
            words = currentSubtitle.getText().split("\\s+");
            notes.clear(); // Clear existing text sprites

            for (String word : words) {
                PVector position = new PVector(random(width), random(height));
                PVector velocity = new PVector(random(-1, 1), random(-1, 1));
                int textSize = 24;
                int color = color(255, 255, 255); // white text
                notes.add(new textSprites(position, velocity, word, textSize, color));
            }
        }

        if (currentSubtitleIndex >= 0 && currentSubtitleIndex < subtitles.size()) {
            // Display the subtitle text on the screen
            String currentSubtitle = subtitles.get(currentSubtitleIndex).getText();

            textAlign(CENTER);
            fill(255);
            text(currentSubtitle, 0, -300); // Adjust the position to be centered and close to the top
            noFill();
        }

        // Update and display text sprites
        for (int i = notes.size() - 1; i >= 0; i--) {
            textSprites textSprite = notes.get(i);
            textSprite.display(this);
            textSprite.update();
        }
    }
}