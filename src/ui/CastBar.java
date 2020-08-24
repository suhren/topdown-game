package ui;

import java.awt.Color;

import framework.DrawUtility;
import framework.Resources;
import framework.SpriteSheet;

public class CastBar extends StatusBar {
	private static final SpriteSheet SPRITE_SHEET = Resources.getSpriteSheet(Resources.SpriteSheets.UI_CAST_BAR);
	private static final int WIDTH = 5;
	private static final int HEIGHT_TIGHTEN = 3;
	
	public CastBar(GameUI gameUI) {
		this(gameUI, 0, 0);
	}
	public CastBar(GameUI gameUI, int posX, int posY) {
		super(gameUI, posX, posY, WIDTH, SPRITE_SHEET);
	}
	@Override
	public void update(double dT) {
	}
	@Override
	protected void drawProgress() {
		if (getActor() != null && getActor().castingAbilitySpell()) {
			double w = getSpriteSize() * WIDTH * (getActor().getAbilityTime() * 1.0 / getActor().getAbilityTimesTotal());
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
	}
	@Override
	public void onShowUI() {
	}
}