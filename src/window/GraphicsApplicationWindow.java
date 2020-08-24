package window;

import java.awt.Color;

public interface GraphicsApplicationWindow {
	
	static final String DEFAULT_TITLE = "GameWindow";
	static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	static final int DEFAULT_WIDTH = 1024; //1280
	static final int DEFAULT_HEIGHT = 576; //720
	static final int DEFAULT_RENDER_MODE = 2;
	
	public void showWindow();
	public void hideWindow();
	public void redraw();
	public void setRenderMode(int mode);
	public void setWindowDimensions(int width, int height);
	public void setGraphicsApplication(GraphicsApplication application);
	public void setInputListener(InputListener listener);
}