package itemFramework;

import itemFramework.Item.Type;

public class ActorItems {
	public class InventoryFullException extends Exception {
	 	private static final long serialVersionUID = -5547123859700942283L;
		private static final String MESSAGE = "Inventory is full";
		public InventoryFullException() {
			super(MESSAGE);
		}
	}
	public class NoItemEquippedException extends Exception {
		private static final long serialVersionUID = -5567911704111590180L;
		private static final String MESSAGE = "No item equipped";
		public NoItemEquippedException() {
			super(MESSAGE);
		}
	}
	public class WrongSlotException extends Exception {
		private static final long serialVersionUID = -3541841626440723529L;
		private static final String MESSAGE = "That item can't be equipped in that slot";
		public WrongSlotException() {
			super(MESSAGE);
		}
	}
	public class NotEquippableException extends Exception {
		private static final long serialVersionUID = -3083034528835641492L;
		private static final String MESSAGE = "That item can't be equipped";
		public NotEquippableException() {
			super(MESSAGE);
		}
	}
	public class NoItemException extends Exception {
		private static final long serialVersionUID = -2608943650177168942L;
		private static final String MESSAGE = "No item";
		public NoItemException() {
			super(MESSAGE);
		}
	}
	
	public class EquipID {
		public static final int MAINHAND 	= 0;
		public static final int OFFHAND 	= 1;
		public static final int HEAD 		= 2;
		public static final int CHEST		= 3;
		public static final int HANDS 		= 4;
		public static final int LEGS 		= 5;
		public static final int FEET 		= 6;
		public static final int TRINKET_A 	= 7;
		public static final int TRINKET_B	= 8;
		public static final int TRINKET_C	= 9;
	}
	
	public static final int N_INVENTORY_ITEMS 	= 16;
	public static final int N_EQUIPPABLE_ITEMS 	= 10;
	
	private Item[] inventoryItems			= new Item[N_INVENTORY_ITEMS];
	private EquippableItem[] equippedItems 	= new EquippableItem[N_EQUIPPABLE_ITEMS];
	
	public void putItemInventory(Item item) throws InventoryFullException {
		int slot;
		if ((slot = getInventorySpace()) >= 0)
			inventoryItems[slot] = item;
		else
			throw new InventoryFullException();
	}
	public Item getItemInventory(int slot) throws NoItemException {
		if (inventoryItems[slot] != null)
			return inventoryItems[slot];
		else
			throw new NoItemException();
	}

	private int getInventorySpace() {
		for (int i = 0; i < inventoryItems.length; i++)
			if (inventoryItems[i] == null)
				return i;
		return -1;
	}
	
	public AbilityItem getMainhandItem() throws NoItemEquippedException {
		if (equippedItems[EquipID.MAINHAND] != null)
			return (AbilityItem)equippedItems[EquipID.MAINHAND];
		else
			throw new NoItemEquippedException();
	}
	public AbilityItem getOffhandItem() throws NoItemEquippedException {
		if (equippedItems[EquipID.OFFHAND] != null)
			return (AbilityItem)equippedItems[EquipID.OFFHAND];
		else
			throw new NoItemEquippedException();
	}
	public EquippableItem getEquippedItem(int slot) throws NoItemEquippedException{
		if (equippedItems[slot] != null)
			return equippedItems[slot];
		else
			throw new NoItemEquippedException();
	}
	public void equipItem(int inventorySlot, int equipSlot) throws WrongSlotException, NotEquippableException {
		if (inventoryItems[inventorySlot] != null) {
			if (inventoryItems[inventorySlot] instanceof EquippableItem) {
				if (isEquippableInSlot((EquippableItem)inventoryItems[inventorySlot], equipSlot)) {
					Item temp = equippedItems[equipSlot];
					equippedItems[equipSlot] = (EquippableItem)inventoryItems[inventorySlot];
					inventoryItems[inventorySlot] = temp;
				}
				else
					throw new WrongSlotException();
			}
			else
				throw new NotEquippableException();
		}
		else {
			inventoryItems[inventorySlot] = equippedItems[equipSlot];
			equippedItems[equipSlot] = null;
		}
	}
	public EquippableItem[] getEquippedItems() {
		return equippedItems;
	}
	public Item[] getInventoryItems() {
		return inventoryItems;
	}
	public void switchInventoryItems(int slot1, int slot2) {
		Item i = inventoryItems[slot1];
		inventoryItems[slot1] = inventoryItems[slot2];
		inventoryItems[slot2] = i;
	}
	public void switchEquipmentItems(int slot1, int slot2) throws WrongSlotException {
		if (isEquippableInSlot(equippedItems[slot1], slot2) && isEquippableInSlot(equippedItems[slot2], slot1)) {
			EquippableItem i = equippedItems[slot1];
			equippedItems[slot1] = equippedItems[slot2];
			equippedItems[slot2] = i;
		}
		else
			throw new WrongSlotException();
	}
	private boolean isEquippableInSlot(EquippableItem item, int slot) {
		if (item != null) {
			switch (item.getEquipmentType()) {
				case ABILITY_ITEM:
					return slot == EquipID.MAINHAND || slot == EquipID.OFFHAND;
				case CHEST:
					return slot == EquipID.CHEST;
				case FEET:
					return slot == EquipID.FEET;
				case HANDS:
					return slot == EquipID.HANDS;
				case HEAD:
					return slot == EquipID.HEAD;
				case LEGS:
					return slot == EquipID.LEGS;
				case TRINKET:
					return slot == EquipID.TRINKET_A || slot == EquipID.TRINKET_B || slot == EquipID.TRINKET_C;
				default:
					return false;
			}
		}
		else
			return true;
	}
	public void moveItem(int slot1, int slot2, Type type1, Type type2) throws WrongSlotException, NotEquippableException, NoItemException {
		if (type1 == Type.INVENTORY && type2 == Type.INVENTORY)
			switchInventoryItems(slot1, slot2);
		else if (type1 == Type.EQUIPMENT && type2 == Type.EQUIPMENT)
			switchEquipmentItems(slot1, slot2);
		else if (type1 == Type.INVENTORY && type2 == Type.EQUIPMENT)
			equipItem(slot1, slot2);
		else if (type1 == Type.EQUIPMENT && type2 == Type.INVENTORY)
			equipItem(slot2, slot1);
	}
}