package window;
import framework.Vector2;

public class InputBuffer {
	
	private static final int N_KEYS = 1024;
	private static final int N_BUTTONS = 3;
	
	private boolean keysDown[] = new boolean[N_KEYS];			// Key is down
	private boolean keysPressed[] = new boolean[N_KEYS];		// Key was up but is now down
	private boolean mouseDown[] = new boolean[N_BUTTONS];		// Button is down
	private boolean mousePressed[] = new boolean[N_BUTTONS];	// Button was up but is now down
	private Vector2 mousePosition = new Vector2();
	private boolean mouseMoved = false;
	
	public boolean keyDown(int keyCode) { return keysDown[keyCode - 1]; }
	public boolean keyUp(int keyCode) { return !keysDown[keyCode - 1]; }
	public boolean keyPressed(int keyCode) { return keysPressed[keyCode - 1]; }
	public boolean[] getKeysDown() { return keysDown; }
	public boolean mouseDown(int button) { return mouseDown[button - 1]; }
	public boolean mouseUp(int button) { return !mouseDown[button - 1]; }
	public boolean mousePressed(int button) { return mousePressed[button - 1]; }
	public boolean[] getmouseDown() { return mouseDown; }
	public Vector2 getMousePosition() { return mousePosition; }
	public void setKeyDown(int keyCode, boolean down) { keysDown[keyCode - 1] = down; }
	public void setKeyPressed(int keyCode, boolean pressed) { keysPressed[keyCode - 1] = pressed; }
	public void setMouseDown(int mouseButton, boolean down) { mouseDown[mouseButton - 1] = down; }
	public void setMousePressed(int mouseButton, boolean pressed) { mousePressed[mouseButton - 1] = pressed; }
	public void setMousePosition(Vector2 position) { mousePosition = position; }
	public void inputProccessed() {
		for (int i = 0; i < N_KEYS; i++)
			keysPressed[i] = false;
		for (int i = 0; i < N_BUTTONS; i++)
			mousePressed[i] = false;
		mouseMoved = false;
	}
	public void setMousePosition(double x, double y) {
		mousePosition.setX(x);
		mousePosition.setY(y);
	}
	public boolean mouseMoved() {
		return mouseMoved;
	}
	public void setMouseMoved(boolean b) {
		mouseMoved = b;
	}
}