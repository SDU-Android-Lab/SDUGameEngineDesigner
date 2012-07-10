package editor;

/**
 * ���ڷ�װTarget������ƶ��¼�,���¼�������λ��������뻭����x��y����
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
