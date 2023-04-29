package com.C21394933.drawObjects;

import com.C21394933.network.DownloadAIGeneratedImage;
import com.C21394933.network.PostOpenAPI;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.analysis.FFT;
import ie.tudublin.DrawObjectAbstractClass;
import ie.tudublin.MusicVisualizer;
import processing.core.PApplet;

public class AIGenerationMenu extends DrawObjectAbstractClass {
    static int timingsCounter;
    AudioPlayer audioPlayer;

    boolean errorMessage = false;
    boolean downloadErrorMessage = false;
    boolean downloadMessage = false;
    boolean downloadState = false;

    private String imageLink = "";

    // Draw Objects
    TEXTBOX textbox;
    SubmitButton submitButton;

    // Constructor
    public AIGenerationMenu(PApplet pApplet, AudioBuffer audioBuffer, AudioPlayer audioPlayer, FFT fft, int windowWidth, int windowHeight) {
        this.pApplet = pApplet;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.audioBuffer = audioBuffer;
        this.audioPlayer = audioPlayer;
        this.fft = fft;

        this.textbox = new TEXTBOX(pApplet);
        this.submitButton = new SubmitButton(pApplet, (windowWidth / 2) + 80, (windowHeight/ 2) + 100);
    } // End WaveFormVisualize Constructor

    int frame = 0;
    boolean state, pstate;
    int time = -1;
    public void render() {
        this.textbox.DRAW();
        this.submitButton.render();
        textboxLogic();
        checkSubmit();
        renderDownlaodMessage();
        renderDownlaodErrorMessage();
        renderErrorMessage();

        /*
        if(pApplet.keyPressed && timingsCounter == 0) {
            MusicVisualizer.timingsCounter++;
            audioPlayer.play();
            System.out.println("Hello");
        }
        */
    }

    private void renderDownlaodMessage() {
        if(!downloadMessage) return;

        pApplet.pushMatrix();
        pApplet.pushStyle();

        pApplet.fill(255);
        pApplet.textAlign(PApplet.CENTER);
        pApplet.text("Processing AI Generated Image", (windowWidth / 2) + 180, (windowHeight / 2) - 200);

        pApplet.popMatrix();
        pApplet.popStyle();
    }

    private void renderDownlaodErrorMessage() {
        if(!downloadErrorMessage) return;

        pApplet.pushMatrix();
        pApplet.pushStyle();

        pApplet.fill(255);
        pApplet.textAlign(PApplet.CENTER);
        pApplet.text("Unable to download image", (windowWidth / 2) + 180, (windowHeight / 2) - 200);

        pApplet.popMatrix();
        pApplet.popStyle();
    }

    private void renderErrorMessage() {
        if(!errorMessage) return;

        pApplet.pushMatrix();
        pApplet.pushStyle();

        pApplet.fill(255);
        pApplet.textAlign(PApplet.CENTER);
        pApplet.text("Cannot process that prompt", (windowWidth / 2) + 180, (windowHeight / 2) - 200);

        pApplet.popMatrix();
        pApplet.popStyle();
    }

    private void textboxLogic() {
        if(pApplet.mousePressed) {
            this.textbox.PRESSED(this.pApplet.mouseX, this.pApplet.mouseY);
        }

        state = pApplet.keyPressed;
        if( pApplet.millis() > time && time != -1 ){
          state = false;
        }
        if(state != pstate){
          if( state ){
            time = pApplet.millis() + 1000;
            textbox.KEYPRESSED(pApplet.key, pApplet.keyCode);
          } else {
            time = -1;
          }
        }
        pApplet.key = ' ';
        pstate = state; // Update previous state for next loop.
    }

    private void checkSubmit() {
        if(!submitButton.getSubmitState() || downloadState) return;

        downloadState = true;
        System.out.println(textbox.Text);
        queryDALLE(textbox.Text);
    }

    private void queryDALLE(String query) {
        downloadMessage = true;
        errorMessage = false;

        new Thread(() -> {
            imageLink = PostOpenAPI.run(query);
            if(imageLink == null) {
                downloadMessage = false;
                errorMessage = true;
                System.out.println("Failed query");
                this.submitButton.setSubmitStateFalse();
                textbox.resetText();
                return;
            }

            // Download image
            if(!DownloadAIGeneratedImage.downloadImage(imageLink)) {
                downloadErrorMessage = true;
                this.submitButton.setSubmitStateFalse();
                textbox.resetText();
                return;
            }
            
            MusicVisualizer.timingsCounter++;
            audioPlayer.play();
        }).start();
    }
}
