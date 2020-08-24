package ui;

import java.awt.image.BufferedImage;

import framework.DrawUtility;
import framework.Resources;
import itemFramework.Item;
import itemFramework.Item.Type;

public abstract class ItemSlot extends FrameElement {
	public static final int SIZE = 32;
	private static final BufferedImage BACKGROUND_IMAGE = Resources.getImage(Resources.Images.ITEMSLOT);
	private int slotID;
	private Type type;
	private boolean drawItem = true;
	
	public ItemSlot(Frame frame, int posX, int posY, int slotID, Type type) {
		super(frame, posX, posY, SIZE, SIZE);
		this.slotID = slotID;
		this.type = type;
	}

	@Override
	public void draw(int x, int y) {
		DrawUtility.drawImageScreen(BACKGROUND_IMAGE, getX() + x, getY() + y);
	}
	
	public int getSlotID() {
		return slotID;
	}
	public abstract Item getItem();
	
	public void setDrawItem(boolean drawItem) {
		this.drawItem = drawItem;
	}
	protected boolean drawItem() {
		return drawItem;
	}
	public Type getType() {
		return type;
	}
}
