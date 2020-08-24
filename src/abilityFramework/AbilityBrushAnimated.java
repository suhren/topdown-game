package abilityFramework;


import framework.Resources;
import framework.Resources.SpriteSheets;
import object.Actor;
import object.AnimatedBrush;
import world.World;

public class AbilityBrushAnimated extends AbilityBrush {
	private AnimatedBrush brush = new AnimatedBrush(0, 0, 32, 32, Resources.getSpriteSheet(SpriteSheets.ERROR), 0, 0, getWorld());
	
	public AbilityBrushAnimated(Actor source, World world) {
		super(source, world);
	}
	
	@Override
	public void update(double dT) {
		super.update(dT);
		brush.update(dT);
	}
	@Override
	public void draw() {
		super.draw();
		brush.draw();
	}
	@Override
	protected void onCast() {
		super.onCast();
	}
	
	// Getters and setters
	public AnimatedBrush getBrush() {
		return brush;
	}
	public void setBrush(AnimatedBrush brush) {
		super.setBrush(brush);
		this.brush = brush;
	}
}
