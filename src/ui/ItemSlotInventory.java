package ui;

import framework.DrawUtility;
import itemFramework.Item;
import itemFramework.Item.Type;
import itemFramework.ItemLibrary;
import itemFramework.ActorItems.NoItemException;

public class ItemSlotInventory extends ItemSlot {

	public ItemSlotInventory(Frame frame, int posX, int posY, int slotID) {
		super(frame, posX, posY, slotID, Type.INVENTORY);
	}
	
	@Override
	public void draw(int x, int y) {
		super.draw(x, y);
		
		if (drawItem()) 
			try {
				DrawUtility.drawImageScreen(ItemLibrary.getIcon(getActor().getData().getActorItems().getItemInventory(getSlotID()).getIconID()), getX() + x, getY() + y);
			} catch (NoItemException e) { }
	}
	
	@Override
	public Item getItem() {
		try {
			return getActor().getData().getActorItems().getItemInventory(getSlotID());
		} catch (NoItemException e) { 
			return null;
		}
	}
}
