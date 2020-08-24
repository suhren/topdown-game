package abilities;

import abilityFramework.AbilityProjectilePayload;
import abilityFramework.Damage;
import abilityFramework.EffectDamage;
import abilityFramework.EffectDamageTick;
import abilityFramework.AbilityLibrary.EffectID;
import framework.Resources;
import framework.Resources.SpriteSheets;
import object.Actor;
import object.AnimatedBrush;
import world.World;

public class Fireball extends AbilityProjectilePayload {
	private static final double CAST_TIME = 0.5;
	private static final double COOLDOWN = 1.0;
	private static final String DESCRIPTION = "Cast a fireball in the cursor direction.";
	
	public Fireball(Actor source, World world) {
		super(source, world);
		setCastTime(CAST_TIME);
		setCooldown(COOLDOWN);
		setLifetime(10);
		setSingleTarget(true);
		setImpactEffectID(Resources.SpriteSheets.IMPACT_FIRE);
		setImpactEffectFPS(15);
		setBrush(new AnimatedBrush(0, 0, 8, 8, Resources.getSpriteSheet(SpriteSheets.FIREBALL), 5, 0, world));
		setDescription(DESCRIPTION);
	}
	
	@Override
	public void onCast() {
		if (getSource() != null) {
			EffectDamage damage = new EffectDamage(new Damage(60, Damage.Type.FIRE));
			EffectDamageTick damageTick = new EffectDamageTick(EffectID.FIRE, new Damage(15, Damage.Type.FIRE), 0.5, 5, Resources.getSpriteSheet(Resources.SpriteSheets.SPELL_FIRE), 5);
			addEffect(damage);
			setSpeed(500);
			addEffect(damageTick);
			addPayloadAttack(new FireballExplosion(getSource(), getWorld()));
		}
		super.onCast();
	}
	
	public static String getDescriptionStatic() {
		return DESCRIPTION;
	}
}
