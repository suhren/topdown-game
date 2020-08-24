package itemFramework;

import java.awt.image.BufferedImage;

import framework.Resources;

public class ItemLibrary {
	public class ItemID {
		public static final int ERROR 		= 0;
		public static final int FIRESTONE 	= 1;
		public static final int SWORD 		= 2;
		public static final int ICESTONE 	= 3;
	}
	public class IconID {
		public static final int ERROR 		= 0;
		public static final int FIRESTONE 	= 1;
		public static final int SWORD 		= 2;
		public static final int ICESTONE 	= 3;
	}
	public static BufferedImage getIcon(int iconID) {
		return Resources.getSpriteSheet(Resources.SpriteSheets.ITEMS).getSprite(iconID);
	}
}