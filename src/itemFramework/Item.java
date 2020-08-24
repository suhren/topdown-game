package itemFramework;

public abstract class Item {
	public static enum Type { INVENTORY, EQUIPMENT, LOOT };
	
	private int itemID;
	private int iconID;
	//private int inventorySlot;
	private String itemName;
	private String itemDescription;

	public Item(int itemID, int iconID) {
		this.itemID = itemID;
		this.iconID = iconID;
	}
	
	public int getItemID() {
		return itemID;
	}
	public int getIconID() {
		return iconID;
	}

	public String getItemName() {
		return itemName;
	}
	protected void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	protected void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getAbilityDescription() {
		return "";
	}

	public String getEquipmentSlotName() {
		return "";
	}
}
