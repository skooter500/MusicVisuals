package C21725659;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

class NoteManager {
    ArrayList<MusicalNoteSprite> notes;
    PApplet parent;

    NoteManager(PApplet parent) {
        this.parent = parent;
        notes = new ArrayList<>();
    }

    void update(float amplitude) {
        for (int i = notes.size() - 1; i >= 0; i--) {
            MusicalNoteSprite note = notes.get(i);
            if (note.update(parent.frameRate)) {
                notes.remove(i);
            }
        }

        if (amplitude > 0.1) {
            float x = parent.random(-parent.width * 0.5f, parent.width * 0.5f);
            float y = parent.random(-parent.height * 0.5f, parent.height * 0.5f);
            float z = parent.random(-100, 100);
            PVector position = new PVector(x, y, z);
            float size = parent.random(1, 5);
            int noteType = (int) parent.random(0, 2);
            float lifetime = parent.random(60, 240);
            notes.add(new MusicalNoteSprite(parent, position, size, noteType, lifetime));
        }
    }

    void display() {
        for (MusicalNoteSprite note : notes) {
            note.display(parent);
        }
    }
}
