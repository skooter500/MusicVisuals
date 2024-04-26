package c22371846;

import ie.tudublin.Heartbeat;

public class Animation {

    Heartbeat HB;

    float angle = 0;

    float[] lerpedBuffer;

    public Animation(Heartbeat HB) {
        this.HB = HB;
        lerpedBuffer = new float[HB.width];
    }

    public void render() {
        float separated = 10;
        float sum = 0;
        float off = 0;
        float average = 0;
        float smoothedAmplitude = 0;

        int bar_values = (int) (HB.mouseX / 20.0f);
        float w = HB.width / (float) bar_values;
        float h = HB.height / separated;
        float H = HB.height / 2;
        float W = HB.width / 2;

        for (int i = 0; i < HB.getAudioBuffer().size(); i++) {
            sum += HB.abs(HB.getAudioBuffer().get(i));
            lerpedBuffer[i] = HB.lerp(lerpedBuffer[i], HB.getAudioBuffer().get(i), 0.05f);
        }
        average = sum / (float) HB.getAudioBuffer().size();

        smoothedAmplitude = HB.lerp(smoothedAmplitude, average, 0.75f);

        HB.getFFT().forward(HB.getAudioBuffer());

        int maxFFTIndex = 0;
        for (int i = 0; i < HB.getFFT().specSize() / 2; i++) {
            if (HB.getFFT().getBand(i) > HB.getFFT().getBand(maxFFTIndex)) {
                maxFFTIndex = i;
            }
        }
        float freq = HB.getFFT().indexToFreq(maxFFTIndex);

        off += 10;
        HB.background((off) % 256, HB.mouseX);
        for (int i = 0; i < HB.getFFT().specSize() / 2; i++) {

            float c = HB.map(i, 0, HB.getAudioBuffer().size(), 0, 255);
            float cc = c * i;

            HB.translate(100, 0, 0);
            HB.rotateY(angle);
            HB.rotateX(angle);

            if (HB.keyCode == HB.LEFT) {
                HB.square(W, H, HB.lerp(0, smoothedAmplitude, (HB.getFFT().getBand(i) * 100)));
                HB.fill((cc + off) % 256, HB.mouseX, 255);
            }

            if (HB.keyCode == HB.RIGHT) {
                HB.triangle(W, H, H, H, i, (HB.getFFT().getBand(i)));
                HB.triangle(W, H, H, H, i, (HB.getFFT().getBand(i)));
                HB.fill((cc + off) % 256, HB.mouseX, 255);
            }

            if (HB.keyCode == HB.UP) {
                HB.circle(W, H, (HB.getFFT().getBand(i) * 12));
                HB.fill((cc + off) % 256, HB.mouseX, 255);
            }

            if (HB.keyCode == HB.DOWN) {
                HB.box((HB.getFFT().getBand(i) * 3));
                HB.fill((cc + off) % 256, HB.mouseX, 255);
            }
        }
        angle += 0.01f;
    }
}

// package c22371846;

// import ddf.minim.AudioBuffer;
// import ddf.minim.AudioInput;
// import ddf.minim.AudioPlayer;
// import ddf.minim.Minim;
// import ddf.minim.analysis.FFT;
// import ie.tudublin.*;

// public class Animation
// {

// Minim minim;
// AudioPlayer ap;
// AudioInput ai;
// AudioBuffer ab;
// FFT fft;
// Heartbeat HB;
// float lerpedBuffer[];

// public void Stuff(Heartbeat HB) {
// this.fft = fft;
// this.minim = minim;
// this.ab = ab;
// this.ap = ap;
// this.ai = ai;
// this.HB = HB;
// }

// public void render()
// {
// float separated = 10;
// float sum = 0;
// float off = 0;
// float average = 0;
// float smoothedAmplitude = 0;
// int bar_values = (int) (HB.mouseX / 20.0f);
// float w = HB.width / (float) bar_values;
// float h = HB.height / separated;
// float H = HB.height / 2;
// float W = HB.width / 2;

// HB.getAmplitude();

// HB.getAudioBuffer();

// int maxFFTIndex = 0;
// for(int i = 0 ; i < fft.specSize() /2 ; i ++)
// {
// if (fft.getBand(i) > fft.getBand(maxFFTIndex))
// {
// maxFFTIndex = i;
// }
// }

// off+=10;
// HB.background((off) % 256, HB.mouseX);
// for(int i = 0 ; i < HB.getFFT() / 2; i ++)
// {
// float c = HB.map(i, 0, HB.getAudioBuffer(), 0, 255);
// float cc = c * i;
// HB.translate(100, 0, 0);
// HB.rotateY(angle);
// HB.rotateX(angle);
// if (HB.keyCode == HB.LEFT)
// {
// HB.square(W, H, HB.lerp(0, smoothedAmplitude, (HB.getBands() * 100)));
// HB.fill((cc + off) % 256, HB.mouseX, 255);
// }

// if (HB.keyCode == HB.RIGHT)
// {
// HB.triangle(W, H, H, H, W, (HB.getBand()));
// HB.fill((cc + off) % 256, HB.mouseX, 255);
// }

// if (HB.keyCode == HB.UP) {
// HB.circle(W, H, (HB.getBand() * 6));
// HB.fill((cc + off) % 256, HB.mouseX, 255);

// }

// if (HB.keyCode == HB.DOWN) {
// HB.box((HB.getBand() * 3));
// HB.fill((cc + off) % 256, HB.mouseX, 255);
// HB.stroke(0);
// }
// }
// angle += 0.01f;
// }
// float angle = 0;
// }
