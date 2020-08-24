package abilityFramework;

import framework.Vector2;

public class Damage {
	public class Type {
		public static final int PHYSICAL 	= 0;
		public static final int FIRE 		= 1;
		public static final int FROST 		= 2;
		public static final int LIFE 		= 3;
		public static final int ETHER 		= 4;
	}
	private int damage;
	private Vector2 force;
	private int type;
	
	public Damage(int type) {
		this(0, type);
	}
	public Damage(int damage, int type) {
		this(damage, new Vector2(), type);
	}
	public Damage(int damage, Vector2 force, int type) {
		this.damage = damage;
		this.force = force;
		this.type = type;
	}
	
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public Vector2 getForce() {
		return force;
	}
	public int getType() {
		return type;
	}
}