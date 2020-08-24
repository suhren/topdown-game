package game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import framework.DrawUtility;
import window.GraphicsApplication;
import window.GraphicsApplicationFrame;
import window.GraphicsApplicationWindow;
import window.GraphicsBuffer;
import window.InputBuffer;
import window.InputListener;

public abstract class Game implements GraphicsApplication, InputListener {
	
	private GraphicsApplicationWindow window;
	private GraphicsBuffer graphicsBuffer;
	private InputBuffer inputBuffer = new InputBuffer();
	private boolean drawFPS = false;
	private int fps;
	private static final Font FPS_FONT = new Font("Arial", Font.PLAIN, 12);
	
	public Game() {
		graphicsBuffer = new GraphicsBuffer(GraphicsApplicationFrame.DEFAULT_WIDTH, GraphicsApplicationFrame.DEFAULT_HEIGHT);
		DrawUtility.setGraphicsBuffer(graphicsBuffer);
	}
	public void update(double dT) {
		fps = (int)(1.0f / dT);
		updateGame(dT);
		inputBuffer.inputProccessed();
		redraw();
	}
	public void redraw() {
		graphicsBuffer.clearGraphics();
		drawGameImage(graphicsBuffer.getGraphics(), graphicsBuffer.getRenderWidth(), graphicsBuffer.getRenderHeight());
		if (drawFPS) {
			graphicsBuffer.getGraphics().setColor(Color.BLACK);
			graphicsBuffer.getGraphics().fillRect(0, 0, 64, 24);
			graphicsBuffer.getGraphics().setColor(Color.WHITE);
			graphicsBuffer.getGraphics().setFont(FPS_FONT);
			graphicsBuffer.getGraphics().drawString("FPS: " + fps, 10, 15);
		}
	}
	public void setRenderSize(int width, int height) {
		graphicsBuffer.setRenderDimensions(width, height);
	}
	@Override
	public GraphicsBuffer getGraphicsBuffer() {
		return graphicsBuffer;
	}
	@Override
	public InputBuffer getInputBuffer() {
		return inputBuffer;
	}
	@Override
	public void setGraphicsApplicationWindow(GraphicsApplicationWindow window) {
		this.window = window;
	}
	protected InputBuffer getInput() {
		return inputBuffer;
	}
	protected void toggleFPS() {
		drawFPS = !drawFPS;
	}
	protected GraphicsApplicationWindow getWindow() {
		return window;
	}
	
	protected abstract void updateGame(double dT);
	protected abstract void drawGameImage(Graphics g, int renderWidth, int renderHeight);
}