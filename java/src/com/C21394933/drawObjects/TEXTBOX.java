package com.C21394933.drawObjects;

import ie.tudublin.DrawObjectAbstractClass;
import processing.core.PApplet;

public class TEXTBOX extends DrawObjectAbstractClass {
    public int X = 0, Y = 0, H = 50, W = 600;
    public int TEXTSIZE = 24;
    
    // COLORS
    public int Background;
    public int Foreground;
    public int BackgroundSelected;
    public int Border;
    
    public boolean BorderEnable = false;
    public int BorderWeight = 1;
    
    public String Text = "";
    public int TextLength = 0;
 
    private boolean selected = false;
    
    TEXTBOX(PApplet pApplet) {
       this.pApplet = pApplet;

       Background = pApplet.color(140, 140, 140);
       Foreground = pApplet.color(0, 0, 0);
       BackgroundSelected = pApplet.color(160, 160, 160);
       Border = pApplet.color(30, 30, 30);

       X = (this.pApplet.width / 2) - 300;
       Y = this.pApplet.height / 2;
    }
    
    void DRAW() {
       // DRAWING THE BACKGROUND
       if (selected) {
            pApplet.fill(BackgroundSelected);
       } else {
            pApplet.fill(Background);
       }
       
       if (BorderEnable) {
            pApplet.strokeWeight(BorderWeight);
            pApplet.stroke(Border);
       } else {
            pApplet.noStroke();
       }
       
        pApplet.rect(X, Y, W, H);
       
       // DRAWING THE TEXT ITSELF
        pApplet.fill(Foreground);
        pApplet.textSize(TEXTSIZE);
        pApplet.text(Text, X + (pApplet.textWidth("a") / 2), Y + TEXTSIZE);
    }
    
    // IF THE KEYCODE IS ENTER RETURN 1
    // ELSE RETURN 0
    boolean KEYPRESSED(char KEY, int KEYCODE) {
       if (selected) {
          if (KEYCODE == (int)PApplet.BACKSPACE) {
             BACKSPACE();
          } else if (KEY == ' ') {
             // SPACE
             addText(' ');
          } else if (KEYCODE == (int)PApplet.ENTER) {
             return true;
          } else {
             // CHECK IF THE KEY IS A LETTER OR A NUMBER
             boolean isKeyCapitalLetter = (KEY >= 'A' && KEY <= 'Z');
             boolean isKeySmallLetter = (KEY >= 'a' && KEY <= 'z');
             boolean isKeyNumber = (KEY >= '0' && KEY <= '9');
       
             if (isKeyCapitalLetter || isKeySmallLetter || isKeyNumber) {
                addText(KEY);
             }
          }
       }
       
       return false;
    }
    
    private void addText(char text) {
       // IF THE TEXT WIDHT IS IN BOUNDARIES OF THE TEXTBOX
       if (pApplet.textWidth(Text + text) < W) {
          Text += text;
          TextLength++;
       }
    }

    public void resetText() {
      TextLength = 0;
      Text = "";
    }
    
    private void BACKSPACE() {
       if (TextLength - 1 >= 0) {
          Text = Text.substring(0, TextLength - 1);
          TextLength--;
       }
    }
    
    // FUNCTION FOR TESTING IS THE POINT
    // OVER THE TEXTBOX
    private boolean overBox(int x, int y) {
       if (x >= X && x <= X + W) {
          if (y >= Y && y <= Y + H) {
             return true;
          }
       }
       
       return false;
    }
    
    void PRESSED(int x, int y) {
       if (overBox(x, y)) {
          selected = true;
       } else {
          selected = false;
       }
    }
 }