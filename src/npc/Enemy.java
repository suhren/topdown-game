package npc;

import animation.Animation;
import animation.AnimationsEnemy;
import object.CollisionObject;
import object.Faction;
import world.Tile;
import world.World;

public class Enemy extends NPC {

	private static final int WIDTH = 32;
	private static final int HEIGHT = 32;
	
	private AnimationsEnemy animations;
	private AIv1 ai;
	
	public Enemy(double posX, double posY, World world) {
		super(posX, posY, WIDTH, HEIGHT, world);
		animations = new AnimationsEnemy();
		setAnimations(animations);
		ai = new AIv1(this);
		setAI(ai);
		getData().getStats().setFaction(Faction.FACTION_EVIL);
	}

	@Override
	protected void updateNPC(double dT) {
		
	}
	@Override
	protected void animationCompleted(Animation animation) {
	}
	@Override
	protected void onCollisionTile(Tile tile, int direction) {
		ai.collideWithTile(tile, direction);
	}
	@Override
	protected void onCollisionGameObject(CollisionObject object) {
	}
	@Override
	protected void onActorCreation() {
	}

	@Override
	public void setMouseInputEnable(boolean mouseInputEnable) {
		
	}
}