package editor;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DirectColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * 要显示的精灵对象
 * 
 * @author lyx
 * 
 */
public class Target {
	private File path;// 精灵图像的路径
	private Screen playPlace;
	private Display display;
	private Image image;// 精灵原图
	private Image present;// 当前图像
	private Image[] frames;// 将原图切成的帧
	private boolean play;// 当前是否播放
	private float presentIndex;// 当前播放帧的索引
	private float speed;// 播放速度
	private int x, y, centerX, centerY;// 相对于Screen的坐标位置以及相对于Screen的中心坐标
	private ArrayList<Integer> framesOrder;
	private int width, height;// target的宽和高
	private int row, rank;// 被分割的行数和列数
	private int alpha = 255;
	private ArrayList<Move> moves;

	public Target(Display display, Screen playPlace) {
		this.display = display;
		this.playPlace = playPlace;
		this.centerX = this.playPlace.getCenterX();
		this.centerY = this.playPlace.getCenterY();
		presentIndex = 0;
		speed = 1.0f;
		this.framesOrder = new ArrayList<Integer>();
		this.moves = new ArrayList<Move>();
	}

	/**
	 * 设定播放次序特定位置对应的帧
	 * 
	 * @param index
	 *            某帧在frameOrder上的位置
	 * @param order
	 *            该位置对应的帧
	 */
	public void addFraOrder(int index, Integer order) {
		if (order == null)
			framesOrder.set(index, -1);
		else
			framesOrder.set(index, order);
	}

	/**
	 * 添加 移动对象,会对已存在的移动进行更新，对未存在的新添加
	 * 
	 * @param key
	 * @param value
	 */
	public void addMoves(Integer index, Move move) {
		moves.set(index, move);
	}

	/**
	 * 清除当前的操作值
	 */
	public void clear() {
		presentIndex = 0;
		this.setPlay(false);
		framesOrder.clear();
	}

	/**
	 * 绘制方法
	 * 
	 * @param g
	 *            画笔
	 */
	public void draw(GC g) {
		if (framesOrder == null || framesOrder.size() == 0
				|| framesOrder.get((int) presentIndex) == -1)
			return;
		present = frames[framesOrder.get((int) presentIndex)];
		nextFrame();
		move();
		if (present != null) {
			int temp = g.getAlpha();
			g.setAlpha(alpha);
			g.drawImage(present, x, y);
			g.setAlpha(temp);
		}
	}

	/**
	 * 得到target的透明度
	 * 
	 * @return 透明度
	 */
	public int getAlpha() {
		return alpha;
	}

	/**
	 * 获取全部帧图像
	 * 
	 * @return 帧图像
	 */
	public Image[] getFrames() {
		return frames;
	}

	/**
	 * 获取精灵的原图
	 * 
	 * @return 精灵原图
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * 获取行数
	 * 
	 * @return 行数
	 */
	public int getRow() {
		return row;
	}

	/**
	 * 获取列数
	 * 
	 * @return 列数
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * 获取播放速度
	 * 
	 * @return 播放速度
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * 获取图像路径
	 * 
	 * @return 图像路径
	 */
	public String getPath() {
		return path.getPath();
	}

	/**
	 * 获取当前播放帧的索引
	 * 
	 * @return 当前播放帧的索引
	 */
	public float getPresentIndex() {
		return presentIndex;
	}

	/**
	 * 获取播放顺序
	 * 
	 * @return 播放顺序
	 */
	public ArrayList<Integer> getOrder() {
		return framesOrder;
	}

	/**
	 * 获取移动事件列表
	 * 
	 * @return 移动事件列表
	 */
	public ArrayList<Move> getMoves() {
		return moves;
	}

	/**
	 * 插入帧
	 * 
	 * @param index
	 *            插入帧开始的索引
	 * @param number
	 *            插入的数量
	 * @return true 如果插入成功
	 */
	public boolean insertFrameOrder(int index, int number) {
		if (framesOrder == null || index - 1 > framesOrder.size()
				|| index - 1 < 0) {
			return false;
		}
		for (int i = 0; i < number; i++) {
			framesOrder.add(index + i, -1);
			moves.add(index + i, new Move(this, centerX - width / 2, centerY
					- height / 2));
		}
		return true;
	}

	public void move() {
		Move current = moves.get((int) presentIndex);
		if (current != null)
			current.exeMove();
	}

	/**
	 * 鼠标按下事件
	 * 
	 * @param e
	 */
	public void mouseDown(MouseEvent e) {
		addMoves((int) presentIndex, new Move(this, e.x, e.y));
	}

	/**
	 * 播放下一帧
	 */
	public void nextFrame() {
		if (!isPlay() || !isFraOrderInitialize()) {
			return;
		}
		presentIndex += speed;
		if (presentIndex >= framesOrder.size()) {
			this.setPlay(false);
			presentIndex = 0;
		}

	}

	/**
	 * 是否播放
	 * 
	 * @return true 如果播放
	 */
	public boolean isPlay() {
		return play;
	}

