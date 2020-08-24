package game;
import framework.GameUtility;
import framework.Vector2;
import object.PointEntity;
import window.GraphicsBuffer;
import world.World;

public class Camera {
	private World world;
	private GraphicsBuffer graphicsBuffer;
	private PointEntity target;
	private Vector2 position;
	private boolean trackTarget = false;
	private double trackRatioX = 0;
	private double trackRatioY = 0;
	private double minX, minY, maxX, maxY;
	private static boolean drawIntersectingTiles 	= false;
	private static boolean drawVelocity 			= false;
	private static boolean drawCollisionBox 		= false;
	private static boolean drawTileRectangles 		= false;
	private static boolean drawObjectTiles 			= false;
	private static boolean drawAttacks 				= false;
	private static boolean drawHealthBars 			= true;
	
	public Camera(World world, GraphicsBuffer graphicsBuffer) {
		this.graphicsBuffer = graphicsBuffer;
		setWorld(world);
		position = new Vector2();
	}
	public void setWorld(World world) {
		this.world = world;
		minX = graphicsBuffer.getRenderWidth() / 2;
		minY = graphicsBuffer.getRenderHeight() / 2;
		maxX = world.getTileMap().getPixelWidth() - graphicsBuffer.getRenderWidth() / 2;
		maxY = world.getTileMap().getPixelHeight() - graphicsBuffer.getRenderHeight() / 2;
	}
	public void update(double dT) {
		if (trackTarget)
			centerOnTarget();
		limitPosition();
	}
	public void setTarget(PointEntity o) {
		trackTarget = true;
		target = o;
		position = o.getPosition().copy();
	}
	public void centerOnTarget() {
		double limitX = 0.5 * trackRatioX * graphicsBuffer.getRenderWidth();
		double limitY = 0.5 * trackRatioY * graphicsBuffer.getRenderHeight();
		double offsetX = target.getPosition().getX() - position.getX();
		double offsetY = target.getPosition().getY() - position.getY();
		double overStepX = Math.abs(offsetX) - Math.abs(limitX);
		double overStepY = Math.abs(offsetY) - Math.abs(limitY);
		
		if (overStepX > 0)
			position.setX(position.getX() + Math.signum(offsetX) * overStepX);
		if (overStepY > 0)
			position.setY(position.getY() + Math.signum(offsetY) * overStepY);
	}
	private void limitPosition() {
		if (world.getTileMap().getPixelWidth() < graphicsBuffer.getRenderWidth())
			position.setX(world.getTileMap().getPixelWidth() / 2);
		else
			position.setX(GameUtility.clamp(minX, maxX, position.getX()));
		
		if (world.getTileMap().getPixelHeight() < graphicsBuffer.getRenderHeight())
			position.setY(world.getTileMap().getPixelHeight() / 2);
		else
			position.setY(GameUtility.clamp(minY, maxY, position.getY()));
	}
	public int getScreenX(double x) {
		return (int)(x - position.getX() + graphicsBuffer.getRenderWidth() / 2);
	}
	public int getScreenY(double y) {
		return (int)(y - position.getY() + graphicsBuffer.getRenderHeight() / 2);
	}
	public double getWorldX(double x) {
		return (int)(x + position.getX() - graphicsBuffer.getRenderWidth() / 2);
	}
	public double getWorldY(double y) {
		return (int)(y + position.getY() - graphicsBuffer.getRenderHeight() / 2);
	}
	public Vector2 getWorldPosition(Vector2 screenPosition) {
		return new Vector2(getWorldX(screenPosition.getX()), getWorldY(screenPosition.getY()));
	}
	public Vector2 geScreenPosition(Vector2 worldPosition) {
		return new Vector2(getScreenX(worldPosition.getX()), getScreenY(worldPosition.getY()));
	}
	public int getRenderWidth() { return graphicsBuffer.getRenderWidth(); }
	public int getRenderHeight() { return graphicsBuffer.getRenderHeight(); }
	
	public static boolean getDrawObjectTiles() { return drawObjectTiles; }
	public static void setDrawObjectTiles(boolean drawObjectTiles) { Camera.drawObjectTiles = drawObjectTiles; }
	public static boolean getDrawTileRectangles() { return drawTileRectangles; }
	public static void setDrawTileRectangles(boolean drawTileRectangles) { Camera.drawTileRectangles = drawTileRectangles; }
	public static boolean getDrawCollisionBox() { return drawCollisionBox; }
	public static void setDrawCollisionBox(boolean drawCollisionBox) { Camera.drawCollisionBox = drawCollisionBox; }
	public static boolean getDrawVelocity() { return drawVelocity; }
	public static void setDrawVelocity(boolean drawVelocity) { Camera.drawVelocity = drawVelocity; }
	public static boolean getDrawIntersectingTiles() { return drawIntersectingTiles; }
	public static void setDrawIntersectingTiles(boolean drawIntersectingTiles) { Camera.drawIntersectingTiles = drawIntersectingTiles; }
	public static boolean getDrawAttacks() { return drawAttacks; }
	public static void setDrawAttacks(boolean drawAttacks) { Camera.drawAttacks = drawAttacks; }
	public static boolean getDrawHealthBars() { return drawHealthBars; }
	public static void setDrawHealthBars(boolean drawHealthBars) { Camera.drawHealthBars = drawHealthBars; }
	public void toggleHealthBars() {
		drawHealthBars = !drawHealthBars;
	}
}