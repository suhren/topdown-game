package abilityFramework;

import framework.Vector2;
import object.Actor;

public class EffectForceLinear extends Effect {
	private Vector2 force;
	
	public EffectForceLinear() {
		this(0);
	}
	public EffectForceLinear(int ID) {
		this(ID, new Vector2());
	}
	public EffectForceLinear(Vector2 force) {
		this(0, force);
	}
	public EffectForceLinear(int ID, Vector2 force) {
		super(ID);
		this.force = force;
	}

	@Override
	public void applyToActor(Actor a) {
		a.applyForce(force);
	}
	@Override
	public void onCast(Actor a) {
		force.setLength(force.getLength() + a.getData().getStats().getPhysicalForce());
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
		return new EffectForceLinear(getID(), force);
	}
}