package framework;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import game.Camera;
import window.GraphicsBuffer;

public class DrawUtility {
	private static GraphicsBuffer gBuffer;
	private static Graphics g;
	private static Camera c;
	
	public static void drawVector(Vector2 p, Vector2 v, Color color) {
		g.setColor(color);
		g.drawLine(c.getScreenX(p.getX()), c.getScreenY(p.getY()), c.getScreenX(p.getX() + v.getX()), c.getScreenY(p.getY() + v.getY()));
	}
	public static void drawImage(Image i, double x, double y) {
		g.drawImage(i, c.getScreenX(x), c.getScreenY(y), null);
	}
	public static void drawImageCenter(Image image, double x, double y) {
		drawImage(image, x - image.getWidth(null) / 2, y - image.getHeight(null) / 2);
	}
	public static void drawImageRotated(BufferedImage image, double x, double y, double a) {
		AffineTransform tx = AffineTransform.getRotateInstance(a, image.getWidth() / 2, image.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		g.drawImage(op.filter(image, null), c.getScreenX(x - image.getWidth() / 2), c.getScreenY(y - image.getHeight() / 2), null);
	}
	public static void drawRect(double x, double y, int width, int height, Color color) {
		g.setColor(color);
		drawRect(x, y, width, height);
	}
	public static void drawRect(double x, double y, int width, int height) {
		g.drawRect(c.getScreenX(x), c.getScreenY(y), width, height);
	}
	public static void fillRect(double x, double y, int width, int height, Color color) {
		g.setColor(color);
		fillRect(x, y, width, height);
	}
	public static void fillRect(double x, double y, int width, int height) {
		g.fillRect(c.getScreenX(x), c.getScreenY(y), width, height);
	}
	public static void drawTextCenter(String s, Font f, double x, double y, Color c) {
		g.setColor(c);
		FontMetrics metrics = g.getFontMetrics(f);
	    // Determine the X coordinate for the text
	    x = x - metrics.stringWidth(s) / 2;
	    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
	    y = y - metrics.getHeight() / 2 + metrics.getAscent();
	    // Set the font
	    g.setFont(f);
	    // Draw the String
	    g.drawString(s, (int)x, (int)y);
	}
	public static void drawTextCenterWorld(String s, Font f, double x, double y, Color color) {
		drawTextCenter(s, f, c.getScreenX(x), c.getScreenY(y), color);
	}
	public static void drawImage(Image image, double x, double y, int width, int height) {
		g.drawImage(image, c.getScreenX(x), c.getScreenY(y), width, height, null);
		
	}
	
	public static Camera getCamera() {
		return c;
	}
	public static void setCamera(Camera camera) {
		DrawUtility.c = camera;
	}
	public static GraphicsBuffer getGraphicsBuffer() {
		return gBuffer;
	}
	public static void setGraphicsBuffer(GraphicsBuffer graphicsBuffer) {
		gBuffer = graphicsBuffer;
		g = gBuffer.getGraphics();
	}
	public static void drawRectScreen(int x, int y, int width, int height, Color color) {
		g.setColor(color);
		g.drawRect(x, y, width, height);
	}
	public static void fillRectScreen(int x, int y, int width, int height, Color color) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
	public static void drawImageScreenClipped(Image i, int x, int y, int clipX, int clipY, int clipWidth, int clipHeight) {
		Shape oldClip = g.getClip();
		g.setClip(clipX, clipY, clipWidth, clipHeight);
		g.drawImage(i, x, y, null);
		g.setClip(oldClip);
	}
	public static void drawImageScreen(Image i, int x, int y) {
		g.drawImage(i, x, y, null);
	}
	public static void drawImageScreenPartial(Image i, int x, int y, int w, int h) {
		g.drawImage(i, x, y, w, h, null);
	}
	public static void drawLineScreen(int x1, int y1, int x2, int y2, Color color) {
		g.setColor(color);
		g.drawLine(x1, y1, x2, y2);
	}
	public static void drawImageWorldClippedToScreen(Image image, int worldX, int worldY) {
		drawImageScreenClipped(image, c.getScreenX(worldX), c.getScreenY(worldY), 0, 0, c.getRenderWidth(), c.getRenderHeight());
	}
	public static void drawStringScreen(String string, Font font, Color color, int x, int y) {
		g.setFont(font);
		g.setColor(color);
		FontMetrics metrics = g.getFontMetrics(font);
		g.drawString(string, x, y + metrics.getHeight());
	}
	public static int getStringWidth(String string, Font font) {
		FontMetrics fontMetrics = g.getFontMetrics(font);
		return fontMetrics.stringWidth(string);
	}
}