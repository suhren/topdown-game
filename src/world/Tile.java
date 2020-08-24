package world;

public class Tile {
	private int tileValue;
	private int objectValue;
	private static final int DEFAULT_TILE_VALUE = 0;
	private static final int DEFAULT_OBJECT_VALUE = 0;
	
	public Tile() {
		this(DEFAULT_TILE_VALUE, DEFAULT_OBJECT_VALUE);
	}
	public Tile(int tileValue) {
		this(tileValue, DEFAULT_OBJECT_VALUE);
	}
	public Tile(int tileValue, int objectValue) {
		this.tileValue = tileValue;
		this.objectValue = objectValue;
	}
	
	public int getTileValue() { return tileValue; }
	public void setTileValue(int tileValue) { this.tileValue = tileValue; }
	public int getObjectValue() { return objectValue; }
	public void setObjectValue(int objectValue) { this.objectValue = objectValue; }
}
