package rush01.objects;

import rush01.mainConstants;

public abstract class AbstractObject implements mainConstants {
	protected int x;
	protected int y;

	public AbstractObject(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
