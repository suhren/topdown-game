package ui;

import java.awt.Color;
import framework.Resources;
import framework.SpriteSheet;
import framework.DrawUtility;

public class HealthBar extends StatusBar {
	private static final SpriteSheet SPRITE_SHEET = Resources.getSpriteSheet(Resources.SpriteSheets.UI_HEALTH_BAR);
	private static final int WIDTH = 5;
	
	public HealthBar(GameUI gameUI) {
		this(gameUI, 0, 0);
	}
	public HealthBar(GameUI gameUI, int posX, int posY) {
		super(gameUI, posX, posY, WIDTH, SPRITE_SHEET);
	}
	
	@Override
	public void update(double dT) {
	}
	@Override
	protected void drawProgress() {
		if (getActor() != null) {
			double w = getSpriteSize() * WIDTH * (getActor().getData().getStats().getHealth() * 1.0 / getActor().getData().getStats().getMaxHealth());
			int n = (int)(w / getSpriteSize());
			int r = (int)(w % getSpriteSize());
			
			for (int i = 0; i < n; i++)
				DrawUtility.drawImageScreen(getSpriteSheet().getSprite(4), getX() + ICON_OFFSET + i * getSpriteSize(), getY());
			if (r > 0)
				DrawUtility.drawImageScreenPartial(getSpriteSheet().getSprite(4), getX() + ICON_OFFSET + n * getSpriteSize(), getY(), r, getSpriteSize());
		}
	}
	@Override
	protected void drawForeground() {
		if (getActor() != null)
			DrawUtility.drawTextCenter(getActor().getData().getStats().getHealth() + " / " + getActor().getData().getStats().getMaxHealth(), Resources.getFont(Resources.Fonts.STATUS_BAR_TEXT), getX() + ICON_OFFSET + (getSpriteSize() * WIDTH / 2.0), getY() + getSpriteSize() / 2, Color.WHITE);
	}
	@Override
	public void onShowUI() {
		// TODO Auto-generated method stub
		
	}
}