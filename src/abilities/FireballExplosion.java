package abilities;

import abilityFramework.AbilityExplosion;
import abilityFramework.Damage;
import abilityFramework.EffectDamage;
import abilityFramework.EffectForceOrigin;
import framework.Resources;
import object.Actor;
import object.Brush;
import world.World;

public class FireballExplosion extends AbilityExplosion {
	private static final String DESCRIPTION = "Cast an exploding fireball in the cursor direction.";
	private static final double COOLDOWN = 1.0;
	
	public FireballExplosion(Actor source, World world) {
		super(source, world);
		setLifetime(0);
		setTicktime(0);
		setSingleTarget(false);
		setSpriteSheet(Resources.getSpriteSheet(Resources.SpriteSheets.EXPLOSION));
		setFrameRate(10);
		setBrush(new Brush(0, 0, 64, 64, world));
		addEffect(new EffectDamage(new Damage(50, Damage.Type.FIRE)));
		addEffect(new EffectForceOrigin(400));
		setDescription(DESCRIPTION);
	}
	
	@Override
	public void onCast() {
		super.onCast();
	}
	
	public static String getDescriptionStatic() {
		return DESCRIPTION;
	}
}
