package ui;
import framework.SpriteSheet;

public abstract class UIElementSprite extends UIElement {
	private SpriteSheet spriteSheet;
	
	public UIElementSprite(GameUI gameUI, int posX, int posY, SpriteSheet spriteSheet) {
		this(gameUI, posX, posY, 0, 0, spriteSheet);
	}
	public UIElementSprite(GameUI gameUI, int posX, int posY, int width, int height, SpriteSheet spriteSheet) {
		super(gameUI, posX, posY, width, height);
		this.spriteSheet = spriteSheet;
		
	}
	
	SpriteSheet getSpriteSheet() { return spriteSheet; }
}