	/**
	 * 判断frameOrder是否已经初始化，即是否已为每帧选定图像
	 * 
	 * @return true 如果已经初始化
	 */
	public boolean isFraOrderInitialize() {
		for (int i = 0; i < framesOrder.size(); i++) {
			if (framesOrder.get(i) == -1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 设置精灵原图
	 * 
	 * @param path
	 *            图像路径
	 */
	public void readImage(File path) {
		this.path = path;
		this.present = null;
		frames = null;
		play = false;
		this.image = new Image(display, path.getPath());
		ImageData data = image.getImageData();
		int whitePixel = data.palette.getPixel(new RGB(255, 255, 255));
		for (int y = 0; y < data.height; y++) {
			for (int x = 0; x < data.width; x++) {
				int pixel = data.getPixel(x, y);
				if (pixel == -256)
					data.setPixel(x, y, whitePixel);
				else
					data.setPixel(x, y, pixel);
			}
		}
		data.transparentPixel = whitePixel;

		//
		// ImageIcon icon = new ImageIcon(this.path.getPath());
		// BufferedImage bufferedImage = getBufferedImage(icon);
		// ImageData data = getImageData(bufferedImage);
		// this.image = new Image(display, data);
	}

	/**
	 * 设置精灵原图
	 * 
	 * @param image
	 *            要设置的图像
	 */
	public void readImage(Image image) {
		this.image = image;
		this.present = null;
		frames = null;
		play = false;
	}

	/**
	 * 设定透明度
	 * 
	 * @param alpha
	 *            透明度
	 */
	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	/**
	 * 设定target的位置
	 * 
	 * @param x
	 *            设定的x坐标
	 * @param y
	 *            设定的y坐标
	 */
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 设置播放状态
	 * 
	 * @param play
	 *            播放状态
	 */
	public void setPlay(boolean play) {
		this.play = play;
	}

	/**
	 * 设定播放次序的长度,并对其初始化,设定数组每个值为-1；
	 * 
	 * @param length
	 */
	public void setFraOrderLen(int length) {
		framesOrder.clear();
		for (int i = 0; i < length; i++) {
			framesOrder.add(-1);
			moves.add(new Move(this, centerX - width / 2, centerY - height / 2));
		}

	}

	/**
	 * 设置播放速度
	 * 
	 * @param speed
	 *            播放速度
	 */
	public void setSpeed(float speed) {
		this.speed = speed - (int) speed / frames.length * frames.length;
	}

	/**
	 * 设定当前播放帧的presentIndex
	 * 
	 * @param index
	 *            要设定的presentIndex
	 */
	public void setPresentIndex(float index) {
		this.presentIndex = index;
	}

	/**
	 * 切分图像
	 * 
	 * @param row
	 *            行数
	 * @param rank
	 *            列数
	 */
	public void splitImage(int row, int rank) {
		this.rank = rank;
		this.row = row;
		frames = new Image[row * rank];
		final float w = image.getBounds().width / rank;
		final float h = image.getBounds().height / row;
		int r = 0;
		int c = 0;
		for (int i = 0; i < frames.length; i++) {
			frames[i] = new Image(display, (int) w, (int) h);
			GC g = new GC(frames[i]);
			g.drawImage(image, (int) (r * w), (int) (c * h), (int) w, (int) h,
					0, 0, (int) w, (int) h);
			
			ImageData data = frames[i].getImageData();
			int whitePixel = data.palette.getPixel(new RGB(255, 255, 255));
			for (int y = 0; y < data.height; y++) {
				for (int x = 0; x < data.width; x++) {
					int pixel = data.getPixel(x, y);
					if (pixel == 0)
						data.setPixel(x, y, whitePixel);
					else
						data.setPixel(x, y, pixel);
				}
			}
			data.transparentPixel = whitePixel;
			 frames[i] = new Image(display,data);
			
			g.dispose();
			r += 1;
			if (r >= rank) {
				r = 0;
				c += 1;
			}
		}
		width = (int) w;
		height = (int) h;

	}

	private BufferedImage getBufferedImage(ImageIcon icon) {
		int width = icon.getIconWidth();
		int height = icon.getIconHeight();
		ImageObserver observer = icon.getImageObserver();
		BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics gc = bufferedImage.createGraphics();
		gc.drawImage(icon.getImage(), 0, 0, observer);
		return bufferedImage;
	}

	private ImageData getImageData(BufferedImage bufferedImage) {
		DirectColorModel colorModel = (DirectColorModel) bufferedImage
				.getColorModel();
		PaletteData palette = new PaletteData(colorModel.getRedMask(),
				colorModel.getGreenMask(), colorModel.getBlueMask());
		ImageData data = new ImageData(bufferedImage.getWidth(),
				bufferedImage.getHeight(), colorModel.getPixelSize(), palette);
		int whitePixel = data.palette.getPixel(new RGB(255, 255, 255)); // 将白色设定为透明色
		WritableRaster raster = bufferedImage.getRaster();
		int[] pixelArray = new int[3];
		for (int y = 0; y < data.height; y++) {
			for (int x = 0; x < data.width; x++) {
				raster.getPixel(x, y, pixelArray);
				int pixel = palette.getPixel(new RGB(pixelArray[0],
						pixelArray[1], pixelArray[2]));
				if (pixel == 0) {// Swing读取PNG图形文件，透明的色素被置为0，所以格式转换是，将它变为白色（之前定义的透明色素）。
					data.setPixel(x, y, whitePixel);

				} else
					data.setPixel(x, y, pixel);
			}
		}
		data.transparentPixel = whitePixel; // 将白色指定为透明色素（同理将其它颜色定为透明色素也是可行的）。
		return data;
	}
}
