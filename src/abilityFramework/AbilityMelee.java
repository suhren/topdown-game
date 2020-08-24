package abilityFramework;

import object.Actor;
import world.World;

public class AbilityMelee extends AbilityBrush {
	private int attackWidth = 32;
	private int attackReach = 32;
	
	public AbilityMelee(Actor source, World world) {
		super(source, world);
	}
	
	@Override
	protected void onCast() {
		super.onCast();
		if (getSource() != null)
			setBrush(AbilityLibrary.getMeleeBrush(getSource(), attackWidth, attackReach));
	}
	
	public void setAttackWidth(int attackWidth) {
		this.attackWidth = attackWidth;
	}
	public void setAttackReach(int attackReach) {
		this.attackReach = attackReach;
	}
}