package com.C21394933.drawObjects;

import ie.tudublin.DrawObjectAbstractClass;
import processing.core.PApplet;

public class SubmitButton extends DrawObjectAbstractClass {
    private int x;
    private int y;
    private int rectSizeX = 200;
    private int rectSizeY = 50;
    private boolean submitState = false;


    // Constructor
    public SubmitButton(PApplet pApplet, int x, int y) {
        this.pApplet = pApplet;
        this.x = x;
        this.y = y;
    } // End WaveFormVisualize Constructor

    public void render() {
        drawButton();
        onClick();
    }

    private void drawButton() {
        pApplet.pushMatrix();
        pApplet.pushStyle();

        // Draw button
        pApplet.stroke(255);
        pApplet.fill(190);

        if(hoverRect()) pApplet.fill(150);
        if(submitState) pApplet.fill(0,150, 0);
        pApplet.rect(x, y, rectSizeX, rectSizeY);
        textDraw();

        pApplet.popMatrix();
        pApplet.popStyle();
    }

    private void textDraw() {
        pApplet.pushMatrix();
        pApplet.pushStyle();

        // Draw button
        pApplet.fill(255);
        pApplet.textAlign(PApplet.CENTER);
        pApplet.text("Submit", x + (rectSizeX / 2), y + (rectSizeY / 2) + 8);

        pApplet.popMatrix();
        pApplet.popStyle();
    }

    private boolean hoverRect() {
        if (pApplet.mouseX >= x && pApplet.mouseX <= x+rectSizeX && pApplet.mouseY >= y && pApplet.mouseY <= y+rectSizeY) {
            return true;
        } else {
            return false;
        }
    }

    private boolean onClick() {
        if (pApplet.mouseX >= x && pApplet.mouseX <= x+rectSizeX && pApplet.mouseY >= y && pApplet.mouseY <= y+rectSizeY) {
            if(!pApplet.mousePressed || submitState) return false;
            submitState = true;
            return true;
        } 
        else {
            return false;
        }
    }

    public void setSubmitStateTrue() {
        this.submitState = true;
    }

    public void setSubmitStateFalse() {
        this.submitState = false;
    }

    public boolean getSubmitState() {
        return submitState;
    }
}
