package object;

import abilities.CampfireBurn;
import abilityFramework.AbilityMelee;
import framework.Resources;
import framework.SpriteSheet;
import world.World;

public class Campfire extends AnimatedBrush {
	
	private static final SpriteSheet SPRITESHEET 	= Resources.getSpriteSheet(Resources.SpriteSheets.CAMPFIRE);
	private static final int SIZE 					= 48;
	private static final int DRAW_Y_OFFSET 			= -12;
	private static final double FRAME_RATE 			= 5;
	private static final double DAMAGE_TICK_TIME	= 0.5;
	private double time;
	
	public Campfire(double posX, double posY, World world) {
		super(posX, posY, SIZE, SIZE, SPRITESHEET, FRAME_RATE, DRAW_Y_OFFSET, world);
	}
	@Override
	public void update(double dT) {
		super.update(dT);
		if ((time += dT) >= DAMAGE_TICK_TIME) {
			time = 0;
			damage();
		}
	}
	private void damage() {
		AbilityMelee ability = new CampfireBurn(null, getWorld());
		ability.getBrush().setPosition(getPosition());
		getWorld().addAbility(ability);
	}
}