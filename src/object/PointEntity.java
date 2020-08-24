package object;

import framework.Vector2;
import world.World;

public abstract class PointEntity extends Entity {
	private Vector2 position;
	
	public PointEntity(World world) {
		this(0, 0, world);
	}
	public PointEntity(double posX, double posY, World world) {
		super(world);
		position = new Vector2(posX, posY);
	}
	
	public Vector2 getPosition() { return position; }
	public double getX() { return position.getX(); }
	public double getY() { return position.getY(); }
	public void setPosition(Vector2 v) { position = v.copy(); }
	public void setX(double x) { position.setX(x); }
	public void setY(double y) { position.setY(y); }
}