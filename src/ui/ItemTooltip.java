package ui;

import java.awt.Color;

public class ItemTooltip extends Frame {
	
	private ItemSlot itemSlot;
	private int MAX_WIDTH = 128;
	private int TEXT_DISTANCE_Y = 8;
	private static final Color BACKGROUND_COLOR = new Color(63, 63, 63, 127);
	private static final Color TITLE_COLOR = Color.WHITE;
	private static final Color ABILITY_COLOR = Color.GREEN;
	private static final Color EQUIPMENT_COLOR = Color.GRAY;
	private static final Color DESCRIPTION_COLOR = new Color(247, 191, 36);
	
	public ItemTooltip(GameUI gameUI, int posX, int posY, ItemSlot itemSlot) {
		super(gameUI, posX, posY);
		this.itemSlot = itemSlot;
		setWidth(MAX_WIDTH);
		setHeight(MAX_WIDTH);
		setBackgroundColor(BACKGROUND_COLOR);
		int yPos = 0;
		Text title = new Text(this, 0, 0, MAX_WIDTH, itemSlot.getItem().getItemName(), TITLE_COLOR);
		addElement(title);
		yPos += title.getHeight() + TEXT_DISTANCE_Y;
		if (itemSlot.getItem().getEquipmentSlotName().length() > 0) {
			Text equipmentSlotName = new Text(this, 0, yPos, MAX_WIDTH, itemSlot.getItem().getEquipmentSlotName(), EQUIPMENT_COLOR);
			addElement(equipmentSlotName);
			yPos += equipmentSlotName.getHeight() + TEXT_DISTANCE_Y;
		}
		if (itemSlot.getItem().getAbilityDescription().length() > 0) {
			Text abilitDescription = new Text(this, 0, yPos, MAX_WIDTH, "Ability: " + itemSlot.getItem().getAbilityDescription(), ABILITY_COLOR);
			addElement(abilitDescription);
			yPos += abilitDescription.getHeight() + TEXT_DISTANCE_Y;
		}
		Text itemDescription = new Text(this, 0, yPos, MAX_WIDTH, "\"" + itemSlot.getItem().getItemDescription() + "\"", DESCRIPTION_COLOR);
		addElement(itemDescription);
		
		pack();
	}

	@Override
	public void onShowUI() {
	}
	public ItemSlot getItemSlot() {
		return itemSlot;
	}
}