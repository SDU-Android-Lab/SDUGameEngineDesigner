package editor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class Editor {
	Display display;
	Shell shell;
	WidgetManager manager;
	Target target;// ����
	boolean canSave = false;// ׼��״��
	File project;

	public Editor(Display display) {
		this.display = display;
	}

	/**
	 * װ��Editor
	 * 
	 * @return true�������óɹ�
	 */
	public boolean launch() {
		shell = new Shell(display, SWT.CLOSE | SWT.SYSTEM_MODAL);
		shell.setLayout(new FillLayout());
		manager = new WidgetManager(shell);
		manager.initialize();
		shell.setBounds(50, 50, 900, 700);
		shell.open();

		return true;
	}

	public void process() {
		manager.addListeners();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * 
	 * �趨֡����Dialog
	 * 
	 * */
	class SetFrame extends Dialog {
		/**
		 * ��ʾ�ı��ĶԻ���
		 */
		private Label label;

		private Text text;

		public SetFrame(Shell parentShell) {
			super(parentShell);
		}

		/**
		 * ���Ի���
		 * 
		 * 
		 */
		protected Control createDialogArea(Composite parent) {

			GridLayout layout = new GridLayout(4, true);
			layout.marginLeft = 30;
			layout.marginHeight = 30;

			parent.setLayout(layout);
			{
				label = new Label(parent, SWT.CHECK);
				label.setText("�趨֡����");
				GridData gd1 = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
				gd1.horizontalSpan = 1;
				label.setLayoutData(gd1);

				text = new Text(parent, SWT.BORDER);
				text.setText("0");
				text.setTextLimit(15);
				GridData gd2 = new GridData(GridData.FILL_HORIZONTAL);
				gd2.horizontalSpan = 3;
				text.setLayoutData(gd2);
				/**
				 * �ð�ť����趨֡���Ĳ���
				 */
				Button b1 = new Button(parent, SWT.PUSH);
				b1.setText("ȷ��");
				GridData gd3 = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
				gd3.horizontalSpan = 2;
				b1.setLayoutData(gd3);
				b1.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						String str = text.getText();
						int number;
						try {
							number = Integer.parseInt(str);
							manager.frames.setFrames(number);
						} catch (NumberFormatException e1) {
							MessageDialog.openWarning(shell, "����", "����������");
							text.setText("");
							return;
						}
						SetFrame.this.close();
					}
				});

				Button b2 = new Button(parent, SWT.PUSH);
				b2.setText("ȡ��");
				GridData gd4 = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
				gd4.horizontalSpan = 2;
				b2.setLayoutData(gd4);
				b2.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						SetFrame.this.close();
					}
				});

			}
			return parent;
		}

		protected Button createButton(Composite parent, int id, String label,
				boolean defaultButton) {
			return null;
		}

		/**
		 * ���öԻ���ı���,ͼƬ
		 */
		protected void configureShell(Shell shell) {
			super.configureShell(shell);
			shell.setText("�趨֡��");
			String path = ISharedImages.IMG_DEC_FIELD_WARNING;
			shell.setImage(PlatformUI.getWorkbench().getSharedImages()
					.getImage(path));
		}

	}

	/**
	 * ����֡���Ի���
	 * 
	 * @author
	 * 
	 */
	class AddFrame extends Dialog {
		/**
		 * ��ʾ�ı��ĶԻ���
		 */
		private Label label;
		private Label label_1;

		private Text text;
		private Text text_1;

		public AddFrame(Shell parentShell) {
			super(parentShell);
		}

		/**
		 * ���Ի��򣬲���ûŪ��
		 * 
		 * 
		 */
		protected Control createDialogArea(Composite parent) {
			GridLayout layout = new GridLayout(1, false);
			layout.marginLeft = 30;
			layout.marginHeight = 30;
			parent.setLayout(layout);

			label = new Label(parent, SWT.CHECK);
			label.setText("��ʼ֡��");
			text = new Text(parent, SWT.BORDER);

			label_1 = new Label(parent, SWT.CHECK);
			label_1.setText("Ҫ����֡��");
			text_1 = new Text(parent, SWT.BORDER);

			Button b1 = new Button(parent, SWT.PUSH);
			b1.setText("ȷ��");
			b1.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					int first, total;// �ֱ��Ӧ��ʼ֡��������֡����Ҫ����֡��
					try {
						first = Integer.parseInt(text.getText());
						total = Integer.parseInt(text_1.getText());
						manager.frames.addFrames(first, total);
					} catch (NumberFormatException e1) {
						MessageDialog.openWarning(shell, "��ʾ", "��ȷ������Ķ�������");
					}
					AddFrame.this.close();
				}
			});

			Button b2 = new Button(parent, SWT.PUSH);
			b2.setText("ȡ��");
			b2.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					AddFrame.this.close();
				}
			});

			return parent;
		}

		protected Button createButton(Composite parent, int id, String label,
				boolean defaultButton) {
			return null;
		}

		/**
		 * ���öԻ���ı���,ͼƬ
		 */
		protected void configureShell(Shell shell) {
			super.configureShell(shell);
			shell.setText("����֡��");
			String path = ISharedImages.IMG_DEC_FIELD_WARNING;
			shell.setImage(PlatformUI.getWorkbench().getSharedImages()
					.getImage(path));
		}

	}

	/**
	 * �趨͸����
	 * 
	 * @author lhy
	 * 
	 */
	class setAlpha extends Dialog {
		/**
		 * ��ʾ�ı��ĶԻ���
		 */
		private Label label;

		private Text text;

		public setAlpha(Shell parentShell) {
			super(parentShell);
		}

		/**
		 * ���Ի���
		 * 
		 * 
		 */
		protected Control createDialogArea(Composite parent) {

			GridLayout layout = new GridLayout(4, true);
			layout.marginLeft = 30;
			layout.marginHeight = 30;

			parent.setLayout(layout);
			{
				label = new Label(parent, SWT.CHECK);
				label.setText("�趨͸���ȣ�");
				GridData gd1 = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
				gd1.horizontalSpan = 1;
				label.setLayoutData(gd1);

				text = new Text(parent, SWT.BORDER);
				text.setText("0");
				text.setTextLimit(15);
				GridData gd2 = new GridData(GridData.FILL_HORIZONTAL);
				gd2.horizontalSpan = 3;
				text.setLayoutData(gd2);
				/**
				 * �ð�ť����趨֡���Ĳ���
				 */
				Button b1 = new Button(parent, SWT.PUSH);
				b1.setText("ȷ��");
				GridData gd3 = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
				gd3.horizontalSpan = 2;
				b1.setLayoutData(gd3);
				b1.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						String str = text.getText();
						int number;
						try {
							number = Integer.parseInt(str);
							target.setAlpha(number);
						} catch (NumberFormatException e1) {
							MessageDialog.openWarning(shell, "����", "����������");
							text.setText("");
							return;
						}
						setAlpha.this.close();
					}
				});

				Button b2 = new Button(parent, SWT.PUSH);
				b2.setText("ȡ��");
				GridData gd4 = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
				gd4.horizontalSpan = 2;
				b2.setLayoutData(gd4);
				b2.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						setAlpha.this.close();
					}

				});

			}
			return parent;
		}

		protected Button createButton(Composite parent, int id, String label,
				boolean defaultButton) {
			return null;
		}

		/**
		 * ���öԻ���ı���,ͼƬ
		 */
		protected void configureShell(Shell shell) {
			super.configureShell(shell);
			shell.setText("�趨֡��");
			String path = ISharedImages.IMG_DEC_FIELD_WARNING;
			shell.setImage(PlatformUI.getWorkbench().getSharedImages()
					.getImage(path));
		}

	}

	/**
	 * �趨ԭͼҪ�и������������
	 * 
	 * @author lhy
	 * 
	 */
	class divisionImage extends Dialog {
		/**
		 * ��ʾ�ı��ĶԻ���
		 */
		private Label label;
		private Label label_1;

		private Text text;
		private Text text_1;

		public divisionImage(Shell parentShell) {
			super(parentShell);
		}

		/**
		 * ���Ի��򣬲���ûŪ��
		 * 
		 * 
		 */
		protected Control createDialogArea(Composite parent) {
			GridLayout layout = new GridLayout(1, false);
			layout.marginLeft = 30;
			layout.marginHeight = 30;
			parent.setLayout(layout);

			label = new Label(parent, SWT.CHECK);
			label.setText("����");
			text = new Text(parent, SWT.BORDER);

			label_1 = new Label(parent, SWT.CHECK);
			label_1.setText("����");
			text_1 = new Text(parent, SWT.BORDER);

			Button b1 = new Button(parent, SWT.PUSH);
			b1.setText("ȷ��");
			b1.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					int row, rank;
					try {
						row = Integer.parseInt(text.getText());
						rank = Integer.parseInt(text_1.getText());
						target.splitImage(row, rank);
					} catch (NumberFormatException e1) {
						MessageDialog.openWarning(shell, "��ʾ", "��ȷ������Ķ�������");
					}
					divisionImage.this.close();
				}
			});

			Button b2 = new Button(parent, SWT.PUSH);
			b2.setText("ȡ��");
			b2.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					divisionImage.this.close();
				}
			});

			return parent;
		}

		protected Button createButton(Composite parent, int id, String label,
				boolean defaultButton) {
			return null;
		}

		/**
		 * ���öԻ���ı���,ͼƬ
		 */
		protected void configureShell(Shell shell) {
			super.configureShell(shell);
			shell.setText("�趨����");
			String path = ISharedImages.IMG_DEC_FIELD_WARNING;
			shell.setImage(PlatformUI.getWorkbench().getSharedImages()
					.getImage(path));
		}

	}

	/**
	 * ���������
	 * 
	 * @author lhy
	 * 
	 */
	class WidgetManager {
		Shell shell;
		/**
		 * ��Ӧ��ť�����½��������򿪡��������桱�����趨֡����������֡���� ��ɾ�����������š���������������趨͸���ȡ�
		 */
		Button build, open, save, setFrame, addFrame, delet, play, clear,
				setAlpha;
		FramesList frames;// ֡��
		Screen screen;// ���Ŵ�
		framesImage framesImage;// ��ž���ԭͼ

		/**
		 * ���췽��
		 * 
		 * @param shell
		 *            Ҫ���ع�������shell
		 * 
		 */
		public WidgetManager(Shell shell) {
			this.shell = shell;
		}

		/**
		 * ��ʼ��,�������~~~
		 * 
		 * @param shell
		 *            ��Ҫ���ع�������shell
		 */
		public void initialize() {
			Composite bottomPanel = new Composite(shell, SWT.NONE);
			bottomPanel.setLayout(new GridLayout(2, false));
			{
				Group operatSpace = new Group(bottomPanel, SWT.NONE);
				operatSpace.setText("������");
				GridData gd = new GridData(GridData.FILL_VERTICAL);
				gd.widthHint = shell.getBounds().width / 4;

				operatSpace.setLayoutData(gd);
				operatSpace.setLayout(new GridLayout(3, true));
				{
					/**
					 * ���޸�
					 */
					Tree tree = new Tree(operatSpace, SWT.BORDER);
					GridData gd3 = new GridData(GridData.FILL_BOTH);
					gd3.horizontalSpan = 3; // ����tree��ռ2��
					tree.setLayoutData(gd3);

					build = new Button(operatSpace, SWT.NONE);
					build.setLayoutData(new GridData(GridData.FILL_HORIZONTAL
							| GridData.VERTICAL_ALIGN_CENTER));
					build.setText("�½�");

					open = new Button(operatSpace, SWT.NONE);
					open.setLayoutData(new GridData(GridData.FILL_HORIZONTAL
							| GridData.VERTICAL_ALIGN_CENTER));
					open.setText("��");

					save = new Button(operatSpace, SWT.NONE);
					save.setLayoutData(new GridData(GridData.FILL_HORIZONTAL
							| GridData.VERTICAL_ALIGN_CENTER));
					save.setText("����");

				}
			}
			{
				Group workSpace = new Group(bottomPanel, SWT.NONE);
				workSpace.setText("������");
				workSpace.setLayoutData(new GridData(GridData.FILL_BOTH));
				workSpace.setLayout(new GridLayout());
				{
					Composite c1 = new Composite(workSpace, SWT.NONE);
					GridData gd = new GridData(GridData.FILL_BOTH);
					c1.setLayoutData(gd);
					c1.setLayout(new GridLayout(5, true));
					{
						/**
						 * ���ڲ����б�ĳ�ʼ��
						 */
						frames = new FramesList(c1, SWT.V_SCROLL | SWT.MULTI
								| SWT.BORDER);
						List list = frames.getList();
						GridData gd2 = new GridData(GridData.FILL_BOTH);
						gd2.horizontalSpan = 1;
						list.setLayoutData(gd2);

						screen = new Screen(c1, SWT.BORDER, display);
						GridData gd3 = new GridData(GridData.FILL_BOTH);
						gd3.horizontalSpan = 4;
						screen.setLayoutData(gd3);

					}
					Composite c2 = new Composite(workSpace, SWT.NONE);
					GridData gd1 = new GridData(GridData.FILL_HORIZONTAL);
					gd1.heightHint = shell.getBounds().height / 2;
					c2.setLayoutData(gd1);
					c2.setLayout(new GridLayout(5, true));
					{
						/**
						 * ��ť��1
						 */
						Composite c3 = new Composite(c2, SWT.NONE);
						GridData gd3 = new GridData(GridData.FILL_VERTICAL);
						gd3.horizontalSpan = 1;
						c3.setLayoutData(gd);
						c3.setLayout(new GridLayout());
						{
							setFrame = new Button(c3, SWT.PUSH);
							setFrame.setLayoutData(new GridData(
									GridData.FILL_HORIZONTAL
											| GridData.VERTICAL_ALIGN_CENTER));
							setFrame.setEnabled(false);
							setFrame.setText("�趨֡��");

							addFrame = new Button(c3, SWT.PUSH);
							addFrame.setLayoutData(new GridData(
									GridData.FILL_HORIZONTAL
											| GridData.VERTICAL_ALIGN_CENTER));
							addFrame.setEnabled(false);
							addFrame.setText("��֡");

							delet = new Button(c3, SWT.PUSH);
							delet.setLayoutData(new GridData(
									GridData.FILL_HORIZONTAL
											| GridData.VERTICAL_ALIGN_CENTER));
							delet.setEnabled(false);
							delet.setText("ɾ����֡");

						}

						/**
						 * ѡ��֡ͼ��
						 */
						framesImage = new framesImage(c2, SWT.NO_BACKGROUND
								| SWT.DOUBLE_BUFFERED);
						GridData gd4 = new GridData(GridData.FILL_BOTH);
						gd4.horizontalSpan = 3;
						framesImage.setLayoutData(gd4);

						/**
						 * ��ť��2
						 */
						Composite c4 = new Composite(c2, SWT.NONE);
						GridData gd5 = new GridData(GridData.FILL_BOTH);
						c4.setLayoutData(gd5);
						c4.setLayout(new GridLayout());
						{
							play = new Button(c4, SWT.PUSH);
							play.setLayoutData(new GridData(
									GridData.FILL_HORIZONTAL
											| GridData.VERTICAL_ALIGN_CENTER));
							play.setEnabled(false);
							play.setText("����");

							clear = new Button(c4, SWT.PUSH);
							clear.setLayoutData(new GridData(
									GridData.FILL_HORIZONTAL
											| GridData.VERTICAL_ALIGN_CENTER));
							clear.setEnabled(false);
							clear.setText("���");

							setAlpha = new Button(c4, SWT.PUSH);
							setAlpha.setLayoutData(new GridData(
									GridData.FILL_HORIZONTAL
											| GridData.VERTICAL_ALIGN_CENTER));
							setAlpha.setEnabled(false);
							setAlpha.setText("�趨͸����");

						}
					}
				}
			}
		}

		/**
		 * �����а�ť�趨Ϊ����
		 */
		public void enableButton() {
			setFrame.setEnabled(true);
			addFrame.setEnabled(true);
			delet.setEnabled(true);
			play.setEnabled(true);
			clear.setEnabled(true);
			setAlpha.setEnabled(true);
		}

		public void copy(File original, File copy) {
			try {
				DataInputStream dis = new DataInputStream(new FileInputStream(
						original));
				DataOutputStream dos = new DataOutputStream(
						new FileOutputStream(copy));
				while (true) {
					dos.writeByte(dis.readByte());
				}
			} catch (EOFException e) {
				return;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void openFile(File file) {
			DataInputStream dis = null;
			String path = null;

			boolean play = false;

			float presentIndex = 0.0f;

			float speed = 0.0f;

			int[] framesOrder = null;

			int row = 0, rank = 0;

			int alpha = 0;

			Move[] moves = null;
			try {
				dis = new DataInputStream(new FileInputStream(file));
				while (true) {
					String a = dis.readUTF();
					if (a.equalsIgnoreCase("path")) {
						path = dis.readUTF();
					}
					if (a.equalsIgnoreCase("play")) {
						play = dis.readBoolean();
					}
					if (a.equalsIgnoreCase("presentIndex")) {
						presentIndex = dis.readFloat();
					}
					if (a.equalsIgnoreCase("speed")) {
						speed = dis.readFloat();
					}
					if (a.equalsIgnoreCase("framesOrder")) {
						framesOrder = new int[dis.readInt()];
						for (int i = 0; i < framesOrder.length; i++) {
							framesOrder[i] = dis.readInt();
						}
					}
					if (a.equalsIgnoreCase("Row")) {
						row = dis.readInt();
					}
					if (a.equalsIgnoreCase("Rank")) {
						rank = dis.readInt();
					}
					if (a.equalsIgnoreCase("alpha")) {
						alpha = dis.readInt();
					}
					if (a.equalsIgnoreCase("Moves")) {
						moves = new Move[dis.readInt()];
						for (int i = 0; i < moves.length; i++) {
							moves[i] = new Move(target, dis.readInt(),
									dis.readInt());
						}
					}
				}
			} catch (EOFException eee) {
				target.readImage(new File(path));
				target.splitImage(row, rank);
				target.setPlay(play);
				target.setSpeed(speed);
				target.setAlpha(alpha);
				target.setFraOrderLen(framesOrder.length);
				for (int i = 0; i < framesOrder.length; i++) {
					target.addFraOrder(i, framesOrder[i]);
				}
				target.setPresentIndex(presentIndex);
				for (int i = 0; i < moves.length; i++) {
					target.addMoves(i, moves[i]);
				}
				frames.setFrames(framesOrder.length);
				for (int i = 0; i < framesOrder.length; i++) {
					frames.setRelation(i, framesOrder[i]);
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException ee) {
				ee.printStackTrace();
			}
		}

		/**
		 * ���������¼���Ӧ
		 */
		public void addListeners() {
			build.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					String filename = null;
					InputDialog input;
					try {
						input = new InputDialog(shell, "�½�", "������:", "�����뾫����",
								null);
						if (input.open() == Window.OK) {
							filename = input.getValue();
						} else {
							return;
						}
						if (filename.equals("")) {
							MessageDialog.openWarning(shell, "�½�", "�ļ�������Ϊ��");
							return;
						}
						project = new File("E:/saveFile/src/" + filename
								+ ".sdua");
						if (project.createNewFile()) {
							FileDialog fileselect = new FileDialog(shell,
									SWT.SINGLE);
							fileselect.setText("ѡ�����ͼƬ");
							fileselect.setFilterNames(new String[] { "ͼƬ�ļ�" });
							fileselect.setFilterExtensions(new String[] {
									"*.jpg", "*.png", "*.bmp" });
							String url = "";
							url = fileselect.open();
							if (url == null || url.equals("")) {
								project.delete();
								return;
							}
							File f = new File(url);
							File picture = new File("E:/saveFile/images/"
									+ f.getName());
							if (!picture.exists()) {
								picture.createNewFile();
								copy(f, picture);
							}
							target = new Target(display, screen);
							target.readImage(picture);
							new divisionImage(WidgetManager.this.shell).open();
							screen.setTarget(target);
							framesImage.initialize();
							enableButton();
							canSave = true;
						} else {
							MessageDialog.openWarning(shell, "�½�", "�ļ��Ѵ��ڣ����");
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});

			open.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					FileDialog fileselect = new FileDialog(shell, SWT.SINGLE);
					fileselect.setText("ѡ�񶯻��ļ�");
					fileselect.setFilterNames(new String[] { "�����ļ�" });
					fileselect.setFilterExtensions(new String[] { "*.sdua" });
					String url = "";
					url = fileselect.open();
					project = new File(url);
					target = new Target(display, screen);
					openFile(project);
					screen.setTarget(target);
					framesImage.initialize();
					enableButton();
					canSave = true;
				}
			});// ����¼�

			save.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					if (!canSave)
						return;
					try {
						DataOutputStream dos = new DataOutputStream(
								new FileOutputStream(project.getPath(), true));

						String path = target.getPath();

						boolean play = target.isPlay();

						float presentIndex = target.getPresentIndex();

						float speed = target.getSpeed();

						ArrayList<Integer> framesOrder = target.getOrder();

						int row = target.getRow(), rank = target.getRank();

						int alpha = target.getAlpha();

						ArrayList<Move> moves = target.getMoves();

						dos.writeUTF("path");
						dos.writeUTF(path);

						dos.writeUTF("play");
						dos.writeBoolean(play);

						dos.writeUTF("presentIndex");
						dos.writeFloat(presentIndex);

						dos.writeUTF("speed");
						dos.writeFloat(speed);

						dos.writeUTF("framesOrder");
						dos.writeInt(framesOrder.size());
						for (int i = 0; i < framesOrder.size(); i++) {
							dos.writeInt(framesOrder.get(i));
						}

						dos.writeUTF("Row");
						dos.writeInt(row);

						dos.writeUTF("Rank");
						dos.writeInt(rank);

						dos.writeUTF("Alpha");
						dos.writeInt(alpha);

						dos.writeUTF("Moves");
						dos.writeInt(moves.size());
						for (int i = 0; i < moves.size(); i++) {
							dos.writeInt(moves.get(i).getX());
							dos.writeInt(moves.get(i).getY());
						}

						dos.close();
						dos = null;

					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					MessageDialog.openInformation(shell, "����", "����ɹ�");
				}
			});

			setFrame.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					new SetFrame(WidgetManager.this.shell).open();
				}
			});

			addFrame.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					new AddFrame(WidgetManager.this.shell).open();
				}
			});

			delet.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					frames.deletFrames();
				}
			});

			play.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					if (target.isFraOrderInitialize()) {
						target.setPresentIndex(0);
						target.setPlay(true);
					}
				}
			});

			clear.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					target.clear();
					manager.frames.clear();
				}
			});

			setAlpha.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					new setAlpha(WidgetManager.this.shell).open();
				}
			});
		}

		/**
		 * �����б���
		 * 
		 * @author lhy
		 * 
		 */
		class FramesList implements SelectionListener {
			List frameList;
			String[] frames;
			HashMap<String, Integer> maps;

			/**
			 * ���췽�� ����
			 * 
			 * @param parent
			 *            ����
			 * @param style
			 *            ����
			 */
			public FramesList(Composite parent, int style) {
				frameList = new List(parent, style);
				frameList.addSelectionListener(this);
				maps = new HashMap<String, Integer>();
			}

			/**
			 * ��֡
			 * 
			 * @param index
			 *            ��ʼ֡������
			 * @param number
			 *            ��֡��
			 */
			public void addFrames(int index, int number) {
				if (!target.insertFrameOrder(index, number)) {
					MessageDialog.openError(shell, "����", "�Ƿ�����");
					return;
				}

				for (int i = 0; i < number; i++) {
					frameList.add("��" + (index + i + 1) + "֡", index + i);
				}
				for (int i = index + number; i < frameList.getItemCount(); i++) {
					frameList.setItem(i, "��" + (i + 1) + "֡");
				}
				frames = frameList.getItems();
			}

			/**
			 * ���~~~~�ع��ʼ��
			 */
			public void clear() {
				maps.clear();
				frames = null;
				frameList.removeAll();
			}

			/**
			 * ɾ��ѡ��֡
			 */
			public void deletFrames() {
				String[] delet = frameList.getSelection();
				for (int i = 0; i < delet.length; i++) {
					maps.remove(delet[i]);
					frameList.remove(delet[i]);
				}
				target.setFraOrderLen(frameList.getItemCount());
				for (int i = 0; i < frameList.getItemCount(); i++) {
					Integer order = maps.get(frameList.getItem(i));
					target.addFraOrder(i, order);
					frameList.setItem(i, "��" + (i + 1) + "֡");
					maps.remove(frameList.getItem(i));
					maps.put("��" + (i + 1) + "֡", order);

				}
				frames = frameList.getItems();
			}

			/**
			 * ��ȡ�б�
			 * 
			 * @return �б�
			 */
			public List getList() {
				return frameList;
			}

			/**
			 * �趨List
			 * 
			 * @param frames
			 *            List������
			 */
			public void setFrames(String[] frames) {
				this.frames = frames;
				frameList.setItems(frames);
				maps.clear();
			}

			/**
			 * �趨List
			 * 
			 * @param number
			 *            list����
			 */
			public void setFrames(int number) {
				frames = new String[number];
				for (int i = 0; i < number; i++) {
					frames[i] = new String("��" + (i + 1) + "֡");
				}
				frameList.setItems(frames);
				target.setFraOrderLen(number);
				maps.clear();
			}

			/**
			 * �趨��Ӧ��ϵ
			 * 
			 * @param keys
			 * @param value
			 */
			public void setRelation(String[] keys, Integer value) {
				for (int i = 0; i < keys.length; i++) {
					maps.put(keys[i], value);
					for (int j = 0; j < frames.length; j++) {
						if (keys[i].equals(frames[j]))
							target.addFraOrder(j, value);
					}
				}
			}

			public void setRelation(int index, Integer value) {
				target.addFraOrder(index, value);
			}

			/**
			 * ѡ��ĳһ֡������ѡ��~~~������֮~~~~return��
			 */
			public void widgetSelected(SelectionEvent e) {
				int selectedItem = frameList.getSelectionIndex();
				target.setPresentIndex(selectedItem);
			}

			/**
			 * û��
			 */
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		}

		/**
		 * ��ž����ȫ������֡
		 * 
		 * @author lhy
		 * 
		 */
		class framesImage extends Canvas implements MouseTrackListener,
				PaintListener, Runnable, MouseListener {
			int row, rank;// ͼƬҪ�гɵ�����������
			Image[] frameImages;// �����ȫ��֡ͼ��
			Rectangle[] frameRects;// ����ȫ��֡ͼ�����߿�
			Image image;// �����ͼ��
			boolean work;// ������Ĺ���״̬
			boolean move;// ͼ���Ƿ�Ӧ���ƶ�
			int refreshRate = 200;// ˢ����
			int speed =25;// ͼ���ƶ��ٶ�
			int outX, outY;// ������һ���뿪ʱ������
			int offX, offY;
			/**
			 * canvas�Ŀ��ߺ��м������Լ�ÿ֡�Ŀ��
			 */
			int width, height, centerX, centerY, imWidth, imHeight;
			int choosen = -1;// ��ѡ�е���������

			/**
			 * ���췽��
			 * 
			 * @param parent
			 * @param style
			 */
			public framesImage(Composite parent, int style) {
				super(parent, style);
				this.work = false;
			}

			/**
			 * ��ʼ��
			 */
			public void initialize() {
				if (target == null)
					return;
				row = target.getRow();
				rank = target.getRank();
				frameImages = target.getFrames();
				frameRects = new Rectangle[row * rank];
				image = target.getImage();
				imWidth = image.getBounds().width / rank;
				imHeight = image.getBounds().height / row;
				addPaintListener(this);
				addMouseTrackListener(this);
				addMouseListener(this);
				work = true;
				new Thread(this).start();
			}

			/**
			 * ����ÿ֡�İ�Χ��
			 */
			public void setFrameRect() {
				int r = 0, c = 0;
				for (int i = 0; i < row * rank; i++) {
					frameRects[i] = new Rectangle(offX + r * imWidth, offY + c
							* imHeight, imWidth - 1, imHeight - 1);
					r += 1;
					if (r >= rank) {
						r = 0;
						c += 1;
					}
				}
			}

			/**
			 * ���Ʒ���
			 */
			public void paintControl(PaintEvent e) {
				if (!isWork())
					return;
				this.width = this.getSize().x;
				this.height = this.getSize().y;
				centerX = width / 2;
				centerY = height / 2;
				Move();
				setFrameRect();
				GC g = e.gc;
				Image bufferImage = new Image(display, this.getSize().x,
						this.getSize().y);
				GC bufferg = new GC(bufferImage);
				bufferg.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
				bufferg.fillRectangle(this.getClientArea());
				bufferg.drawImage(image, offX, offY);
				bufferg.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
				drawRect(bufferg);
				bufferg.drawRectangle(offX + 1, offY + 1,
						image.getBounds().width - 3,
						image.getBounds().height - 3);
				dealChoosen(bufferg);

				g.drawImage(bufferImage, 0, 0);
				bufferg.dispose();
				g.dispose();

			}

			/**
			 * �ƶ������ϵ�ͼ��
			 */
			public void Move() {
				if (!move)
					return;
				if (outX < centerX) {
					if (offX >= 0) {
						offX = 0;
					} else {
						offX += speed;
					}
				}
				if (outX > centerX) {
					if (offX <= width - image.getBounds().width) {
						offX = width - image.getBounds().width;
					} else {
						offX -= speed;
					}
				}
				if (outY < centerY) {
					if (offY >= 0) {
						offY = 0;
					} else {
						offY += speed;
					}

				}
				if (outY > centerY) {
					if (offY <= height - image.getBounds().height) {
						offY = height - image.getBounds().height;
					} else {
						offY -= speed;
					}
				}

			}

			/**
			 * ���ư�Χ��
			 */
			public void drawRect(GC g) {
				for (int i = 0; i < frameRects.length; i++) {
					g.drawRectangle(frameRects[i]);
				}
			}

			/**
			 * ����ѡ�е�����
			 * 
			 * @param g
			 */
			public void dealChoosen(GC g) {
				if (choosen == -1)
					return;
				Rectangle preRect = g.getClipping();
				int preAlpha = g.getAlpha();
				g.setClipping(frameRects[choosen]);
				g.setAlpha(preAlpha / 2);
				g.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
				g.fillRectangle(offX, offY, image.getBounds().width,
						image.getBounds().height);
				g.drawImage(image, offX, offY);
				g.setClipping(preRect);
				g.setAlpha(preAlpha);

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
			 * ��ȡ����״̬
			 * 
			 * @return ����״̬
			 */
			public boolean isWork() {
				return work;
			}

			/**
			 * �̳еķ���
			 * 
			 * @param e
			 */
			public void mouseEnter(MouseEvent e) {
				move = false;
			}

			/**
			 * ������뿪ʱ~~~
			 */
			public void mouseExit(MouseEvent e) {
				this.outX = e.x;
				this.outY = e.y;
				move = true;

			}

			/**
			 * �̳еķ���
			 */
			public void mouseHover(MouseEvent e) {

			}

			/**
			 * �̳еķ���
			 */
			public void mouseDoubleClick(MouseEvent e) {
			}

			/**
			 * ��갴��
			 */
			public void mouseDown(MouseEvent e) {
				if (frames.getList().getSelectionCount() == 0) {
					return;
				}
				Point p = new Point(e.x, e.y);
				for (int i = 0; i < frameRects.length; i++) {
					if (frameRects[i].contains(p)) {
						choosen = i;
						frames.setRelation(frames.getList().getSelection(), i);
						return;
					}
				}

			}

			/**
			 * ���̧��
			 */
			public void mouseUp(MouseEvent e) {
			}

			public void run() {
				try {
					while (work) {
						display.asyncExec(new Runnable() {
							public void run() {
								if (!framesImage.this.isDisposed())
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

			public boolean onDispose() {
				if (isWork()) {
					this.setWork(false);
				}
				if (!isDisposed()) {
					this.dispose();
				}
				frameImages = null;
				image = null;
				return true;
			}
		}

	}

	/**
	 * �ͷ�ϵͳ��Դ
	 * 
	 * @return
	 */
	public boolean dispose() {
		if (!shell.isDisposed()) {
			shell.dispose();
			shell = null;
		}
		return true;
	}
}