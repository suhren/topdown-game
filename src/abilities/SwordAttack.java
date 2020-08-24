package abilities;

import abilityFramework.AbilityMelee;
import abilityFramework.Damage;
import abilityFramework.EffectDamage;
import abilityFramework.EffectForceLinear;
import framework.Vector2;
import object.Actor;
import world.World;

public class SwordAttack extends AbilityMelee {
	private static final double CAST_TIME = 0.5;
	private static final String DESCRIPTION = "Swing the sword.";
	private static final double COOLDOWN = 1.0;
	
	public SwordAttack(Actor source, World world) {
		super(source, world);
		setCastTime(CAST_TIME);
		setCooldown(COOLDOWN);
		setLifetime(0);
		setSingleTarget(false);
		setAttackReach(48);
		setAttackWidth(48);
		setMeleeAbility(true);
		setDescription(DESCRIPTION);
	}
	
	@Override
	public void onCast() {
		if (getSource() != null) {
			EffectDamage damage = new EffectDamage(new Damage(60, Damage.Type.PHYSICAL));
			EffectForceLinear force = new EffectForceLinear(Vector2.getDirectionVector(getSource().getDirection()));
			addEffect(damage);
			addEffect(force);
		}
		super.onCast();
	}
	
	public static String getDescriptionStatic() {
		return DESCRIPTION;
	}
}
