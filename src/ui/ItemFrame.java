package ui;

import java.util.ArrayList;
import java.util.List;

public class ItemFrame extends Frame {
	private List<ItemSlot> itemSlots = new ArrayList<ItemSlot>();
	
	public ItemFrame(GameUI gameUI, String title, int posX, int posY) {
		super(gameUI, title, posX, posY);
		// TODO Auto-generated constructor stub
	}
	public void addElement(ItemSlot itemSlot) {
		super.addElement(itemSlot);
		itemSlots.add(itemSlot);
	}
	@Override()
	protected void clearElements() {
		super.clearElements();
		itemSlots.clear();
	}

	@Override
	public void onShowUI() {
	}
	
	public List<ItemSlot> getItemSlots() {
		return itemSlots;
	}
}