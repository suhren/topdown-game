package abilityFramework;

import object.Actor;
import world.World;

public abstract class AbilitySelfTarget extends Ability {

	public AbilitySelfTarget(Actor source, World world) {
		super(source, world);
	}
	
	@Override
	protected void onCast() {
		for (Effect e : getEffects())
			e.applyToActor(getSource());
		dispose();
	}
}