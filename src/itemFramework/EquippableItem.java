package itemFramework;

public class EquippableItem extends Item {
	public static enum EquipmentType { ABILITY_ITEM, HEAD, CHEST, HANDS, LEGS, FEET, TRINKET};
	private EquipmentType equipmentType;
	
	public EquippableItem(int itemID, int iconID, EquipmentType equipmentType) {
		super(itemID, iconID);
		this.equipmentType = equipmentType;
	}

	public EquipmentType getEquipmentType() {
		return equipmentType;
	}
	@Override
	public String getEquipmentSlotName() {
		switch(equipmentType) {
			case ABILITY_ITEM: return "Ability item";
			case HEAD: 		return "Ability item";
			case CHEST: 	return "Chest";
			case HANDS: 	return "Hands";
			case LEGS: 		return "Legs";
			case FEET: 		return "Feet";
			case TRINKET: 	return "Trinket";
			default:		return "Unknown";
		}
	}
}