package object;

public class ObjectTile {
	private int x, y, type;
	
	public ObjectTile (int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public int getType() { return type; }
}