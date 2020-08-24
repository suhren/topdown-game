package abilityFramework;

import framework.SpriteSheet;
import object.Actor;
import object.BrushEffect;
import world.World;

public class AbilityExplosion extends AbilityBrush {
	private SpriteSheet spriteSheet;
	private double frameRate;
	
	public AbilityExplosion(Actor source, World world) {
		super(source, world);
	}
	public void setSpriteSheet(SpriteSheet spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
	public void setFrameRate(double frameRate) {
		this.frameRate = frameRate;
	}
	@Override
	public void onEntityDispose() {
		getWorld().addEntity(new BrushEffect(getBrush().getX(), getBrush().getY(), spriteSheet, frameRate, 0, getWorld()));
	}
	@Override
	protected void onCast() {
		super.onCast();
	}
}