package ie.tudublin;

import processing.core.PApplet;
import ddf.minim.*;
import ddf.minim.analysis.FFT;

public abstract class Visual extends PApplet
{
	public int frameSize = 1024;
	public int sampleRate = 44100;

	public float[] bands;
	public float[] lerpedBands;

	public Minim minim;
	public AudioInput ai;
	public AudioSample as;
	public AudioBuffer ab;
	FFT fft;

	public float amplitude  = 0;
	public float lerpedAmplitude = 0;
	
	public void startMinim() 
	{
		minim = new Minim(this);

		fft = new FFT(frameSize, sampleRate);

		bands = new float[(int) log2(frameSize)];
  		lerpedBands = new float[bands.length];

	}

	float log2(float f) {
		return log(f) / log(2.0f);
	}

	protected void calculateFFT() throws VisualException
	{
		fft.window(FFT.HAMMING);
		if (ab != null)
		{
			fft.forward(ab);
		}
		else
		{
			throw new VisualException("You must call startListening or loadAudio before calling fft");
		}
	}

	
	public void calculateAverageAmplitude()
	{
		float total = 0;
		for(int i = 0 ; i < ab.size() ; i ++)
        {
			total += abs(ab.get(i));
		}
		amplitude = total / ab.size();
		lerpedAmplitude = PApplet.lerp(lerpedAmplitude, amplitude, 0.1f);
	}


	protected void calculateFrequencyBands() {
		for (int i = 0; i < bands.length; i++) {
			int start = (int) pow(2, i) - 1;
			int w = (int) pow(2, i);
			int end = start + w;
			float average = 0;
			for (int j = start; j < end; j++) {
				average += fft.getBand(j) * (j + 1);
			}
			average /= (float) w;
			bands[i] = average * 5.0f;
			lerpedBands[i] = lerp(lerpedBands[i], bands[i], 0.05f);
		}
	}

	public void startListening()
	{
		ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
		ab = ai.left;
	}

	public void loadAudio(String filename)
	{
		as = minim.loadSample(filename, frameSize);
		ab = as.left;
	}
}
