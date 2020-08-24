package object;
import framework.Vector2;

public class Collision {
	private Vector2 newPosition, newVelocity;
	private double timeToCollision = 0;
	public Collision(double newX, double newY, double velX, double velY, double timeToCollision) {
		newPosition = new Vector2(newX, newY);
		newVelocity = new Vector2(velX, velY);
		this.timeToCollision = timeToCollision;
	}
	public Vector2 getNewPosition() { return newPosition; }
	public Vector2 getNewVelocity() { return newVelocity; }
	public double getTimeToCollision() { return timeToCollision; };
	@Override
	public String toString() {
		return newPosition + " " + newVelocity;
	}
}