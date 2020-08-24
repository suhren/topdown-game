package ui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import framework.DrawUtility;
import framework.Resources;

public class Text extends FrameElement {
	private static final Color DEFAULT_COLOR = Color.LIGHT_GRAY;
	private static final Font DEFAULT_FONT = Resources.getFont(Resources.Fonts.ITEM_DESCRIPTION);
	private Color color;
	private Font font;
	private String[] lines;
	
	public Text(Frame frame, int posX, int posY, int maxWidth, String string) {
		this(frame, posX, posY, maxWidth, string, DEFAULT_FONT, DEFAULT_COLOR);
	}
	public Text(Frame frame, int posX, int posY, int maxWidth, String string, Color color) {
		this(frame, posX, posY, maxWidth, string, DEFAULT_FONT, color);
	}
	public Text(Frame frame, int posX, int posY, int maxWidth, String string, Font font, Color color) {
		super(frame, posX, posY);
		this.font = font;
		this.color = color;
		lines = splitString(string, maxWidth, font);
	}

	private String[] splitString(String string, int maxWidth, Font font) {
		String[] tokens = string.split(" ");
		String currentLine = "";
		List<String> lines = new ArrayList<String>();
		
		int currentWidth = 0;
		int currentMax = 0;
		
		for (String token : tokens) {
			currentWidth += getStringWidth(token + " ", font);
			
			if (currentWidth > maxWidth) {
				lines.add(currentLine);
				currentLine = token + " ";
				currentWidth = getStringWidth(currentLine, font);
			}
			else
				currentLine += token + " ";
			
			if (currentWidth > currentMax)
				currentMax = currentWidth;
		}
		lines.add(currentLine);
		setWidth((currentMax <= maxWidth) ? currentMax : maxWidth);
		setHeight(lines.size() * font.getSize());
		
		return lines.toArray(new String[lines.size()]);
	}
	
	private int getStringWidth(String string, Font font) {
		return DrawUtility.getStringWidth(string, font);
	}
	
	@Override
	public void draw(int x, int y) {
		for (int i = 0; i < lines.length; i++)
			DrawUtility.drawStringScreen(lines[i], font, color, x + getX(), y + getY() + i * font.getSize());
	}
}