package items;

import abilityFramework.AbilityLibrary;
import itemFramework.AbilityItem;
import itemFramework.ItemLibrary;

public class Icestone extends AbilityItem {
	private static int ITEM_ID						= ItemLibrary.ItemID.ICESTONE;
	private static int ICON_ID						= ItemLibrary.IconID.ICESTONE;
	private static int ABILITY_ID 					= AbilityLibrary.AbilityID.ERROR;
	private static final String ITEM_NAME 			= "Icestone";
	private static final String ITEM_DESCRIPTION 	= "A glimmering orb, freezing to the touch.";
	
	public Icestone() {
		super(ITEM_ID, ICON_ID, ABILITY_ID);
		setItemName(ITEM_NAME);
		setItemDescription(ITEM_DESCRIPTION);
	}
}