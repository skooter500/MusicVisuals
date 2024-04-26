package c22371846;


import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import ie.tudublin.Heartbeat;

public class Graph 
{
    
    Heartbeat HB;
    float lerpedBuffer[];

    public Graph(Heartbeat HB) {
        this.HB = HB;
        lerpedBuffer = new float[HB.width];
    }

    public void render()
	{
        float separated = 10;
        float off = 0;
        int bar_values = (int) (HB.mouseX / 20.0f);
        float w = HB.width / (float) bar_values;
        float h = HB.height / separated;
        float H = HB.height / 2;
        float sum = 0;
        float average = 0;
        float smoothedAmplitude = 0;

        HB.getAmplitude();

        HB.getFFT().forward(HB.getAudioBuffer());

        int maxFFTIndex = 0;
        for (int i = 0; i < HB.getFFT().specSize() / 2; i++) {
            if (HB.getFFT().getBand(i) > HB.getFFT().getBand(maxFFTIndex)) {
                maxFFTIndex = i;
            }
        }
        float freq = HB.getFFT().indexToFreq(maxFFTIndex);

		for (int i = 0; i < HB.getAudioBuffer().size(); i++) {
            sum += HB.abs(HB.getAudioBuffer().get(i));
            lerpedBuffer[i] = HB.lerp(lerpedBuffer[i], HB.getAudioBuffer().get(i), 0.05f);
        }
        average = sum / (float) HB.getAudioBuffer().size();

        smoothedAmplitude = HB.lerp(smoothedAmplitude, average, 0.75f);
        off += 10;
        HB.background(0);
        HB.text("Frequencies: " + freq, HB.width - 300, HB.height - 50);

        //bar values
        for (int i = 1; i < HB.getAudioBuffer().size(); i++) 
        {
            float num = 50;
            float f = lerpedBuffer[i] * H * 4.0f;
            float x = HB.map(num * i, HB.width - 450, HB.width, num, HB.width);
            float c = HB.map((off), 0, 200, 0, 255);
            float cc = c * i;
            HB.fill(cc % 256, 255, 255);
            HB.rect(x, 0, w, f * H * 0.1F);
            HB.noStroke();
        }

        for (int i = 0; i < 20; i++) 
        {
            HB.text(i * 50, 25, (i * 50) + 10);
        }
    }
}