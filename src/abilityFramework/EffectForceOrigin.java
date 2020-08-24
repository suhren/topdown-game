package abilityFramework;

import framework.Vector2;
import object.Actor;

public class EffectForceOrigin extends Effect {
	private double force;
	private Vector2 origin;

	public EffectForceOrigin(double force) {
		this(0, force);
	}
	public EffectForceOrigin(int ID, double force) {
		this(ID, force, new Vector2());
	}
	public EffectForceOrigin(int ID, double force, Vector2 origin) {
		super(ID);
		this.force = force;
		this.origin = origin;
	}
	@Override
	public void applyToActor(Actor a) {
		Vector2 v = a.getPosition().getSubtractedInstance(origin);
		v.setLength(force);
		a.applyForce(v);
	}
	@Override
	public void onCast(Actor a) {
		origin = a.getPosition();
		force += a.getData().getStats().getSpellForce();
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
		return new EffectForceOrigin(getID(), force, origin);
	}
}