package ui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import framework.DrawUtility;
import framework.Resources;

public abstract class Frame extends UIElement {
	private static final int DEFAULT_PADDING = 8;
	private static final Color DEFAULT_BACKGROUND_COLOR = Color.DARK_GRAY;
	private static final Color DEFAULT_BORDER_COLOR = Color.BLACK;
	private static final int BORDER_WIDTH = 2;
	
	private List<FrameElement> elements = new ArrayList<FrameElement>();
	private String title;
	private int paddingX, paddingY;
	private Color backgroundColor, borderColor;
	
	public Frame(GameUI gameUI, int posX, int posY) {
		this(gameUI, "", posX, posY, 0, 0);
	}
	public Frame(GameUI gameUI, String title, int posX, int posY) {
		this(gameUI, title, posX, posY, 0, 0);
	}
	public Frame(GameUI gameUI, String title, int posX, int posY, int width, int height) {
		super(gameUI, posX, posY, width, height);
		this.title = title;
		this.paddingX = DEFAULT_PADDING;
		this.paddingY = DEFAULT_PADDING;
		backgroundColor = DEFAULT_BACKGROUND_COLOR;
		borderColor = DEFAULT_BORDER_COLOR;
	}

	protected void clearElements() {
		elements.clear();
	}
	public void addElement(FrameElement element) {
		elements.add(element);
	}

	public void pack() {
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;
		
		for (FrameElement e : elements) {
			if (e.getX() < minX)
				minX = e.getX();
			if (e.getY() < minY)
				minY = e.getY();
			if (e.getX() + e.getWidth() > maxX)
				maxX = e.getX() + e.getWidth();
			if (e.getY() + e.getHeight() > maxY)
				maxY = e.getY() + e.getHeight();
		}
		
		for (FrameElement e : elements) {
			e.setX(e.getX() - minX + paddingX);
			e.setY(e.getY() - minY + paddingY);
		}
		
		setWidth(maxX - minX + 2 * paddingX);
		setHeight(maxY - minY + 2 * paddingY);
	}
	
	@Override
	public void update(double dT) {
		
	}

	@Override
	public void draw() {
		DrawUtility.fillRectScreen(getX(), getY(), getWidth(), getHeight(), backgroundColor);
		
		for (int i = 0; i < BORDER_WIDTH; i++)
			DrawUtility.drawRectScreen(getX() + i, getY() + i, getWidth() - 2*i, getHeight() - 2*i, borderColor);
		
		DrawUtility.drawTextCenter(title, Resources.getFont(Resources.Fonts.FRAME_TITLE), getX() + getWidth() / 2, getY() - 8, Color.WHITE);
		
		for (FrameElement e : elements)
			e.draw(getX(), getY());
	}

	public List<FrameElement> getElements() {
		return elements;
	}
	protected void setPaddingX(int paddingX) {
		this.paddingX = paddingX;
	}
	protected void setPaddingY(int paddingY) {
		this.paddingY = paddingY;
	}
	protected void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	protected void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
}
