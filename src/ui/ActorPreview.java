package ui;

import java.awt.Image;

import framework.DrawUtility;

public class ActorPreview extends FrameElement {
	
	private int centerX, centerY, clipWidth, clipHeight;
	private double ratio;
	
	public ActorPreview(Frame frame, int centerX, int centerY, int clipWidth, int clipHeight, double ratio) {
		super(frame, centerX - clipWidth / 2, centerY - clipHeight / 2);
		this.centerX = centerX;
		this.centerY = centerY;
		this.ratio = ratio;
		this.clipWidth = clipWidth;
		this.clipHeight = clipHeight;
	}

	@Override
	public void draw(int x, int y) {
		Image image = getActor().getImage();
		image = image.getScaledInstance((int)(image.getWidth(null) * ratio), (int)(image.getHeight(null) * ratio), Image.SCALE_DEFAULT);
		DrawUtility.drawImageScreenClipped(image, x + centerX - image.getWidth(null) / 2, y + centerY - image.getHeight(null) / 2, x + getX(), y + getY(), clipWidth, clipHeight);
	}
}
