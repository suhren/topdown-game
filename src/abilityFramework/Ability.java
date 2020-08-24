package abilityFramework;

import java.util.ArrayList;
import java.util.List;

import framework.GameUtility;
import object.Actor;
import object.Entity;
import world.World;

public abstract class Ability extends Entity {
	private Actor source;
	private List<Effect> effects = new ArrayList<Effect>();
	private double currentCastTime = 0;
	private double castTime = 0;
	private boolean meleeAbility = false;
	private String description = "";
	private double cooldown = 0;
	
	public Ability(Actor source, World world) {
		super(world);
		this.source = source;
	}

	protected void addEffect(Effect effect) {
		effects.add(effect);
	}
	
	public void updateCast(double dT) {
		currentCastTime = GameUtility.clampUp(castTime, currentCastTime + dT);
	}
	public void cast() {
		if (currentCastTime >= castTime)
			onCast();
	}
	protected void onCast() {
		if (source != null)
			for (Effect e : effects)
				e.onCast(source);
	}
	
	// Getters and setters
	public double getCooldown() {
		return cooldown;
	}
	protected void setCooldown(double cooldown) {
		this.cooldown = cooldown;
	}
	protected List<Effect> getEffects() {
		return effects;
	}
	protected Actor getSource() {
		return source;
	}
	public double getCastTime() {
		return castTime;
	}
	protected void setCastTime(double castTime) {
		this.castTime = castTime;
	}
	protected void setMeleeAbility(boolean meleeAbility) {
		this.meleeAbility = meleeAbility;
	}
	public boolean isMeleeAbility() {
		return meleeAbility;
	}
	public String getDescription() {
		return description;
	}
	protected void setDescription(String description) {
		this.description = description;
	}
}