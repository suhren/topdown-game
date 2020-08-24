package object;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import abilityFramework.Ability;
import abilityFramework.AbilityLibrary;
import abilityFramework.Damage;
import abilityFramework.Effect;
import animation.Animation;
import animation.AnimationListener;
import animation.AnimationsActor;
import framework.DrawUtility;
import framework.GameUtility;
import framework.Vector2;
import game.Camera;
import world.Tile;
import world.TileMap;
import world.World;

public abstract class Actor extends CollisionObject implements AnimationListener {
	protected static enum State { 
		ABILITY_MELEE_DIRECTION, ABILITY_MELEE_CURSOR, ABILITY_MELEE_END, 
		ABILITY_SPELL_DIRECTION, ABILITY_SPELL_CURSOR, ABILITY_SPELL_END, 
		RUNNING, IDLE };
	private static final double ABILITY_COOLDOWN_MIN = 0.3;
	
	private ActorData data = new ActorData();
	private State state = State.IDLE;
	private AnimationsActor animations;
	private int direction;
	
	private Ability castingAbility;
	private double abilityCooldown = 0;
	
	private Vector2 cursorPosition;
	private List<Effect> effects = new ArrayList<Effect>();
	private List<Effect> disposedEffects = new ArrayList<Effect>();
	
	// Initialize actor
	public Actor(double posX, double posY, int width, int height, World world) {
		super(posX, posY, width, height, world);
	}
	public Actor(double posX, double posY, int width, int height, int[] collisionTiles, int[] collisionObjectTiles, World world) {
		super(posX, posY, width, height, collisionTiles, collisionObjectTiles, world);
	}
	
	// Update
	@Override
	public void update(double dT) {
		super.update(dT);
		
		for (Effect s : effects)
			s.update(dT);
		
		for (Effect s: disposedEffects)
			effects.remove(s);
		
		disposedEffects.clear();
		
		applyDeaccelerationForce();
		
		animations.updateAnimations(dT);
		
		if (castingAbilityCursor())
			direction = getCursorPosition().getSubtractedInstance(getPosition()).getDirection();
		
		if (castingAbilitySpell())
			animations.setAnimationCast(direction);
		else if (castingAbilityMelee())
			animations.setAnimationAttack(direction);
		
		if (castingAbility())
			castingAbility.updateCast(dT);
		else if (abilityCooldown > 0)
			abilityCooldown = GameUtility.clampDown(0.0, abilityCooldown - dT);
		
		if (state == State.RUNNING && getVelocityVector().getLength() == 0) {
			state = State.IDLE;
			animations.setAnimationIdle(getDirection());
		}
	}
	
	// Movement
	public void moveUp(double dT) {
		state = State.RUNNING;
		Vector2 v = new Vector2(0, -data.getStats().getAccelration());
		increaseVelocity(v.getScaledInstance(dT), data.getStats().getMaxSpeed());
		setDirection(Vector2.Direction.UP);
		animations.setAnimationMoveUp();
	}
	public void moveDown(double dT) {
		state = State.RUNNING;
		Vector2 v = new Vector2(0, data.getStats().getAccelration());
		increaseVelocity(v.getScaledInstance(dT), data.getStats().getMaxSpeed());
		setDirection(Vector2.Direction.DOWN);
		animations.setAnimationMoveDown();
	}
	public void moveLeft(double dT) {
		state = State.RUNNING;
		Vector2 v = new Vector2(-data.getStats().getAccelration(), 0);
		increaseVelocity(v.getScaledInstance(dT), data.getStats().getMaxSpeed());
		setDirection(Vector2.Direction.LEFT);
		animations.setAnimationMoveLeft();
	}
	public void moveRight(double dT) {
		state = State.RUNNING;
		Vector2 v = new Vector2(data.getStats().getAccelration(), 0);
		increaseVelocity(v.getScaledInstance(dT), data.getStats().getMaxSpeed());
		setDirection(Vector2.Direction.RIGHT);
		animations.setAnimationMoveRight();
	}
	public void moveDirection(int direction, double dT) {
		if (direction == Vector2.Direction.RIGHT)
			moveRight(dT);
		else if (direction == Vector2.Direction.UP)
			moveUp(dT);
		else if (direction == Vector2.Direction.LEFT)
			moveLeft(dT);
		else if (direction == Vector2.Direction.DOWN)
			moveDown(dT);
	}
	
	// Attack
	protected void startAbilityCursor(int abilityID) {
		if (abilityCooldown == 0) {
			castingAbility = AbilityLibrary.getAbility(this, getWorld(), abilityID);
			if (castingAbility.isMeleeAbility()) {
				animations.setAnimationAttack(direction);
				state = State.ABILITY_MELEE_CURSOR;
			}
			else {
				animations.setAnimationCast(direction);
				state = State.ABILITY_SPELL_CURSOR;
			}
		}
	}
	protected void startAbilityDirection(int abilityID, int direction) {
		if (abilityCooldown == 0) {
			castingAbility = AbilityLibrary.getAbility(this, getWorld(), abilityID);
			setDirection(direction);
			if (castingAbility.isMeleeAbility()) {
				animations.setAnimationAttack(direction);
				state = State.ABILITY_MELEE_DIRECTION;
			}
			else {
				animations.setAnimationCast(direction);
				state = State.ABILITY_SPELL_DIRECTION;
			}
		}
	}
	protected void finishAbility() {
		abilityCooldown = ABILITY_COOLDOWN_MIN;
		castingAbility.cast();
		
		if (castingAbility.isMeleeAbility()) {
			animations.setAnimationAttackEnd(direction);
			state = State.ABILITY_MELEE_END;
		}
		else {
			animations.setAnimationCastEnd(direction);
			state = State.ABILITY_SPELL_END;
		}
	}
	
