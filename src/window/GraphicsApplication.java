package window;

public interface GraphicsApplication {
	public GraphicsBuffer getGraphicsBuffer();
	public void setRenderSize(int width, int height);
	public void setGraphicsApplicationWindow(GraphicsApplicationWindow window);
}