package window;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import java.awt.Graphics;

public class GraphicsBuffer {
	private Graphics graphics;
	private BufferedImage image;
	private JPanel panel;
	
	public GraphicsBuffer(int width, int height) {
		initializeBuffer(width, height);
	}
	public void clearGraphics() {
		graphics.clearRect(0, 0, image.getWidth(), image.getHeight());
	}
	public Image getImage() {
		return image;
	}
	public Graphics getGraphics() {
		return graphics;
	}
	public int getRenderWidth() {
		return image.getWidth();
	}
	public int getRenderHeight() {
		return image.getHeight();
	}
	public void setRenderDimensions(int width, int height) {
		initializeBuffer(width, height);
	}
	private void initializeBuffer(int width, int height) {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		graphics = image.createGraphics();
		clearGraphics();
	}
	public int getPanelWidth() {
		return panel.getWidth();
	}
	public int getPanelHeight() {
		return panel.getHeight();
	}
}