	// Events
	@Override
	public void animationComplete(Animation animation) {
		animationCompleted(animation);
	}
	protected abstract void animationCompleted(Animation animation);
	
	// Affect actor
	public void addEffect(Effect effect) {
		boolean add = true;
		Effect oldEffect = null;
		for (Effect e : effects)
			if (e.getID() == effect.getID()) {
				add = false;
				oldEffect = e;
				break;
			}
		if (add)
			effects.add(effect.getCopy());
		else
			oldEffect.refresh();
	}
	public void removeEffect(Effect effect) {
		disposedEffects.add(effect);
	}
	private void reduceHealth(int damage) {
		setHealth(data.getStats().getHealth() - damage);
	}
	private void setHealth(int health) {
		if (health > 0)
			data.getStats().setHealth(health);
		else {
			data.getStats().setHealth(0);
			actorDeath();
		}
	}
	public void applyDamage(Damage damage) {
		reduceHealth(damage.getDamage());
	}
	private void applyDeaccelerationForce() {
		double force = Math.abs(getDeacceleration() * getMass());
		if (getDeacceleration() < 0 && force > 1000)
			reduceHealth((int)(force/10));
	}
	
	// On creation and disposal
	@Override
	protected void onCollisionTile(Tile tile, int direction) {
		// TODO Auto-generated method stub
	}
	@Override
	protected void onCollisionGameObject(CollisionObject object) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onEntityCreation() {
		getWorld().actorAdded(this);
		onActorCreation();
	}
	@Override
	public void onEntityDispose() {
		//effects.clear();
	}
	public void actorDeath() {
		dispose();
	}
	protected abstract void onActorCreation();
	
	// Draw actor
	@Override
	public void draw() {
		DrawUtility.drawImage(getImage(), getX() - getImage().getWidth(null) / 2, getY()  - getImage().getHeight(null) / 2);

		for (Effect s : effects)
			s.draw();
		
		if (Camera.getDrawIntersectingTiles())
			for (int x = getTopLeftTileX(); x <= getBottomRightTileX(); x++)
				for (int y = getTopLeftTileY(); y <= getBottomRightTileY(); y++)
					DrawUtility.drawRect(x * TileMap.TILE_SIZE, y * TileMap.TILE_SIZE, TileMap.TILE_SIZE, TileMap.TILE_SIZE, Color.ORANGE);
		
		if (Camera.getDrawCollisionBox())
			DrawUtility.drawRect(getTopLeftX(), getTopLeftY(), getWidth(), getHeight(), Color.CYAN);
		
		if (Camera.getDrawVelocity())
			DrawUtility.drawVector(getPosition(), getVelocityVector(), Color.GREEN);
	}
	
	// Getters and setters
	public BufferedImage getSnapshot() {
		return animations.getFrameImage(AnimationsActor.ID_IDLE_DOWN, 0);
	}
	public double getAbilityTime() {
		return castingAbility.getCastTime();
	}
	public ActorData getData() {
		return data;
	}
	protected State getState() {
		return state;
	}
	public int getDirection() {
		return direction;
	}
	public Image getImage() {
		return animations.getSprite();
	}
	public Vector2 getCursorPosition() {
		return cursorPosition;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	protected void setCursorPosition(Vector2 cursorPosition) {
		this.cursorPosition = cursorPosition;
	}
	protected void setState(State state) {
		this.state = state;
	}
	protected void setAnimations(AnimationsActor animations) {
		this.animations = animations;
		animations.addAnimationListener(this);
	}
	public boolean drawMousePointer() {
		return state != State.ABILITY_SPELL_CURSOR;
	}
	public boolean castingAbilityMelee() {
		return state == State.ABILITY_MELEE_DIRECTION | state == State.ABILITY_MELEE_CURSOR;
	}
	public boolean castingAbilitySpell() {
		return state == State.ABILITY_SPELL_DIRECTION | state == State.ABILITY_SPELL_CURSOR;
	}
	public boolean castingAbilityCursor() {
		return state == State.ABILITY_MELEE_CURSOR | state == State.ABILITY_SPELL_CURSOR;
	}
	public boolean castingAbility() {
		return state == State.ABILITY_MELEE_DIRECTION | state == State.ABILITY_MELEE_CURSOR |
			 state == State.ABILITY_SPELL_DIRECTION | state == State.ABILITY_SPELL_CURSOR;
	}
	public abstract void setMouseInputEnable(boolean b);
}