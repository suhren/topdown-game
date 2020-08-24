package animation;
public class Animation {
	private static final double DEFAULT_FRAME_RATE = 10;
	private static final int[] DEFAULT_SPRITES = {0};
	private int[] sprites;
	private double nFrames;
	private double currentFrame;
	private double frameRate;
	private final int ID;
	private boolean generateEvent;
	private boolean animationComplete = false;
	
	public Animation(int ID) {
		this(ID, DEFAULT_SPRITES, DEFAULT_FRAME_RATE, false);
	}
	public Animation(int ID, int[] sprites, double frameRate, boolean generateEvent) {
		this.ID = ID;
		setAnimation(sprites, frameRate, generateEvent);
	}
	public void setAnimation(int[] sprites, double frameRate, boolean generateEvent){
		this.sprites = sprites; 
		this.frameRate = frameRate;
		this.generateEvent = generateEvent;
		nFrames = sprites.length;
	}
	public void updateAnimation(double dT) {
		currentFrame += dT * frameRate;
		if (currentFrame >= nFrames) {
			animationComplete = true;
			currentFrame = currentFrame % nFrames;
		}
	}
	public void resetAnimation() {
		currentFrame = 0;
	}
	public int getSprite() {
		return sprites[(int)currentFrame];
	}
	public int getID() {
		return ID;
	}
	public boolean generateEventOnFinish() {
		return generateEvent;
	}
	public void setAnimationComplete(boolean val) {
		animationComplete = val;
	}
	public boolean getAnimationComplete() {
		return animationComplete;
	}
	public double getNumberOfFrames() {
		return nFrames;
	}
	public double getFrameRate() {
		return frameRate;
	}
	public int getSprite(int frame) {
		return sprites[frame];
	}
}