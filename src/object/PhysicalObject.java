package object;
import framework.Vector2;
import world.World;

public abstract class PhysicalObject extends MovingObject {
	private static final double DEFAULT_MASS = 1.0;
	private static final double DEFAULT_FRICTION = 800.0;
	private double mass;
	private Vector2 accelerationVector;
	private double friction, deacceleration;
	
	public PhysicalObject(double posX, double posY, int width, int height, World world) {
		this(posX, posY, width, height, DEFAULT_FRICTION, DEFAULT_MASS, world);
	}
	public PhysicalObject(double posX, double posY, int width, int height, double friction, double mass, World world) {
		super(posX, posY, width, height, world);
		this.mass = mass;
		this.friction = friction;
		accelerationVector = new Vector2();
	}
	
	public void applyForce(Vector2 force) { applyAcceleration(force.getScaledInstance(1.0 / mass)); }
	public void applyForceX(double force) { applyAccelerationX(force / mass); }
	public void applyForceY(double force) { applyAccelerationY(force / mass); }
	public void applyAcceleration(Vector2 acceleration) { accelerationVector.add(acceleration); }
	public void applyAccelerationX(double acceleration) { accelerationVector.addX(acceleration); }
	public void applyAccelerationY(double acceleration) { accelerationVector.addY(acceleration); }
	public void increaseVelocity(Vector2 acceleration, double maxSpeed) {
		double oldLength = getVelocityVector().getLength();
		double newLength = getVelocityVector().copy().getAddedInstance(acceleration).getLength();
		getVelocityVector().add(acceleration);
		if (oldLength < newLength && newLength > maxSpeed)
			getVelocityVector().constrainLength(oldLength);
	}
	public double getMass() { return mass; }
	@Override
	public void update(double dT) {
		super.update(dT);
		deacceleration = getVelocityVector().getLength();
		getVelocityVector().add(accelerationVector.getScaledInstance(dT));
		getVelocityVector().subtractLengthToZero(friction * dT);
		accelerationVector.scale(0);
		deacceleration = getVelocityVector().getLength() - deacceleration;
	}
	public Vector2 getAccelerationVector() { return accelerationVector; }
	public double getDeacceleration() { return deacceleration; }
}