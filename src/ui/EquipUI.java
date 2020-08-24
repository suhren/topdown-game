package ui;

import itemFramework.ActorItems;

public class EquipUI extends ItemFrame {
	private static final String TITLE = "Equipment";
	private static final int ICON_DISTANCE = 4;
	private static final int COLUMN_WIDTH = 100;
	private static final int PADDING = 4;
	private static final double ACTOR_PREVIEW_MAGNIFICATION = 2.0;
	
	public EquipUI(GameUI gameUI) {
		this(gameUI, 0, 0);
	}
	public EquipUI(GameUI gameUI, int posX, int posY) {
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
		
		int yOffset = ItemSlot.SIZE + ICON_DISTANCE;
		int xOffset = ItemSlot.SIZE + COLUMN_WIDTH;
		
		// Preview
		addElement(new ActorPreview(this,ItemSlot.SIZE + COLUMN_WIDTH / 2, yOffset * 2, COLUMN_WIDTH, yOffset * 4, ACTOR_PREVIEW_MAGNIFICATION));
		
		// Left column
		addElement(new ItemSlotEquipment(this, 0, yOffset * 0, ActorItems.EquipID.HEAD));
		addElement(new ItemSlotEquipment(this, 0, yOffset * 1, ActorItems.EquipID.CHEST));
		addElement(new ItemSlotEquipment(this, 0, yOffset * 2, ActorItems.EquipID.LEGS));
		addElement(new ItemSlotEquipment(this, 0, yOffset * 3, ActorItems.EquipID.FEET));
		
		// Right column
		addElement(new ItemSlotEquipment(this, xOffset, yOffset * 0, ActorItems.EquipID.HANDS));
		addElement(new ItemSlotEquipment(this, xOffset, yOffset * 1, ActorItems.EquipID.TRINKET_A));
		addElement(new ItemSlotEquipment(this, xOffset, yOffset * 2, ActorItems.EquipID.TRINKET_B));
		addElement(new ItemSlotEquipment(this, xOffset, yOffset * 3, ActorItems.EquipID.TRINKET_C));
		
		// Bottom
		addElement(new ItemSlotEquipment(this, ItemSlot.SIZE + COLUMN_WIDTH / 2 - ICON_DISTANCE / 2 - ItemSlot.SIZE, yOffset * 4, ActorItems.EquipID.MAINHAND));
		addElement(new ItemSlotEquipment(this, ItemSlot.SIZE + COLUMN_WIDTH / 2 + ICON_DISTANCE / 2, yOffset * 4, ActorItems.EquipID.OFFHAND));
		
		pack();
	}
}
