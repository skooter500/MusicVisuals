package C19307776;

public enum Properties {
	WIDTH(0), HEIGHT(1), XPOS(2), YPOS(3);

	private final int value;
	private Properties(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
