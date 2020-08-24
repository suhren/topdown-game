package abilityFramework;

import object.Actor;

public abstract class Effect {
	private int ID;
	public Effect(int ID) {
		this.ID = ID;
	}
	public abstract void applyToActor(Actor a);
	public abstract void onCast(Actor a);
	public abstract void update(double dT);
	public abstract void refresh();
	public abstract void draw();
	public int getID() {
		return ID;
	}
	public abstract Effect getCopy();
}