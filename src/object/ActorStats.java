package object;

public class ActorStats {
	private static final int DEFAULT_HEALTH = 500;
	private static final double DEFAULT_ACCELERATION = 1000.0;
	private static final double DEFAULT_MAX_SPEED = 100.0;
	
	private int maxHealth;
	private int damage;
	private double physicalForce;
	private double spellForce;
	private int health;
	private int faction;
	private int[] enemyFactions;
	private double acceleration;
	private double maxSpeed;
	
	public ActorStats() {
		this.faction = Faction.DEFAULT_FACTION;
		this.enemyFactions = Faction.DEFAULT_FACTIONS_ENEMY;
		this.maxHealth = DEFAULT_HEALTH;
		this.acceleration = DEFAULT_ACCELERATION;
		this.maxSpeed = DEFAULT_MAX_SPEED;
		health = maxHealth;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public double getPhysicalForce() {
		return physicalForce;
	}
	public void setPhysicalForce(double physicalForce) {
		this.physicalForce = physicalForce;
	}
	
	public double getSpellForce() {
		return spellForce;
	}
	public void setSpellForce(double spellForce) {
		this.spellForce = spellForce;
	}

	
	public int[] getEnemyFactions() {
		return enemyFactions;
	}
	public void setEnemyFactions(int[] enemyFactions) {
		this.enemyFactions = enemyFactions;
	}
	
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getFaction() {
		return faction;
	}
	public void setFaction(int faction) {
		this.faction = faction;
	}

	public double getAccelration() {
		return acceleration;
	}
	
	public double getMaxSpeed() {
		return maxSpeed;
	}
}