package example.parts.backgrounds;


// import example.MyVisual;
// import processing.core.PGraphics;

// public class MolecularsBC {
//     MyVisual mv;
//     PGraphics pg;

//     public MolecularsBC(MyVisual mv) {
//         this.mv = mv;
//         pg = mv.createGraphics(mv.width, mv.height);
//     }
    
//     public void render() {
//         mv.colorMode(mv.HSB);
//         pg.beginDraw();
//         for (int x = 0; x < mv.width; x += 4) {
//             for (int y = 0; y < mv.height; y += 4) {
//                 float noiseValue = mv.noise((float) x/100, (float) y/100, mv.getAmplitude()*10);
//                 int colorValue = mv.color(mv.map(noiseValue, 0, 1, 0, 255), 255, 255);
//                 pg.stroke(colorValue);
//                 pg.point(x, y);
//             }
//         }
//         pg.endDraw();
//         mv.image(pg, 0, 0);
//     }
// }

import example.MyVisual;
import processing.core.PGraphics;

public class MolecularsBC {
    MyVisual mv;
    PGraphics pg;

    public MolecularsBC(MyVisual mv) {
        this.mv = mv;
        pg = mv.createGraphics(mv.width, mv.height);
    }

    public void render() {
        mv.colorMode(mv.HSB);

        pg.beginDraw();
        pg.background(0);
        pg.noStroke();

        for (int x = 0; x < mv.width; x += 5) {
            for (int y = 0; y < mv.height; y += 5) {
                float distance = dist(x, y, mv.width/2, mv.height/2);
                float size = mv.map(distance, 0, mv.width/2, 15, 0);

                float noiseValue = mv.noise(x/100f, y/100f, mv.getAmplitude()*10);
                int colorValue = mv.color(mv.map(noiseValue, 0, 1, 0, 255), 255, 255);

                pg.fill(colorValue);
                pg.rect(x-size/2, y-size/2, size, size);
            }
        }

        pg.endDraw();
        mv.image(pg, 0, 0);
    }

    private float dist(float x1, float y1, float x2, float y2) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        return mv.sqrt(dx*dx + dy*dy);
    }
}
