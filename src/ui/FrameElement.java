package ui;

import object.Actor;

public abstract class FrameElement {
	private int posX, posY, width, height;
	private Frame frame;
	
	public FrameElement(Frame frame, int posX, int posY) {
		this(frame, posX, posY, 0, 0);
	}
	public FrameElement(Frame frame, int posX, int posY, int width, int height) {
		this.frame = frame;
		this.posX = posX;
		this.posY = posY;
		this.setWidth(width);
		this.setHeight(height);
	}
	public abstract void draw(int x, int y);
	
	public int getX() {
		return posX;
	}
	public void setX(int posX) {
		this.posX = posX;
	}
	public int getY() {
		return posY;
	}
	public void setY(int posY) {
		this.posY = posY;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Actor getActor() {
		return frame.getActor();
	}
	public Frame getFrame() {
		return frame;
	}
}
