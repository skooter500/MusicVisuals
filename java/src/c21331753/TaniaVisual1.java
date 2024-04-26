package c21331753;

import ie.tudublin.CombinedVisual;
import processing.core.PApplet;

public class TaniaVisual1 extends PApplet {
    CombinedVisual cv;
    int cols, rows;
    int resolution = 20; // Change the resolution to 1 for a grid covering the entire screen
    boolean[][] grid;
    boolean[][] next;

    public TaniaVisual1(CombinedVisual cv) {
        this.cv = cv;
    }

    public void render() {
        cv.calculateAverageAmplitude();
        float amplitude = cv.getSmoothedAmplitude();
        cv.background(0);
        cv.colorMode(HSB, 360, 100, 100);

        // Call setup method to initialize the Game of Life grid
        setup();
        updateGrid();
        drawGrid();

        float eyeSize = 300; // Increase eye size

        // Draw eyes in the center of the screen
        float centerX = cv.width / 2;
        float centerY = cv.height / 2;
        cv.fill(255); // Eye color (white)
        cv.stroke(255); // Eye stroke (white)
        drawEye(centerX - 200, centerY, eyeSize); // Left eye
        drawEye(centerX + 200, centerY, eyeSize); // Right eye
    }



    private void drawEye(float x, float y, float size) {
        // Draw outer eye
        cv.strokeWeight(2);
        cv.fill(255); // Eye color (white)
        cv.ellipse(x, y, size, size);

        // Calculate pupil movement based on amplitude
        float pupilDiameter = size / 4;
        float pupilX = map(cv.getSmoothedAmplitude(), 0, 1, x - 100, x + 300); // Map amplitude to pupil's diameter range

        // Calculate pupil color based on amplitude
        float pupilColor = map(cv.getSmoothedAmplitude(), 0, 1, 0, 360); // Map amplitude to hue
        
        // Draw pupils
        cv.fill(pupilColor, 80, 80); // Set pupil color
        float bounceOffset = map(sin(frameCount * 0.5f), -1, 1, -size / 2, size / 2); // Calculate bouncing offset
        cv.ellipse(pupilX, y + bounceOffset*2, pupilDiameter, pupilDiameter); // Draw pupil at calculated X position with bouncing effect
    }

    public void setup() {
        cols = width;
        rows = height;
        grid = new boolean[cols][rows];
        next = new boolean[cols][rows];
        // Initialize grid randomly
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                grid[i][j] = random(1) > 0.5;
            }
        }
    }

    private void updateGrid() {
        // Compute next generation based on rules
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                int neighbors = countNeighbors(grid, i, j);
                if (grid[i][j]) {
                    next[i][j] = (neighbors == 2 || neighbors == 3);
                } else {
                    next[i][j] = (neighbors == 3);
                }
            }
        }
        // Swap grids
        boolean[][] temp = grid;
        grid = next;
        next = temp;
    }

    private int countNeighbors(boolean[][] grid, int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int col = (x + i + cols) % cols;
                int row = (y + j + rows) % rows;
                if (grid[col][row]) {
                    count++;
                }
            }
        }
        if (grid[x][y]) {
            count--;
        }
        return count;
    }

    private void drawGrid() {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                float x = i * resolution;
                float y = j * resolution;
                float hue = random(360); // Generate a random hue value
                cv.fill(hue, 80, 80); // Set fill color using the random hue
                cv.stroke(255);
                cv.rect(x, y, resolution - 1, resolution - 1);
            }
        }
    }
}
