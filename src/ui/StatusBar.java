package ui;

import framework.DrawUtility;
import framework.SpriteSheet;

public abstract class StatusBar extends UIElementSprite {
	private int width;
	private int spriteSize;
	protected static final int ICON_OFFSET = 40;
	
	public StatusBar(GameUI gameUI, int width, SpriteSheet spriteSheet) {
		this(gameUI, 0, 0, width, spriteSheet);
	}
	public StatusBar(GameUI gameUI, int posX, int posY, int width, SpriteSheet spriteSheet) {
		super(gameUI, posX, posY, ICON_OFFSET + spriteSheet.getSpriteWidth() * width, spriteSheet.getSpriteHeight(), spriteSheet);
		this.width = width;
		this.spriteSize = spriteSheet.getSpriteWidth();
	}
	protected int getSpriteSize() {
		return spriteSize;
	}
	@Override
	public void update(double dT) {
	}
	@Override
	public void draw() {
		// Draw icon
		DrawUtility.drawImageScreen(getSpriteSheet().getSprite(0), getX(), getY());
		// Draw background
		for (int i = 0; i < width; i++)
			DrawUtility.drawImageScreen(getSpriteSheet().getSprite(5), getX() + ICON_OFFSET + spriteSize * i, getY());
		
		drawProgress();
		
		// Draw frame
		DrawUtility.drawImageScreen(getSpriteSheet().getSprite(1), getX() + ICON_OFFSET, getY());
		for (int i = 1; i < width - 1; i++)
			DrawUtility.drawImageScreen(getSpriteSheet().getSprite(2), getX() + ICON_OFFSET + spriteSize * i, getY());
		DrawUtility.drawImageScreen(getSpriteSheet().getSprite(3), getX() + ICON_OFFSET + (width - 1) * spriteSize, getY());
		
		drawForeground();
	}
	protected abstract void drawProgress();
	protected abstract void drawForeground();
}