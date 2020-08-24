package ui;

import java.util.ArrayList;
import java.util.List;

import framework.GameUtility;
import object.Actor;
import window.GraphicsBuffer;

public class GameUI {
	private MousePointer mousePointer;
	private HealthBar healthBar;
	private CastBar castBarMainHand, castBarOffHand;
	private InventoryUI inventory;
	private EquipUI equipUI;
	private List<UIElement> elements = new ArrayList<UIElement>();
	private List<UIElement> messages = new ArrayList<UIElement>();
	private List<UIElement> disposedMessages = new ArrayList<UIElement>();
	private List<UIElement> messageQue = new ArrayList<UIElement>();
	private boolean showInventory;
	private Actor actor;
	private GraphicsBuffer graphicsBuffer;
	
	public GameUI(GraphicsBuffer graphicsBuffer) {
		this.graphicsBuffer = graphicsBuffer;
		healthBar = new HealthBar(this, 16, 16);
		castBarMainHand = new CastBar(this, 16, 64);
		castBarOffHand = new CastBar(this, 16, 112);
		inventory = new InventoryUI(this, 750, 430);
		equipUI = new EquipUI(this, 16, 300);
		mousePointer = new MousePointer(this, 0, 0);
		elements.add(healthBar);
		elements.add(castBarMainHand);
		elements.add(castBarOffHand);
		elements.add(inventory);
		elements.add(equipUI);
		elements.add(mousePointer);
	}
	
	public void update(double dT) {
		for (UIElement e : messageQue)
			messages.add(e);
		messageQue.clear();
		
		for (UIElement e : elements)
			e.update(dT);
		
		for (UIElement e : messages)
			e.update(dT);
		
		for (UIElement e : disposedMessages)
			messages.remove(e);
		disposedMessages.clear();
	}
	public void draw() {
		healthBar.draw();
		castBarMainHand.draw();
		castBarOffHand.draw();
		
		if (showInventory) {
			inventory.draw();
			equipUI.draw();
		}
		
		for (UIElement e : messages)
			e.draw();
		
		mousePointer.draw();
	}
	public StatusBar getHealthBar() {
		return healthBar;
	}
	public StatusBar getCastBarMainHand() {
		return castBarMainHand;
	}
	public StatusBar getCastBarOffHand() {
		return castBarOffHand;
	}
	public InventoryUI getInventory() {
		return inventory;
	}
	public EquipUI getEquipUI() {
		return equipUI;
	}
	public MousePointer getMousePointer() {
		return mousePointer;
	}
	public void toggleInventory() {
		if (showInventory = !showInventory) {
			inventory.onShowUI();
			equipUI.onShowUI();
		}
		mousePointer.setUIMode(showInventory);
	}

	public Actor getActor() {
		return actor;
	}
	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public ItemSlot getItemSlotAtPosition(int x, int y) {
		for (ItemSlot i : inventory.getItemSlots())
			if (inventory.getX() + i.getX() <= x && inventory.getX() + i.getX() + i.getWidth() >= x &&
				inventory.getY() + i.getY() <= y && inventory.getY() + i.getY() + i.getHeight() >= y)
				return i;
		for (ItemSlot i : equipUI.getItemSlots())
			if (equipUI.getX() + i.getX() <= x && equipUI.getX() + i.getX() + i.getWidth() >= x &&
					equipUI.getY() + i.getY() <= y && equipUI.getY() + i.getY() + i.getHeight() >= y)
				return i;
		return null;
	}

	public void constrainElementToScreen(UIElement element) {
		element.setX(GameUtility.clamp(0, graphicsBuffer.getRenderWidth() - element.getWidth(), element.getX()));
		element.setY(GameUtility.clamp(0, graphicsBuffer.getRenderHeight() - element.getHeight(), element.getY()));
	}

	
	public void showMessage(String message, double lifeTime) {
		messageQue.add(new Message(this, message, lifeTime));
	}
	public void disposeElement(UIElement element) {
		disposedMessages.add(element);
	}
}