package abilities;

import abilityFramework.AbilityMelee;
import abilityFramework.Damage;
import abilityFramework.EffectDamageTick;
import abilityFramework.AbilityLibrary.EffectID;
import framework.Resources;
import object.Actor;
import object.Brush;
import object.Faction;
import world.World;

public class CampfireBurn extends AbilityMelee {
	private static final String DESCRIPTION = "How did you get a hold of this?";
	
	public CampfireBurn(Actor source, World world) {
		super(source, world);
		setLifetime(0);
		setSingleTarget(false);
		addEffect(new EffectDamageTick(EffectID.FIRE_CAMPFIRE, new Damage(10, Damage.Type.FIRE), 0.5, 5, Resources.getSpriteSheet(Resources.SpriteSheets.SPELL_FIRE), 5));
		setBrush(new Brush(0, 0, 32, 32));
		setTargetFactions(Faction.FACTIONS_ALL);
	}
	
	@Override
	public void onCast() {
		super.onCast();
	}
	
	public static String getDescriptionStatic() {
		return DESCRIPTION;
	}
}
