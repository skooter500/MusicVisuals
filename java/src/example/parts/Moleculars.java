package example.parts;



// package example.parts;

// import example.MyVisual;
// import processing.core.PGraphics;

// public class Part5 {
//     MyVisual mv;
//     PGraphics pg;

//     public Part5(MyVisual mv) {
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

public class Moleculars {
    MyVisual mv;
    PGraphics pg;

    public Moleculars(MyVisual mv) {
        this.mv = mv;
        pg = mv.createGraphics(mv.width, mv.height/2);
    }

    public void render() {
        mv.colorMode(mv.HSB);
        pg.beginDraw();
        for (int x = 0; x < mv.width; x++) {
            for (int y = 0; y < mv.height/2; y++) {
                float noiseValue = mv.noise((float) x/100, (float) y/100, mv.getAmplitude()*10);
                int colorValue = mv.color(mv.map(noiseValue, 0, 1, 0, 255), 255, 255);
                pg.stroke(colorValue);
                pg.point(x, y);
            }
        }
        pg.endDraw();
        mv.image(pg, 0, 0);
        mv.image(pg, 0, mv.height/2);
    }
}
