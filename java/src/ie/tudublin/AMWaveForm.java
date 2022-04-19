package ie.tudublin;

import processing.core.PApplet;

/*
A waveform spanning vertically across the screen
*/

public class AMWaveForm 
{
    SongVisuals sv;
    float cy = 0;
    float f = 0;
    float[] lerpedBuffer; // replace with getSmoothedAudio() later

    public AMWaveForm(SongVisuals sv)
    {
        this.sv = sv;
        cy = this.sv.height / 2;
        f = 0;
        lerpedBuffer = new float[this.sv.width];
    }

    public void render()
    {
        sv.colorMode(PApplet.HSB);
        for (int i = 0; i < sv.getAudioBuffer().size(); i++)
        {
            lerpedBuffer[i] = PApplet.lerp(lerpedBuffer[i], sv.getAudioBuffer().get(i), 0.05f);
            sv.stroke
            (
                PApplet.map(i, 0, sv.getAudioBuffer().size(), 0, 255),
                255,
                255
            );
            float feh = lerpedBuffer[i] * cy * 4.0f;
            sv.line(cy* 3.5f, i, 3.5f*(cy) + (cy*(0.005f*feh)), i );
            sv.line(cy* 0.5f, i, 0.5f*(cy) + (cy *(0.005f*feh)), i );
        }
        
    }
}
