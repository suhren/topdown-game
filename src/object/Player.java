package object;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import abilityFramework.AbilityLibrary;
import animation.Animation;
import animation.AnimationsActor;
import animation.AnimationsHumanoid;
import animation.AnimationsPlayer;
import framework.Vector2;
import game.Camera;
import itemFramework.ActorItems.InventoryFullException;
import items.Firestone;
import items.Icestone;
import items.Sword;
import window.InputBuffer;
import world.World;

public class Player extends Actor {
	private static final int KEY_MOVE_RIGHT = KeyEvent.VK_D;
	private static final int KEY_MOVE_UP = KeyEvent.VK_W;
	private static final int KEY_MOVE_LEFT = KeyEvent.VK_A;
	private static final int KEY_MOVE_DOWN = KeyEvent.VK_S;
	private static final int KEY_ATTACK_RIGHT = KeyEvent.VK_RIGHT;
	private static final int KEY_ATTACK_UP = KeyEvent.VK_UP;
	private static final int KEY_ATTACK_LEFT = KeyEvent.VK_LEFT;
	private static final int KEY_ATTACK_DOWN = KeyEvent.VK_DOWN;
	private static final int BUTTON_ATTACK = MouseEvent.BUTTON1;
	private static final int BUTTON_ATTACK_SECONDARY = MouseEvent.BUTTON3;
	
	private static final int WIDTH = 22;
	private static final int HEIGHT = 36;
	private static final int[] COLLISION_TILES = { 3 };
	private static final int[] COLLISION_OBJECT_TILES = { 1 };
	private static final double ACCELERATION = 1300.0;
	private static final double MAX_SPEED = 200.0;
	private static final int[] ENEMY_FACTIONS = { Faction.FACTION_NEUTRAL, Faction.FACTION_EVIL };
	private static final int FACTION = Faction.FACTION_GOOD;
	private Camera camera;
	private AnimationsHumanoid animations;
	private InputBuffer input;
	private static final int DAMAGE = 20;
	private static final double FORCE = 10000;
	private static final double SPELL_FORCE = 20000;
	private boolean mouseInputEnable = true;
	
