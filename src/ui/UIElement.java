package ui;

import object.Actor;

public abstract class UIElement {
	private int posX;
	private int posY;
	private int width;
	private int height;
	private GameUI gameUI;
	
	public UIElement(GameUI gameUI, int width, int height) {
		this(gameUI, 0, 0, width, height);
	}
	public UIElement(GameUI gameUI,int posX, int posY, int width, int height) {
		this.gameUI = gameUI;
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
	}
	
	public int getX() {
		return posX;
	}
	public int getY() {
		return posY;
	}
	public void setX(int posX) {
		this.posX = posX;
	}
	public void setY(int posY) {
		this.posY = posY;
	}
	public void setPosition(int posX, int posY) {
		this.posX = posX;
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
	protected Actor getActor() {
		return gameUI.getActor();
	}
	protected GameUI getUI() {
		return gameUI;
	}
	public void dispose() {
		gameUI.disposeElement(this);
	}
	public abstract void update(double dT);
	public abstract void draw();
	public abstract void onShowUI();
}