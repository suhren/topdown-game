package npc;

import object.Actor;

public abstract class AI {
	private Actor actor;
	public AI(Actor actor) {
		this.actor = actor;
	}
	protected Actor getActor() { return actor; }
	protected abstract void update(double dT);
}