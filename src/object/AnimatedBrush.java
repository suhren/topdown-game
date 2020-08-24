package object;

import java.awt.image.BufferedImage;

import framework.SpriteSheet;
import framework.DrawUtility;
import world.World;

public class AnimatedBrush extends Brush {
	private double frame, frameRate;
	private SpriteSheet spriteSheet;
	private int drawYOffset;
	
	public AnimatedBrush(double posX, double posY, int width, int height, SpriteSheet spriteSheet, double frameRate, int drawYOffset, World world) {
		super(posX, posY, width, height, world);
		this.spriteSheet = spriteSheet;
		this.frameRate = frameRate;
		this.drawYOffset = drawYOffset;
	}
	

	@Override
	public void update(double dT) {
		frame += dT * frameRate;
		if (frame >= spriteSheet.getNumberOfSprites()) {
			frame = frame % spriteSheet.getNumberOfSprites();
			onAnimationFinished();
		}
	}
	protected void setFrame(double frame) {
		this.frame = frame;
	}
	protected void onAnimationFinished() {
		// Override this if needed
	}
	@Override
	public int getDrawY() {
		return (int)getY() + drawYOffset;
	}
	@Override
	public void draw() {
		DrawUtility.drawImageCenter(getImage(), getX(), getY());
	}
	public BufferedImage getImage() {
		return spriteSheet.getSprite((int)(frame) % spriteSheet.getNumberOfSprites());
	}
}