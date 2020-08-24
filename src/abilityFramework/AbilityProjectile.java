package abilityFramework;

import framework.DrawUtility;
import framework.Resources;
import framework.Vector2;
import object.Actor;
import object.BrushEffect;
import world.TileMap;
import world.World;

public class AbilityProjectile extends AbilityBrushAnimated {
	private int impactEffectID = -1;
	private int impactEffectFPS;
	private Vector2 velocity = new Vector2();
	private double speed = 0;
	
	public AbilityProjectile(Actor source, World world) {
		super(source, world);
	}

	@Override
	public void update(double dT) {
		super.update(dT);
		getBrush().getPosition().add(velocity.getScaledInstance(dT));
		int topLeftTileX = (int)(getBrush().getTopLeftX() / TileMap.TILE_SIZE);
		int topLeftTileY = (int)(getBrush().getTopLeftY() / TileMap.TILE_SIZE);
		int bottomRightTileX = (int)(getBrush().getBottomRightX() / TileMap.TILE_SIZE);
		int bottomRightTileY = (int)(getBrush().getBottomRightY() / TileMap.TILE_SIZE);
		
		for (int x = topLeftTileX; x <= bottomRightTileX; x++)
			for (int y = topLeftTileY; y <= bottomRightTileY; y++)
				if (getWorld().getTileMap().isCollisionTile(x, y)) {
					dispose();
					break;
				}
	}
	
	@Override
	public void draw() {
		DrawUtility.drawImageRotated(getBrush().getImage(), getBrush().getX(), getBrush().getY(), velocity.getAngle());
	}
	@Override
	public void onEntityDispose() {
		if (impactEffectID >= 0)
			getWorld().addEntity(new BrushEffect(getBrush().getX(), getBrush().getY(), Resources.getSpriteSheet(impactEffectID), impactEffectFPS, 0, getWorld()));
	}
	@Override
	protected void onCast() {
		super.onCast();
		if (getSource() != null) {
			velocity = getSource().getCursorPosition().getSubtractedInstance(getSource().getPosition());
			velocity.setLength(speed);
		}
	}
	
	// Getters and setters
	protected Vector2 getVelocityVector() {
		return velocity;
	}
	protected void setSpeed(double speed) {
		this.speed = speed;
	}
	protected void setVelocityVector(Vector2 velocity) {
		this.velocity = velocity;
	}
	public void setImpactEffectID(int impactEffectID) {
		this.impactEffectID = impactEffectID;
	}
	public void setImpactEffectFPS(int impactEffectFPS) {
		this.impactEffectFPS = impactEffectFPS;
	}
}