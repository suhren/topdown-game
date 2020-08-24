package abilityFramework;

import framework.GameUtility;
import object.Actor;
import object.Faction;
import world.World;

public abstract class AbilityWorld extends Ability {
	private int[] targetFactions = Faction.DEFAULT_FACTIONS_ENEMY;
	private double currentTick = 0;
	private double tickTime = 0;
	private double lifeTime = 5;
	private boolean done = false;
	private boolean singleTarget = false;
	
	public AbilityWorld(Actor source, World world) {
		super(source, world);
	}
	
	public void update(double dT) {
		if (!done) {
			if (currentTick >= tickTime) {
				for (Actor a : getWorld().getActors()) {
					if (a != getSource() && isTarget(a) && inRange(a)) {
						for (Effect e : getEffects())
							e.applyToActor(a);
						if (singleTarget) {
							done = true;
							dispose();
							break;
						}
					}
				}
				if ((lifeTime -= dT) <= 0) {
					done = true;
					dispose();
				}
				currentTick = 0;
			}
			else
				currentTick += dT;
		}
	}
	private boolean isTarget(Actor a) {
		return GameUtility.isInArray(a.getData().getStats().getFaction(), targetFactions);
	}
	
	@Override
	protected void onCast() {
		super.onCast();
		if (getSource() != null)
			targetFactions = getSource().getData().getStats().getEnemyFactions();
		getWorld().addAbility(this);
	}
	protected abstract boolean inRange(Actor a);
	
	protected int[] getTargetFactions() {
		return targetFactions;
	}
	protected void setTargetFactions(int[] targetFactions) {
		this.targetFactions = targetFactions;
	}
	protected void setSingleTarget(boolean singleTarget) {
		this.singleTarget = singleTarget;
	}
	protected void setLifetime(double lifeTime) {
		this.lifeTime = lifeTime;
	}
	protected void setTicktime(double tickTime) {
		this.tickTime = tickTime;
	}
}