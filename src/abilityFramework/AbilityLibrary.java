package abilityFramework;

import abilities.*;
import framework.Vector2;
import object.Actor;
import object.Brush;
import world.World;

public class AbilityLibrary {
	public abstract class AbilityID {
		public static final int ERROR		 		= 0;
		public static final int MELEE_SWORD 		= 1;
		public static final int FIREBALL 			= 2;
		public static final int FIREBALL_EXPLOSION 	= 3;
		public static final int CAMPFIRE_BURN 		= 4;
	}
	public abstract class EffectID {
		public static final int ERROR 			= 0;
		public static final int FIRE 			= 1;
		public static final int FIRE_CAMPFIRE 	= 2;
	}
	
	public static String getAbilityDescription(int abilityID) {
		switch(abilityID) {
			case AbilityID.MELEE_SWORD:
				return SwordAttack.getDescriptionStatic();
			case AbilityID.FIREBALL:
				return Fireball.getDescriptionStatic();
			case AbilityID.FIREBALL_EXPLOSION:
				return FireballExplosion.getDescriptionStatic();
			case AbilityID.CAMPFIRE_BURN:
				return CampfireBurn.getDescriptionStatic();
		}
		return "Unknown.";
	}
	public static Ability getAbility(Actor actor, World world, int ID) {
		switch(ID) {
			case AbilityID.MELEE_SWORD:
				return new SwordAttack(actor, world);
			case AbilityID.FIREBALL:
				return new Fireball(actor, world);
			case AbilityID.FIREBALL_EXPLOSION:
				return new FireballExplosion(actor, world);
			case AbilityID.CAMPFIRE_BURN:
				return new CampfireBurn(actor, world);
		}
		return null;
	}
	public static Brush getMeleeBrush(Actor a, int width, int reach) {
		switch (a.getDirection()) {
			case Vector2.Direction.RIGHT:
				return new Brush(a.getBottomRightX() + reach / 2, a.getY(), reach, width);
			case Vector2.Direction.UP:
				return new Brush(a.getX(), a.getTopLeftY() - reach / 2, width, reach);
			case Vector2.Direction.LEFT:
				return new Brush(a.getTopLeftX() - reach / 2, a.getY(), reach, width);
			case Vector2.Direction.DOWN:
				return new Brush(a.getX(), a.getBottomRightY() + reach / 2, width, reach);
			default:
				return null;
		}
	}
}