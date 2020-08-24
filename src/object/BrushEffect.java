package object;

import framework.SpriteSheet;
import world.World;

public class BrushEffect extends AnimatedBrush {
	public BrushEffect(double posX, double posY, SpriteSheet spriteSheet, double frameRate,
			int drawYOffset, World world) {
		super(posX, posY, spriteSheet.getSpriteWidth(), spriteSheet.getSpriteHeight(), spriteSheet, frameRate, drawYOffset, world);
	}

	@Override
	protected void onAnimationFinished() {
		dispose();
	}
}
