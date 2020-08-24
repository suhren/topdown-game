package itemFramework;

import abilityFramework.AbilityLibrary;

public class AbilityItem extends EquippableItem {
	private int abilityID;
	
	public AbilityItem(int itemID, int iconID, int abilityID) {
		super(itemID, iconID, EquipmentType.ABILITY_ITEM);
		this.abilityID = abilityID;
	}
	
	public int getAbilityID() {
		return abilityID;
	}
	@Override
	public String getAbilityDescription() {
		return AbilityLibrary.getAbilityDescription(abilityID);
	}
}