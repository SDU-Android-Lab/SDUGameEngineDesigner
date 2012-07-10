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
 * Ҫ��ʾ�ľ������
 * 
 * @author lyx
 * 
 */
public class Target {
	private File path;// ����ͼ���·��
	private Screen playPlace;
	private Display display;
	private Image image;// ����ԭͼ
	private Image present;// ��ǰͼ��
	private Image[] frames;// ��ԭͼ�гɵ�֡
	private boolean play;// ��ǰ�Ƿ񲥷�
	private float presentIndex;// ��ǰ����֡������
	private float speed;// �����ٶ�
	private int x, y, centerX, centerY;// �����Screen������λ���Լ������Screen����������
	private ArrayList<Integer> framesOrder;
	private int width, height;// target�Ŀ�͸�
	private int row, rank;// ���ָ������������
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
	 * �趨���Ŵ����ض�λ�ö�Ӧ��֡
	 * 
	 * @param index
	 *            ĳ֡��frameOrder�ϵ�λ��
	 * @param order
	 *            ��λ�ö�Ӧ��֡
	 */
	public void addFraOrder(int index, Integer order) {
		if (order == null)
			framesOrder.set(index, -1);
		else
			framesOrder.set(index, order);
	}

	/**
	 * ��� �ƶ�����,����Ѵ��ڵ��ƶ����и��£���δ���ڵ������
	 * 
	 * @param key
	 * @param value
	 */
	public void addMoves(Integer index, Move move) {
		moves.set(index, move);
	}

	/**
	 * �����ǰ�Ĳ���ֵ
	 */
	public void clear() {
		presentIndex = 0;
		this.setPlay(false);
		framesOrder.clear();
	}

	/**
	 * ���Ʒ���
	 * 
	 * @param g
	 *            ����
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
	 * �õ�target��͸����
	 * 
	 * @return ͸����
	 */
	public int getAlpha() {
		return alpha;
	}

	/**
	 * ��ȡȫ��֡ͼ��
	 * 
	 * @return ֡ͼ��
	 */
	public Image[] getFrames() {
		return frames;
	}

	/**
	 * ��ȡ�����ԭͼ
	 * 
	 * @return ����ԭͼ
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public int getRow() {
		return row;
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * ��ȡ�����ٶ�
	 * 
	 * @return �����ٶ�
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * ��ȡͼ��·��
	 * 
	 * @return ͼ��·��
	 */
	public String getPath() {
		return path.getPath();
	}

	/**
	 * ��ȡ��ǰ����֡������
	 * 
	 * @return ��ǰ����֡������
	 */
	public float getPresentIndex() {
		return presentIndex;
	}

	/**
	 * ��ȡ����˳��
	 * 
	 * @return ����˳��
	 */
	public ArrayList<Integer> getOrder() {
		return framesOrder;
	}

	/**
	 * ��ȡ�ƶ��¼��б�
	 * 
	 * @return �ƶ��¼��б�
	 */
	public ArrayList<Move> getMoves() {
		return moves;
	}

	/**
	 * ����֡
	 * 
	 * @param index
	 *            ����֡��ʼ������
	 * @param number
	 *            ���������
	 * @return true �������ɹ�
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
	 * ��갴���¼�
	 * 
	 * @param e
	 */
	public void mouseDown(MouseEvent e) {
		addMoves((int) presentIndex, new Move(this, e.x, e.y));
	}

	/**
	 * ������һ֡
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
	 * �Ƿ񲥷�
	 * 
	 * @return true �������
	 */
	public boolean isPlay() {
		return play;
	}

	/**
	 * �ж�frameOrder�Ƿ��Ѿ���ʼ�������Ƿ���Ϊÿ֡ѡ��ͼ��
	 * 
	 * @return true ����Ѿ���ʼ��
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
	 * ���þ���ԭͼ
	 * 
	 * @param path
	 *            ͼ��·��
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
	 * ���þ���ԭͼ
	 * 
	 * @param image
	 *            Ҫ���õ�ͼ��
	 */
	public void readImage(Image image) {
		this.image = image;
		this.present = null;
		frames = null;
		play = false;
	}

	/**
	 * �趨͸����
	 * 
	 * @param alpha
	 *            ͸����
	 */
	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	/**
	 * �趨target��λ��
	 * 
	 * @param x
	 *            �趨��x����
	 * @param y
	 *            �趨��y����
	 */
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * ���ò���״̬
	 * 
	 * @param play
	 *            ����״̬
	 */
	public void setPlay(boolean play) {
		this.play = play;
	}

	/**
	 * �趨���Ŵ���ĳ���,�������ʼ��,�趨����ÿ��ֵΪ-1��
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
	 * ���ò����ٶ�
	 * 
	 * @param speed
	 *            �����ٶ�
	 */
	public void setSpeed(float speed) {
		this.speed = speed - (int) speed / frames.length * frames.length;
	}

	/**
	 * �趨��ǰ����֡��presentIndex
	 * 
	 * @param index
	 *            Ҫ�趨��presentIndex
	 */
	public void setPresentIndex(float index) {
		this.presentIndex = index;
	}

	/**
	 * �з�ͼ��
	 * 
	 * @param row
	 *            ����
	 * @param rank
	 *            ����
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
		int whitePixel = data.palette.getPixel(new RGB(255, 255, 255)); // ����ɫ�趨Ϊ͸��ɫ
		WritableRaster raster = bufferedImage.getRaster();
		int[] pixelArray = new int[3];
		for (int y = 0; y < data.height; y++) {
			for (int x = 0; x < data.width; x++) {
				raster.getPixel(x, y, pixelArray);
				int pixel = palette.getPixel(new RGB(pixelArray[0],
						pixelArray[1], pixelArray[2]));
				if (pixel == 0) {// Swing��ȡPNGͼ���ļ���͸����ɫ�ر���Ϊ0�����Ը�ʽת���ǣ�������Ϊ��ɫ��֮ǰ�����͸��ɫ�أ���
					data.setPixel(x, y, whitePixel);

				} else
					data.setPixel(x, y, pixel);
			}
		}
		data.transparentPixel = whitePixel; // ����ɫָ��Ϊ͸��ɫ�أ�ͬ��������ɫ��Ϊ͸��ɫ��Ҳ�ǿ��еģ���
		return data;
	}
}
