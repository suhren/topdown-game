package npc;

import java.awt.Color;
import java.awt.image.BufferedImage;

import framework.Resources;
import framework.DrawUtility;
import game.Camera;
import object.Actor;
import world.World;

public abstract class NPC extends Actor {
	private AI ai;
	private static final int HEALTH_BAR_OFFSET = 16;
	private static final BufferedImage HEALTHBAR_IMAGE = Resources.getImage(Resources.Images.HEALTH_BAR);
	
	public NPC(double posX, double posY, int width, int height, World world) {
		super(posX, posY, width, height, world);
	}
	@Override
	public void update(double dT) {
		super.update(dT);
		ai.update(dT);
		updateNPC(dT);
	}
	protected abstract void updateNPC(double dT);
	protected AI getAI() { return ai; }
	protected void setAI(AI ai) { this.ai = ai; }
	@Override
	public void draw() {
		super.draw();
		
		if (Camera.getDrawHealthBars()) {
			DrawUtility.drawImageCenter(HEALTHBAR_IMAGE, getX(), getTopLeftY() - HEALTH_BAR_OFFSET);
			DrawUtility.fillRect(getX() - HEALTHBAR_IMAGE.getWidth(null) / 2 + 1, getTopLeftY() - HEALTHBAR_IMAGE.getHeight(null) / 2 - HEALTH_BAR_OFFSET + 1, (int)(getData().getStats().getHealth() * 1.0 / getData().getStats().getMaxHealth() * (HEALTHBAR_IMAGE.getWidth(null) - 2)), HEALTHBAR_IMAGE.getHeight(null) - 2, Color.RED);
		}
	}
}