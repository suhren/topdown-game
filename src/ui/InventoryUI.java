package ui;

import itemFramework.Item;

public class InventoryUI extends ItemFrame {
	private static final String TITLE = "Inventory";
	private static final int WIDTH = 8;
	private static final int PADDING = 4;
	
	public InventoryUI(GameUI gameUI, int posX, int posY) {
		super(gameUI, TITLE, posX, posY);
		setPaddingX(PADDING);
		setPaddingY(PADDING);
	}
	@Override
	public void update(double dT) {
	}
	@Override
	public void onShowUI() {
		clearElements();
		Item[] items = getActor().getData().getActorItems().getInventoryItems();
		for (int i = 0; i < items.length; i++)
			addElement(new ItemSlotInventory(this, (i % WIDTH) * ItemSlot.SIZE, (i / WIDTH) * ItemSlot.SIZE, i));
		pack();
	}
}