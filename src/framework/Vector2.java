package framework;

public class Vector2 {
	private double x, y;
	public class Direction {
		public static final int RIGHT = 0;
		public static final int UP = 1;
		public static final int LEFT = 2;
		public static final int DOWN = 3;
	}
	
	public Vector2() {
		this(0, 0);
	}
	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public double getX() { return x; }
	public double getY() { return y; }
	public void setX(double x) { this.x = x; }
	public void setY(double y) { this.y = y; }
	public double getLength() { return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)); }
	public double getAngle() { return Math.atan2(y, x); }
	public void addX(double v) { x += v; }
	public void addY(double v) { y += v; }
 	public void scale(double s) { x *= s; y *= s; }
	public void setLength(double l) {
		if (getLength() > 0)
			scale(l / getLength());
	}
	public void constrainLength(double l) {
		if (getLength() > l)
			setLength(l);
	}
	public void constrainLengthX(double l) {
		if (Math.abs(x) > l)
			setX(Math.signum(x) * l);
	}
	public void constrainLengthY(double l) {
		if (Math.abs(y) > l)
			setX(Math.signum(y) * l);
	}
	public void add(Vector2 v) {
		x += v.getX();
		y += v.getY();
	}
	public void subtract(Vector2 v) {
		x -= v.getX();
		y -= v.getY();
	}
	public void subtractLengthToZero(double l) {
		double d = getLength() - l;
		if (d > 0)
			setLength(d);
		else
			setLength(0);
		
	}
	public Vector2 getScaledInstance(double s) {
		return new Vector2(x*s, y*s);
	}
	public double distanceTo(Vector2 v) {
		return Math.sqrt(Math.pow(x - v.getX(), 2) + Math.pow(y - v.getY(), 2));
	}
	public Vector2 copy() {
		return new Vector2(x, y);
	}
	public void normalize() {
		if (getLength() > 0)
			scale(1.0 / getLength());
	}
	public Vector2 getNormalizedInstance() {
		Vector2 v = this.copy();
		v.normalize();
		return v;
	}
	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}
	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof Vector2) {
			Vector2 v = (Vector2)o;
			return v.getX() == this.x && v.getY() == this.y;
		}	
		return false;
	}
	public void set(Vector2 v) {
		x = v.getX();
		y = v.getY();
	}
	public int getDirection() {
		if (Math.abs(y) >= Math.abs(x))
			return (y >= 0) ? Direction.DOWN : Direction.UP;
		else
			return (x >= 0) ? Direction.RIGHT : Direction.LEFT;
	}
	public static Vector2 getDirectionVector(int direction) {
		switch(direction) {
			case Direction.RIGHT:
				return new Vector2(1, 0);
			case Direction.UP:
				return new Vector2(0, -1);
			case Direction.LEFT:
				return new Vector2(-1, 0);
			case Direction.DOWN:
				return new Vector2(0, 1);
			default:
					return null;
		}
	}
	public Vector2 getAddedInstance(Vector2 v) { return new Vector2(x + v.getX(), y + v.getY()); }
	public Vector2 getSubtractedInstance(Vector2 v) { return new Vector2(x - v.getX(), y - v.getY()); }
	public static int GetOppositeDirection(int direction) {
		return (direction + 2) % 4;
	}
}
