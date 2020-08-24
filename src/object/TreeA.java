package object;

import framework.Resources;
import framework.SpriteSheet;
import world.World;

public class TreeA extends AnimatedFoliage {
	private static final int SPRITE_WIDHT 			= 128;
	private static final int SPRITE_HEIGHT 			= 128;
	private static final int CENTER_X				= 48;
	private static final int CENTER_Y				= 90;
	private static final ObjectTile[] OBJECT_TILES	= { new ObjectTile(0, 0, 1) };
	private static final double FRAME_RATE			= 2;
	private static final int DRAW_Y_OFFSET			= 32;
	private static SpriteSheet SPRITESHEET 			= Resources.getSpriteSheet(Resources.SpriteSheets.TREE_A);
	
	public TreeA(double tileX, double tileY, int tileSize, World world) {
		super(tileX, tileY, tileSize, SPRITE_WIDHT, SPRITE_HEIGHT, CENTER_X, CENTER_Y, OBJECT_TILES, FRAME_RATE, DRAW_Y_OFFSET, SPRITESHEET, world);
	}
}
