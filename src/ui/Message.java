package ui;

import java.awt.Color;
import java.awt.Font;

import framework.DrawUtility;
import framework.Resources;

public class Message extends UIElement {
	private static final Font FONT = Resources.getFont(Resources.Fonts.UI_MESSAGE);
	private static final Color color = Color.RED;
	private double lifeTime;
	private String message;
	
	public Message(GameUI gameUI, String message, double lifeTime) {
		super(gameUI, 0, 0);
		this.message = message;
		this.lifeTime = lifeTime;
	}

	@Override
	public void update(double dT) {
		if ((lifeTime -= dT) <= 0)
			dispose();
	}

	@Override
	public void draw() {
		DrawUtility.drawTextCenter(message, FONT, DrawUtility.getGraphicsBuffer().getRenderWidth() / 2, DrawUtility.getGraphicsBuffer().getRenderHeight() / 2, color);
	}

	@Override
	public void onShowUI() {
	}

}