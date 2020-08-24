package window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputHandler implements MouseListener, MouseMotionListener, KeyListener {
	
	private InputBuffer inputBuffer;
	private DrawPanel panel;
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (inputBuffer != null) {
			inputBuffer.setKeyPressed(e.getKeyCode(), !inputBuffer.keyDown(e.getKeyCode()));
			inputBuffer.setKeyDown(e.getKeyCode(), true);
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if (inputBuffer != null) {
			inputBuffer.setKeyPressed(e.getKeyCode(), false);
			inputBuffer.setKeyDown(e.getKeyCode(), false);
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if (inputBuffer != null) {
			inputBuffer.setMousePressed(e.getButton(), !inputBuffer.mouseDown(e.getButton()));
			inputBuffer.setMouseDown(e.getButton(), true);
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if (inputBuffer != null) {
			inputBuffer.setMousePressed(e.getButton(), false);
			inputBuffer.setMouseDown(e.getButton(), false);
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		double x = e.getX() * 1.0 / panel.getWidth() * panel.getGraphicsBuffer().getRenderWidth();
		double y = e.getY() * 1.0 / panel.getHeight() * panel.getGraphicsBuffer().getRenderHeight();
		inputBuffer.setMousePosition(x, y);
		inputBuffer.setMouseMoved(true);
	}
	public void setInputBuffer(InputBuffer listener) { inputBuffer = listener; }
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}
	
	public void setPanel(DrawPanel panel) {
		this.panel = panel;
	}
	
	// Unused
	@Override
	public void keyTyped(KeyEvent e) { }
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
}