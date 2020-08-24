package window;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class GraphicsApplicationFrame extends JFrame implements GraphicsApplicationWindow {
	private static final long serialVersionUID = 1L;
	private DrawPanel drawPanel;
	private InputHandler inputHandler = new InputHandler();
	
	public GraphicsApplicationFrame() {
		super();
		setUpFrame();
		this.addKeyListener(inputHandler);
		drawPanel.addMouseListener(inputHandler);
		drawPanel.addMouseMotionListener(inputHandler);
		
		inputHandler.setPanel(drawPanel);
		
		setRenderMode(DEFAULT_RENDER_MODE);
		
		// Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		// Create a new blank cursor.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");
		// Set the blank cursor to the JFrame.
		drawPanel.setCursor(blankCursor);
	}
	private void setUpFrame() {
		setIgnoreRepaint(true);
		setTitle(DEFAULT_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		drawPanel = new DrawPanel();
		setBackground(DEFAULT_BACKGROUND_COLOR);
		getContentPane().add(drawPanel);
		getContentPane().setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		pack();
	}
	@Override
	public void showWindow() {
		setVisible(true);
	}
	@Override
	public void hideWindow() {
		setVisible(false);
	}
	@Override
	public void redraw() {
		drawPanel.repaint();
	}
	@Override
	public void setGraphicsApplication(GraphicsApplication application) {
		drawPanel.setGraphicsBuffer(application.getGraphicsBuffer());
	}
	@Override
	public void setRenderMode(int mode) {
		if (mode == 1) {
			resizable(false);
			setWindowDimensions(drawPanel.getRenderWidth(), drawPanel.getRenderHeight());
			stretchImage(false);
		}
		else if (mode == 2) {
			resizable(true);
			stretchImage(true);
		}
		else {
			resizable(true);
			stretchImage(false);
		}
	}
	public void resizable(boolean b) {
		this.setResizable(b);
	}
	public void setWindowDimensions(int width, int height) {
		this.setSize(new Dimension(drawPanel.getRenderWidth(), drawPanel.getRenderHeight()));
		pack();
	}
	public void stretchImage(boolean b) {
		drawPanel.stretchImage(b);
	}
	@Override
	public void setInputListener(InputListener listener) {
		inputHandler.setInputBuffer(listener.getInputBuffer());
	}
}