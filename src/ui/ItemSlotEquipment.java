package ui;

import framework.DrawUtility;
import itemFramework.Item;
import itemFramework.Item.Type;
import itemFramework.ItemLibrary;
import itemFramework.ActorItems.NoItemEquippedException;

public class ItemSlotEquipment extends ItemSlot {

	public ItemSlotEquipment(Frame frame, int posX, int posY, int slotID) {
		super(frame, posX, posY, slotID, Type.EQUIPMENT);
	}
	
	@Override
	public void draw(int x, int y) {
		super.draw(x, y);
		
		if (drawItem())
			try {
				DrawUtility.drawImageScreen(ItemLibrary.getIcon(getActor().getData().getActorItems().getEquippedItem(getSlotID()).getIconID()), getX() + x, getY() + y);
			} catch (NoItemEquippedException e) { }
	}
	
	@Override
	public Item getItem() {
		try {
			return getActor().getData().getActorItems().getEquippedItem(getSlotID());
		} catch (NoItemEquippedException e) {
			return null;
		}
	}
}
