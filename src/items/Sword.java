package items;

import abilityFramework.AbilityLibrary;
import itemFramework.AbilityItem;
import itemFramework.ItemLibrary;

public class Sword extends AbilityItem {
	private static int ITEM_ID						= ItemLibrary.ItemID.SWORD;
	private static int ICON_ID						= ItemLibrary.IconID.SWORD;
	private static int ABILITY_ID 					= AbilityLibrary.AbilityID.MELEE_SWORD;
	private static final String ITEM_NAME 			= "Shortsword";
	private static final String ITEM_DESCRIPTION 	= "An ordinary shortsword.";
	
	public Sword() {
		super(ITEM_ID, ICON_ID, ABILITY_ID);
		setItemName(ITEM_NAME);
		setItemDescription(ITEM_DESCRIPTION);
	}
}
