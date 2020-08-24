package window;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GraphicsBuffer graphicsBuffer = null;
	private boolean stretchImage = false;
	
	public DrawPanel() {
		this.setIgnoreRepaint(true);
	}
	@Override
    public void paintComponent(Graphics g) {
		if (stretchImage)
			g.drawImage(graphicsBuffer.getImage().getScaledInstance(super.getParent().getWidth(), super.getParent().getHeight(), Image.SCALE_DEFAULT), 0, 0, null);
		else
			g.drawImage(graphicsBuffer.getImage(), 0, 0, null);
    }
	/**
	 * Sets the reference to the graphics buffer we are going to draw on this panel.
	 * @param  graphicsBuffer  a reference to the graphics buffer
	 */
	public void setGraphicsBuffer(GraphicsBuffer graphicsBuffer) {
		this.graphicsBuffer = graphicsBuffer;
	}
	public GraphicsBuffer getGraphicsBuffer() {
		return graphicsBuffer;
	}
	public int getRenderWidth() {
		if (graphicsBuffer != null)
			return graphicsBuffer.getRenderWidth();
		else
			return GraphicsApplicationWindow.DEFAULT_WIDTH;
	}
	public int getRenderHeight() {
		if (graphicsBuffer != null)
			return graphicsBuffer.getRenderWidth();
		else
			return GraphicsApplicationWindow.DEFAULT_HEIGHT;
	}
	public void stretchImage(boolean b) {
		stretchImage = b;
	}
}