	public Player(double posX, double posY, World world) {
		super(posX, posY, WIDTH, HEIGHT, COLLISION_TILES, COLLISION_OBJECT_TILES, world);
		animations = new AnimationsPlayer();
		setAnimations(animations);
		getData().getStats().setDamage(DAMAGE);
		getData().getStats().setPhysicalForce(FORCE);
		getData().getStats().setSpellForce(SPELL_FORCE);
		getData().getStats().setEnemyFactions(ENEMY_FACTIONS);
		getData().getStats().setFaction(FACTION);
		try {
			getData().getActorItems().putItemInventory(new Firestone());
			getData().getActorItems().putItemInventory(new Sword());
			getData().getActorItems().putItemInventory(new Sword());
			getData().getActorItems().putItemInventory(new Icestone());
			getData().getActorItems().putItemInventory(new Icestone());
			getData().getActorItems().putItemInventory(new Icestone());
		} catch (InventoryFullException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update(double dT) {
		super.update(dT);
		if (getState() == State.IDLE || getState() == State.RUNNING) {
			inputMove(dT);
			inputAttack(dT);
		}
		
		if (getState() == State.ABILITY_SPELL_CURSOR && input.mouseUp(BUTTON_ATTACK_SECONDARY))
			finishAbility();
		
		if (getState() == State.ABILITY_MELEE_CURSOR && input.mouseUp(BUTTON_ATTACK))
			finishAbility();
		
		if (getState() == State.ABILITY_MELEE_DIRECTION) {
			if (input.keyPressed(KEY_ATTACK_RIGHT))
				setDirection(Vector2.Direction.RIGHT);
			else if (input.keyPressed(KEY_ATTACK_UP))
				setDirection(Vector2.Direction.UP);
			else if (input.keyPressed(KEY_ATTACK_LEFT))
				setDirection(Vector2.Direction.LEFT);
			else if (input.keyPressed(KEY_ATTACK_DOWN))
				setDirection(Vector2.Direction.DOWN);
			else
				switch (getDirection()) {
					case Vector2.Direction.RIGHT:
						if (input.keyUp(KEY_ATTACK_RIGHT))
							finishAbility();
						break;
					case Vector2.Direction.UP:
						if (input.keyUp(KEY_ATTACK_UP))
							finishAbility();
						break;
					case Vector2.Direction.LEFT:
						if (input.keyUp(KEY_ATTACK_LEFT))
							finishAbility();
						break;
					case Vector2.Direction.DOWN:
						if (input.keyUp(KEY_ATTACK_DOWN))
							finishAbility();
						break;
				}
		}
		
		setCursorPosition(camera.getWorldPosition(input.getMousePosition()));
	}
	private void inputAttack(double dT) {
		if (input.keyPressed(KEY_ATTACK_RIGHT))
			startAbilityDirection(Vector2.Direction.RIGHT, AbilityLibrary.AbilityID.MELEE_SWORD);
		else if (input.keyPressed(KEY_ATTACK_UP))
			startAbilityDirection(Vector2.Direction.UP, AbilityLibrary.AbilityID.MELEE_SWORD);
		else if (input.keyPressed(KEY_ATTACK_LEFT))
			startAbilityDirection(Vector2.Direction.LEFT, AbilityLibrary.AbilityID.MELEE_SWORD);
		else if (input.keyPressed(KEY_ATTACK_DOWN))
			startAbilityDirection(Vector2.Direction.DOWN, AbilityLibrary.AbilityID.MELEE_SWORD);
		else if (input.mousePressed(BUTTON_ATTACK) && mouseInputEnable)
			startAbilityCursor(AbilityLibrary.AbilityID.MELEE_SWORD);
		else if (input.mousePressed(BUTTON_ATTACK_SECONDARY) && mouseInputEnable)
			startAbilityCursor(AbilityLibrary.AbilityID.FIREBALL);
	}
	private void inputMove(double dT) {
		Vector2 dir = new Vector2();
		
		if (input.keyDown(KEY_MOVE_RIGHT))
			dir.addX(ACCELERATION);
		if (input.keyDown(KEY_MOVE_LEFT))
			dir.addX(-ACCELERATION);
		if (input.keyDown(KEY_MOVE_DOWN))
			dir.addY(ACCELERATION);
		if (input.keyDown(KEY_MOVE_UP))
			dir.addY(-ACCELERATION);

		dir.constrainLength(ACCELERATION);
		increaseVelocity(dir.getScaledInstance(dT), MAX_SPEED);
		
		if (dir.getLength() > 0) {
			setDirection(dir.getDirection());
			animations.setAnimationMove(dir.getDirection());
			setState(State.RUNNING);
		}
		else {
			animations.setAnimationIdle(getDirection());
			setState(State.IDLE);
		}
	}
	@Override
	protected void animationCompleted(Animation animation) {
		if (getState() == State.ABILITY_MELEE_END) {
			switch(animation.getID()) {
				case AnimationsActor.ID_ATTACK_END_RIGHT:
					setState(State.IDLE);
					animations.setAnimationIdleRight();
					break;
				case AnimationsActor.ID_ATTACK_END_UP:
					setState(State.IDLE);
					animations.setAnimationIdleUp();
					break;
				case AnimationsActor.ID_ATTACK_END_LEFT:
					setState(State.IDLE);
					animations.setAnimationIdleLeft();
					break;
				case AnimationsActor.ID_ATTACK_END_DOWN:
					setState(State.IDLE);
					animations.setAnimationIdleDown();
					break;
			}
		}
		if (getState() == State.ABILITY_SPELL_END) {
			switch(animation.getID()) {
				case AnimationsActor.ID_CAST_END_RIGHT:
					setState(State.IDLE);
					animations.setAnimationIdleRight();
					break;
				case AnimationsActor.ID_CAST_END_UP:
					setState(State.IDLE);
					animations.setAnimationIdleUp();
					break;
				case AnimationsActor.ID_CAST_END_LEFT:
					setState(State.IDLE);
					animations.setAnimationIdleLeft();
					break;
				case AnimationsActor.ID_CAST_END_DOWN:
					setState(State.IDLE);
					animations.setAnimationIdleDown();
					break;
			}
		}
	}
	public void setInputBuffer(InputBuffer input) {
		this.input = input;
	}
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	@Override
	public void onEntityDispose() {
		super.onEntityDispose();
		getWorld().playerDisposed(this);
	}
	@Override
	protected void onActorCreation() {
		// getWorld().getPlayers().add(this);
	}
	@Override
	public void setMouseInputEnable(boolean mouseInputEnable) {
		this.mouseInputEnable = mouseInputEnable;
	}
}