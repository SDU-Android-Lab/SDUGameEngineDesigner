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

	private int refreshRate = 200;// 刷屏率为20ms

	private Color bgColor;// 背景色

	private int presentColor = SWT.COLOR_BLACK;// 当前背景色

	private boolean work = false;// 工作状态

	private Display display;

	private Target target;// 当前播放对象

	private int x, y;// screen的中心坐标

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
	 * 获取中心坐标x
	 * 
	 * @return 中心坐标
	 */
	public int getCenterX() {
		return x;
	}

	/**
	 * 获取中心坐标y
	 * 
	 * @return 中心坐标
	 */
	public int getCenterY() {
		return y;
	}

	/**
	 * 设定Screen要播放的target
	 * 
	 * @param target
	 */
	public void setTarget(Target target) {
		this.target = target;
	}

	/**
	 * 移除当前target
	 */
	public void removeTarget() {
		this.target = null;

	}

	/**
	 * 绘制方法
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
	 * 线程工作
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
	 * 设置刷屏率
	 * 
	 * @param refreshRate
	 *            刷屏率
	 */
	public void setRefreshRate(int refreshRate) {
		this.refreshRate = refreshRate;
	}

	/**
	 * 获取刷屏率
	 * 
	 * @return 当前刷屏率
	 */
	public int getRefreshRate() {
		return refreshRate;
	}

	/**
	 * 是否处于工作状态
	 * 
	 * @return true 如果工作
	 */
	public boolean isWork() {
		return work;
	}

	/**
	 * 设置工作状态
	 * 
	 * @param work
	 *            工作状态
	 */
	public void setWork(boolean work) {
		this.work = work;
	}

	/**
	 * 释放资源
	 * 
	 * @return true 释放成功
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
	 * 鼠标双击事件
	 * 
	 * @param e
	 */
	public void mouseDoubleClick(MouseEvent e) {
	}

	/**
	 * 鼠标按下事件
	 */
	public void mouseDown(MouseEvent e) {
		if (target != null)
			target.mouseDown(e);
	}

	/**
	 * 鼠标按键弹起事件
	 */
	public void mouseUp(MouseEvent e) {

	}

}
