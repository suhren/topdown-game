package abilityFramework;

import framework.DrawUtility;
import game.Camera;
import object.Actor;
import object.Brush;
import world.World;

public class AbilityBrush extends AbilityWorld {
	private Brush brush = new Brush(0, 0, 32, 32);
	
	public AbilityBrush(Actor source, World world) {
		super(source, world);
	}
	
	@Override
	protected boolean inRange(Actor a) {
		return brush.intersects(a);
	}
	@Override
	public void draw() {
		if (Camera.getDrawAttacks())
			DrawUtility.drawRect(brush.getTopLeftX(), brush.getTopLeftY(), brush.getWidth(), brush.getHeight());
	}	
	@Override
	public void onEntityDispose() {
	}
	@Override
	public void onEntityCreation() {
	}
	@Override
	protected void onCast() {
		super.onCast();
		if (getSource() != null)
			brush.setPosition(getSource().getPosition());
	}
	@Override
	public void update(double dT) {
		super.update(dT);
	}
	// Getters and setters
	@Override
	public int getDrawY() {
		return (int)brush.getY();
	}
	public void setBrush(Brush brush) {
		this.brush = brush;
	}
	public Brush getBrush() {
		return brush;
	}
}