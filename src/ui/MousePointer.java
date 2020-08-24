package ui;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import framework.DrawUtility;
import framework.Resources;
import itemFramework.ActorItems.NoItemException;
import itemFramework.ActorItems.NotEquippableException;
import itemFramework.ActorItems.WrongSlotException;
import itemFramework.ItemLibrary;
import window.InputBuffer;

public class MousePointer extends UIElement {
	private static final BufferedImage IMAGE = Resources.getImage(Resources.Images.MOUSE_POINTER);
	private static final int SIZE = 32;
	private static final int CLICK_BUTTON = MouseEvent.BUTTON1;
	private static final double ERROR_MESSAGE_TIME = 2;
	public static enum State { IDLE, DRAGGING };
	
	private InputBuffer input;
	private boolean uiMode;
	private State state = State.IDLE;
	private ItemTooltip itemTooltip;
	private ItemSlot fromItemSlot;
	
	public MousePointer(GameUI gameUI, int posX, int posY) {
		super(gameUI, posX, posY, SIZE, SIZE);
	}
	
	public void setInputBuffer(InputBuffer input) {
		this.input = input;
	}
	@Override
	public void draw() {
		//if (actor.drawMousePointer())
		DrawUtility.drawImageScreen(IMAGE, getX(), getY());
		if (itemTooltip != null)
			itemTooltip.draw();
		if (fromItemSlot != null)
			DrawUtility.drawImageScreen(ItemLibrary.getIcon(fromItemSlot.getItem().getIconID()), getX(), getY());
	}
	@Override
	public void update(double dT) {
		setX((int)input.getMousePosition().getX());
		setY((int)input.getMousePosition().getY());
		
		if (uiMode)
			updateInput();
	}
	private void updateInput() {
		if (state == State.IDLE) {
			ItemSlot itemSlot = getUI().getItemSlotAtPosition(getX(), getY());
			if (itemSlot != null && itemSlot.getItem() != null) {
				if (itemTooltip == null || itemTooltip.getItemSlot().getSlotID() != itemSlot.getSlotID()) {
					itemTooltip = new ItemTooltip(getUI(), itemSlot.getFrame().getX() + itemSlot.getX(), itemSlot.getFrame().getY() + itemSlot.getY(), itemSlot);
					getUI().constrainElementToScreen(itemTooltip);
				}
				if (input.mousePressed(CLICK_BUTTON)) {
					startMove(itemSlot);
				}
			}
			else
				itemTooltip = null;
		}
		else if (state == State.DRAGGING)
			if (input.mouseUp(CLICK_BUTTON))
				stopMove();
	}
	private void stopMove() {
		ItemSlot itemSlot = getUI().getItemSlotAtPosition(getX(), getY());
		if (itemSlot != null)
			try {
				getActor().getData().getActorItems().moveItem(fromItemSlot.getSlotID(), itemSlot.getSlotID(), fromItemSlot.getType(), itemSlot.getType());
			} catch (WrongSlotException | NotEquippableException | NoItemException e) {
				getUI().showMessage(e.getMessage(), ERROR_MESSAGE_TIME);
			} finally {
				moveDone();
			}
		else
			moveDone();
	}
	private void moveDone() {
		fromItemSlot.setDrawItem(true);
		fromItemSlot = null;
		state = State.IDLE;
	}
	private void startMove(ItemSlot itemSlot) {
		state = State.DRAGGING;
		fromItemSlot = itemSlot;
		itemSlot.setDrawItem(false);
		itemTooltip = null;
	}
	@Override
	public void onShowUI() {

	}
	public void setUIMode(boolean uiMode) {
		this.uiMode = uiMode;
		getActor().setMouseInputEnable(!uiMode);
		if (!uiMode)
			itemTooltip = null;
	}
}