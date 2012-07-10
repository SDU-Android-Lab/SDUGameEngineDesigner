package editor;

/**
 * 用于封装Target对象的移动事件,该事件描述的位置是相对与画布的x、y坐标
 * 
 * @author lhy
 * 
 */
public class Move {
	Target t;
	int x, y;

	public Move(Target t, int x, int y) {
		this.t = t;
		this.x = x;
		this.y = y;
	}

	public void exeMove() {
		t.setLocation(x, y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
