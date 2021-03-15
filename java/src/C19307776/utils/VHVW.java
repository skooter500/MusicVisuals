package C19307776.utils;

/**
 * This class recreates viewport units from CSS.
 * vw(1f) = 1% of the screen width;
 * vh(1f0 = 1% the height of the screen)
 */

public class VHVW {
	float w;
	float h;
	public VHVW(float w, float h) {
		this.w = w;
		this.h = h;
	}
	public float vw(float value) {
		return (w/100f)*value;
	}
	public float vh(float value) {
		return (h/100f)*value;
	}
}
