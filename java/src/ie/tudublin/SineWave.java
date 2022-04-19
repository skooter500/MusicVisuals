package ie.tudublin;

import processing.core.PApplet;

public class SineWave {
    SongVisuals sv;
    float x = 0;
    float y = 0;
    float t = 0;
    float diameter  = 2;
    float frequency = 990000000000000000000000000000000000.0f; // citation needed
    float[] lerpedBuffer;

    public SineWave(SongVisuals sv){
        this.sv = sv;
        x = this.sv.width / 2;
        y = this.sv.height/ 2;
        lerpedBuffer = new float[this.sv.width];
    }

    public void render(){

        sv.colorMode(PApplet.HSB);
        for(int i = 0 ; i < sv.getAudioBuffer().size() ; i ++)
        {
            lerpedBuffer[i] = PApplet.lerp(lerpedBuffer[i], sv.getAudioBuffer().get(i), 0.05f);
            sv.stroke(
                PApplet.map(i, 0, sv.getAudioBuffer().size(), 0, 255)
                , 255
                , 255
            );
            float feh = lerpedBuffer[i] * (y / 2) * 4.0f;
            // Horizontal
            sv.ellipse(i*diameter, 3*(feh)*PApplet.sin(frequency*(feh))+y, diameter, diameter);
            //mv.ellipse((feh)*PApplet.sin(frequency*(feh))+y, i*diameter, diameter, diameter);
            
            // Vertical
            //mv.ellipse(200*mv.getAudioBuffer().get(i)*PApplet.sin(frequency*(i))+y, i*diameter, diameter, diameter);
            //t += 0.1f;

        }
    }
}
