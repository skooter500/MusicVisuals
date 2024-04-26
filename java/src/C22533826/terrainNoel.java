package C22533826;

import ie.tudublin.VisualException;
import ddf.minim.analysis.FFT;
import ie.tudublin.Heartbeat;

public class terrainNoel {

    Heartbeat HB;

    int cols, rows;
    int scale = 30; // Determines how large the cubes are.

    int w = 2600;
    int h = 1600;

    // Variables for the terrain.
    float flying = 0;
    float yOffset = 0;
    float xoff = 0;
    float[][] cubeTerrain;

    public terrainNoel(int height, int width, Heartbeat HB) {
        this.HB = HB;

        // Set the number of columns and rows based on the scale, w, and h.
        this.cols = HB.floor(w / scale);
        this.rows = HB.floor(h / scale);

        // Initialize the terrain.
        this.cubeTerrain = new float[cols][rows];
        init_cubeTerrain();
    }

    public void render() {
        HB.stroke(255);
        HB.noFill();
        HB.strokeWeight(0);
        generate_Terrain();

        // Get desired angle for terrain.
        HB.translate(HB.width / 3.8f, HB.height / 1.5f);
        HB.rotateX(HB.PI / 2.5f);
        HB.translate(-HB.width / 2.5f, (-HB.height / 1.5f));

        // Render the terrain, cube by cube.
        render_Terrain();
    }

    public void render_Terrain() {
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                color_Terrain(x, y);
                HB.pushMatrix();
                HB.translate(x * scale, y * scale, cubeTerrain[x][y]);
                HB.box(scale, scale, scale * 8);
                HB.popMatrix();
                yOffset += 0.5;
            }
            xoff += 0.5;
        }
    }

    public void generate_Terrain() {
        HB.calculateAverageAmplitude();
        flying -= 0.025f;
        yOffset = flying;

        // Variables to determine terrain bounds, where it is 'flat', and where it is
        // 'hilly'.
        float middleMax = cols / 3.3f * 2;
        int outerStart = (int) (cols * 0.1);
        int outerEnd = (int) (cols * 0.9);

        for (int y = 0; y < rows; y++) {
            float xoff = 0;
            for (int x = 0; x < cols; x++) {
                // Using amplitude and noise to generate terrain. Creates a pseduorandom terrain
                // initially and we add the amplitude to it for interactivity with the song.
                float noiseVal = HB.noise(xoff, yOffset);
                float amplitude = HB.getSmoothedAmplitude();
                float noiseHeight = HB.map(noiseVal, 0, 1, -100, 100);
                float amplitudeHeight = amplitude * 200;
                float height = noiseHeight + amplitudeHeight;

                if (x > cols / 2.4f && x < middleMax) {
                    cubeTerrain[x][y] = HB.map(HB.noise(xoff, yOffset), 0, 1, -50, -10) + (height) / 2.5f;
                } else if (x < outerStart || x > outerEnd) {
                    cubeTerrain[x][y] = HB.map(HB.noise(xoff, yOffset), 0, 1, -100, 200) + height;
                } else {
                    cubeTerrain[x][y] = HB.map(HB.noise(xoff, yOffset), 0, 1, -100, 150) + height;
                }
                xoff += 0.2;
            }
            yOffset += 0.2;
        }
    }

    public void init_cubeTerrain() {
        // Initializes the terrain to 0.
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                cubeTerrain[x][y] = 0;
            }
        }
    }

    public void color_Terrain(int x, int y) {
        // Colors each cube based on the height of the terrain. Adds depth.
        float hue = 180; // Constant hue.
        float brightness = HB.map(cubeTerrain[x][y], -100, 100, 10, 90); // Map brightness based on height.
        HB.fill(hue, 100, brightness);
        // Uncomment below for a more colorful terrain, matches heart visual/colors.
        // HB.calculateAverageAmplitude();
        // float volume = HB.getSmoothedAmplitude();
        // float hue = HB.map(volume, 0, 1, 0, 255);
    }
}