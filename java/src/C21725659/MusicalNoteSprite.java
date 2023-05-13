package C21725659;

import processing.core.PApplet;
import processing.core.PVector;

class MusicalNoteSprite {
    PVector position;
    float size;
    int noteType;
    float lifetime;
    float remainingLifetime;
  
    MusicalNoteSprite(PApplet parent, PVector position, float size, int noteType, float lifetime) {
        this.position = position;
        this.size = size;
        this.noteType = noteType;
        this.lifetime = lifetime;
        this.remainingLifetime = lifetime;
    }

    void display(PApplet parent) {
        parent.pushMatrix();
        parent.translate(position.x, position.y, position.z);
        parent.rotateY(parent.frameCount * 0.01f);
        parent.scale(size);
        parent.noStroke();
        parent.fill(255);
        switch (noteType) {
            case 0:
                displayQuarterNote(parent);
                break;
            case 1:
                displayEighthNote(parent);
                break;
            default:
                break;
        }
        parent.popMatrix();
    }

    void displayQuarterNote(PApplet parent) {
        parent.sphere(size * 0.25f);
        drawCylinder(parent, size * 0.125f, size);
    }

    void displayEighthNote(PApplet parent) {
        parent.sphere(size * 0.125f);
        drawCylinder(parent, size * 0.0625f, size);
    }

    void drawCylinder(PApplet parent, float r, float h) {
        int sides = 16;
        parent.beginShape(PApplet.QUAD_STRIP);
        for (int i = 0; i <= sides; i++) {
            float angle = PApplet.TWO_PI / sides * i;
            float x = PApplet.cos(angle) * r;
            float y = PApplet.sin(angle) * r;
            parent.vertex(x, y, 0);
            parent.vertex(x, y, h);
        }
        parent.endShape();
    }

    boolean update(float deltaTime){
        remainingLifetime -= deltaTime;
        return remainingLifetime <= 0;
    }
}

