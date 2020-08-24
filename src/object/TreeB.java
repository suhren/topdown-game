package object;

import framework.Resources;
import framework.SpriteSheet;
import world.World;

public class TreeB extends AnimatedFoliage {
	private static final int SPRITE_WIDHT 			= 64;
	private static final int SPRITE_HEIGHT 			= 64;
	private static final int CENTER_X				= 16;
	private static final int CENTER_Y				= 16;
	private static final ObjectTile[] OBJECT_TILES	= { new ObjectTile(0, 0, 1) };
	private static final double FRAME_RATE			= 2;
	private static final int DRAW_Y_OFFSET			= 0;
	private static SpriteSheet SPRITESHEET 			= Resources.getSpriteSheet(Resources.SpriteSheets.TREE_B);
	
	public TreeB(double tileX, double tileY, int tileSize, World world) {
		super(tileX, tileY, tileSize, SPRITE_WIDHT, SPRITE_HEIGHT, CENTER_X, CENTER_Y, OBJECT_TILES, FRAME_RATE, DRAW_Y_OFFSET, SPRITESHEET, world);
	}
}