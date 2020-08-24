package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import framework.GameUtility;
import framework.Resources;
import framework.SpriteSheet;

public class TileMap {
	private static final int[] DEFAULT_COLLISION_TILES = { 3 };
	private static final int[] DEFAULT_COLLISION_OBJECT_TILES = { 1 };
	private static BufferedImage image;
	private static Graphics imageGraphics;
	public static final int TILE_SIZE = 32;
	private Tile[][] tiles;
	private SpriteSheet spriteSheetTiles = Resources.getSpriteSheet(Resources.SpriteSheets.TILES);
	
	public TileMap(int width, int height) {
		tiles = new Tile[width][height];
		image = new BufferedImage(width * TILE_SIZE, height * TILE_SIZE, BufferedImage.TYPE_INT_RGB);
		imageGraphics = image.createGraphics();
	}
	public void drawImage() {
		for (int x = 0; x < getWidth(); x++)
			for (int y = 0; y < getHeight(); y++) {
				Tile t = getTile(x, y);
				if (t.getTileValue() > 0)
					imageGraphics.drawImage(spriteSheetTiles.getSprite(t.getTileValue()), TileMap.TILE_SIZE * x, TileMap.TILE_SIZE * y, null);
			}
	}
	public Tile getTile(int x, int y) {return tiles[x][y]; }
	public Tile getTileAtPosition(int posX, int posY) { return tiles[posX / TILE_SIZE][posY / TILE_SIZE]; }
	public Tile[][] getTiles() { return tiles; }
	public int getWidth() { return tiles.length; }
	public int getHeight() { return tiles[0].length; }
	public int getPixelWidth() { return tiles.length * TILE_SIZE; }
	public int getPixelHeight() { return tiles[0].length * TILE_SIZE; }
	public void setTileValue(int x, int y, int value) { 
		tiles[x][y].setTileValue(value);
	}
	public void setObjectValue(int x, int y, int value) {
		tiles[x][y].setObjectValue(value);
	}
	public void addTile(int x, int y, int t) {
		tiles[x][y] = new Tile(t);
	}
	public static int getTilePos(double pos) {
		return (int)(pos / TILE_SIZE);
	}
	public boolean isCollisionTile(int x, int y) {
		if (GameUtility.isInArray(getTile(x, y).getTileValue(), DEFAULT_COLLISION_TILES))
			return true;
		if (GameUtility.isInArray(getTile(x, y).getObjectValue(), DEFAULT_COLLISION_OBJECT_TILES))
			return true;
		return false;
	}
	public BufferedImage getImage() {
		return image;
	}
}