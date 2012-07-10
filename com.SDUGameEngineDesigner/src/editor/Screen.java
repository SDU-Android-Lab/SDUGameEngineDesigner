package editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class Screen extends Canvas implements PaintListener, Runnable,
		MouseListener {

	private int refreshRate = 200;// ˢ����Ϊ20ms

	private Color bgColor;// ����ɫ

	private int presentColor = SWT.COLOR_BLACK;// ��ǰ����ɫ

	private boolean work = false;// ����״̬

	private Display display;

	private Target target;// ��ǰ���Ŷ���

	private int x, y;// screen����������

	public Screen(Composite parent, int style, Display display) {
		super(parent, SWT.NO_BACKGROUND | SWT.DOUBLE_BUFFERED | style);
		this.display = display;
		bgColor = display.getSystemColor(presentColor);
		addPaintListener(this);
		this.setWork(true);
		this.addMouseListener(this);
		new Thread(this).start();
	}

	/**
	 * ��ȡ��������x
	 * 
	 * @return ��������
	 */
	public int getCenterX() {
		return x;
	}

	/**
	 * ��ȡ��������y
	 * 
	 * @return ��������
	 */
	public int getCenterY() {
		return y;
	}

	/**
	 * �趨ScreenҪ���ŵ�target
	 * 
	 * @param target
	 */
	public void setTarget(Target target) {
		this.target = target;
	}

	/**
	 * �Ƴ���ǰtarget
	 */
	public void removeTarget() {
		this.target = null;

	}

	/**
	 * ���Ʒ���
	 */
	public void paintControl(PaintEvent e) {
		if (!isWork())
			return;

		x = this.getSize().x / 2;
		y = this.getSize().y / 2;

		GC g = e.gc;
		Image bufferImage = new Image(display, this.getSize().x,
				this.getSize().y);
		GC bufferg = new GC(bufferImage);
		bufferg.setBackground(bgColor);
		bufferg.fillRectangle(this.getClientArea());
		bufferg.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
		bufferg.drawLine(0, this.getSize().y / 2, this.getSize().x,
				this.getSize().y / 2);
		bufferg.drawLine(this.getSize().x / 2, 0, this.getSize().x / 2,
				this.getSize().y);
		if (target != null)
			target.draw(bufferg);

		g.drawImage(bufferImage, 0, 0);
		bufferg.dispose();
		g.dispose();

	}

	/**
	 * �̹߳���
	 */
	public void run() {
		try {
			while (work) {
				display.asyncExec(new Runnable() {
					public void run() {
						if (!Screen.this.isDisposed())
							redraw();
					}
				});
				Thread.sleep(refreshRate);
			}
		} catch (SWTException e) {
			return;
		} catch (InterruptedException e) {
			return;
		}
	}

	/**
	 * ����ˢ����
	 * 
	 * @param refreshRate
	 *            ˢ����
	 */
	public void setRefreshRate(int refreshRate) {
		this.refreshRate = refreshRate;
	}

	/**
	 * ��ȡˢ����
	 * 
	 * @return ��ǰˢ����
	 */
	public int getRefreshRate() {
		return refreshRate;
	}

	/**
	 * �Ƿ��ڹ���״̬
	 * 
	 * @return true �������
	 */
	public boolean isWork() {
		return work;
	}

	/**
	 * ���ù���״̬
	 * 
	 * @param work
	 *            ����״̬
	 */
	public void setWork(boolean work) {
		this.work = work;
	}

	/**
	 * �ͷ���Դ
	 * 
	 * @return true �ͷųɹ�
	 */
	public boolean onDispose() {
		if (isWork()) {
			this.setWork(false);
		}
		if (!isDisposed()) {
			this.dispose();
		}
		return true;
	}

	/**
	 * ���˫���¼�
	 * 
	 * @param e
	 */
	public void mouseDoubleClick(MouseEvent e) {
	}

	/**
	 * ��갴���¼�
	 */
	public void mouseDown(MouseEvent e) {
		if (target != null)
			target.mouseDown(e);
	}

	/**
	 * ��갴�������¼�
	 */
	public void mouseUp(MouseEvent e) {

	}

}
