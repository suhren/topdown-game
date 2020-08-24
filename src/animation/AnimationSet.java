package animation;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import framework.SpriteSheet;

public abstract class AnimationSet {
	private SpriteSheet spriteSheet;
	private HashMap<Integer, Animation> animations = new HashMap<Integer, Animation>();
	private Animation currentAnimation;
	private List<AnimationListener> animationListeners = new ArrayList<AnimationListener>();
	
	public void updateAnimations(double dT) {
		currentAnimation.updateAnimation(dT);
		if (currentAnimation.getAnimationComplete()) {
			currentAnimation.setAnimationComplete(false);
			if (currentAnimation.generateEventOnFinish()) {
				for (AnimationListener l : animationListeners)
					l.animationComplete(currentAnimation);
			}
		}
	}
	public void addAnimationListener(AnimationListener listener) {
		animationListeners.add(listener);
	}
	void addAnimation(Animation animation) {
		animations.put(animation.getID(), animation);
	}
	Animation getAnimation(int ID) {
		return animations.get(ID);
	}
	void setCurrentAnimation(int ID) {
		if (currentAnimation != null)
			if (currentAnimation.getID() != ID)
				currentAnimation.resetAnimation();
		
		currentAnimation = animations.get(ID);
	}
	public Image getSprite() {
		return spriteSheet.getSprite(currentAnimation.getSprite());
	}
	static int[] readIntArray(String line) {
		String []strings = line.replaceAll("^\\s*\\[|\\]\\s*$", "").split("\\s*,\\s*");
		int[] array = new int[strings.length];
		for (int i = 0; i < strings.length; i++)
			array[i] = Integer.parseInt(strings[i]);
		return array;
	}
	static double readDouble(String line) {
		return Double.valueOf(line);
	}
	static int readInteger(String line) {
		return Integer.valueOf(line);
	}
	static String readString(String line) {
		return line;
	}
	public BufferedImage getFrameImage(int animation, int frame) {
		return spriteSheet.getSprite(animations.get(animation).getSprite(0));
	}
	protected void setSpriteSheet(SpriteSheet spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
}