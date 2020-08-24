package object;

import framework.SpriteSheet;
import world.World;

public abstract class AnimatedFoliage extends AnimatedBrush {
	private ObjectTile[] objectTiles;
	
	public AnimatedFoliage(double tileX, double tileY, int tileSize, int spriteWidth, int spriteHeight, int centerX, int centerY, ObjectTile[] objectTiles, double frameRate, int drawYOffset, SpriteSheet spriteSheet, World world) {
		super(tileX * tileSize - centerX + spriteWidth / 2, tileY * tileSize - centerY + spriteHeight / 2, spriteWidth, spriteHeight, spriteSheet, frameRate, drawYOffset, world);
		this.objectTiles = objectTiles;
		setFrame(Math.random() * (spriteSheet.getNumberOfSprites() - 1));
	}
	public ObjectTile[] getObjectTiles() {
		return objectTiles;
	}
}