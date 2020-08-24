package object;

import framework.Vector2;
import world.World;

public class Brush extends PointEntity {
	private int width, height;
	
	public Brush(double posX, double posY, int width, int height) {
		this(posX, posY, width, height, null);
	}
	public Brush(double posX, double posY, int width, int height, World world) {
		super(posX, posY, world);
		this.width = width;
		this.height = height;
	}
	
	public Vector2 getTopLeft() { return new Vector2(getX() - width / 2, getY() - height / 2); }
	public Vector2 getBottomRight() { return new Vector2(getX() + width / 2, getY() + height / 2); }
	public double getTopLeftX() { return getX() - width / 2; }
	public double getTopLeftY() { return getY() - height / 2; }
	public double getBottomRightX() { return getX() + width / 2; }
	public double getBottomRightY() { return getY() + height / 2; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public boolean intersects(Brush b) {
		if (getTopLeftX() > b.getBottomRightX() || getBottomRightX() < b.getTopLeftX())
			return false;
		if (getTopLeftY() > b.getBottomRightY() || getBottomRightY() < b.getTopLeftY())
			return false;
		return true;
	}
	public boolean contains(Brush b) {
		return getTopLeftX() < b.getTopLeftX() && 
			   getBottomRightX() > b.getBottomRightX() &&
			   getTopLeftY() < b.getTopLeftY() && 
			   getBottomRightY() > b.getBottomRightY();
	}

	@Override
	public void draw() {
	}
	@Override
	public void update(double dT) {
	}
	@Override
	public void onEntityCreation() {
	}
	@Override
	public int getDrawY() {
		return (int)getY();
	}
	@Override
	protected void onEntityDispose() {
	}
}