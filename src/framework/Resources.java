package framework;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Resources {
	public class Fonts {
		public static final int ERROR		 				= 0;
		public static final int STATUS_BAR_TEXT 			= 1;
		public static final int ITEM_DESCRIPTION 			= 2;
		public static final int FRAME_TITLE 				= 3;
		public static final int UI_MESSAGE 					= 4;
	}
	public class Images {
		public static final int ERROR			= 0;
		public static final int HEALTH_BAR		= 1;
		public static final int MOUSE_POINTER	= 2;
		public static final int ITEMSLOT 		= 3;
	}
	public class SpriteSheets {
		public static final int ERROR 				= 0;
		public static final int PLAYER 				= 1;
		public static final int TREE_A  			= 2;
		public static final int TREE_B 				= 3;
		public static final int CAMPFIRE  			= 4;
		public static final int FIREBALL			= 5;
		public static final int EXPLOSION 			= 6;
		public static final int UI_HEALTH_BAR 		= 7;
		public static final int ENEMY 				= 8;
		public static final int CASTBAR 			= 9;
		public static final int SPELL_FIRE 			= 10;
		public static final int UI_CAST_BAR 		= 11;
		public static final int IMPACT_FIRE 		= 12;
		public static final int UI_POWER_BAR 		= 13;
		public static final int UI_CAST_BAR_CYCLIC 	= 14;
		public static final int TILES				= 15;
		public static final int ITEMS				= 16;
		public static final int UI_INVENTORY		= 17;
	}
	
	private static Font[] fonts;
	private static BufferedImage[] images;
	private static SpriteSheet[] spriteSheets;
	
	public static void loadResources() {
		loadFonts();
		loadImages();
		loadSpriteSheets();
	}
	private static void loadFonts() {
		try {
			fonts = new Font[5];
			fonts[Fonts.STATUS_BAR_TEXT] 	= getFont("assets/fonts/Minecraftia-Regular.ttf", 16);
			fonts[Fonts.ITEM_DESCRIPTION] 	= getFont("assets/fonts/Minecraftia-Regular.ttf", 12);
			fonts[Fonts.FRAME_TITLE] 		= getFont("assets/fonts/Minecraftia-Regular.ttf", 16);
			fonts[Fonts.UI_MESSAGE] 		= getFont("assets/fonts/Minecraftia-Regular.ttf", 16);
		}
		catch (IOException|FontFormatException e) {
		    	e.printStackTrace();
		}
	}
	private static Font getFont(String path, int size) throws FontFormatException, IOException {
		return Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(path))).deriveFont(Font.TRUETYPE_FONT, size);
	}
	private static void loadImages() {
		try {
			images = new BufferedImage[4];
			images[Images.MOUSE_POINTER] 	= getImage("assets/error.png");
			images[Images.MOUSE_POINTER] 	= getImage("assets/mousePointer.png");
			images[Images.HEALTH_BAR] 		= getImage("assets/health_bar.png");
			images[Images.ITEMSLOT] 		= getImage("assets/itemslot.png");
		}
		catch (IOException e) {
		    	e.printStackTrace();
		}
	}
	private static BufferedImage getImage(String path) throws IOException {
		return ImageIO.read(new File(path));
	}
	private static void loadSpriteSheets() {
		spriteSheets = new SpriteSheet[18];
		spriteSheets[SpriteSheets.ERROR] 				= new SpriteSheet("assets/error.png", 32);
		spriteSheets[SpriteSheets.TILES] 				= new SpriteSheet("assets/tiles.bmp", 32);
		spriteSheets[SpriteSheets.PLAYER] 				= new SpriteSheet("assets/player.png", 64);
		spriteSheets[SpriteSheets.TREE_A] 				= new SpriteSheet("assets/tree_v3.png", 128);
		spriteSheets[SpriteSheets.TREE_B] 				= new SpriteSheet("assets/tree_v4.png", 64);
		spriteSheets[SpriteSheets.CAMPFIRE] 			= new SpriteSheet("assets/campfire_medium.png", 48);
		spriteSheets[SpriteSheets.FIREBALL] 			= new SpriteSheet("assets/spell_fireball.png", 32);
		spriteSheets[SpriteSheets.EXPLOSION] 			= new SpriteSheet("assets/effect_explosion.png", 64);
		spriteSheets[SpriteSheets.UI_HEALTH_BAR] 		= new SpriteSheet("assets/ui_health_bar.png", 32);
		spriteSheets[SpriteSheets.UI_CAST_BAR] 			= new SpriteSheet("assets/ui_cast_bar.png", 32);
		spriteSheets[SpriteSheets.CASTBAR	] 			= new SpriteSheet("assets/cast_bar.png", 16);
		spriteSheets[SpriteSheets.ENEMY] 				= new SpriteSheet("assets/enemy.png", 64);
		spriteSheets[SpriteSheets.SPELL_FIRE] 			= new SpriteSheet("assets/effect_spell_fire.png", 64);
		spriteSheets[SpriteSheets.IMPACT_FIRE] 			= new SpriteSheet("assets/effect_impact_fire.png", 64);
		spriteSheets[SpriteSheets.UI_POWER_BAR] 		= new SpriteSheet("assets/ui_power_bar.png", 32);
		spriteSheets[SpriteSheets.UI_CAST_BAR_CYCLIC] 	= new SpriteSheet("assets/ui_cast_bar_cyclic.png", 32);
		spriteSheets[SpriteSheets.ITEMS] 				= new SpriteSheet("assets/items.png", 32);
		spriteSheets[SpriteSheets.UI_INVENTORY] 		= new SpriteSheet("assets/ui_inventory.png", 32);
	}
	
	public static Font getFont(int fontID) {
		return fonts[fontID];
	}
	public static BufferedImage getImage(int imageID) {
		return images[imageID];
	}
	public static SpriteSheet getSpriteSheet(int spriteSheetID) {
		return spriteSheets[spriteSheetID];
	}
	
	public static BufferedImage readImage(String path) {
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}