package framework;
import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage[] sprites;
	private int spriteWidth, spriteHeight, spritesX, nSprites, spritesY, width, height;
	private final static int DEFAULT_SIZE = 32;
	
	public SpriteSheet(String path) {
		this(path, DEFAULT_SIZE);
	}
	public SpriteSheet(String path, int spriteSize) {
		this(path, spriteSize, spriteSize);
	}
	public SpriteSheet(String path, int spriteWidth, int spriteHeight) {
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		readImage(path);
	}
	private void readImage(String path) {
		BufferedImage image = Resources.readImage(path);
		width = image.getWidth();
		height = image.getHeight();
		spritesX = width / spriteWidth;
		spritesY = height / spriteHeight;
		nSprites = spritesX * spritesY;
		sprites = new BufferedImage[nSprites];
		for (int row = 0; row < spritesY; row++)
			for (int col = 0; col < spritesX; col++)
				sprites[row * spritesX + col] = image.getSubimage(spriteWidth * col, spriteHeight * row, spriteWidth, spriteHeight);
	}
	public BufferedImage getSprite(int s) { return sprites[s]; }
	public BufferedImage getSprite(int x, int y) { return sprites[y * spritesX + x]; }
	public int getSpritesX() { return spritesX; }
	public int getSpritesY() { return spritesY; }
	public int getNumberOfSprites() { return nSprites; }
	public int getSpriteWidth() { return spriteWidth; }
	public int getSpriteHeight() { return spriteHeight; }
}