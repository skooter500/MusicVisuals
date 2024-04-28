package C22790201;

import ie.tudublin.OurVisual;
import processing.core.*;
import ddf.minim.*;

public class AleenaVisual1
{
    OurVisual ov;
    float[] smoothedBuffer; // Smoothed audio buffer
    float smoothingFactor = 0.2f; // Adjust the smoothing factor as needed, 'f' added to specify float literal

    public AleenaVisual1(OurVisual ov)
    {
        this.ov = ov;
    }

    public void render()
    {
        ov.colorMode(PApplet.HSB);

        // Set the color and stroke weight for the waveform
        ov.stroke(255);
        ov.strokeWeight(1);

        // Get the audio buffer from OurVisual
        AudioBuffer audioBuffer = ov.getAudioBuffer();

        // Smooth the audio buffer
        if (smoothedBuffer == null || smoothedBuffer.length != audioBuffer.size()) {
            smoothedBuffer = new float[audioBuffer.size()];
        }
        for (int i = 0; i < audioBuffer.size(); i++) {
            smoothedBuffer[i] += (audioBuffer.get(i) - smoothedBuffer[i]) * smoothingFactor;
        }
        
        // Calculate the width and height of the waveform
        float waveformWidth = ov.width / 2; // Width of the waveform
        float waveformHeight = ov.height / 2; // Height of the waveform

        // Top edge
        renderWaveform(0, 0, ov.width, waveformHeight, 0, 0);

        // Right edge
        renderWaveform(ov.width - waveformWidth, 0, waveformWidth, ov.height, 0, 0);

        // Bottom edge
        renderWaveform(0, ov.height - waveformHeight, ov.width, waveformHeight, 0, 0);

        // Left edge
        renderWaveform(0, 0, waveformWidth, ov.height, 0, 0);

        renderSpectrumAnalyzer();
    }

    private void renderWaveform(float xStart, float yStart, float width, float height, float xOffset, float yOffset)
    {
    ov.beginShape();
    for (int i = 0; i < smoothedBuffer.length; i++) {
        float x = PApplet.map(i, 0, smoothedBuffer.length, xStart, xStart + width);
        float y = PApplet.map(smoothedBuffer[i], -1f, 1f, yStart, yStart + height);
        
        // Calculate hue based on audio amplitude
        float hue = PApplet.map(smoothedBuffer[i], -1f, 1f, 0, 255);
        
        // Set the fill color based on hue, with full saturation and brightness
        ov.fill(hue, 255, 255);
        
        ov.vertex(x + xOffset * width, y + yOffset * height);
    }
    ov.endShape();
    }

    public void renderSpectrumAnalyzer() {
        ov.colorMode(PApplet.HSB);
        ov.noStroke();
    
        // Get the audio buffer from OurVisual
        AudioBuffer audioBuffer = ov.getAudioBuffer();
    
        // Calculate the width and height of each bar in the spectrum analyzer
        float barWidth = ov.width / (float) audioBuffer.size();
        float barHeight;
    
        // Draw the spectrum analyzer bars
        for (int i = 0; i < audioBuffer.size(); i++) {
            // Calculate the height of the bar based on audio amplitude
            barHeight = PApplet.map(audioBuffer.get(i), -1, 1, ov.height, 0);
    
            // Calculate hue based on audio amplitude
            float hue = PApplet.map(audioBuffer.get(i), -1, 1, 0, 255);
    
            // Set the fill color based on hue, with full saturation and brightness
            ov.fill(hue, 255, 255);
    
            // Draw the bar
            ov.rect(i * barWidth, ov.height - barHeight, barWidth, barHeight);
        }
    }
}
