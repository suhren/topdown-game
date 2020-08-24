package abilityFramework;

import object.Actor;

public class EffectDamage extends Effect {
	Damage damage;
	
	public EffectDamage(Damage damage) {
		this(0, damage);
	}
	public EffectDamage(int ID, Damage damage) {
		super(ID);
		this.damage = damage;
	}
	@Override
	public void applyToActor(Actor a) {
		a.applyDamage(damage);
	}
	@Override
	public void onCast(Actor a) {
		this.damage.setDamage(damage.getDamage() + a.getData().getStats().getDamage());
	}
	@Override
	public void update(double dT) {
	}
	@Override
	public void draw() {
	}
	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Effect getCopy() {
		return new EffectDamage(damage);
	}
}