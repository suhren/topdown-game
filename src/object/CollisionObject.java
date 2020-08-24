package object;
import framework.GameUtility;
import framework.Vector2;
import world.Tile;
import world.TileMap;
import world.World;

public abstract class CollisionObject extends PhysicalObject {
	private int topLeftTileX, topLeftTileY, bottomRightTileX, bottomRightTileY;
	private static int[] DEFAULT_COLLISION_TILES = { 3 };
	private static int[] DEFAULT_COLLISION_OBJECT_TILES = { 1 };
	private int[] collisionTiles, collisionObjectTiles;
	
	public CollisionObject(double posX, double posY, int width, int height, World world) {
		this(posX, posY, width, height, DEFAULT_COLLISION_TILES, DEFAULT_COLLISION_OBJECT_TILES, world);
	}
	public CollisionObject(double posX, double posY, int width, int height, int[] collisionTiles, int[] collisionObjectTiles, World world) {
		super(posX, posY, width, height, world);
		this.collisionTiles = collisionTiles;
		this.collisionObjectTiles = collisionObjectTiles;
	}

	@Override
	public void update(double dT) {
		super.update(dT);
		Vector2 oldCenter = getPosition().copy(); 
		oldCenter.add(getVelocityVector().getScaledInstance(-dT));
		boolean collision = false;
		
		do {
			collision = false;
			topLeftTileX = (int)(getTopLeftX() / TileMap.TILE_SIZE);
			topLeftTileY = (int)(getTopLeftY() / TileMap.TILE_SIZE);
			bottomRightTileX = (int)(getBottomRightX() / TileMap.TILE_SIZE);
			bottomRightTileY = (int)(getBottomRightY() / TileMap.TILE_SIZE);
			boolean[][] tiles = new boolean[(1 + bottomRightTileY - topLeftTileY)][(1 + bottomRightTileX - topLeftTileX)];
			
			for (int row = 0; row < tiles.length; row++)
				for (int col = 0; col < tiles[0].length; col++) {
					Tile t = getWorld().getTileMap().getTile(topLeftTileX + col, topLeftTileY + row);
					tiles[row][col] = isCollisionTile(t);
					if (!collision && tiles[row][col] == true) {
						collision = true;
						onCollisionTile(t, getVelocityVector().getDirection());
					}
				}
			
			solveContactTiles(topLeftTileX, topLeftTileY, tiles, oldCenter);
		
		} while (collision);
			
	}
	private void solveContactTiles(int tlX, int tlY, boolean[][] cTiles, Vector2 oldCenter) {
		Collision firstCollision = null;
		
		for (int row = 0; row < cTiles.length; row++)
			for (int col = 0; col < cTiles[0].length; col++)
				if (cTiles[row][col]) {
					Collision c = getCollision(tlX + col, tlY + row, oldCenter);
					if (firstCollision == null)
						firstCollision = c;
					else if (c.getTimeToCollision() < firstCollision.getTimeToCollision())
						firstCollision = c;
				}
		
		if (firstCollision != null) {
			setPosition(firstCollision.getNewPosition());
			getVelocityVector().set((firstCollision.getNewVelocity()));
		}
	}
	private Collision getCollision(int x, int y, Vector2 oldCenter) {
		double sX = Math.signum(getVelocityVector().getX());
		double sY = Math.signum(getVelocityVector().getY());
		double offsetX1 = sX * (getWidth() / 2);
		double offsetY1 = sY * (getHeight() / 2);
		double offsetX2 = sX * TileMap.TILE_SIZE / 2;
		double offsetY2 = sY * TileMap.TILE_SIZE / 2;
		
		Vector2 p1 = oldCenter.copy();
		p1.add(new Vector2(offsetX1, offsetY1));
		Vector2 p2 = new Vector2((x + 0.5) * TileMap.TILE_SIZE, (y + 0.5) * TileMap.TILE_SIZE);
		p2.add(new Vector2(-offsetX2, -offsetY2));
		p1.subtract(p2);
		
		double tX = (Math.abs(getVelocityVector().getX()) > 0 ) ? p1.getX() / getVelocityVector().getX() : Double.MAX_VALUE;
		double tY = (Math.abs(getVelocityVector().getY()) > 0 ) ? p1.getY() / getVelocityVector().getY() : Double.MAX_VALUE;
		
		if (tX < tY)
			return new Collision(p2.getX() - offsetX1 - 0.5 * sX, getY(), 0, getVelocityVector().getY(), Math.abs(tX));
		else
			return new Collision(getX(), p2.getY() - offsetY1 - 0.5 * sY, getVelocityVector().getX(), 0, Math.abs(tY));
	}
	private boolean isCollisionTile(Tile t) {
		if (GameUtility.isInArray(t.getTileValue(), collisionTiles))
			return true;
		if (GameUtility.isInArray(t.getObjectValue(), collisionObjectTiles))
			return true;
		return false;
	}
	public int getTopLeftTileX() { return topLeftTileX; }
	public int getTopLeftTileY() { return topLeftTileY; }
	public int getBottomRightTileX() { return bottomRightTileX; }
	public int getBottomRightTileY() { return bottomRightTileY; }
	protected abstract void onCollisionTile(Tile tile, int direction);
	protected abstract void onCollisionGameObject(CollisionObject object);
}