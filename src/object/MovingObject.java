package object;
import framework.Vector2;
import world.World;

public abstract class MovingObject extends Brush {
	private static final double DEFAULT_MAXSPEED = 2000.0;
	private static final double DEFAULT_MINSPEED = 40.0;
	
	private Vector2 velocityVector;
	private double minSpeed;
	private double maxSpeed;
	private boolean moving = false;
	
	public MovingObject(double posX, double posY, int width, int height, World world) {
		super(posX, posY, width, height, world);
		minSpeed = DEFAULT_MINSPEED;
		maxSpeed = DEFAULT_MAXSPEED;
		velocityVector = new Vector2();
	}
	
	@Override
	public void update(double dT) {
		velocityVector.constrainLength(maxSpeed);
		moving = velocityVector.getLength() > minSpeed;
		if (moving)
			getPosition().add(velocityVector.getScaledInstance(dT));
	}
	public Vector2 getVelocityVector() { return velocityVector; }
	public void setVelocityX(double a) { velocityVector.setX(a); }
	public void setVelocityY(double a) { velocityVector.setY(a); }
	public double getMaxSpeed() { return maxSpeed; }
	public boolean isMoving() { return moving; }
}