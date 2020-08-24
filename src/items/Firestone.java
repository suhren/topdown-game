package items;

import abilityFramework.AbilityLibrary;
import itemFramework.AbilityItem;
import itemFramework.ItemLibrary;

public class Firestone extends AbilityItem {
	private static int ITEM_ID						= ItemLibrary.ItemID.FIRESTONE;
	private static int ICON_ID						= ItemLibrary.IconID.FIRESTONE;
	private static int ABILITY_ID 					= AbilityLibrary.AbilityID.FIREBALL;
	private static final String ITEM_NAME 			= "Firestone";
	private static final String ITEM_DESCRIPTION 	= "A faintly glowing orb, almost too hot to hold in your hand.";
	
	public Firestone() {
		super(ITEM_ID, ICON_ID, ABILITY_ID);
		setItemName(ITEM_NAME);
		setItemDescription(ITEM_DESCRIPTION);
	}
}