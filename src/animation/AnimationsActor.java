package animation;
import java.io.File;
import java.util.Scanner;

import framework.SpriteSheet;
import framework.Vector2;

public abstract class AnimationsActor extends AnimationSet {
	public static final int ID_MOVE_RIGHT 		= 0;
	public static final int ID_MOVE_UP 			= 1;
	public static final int ID_MOVE_LEFT 		= 2;
	public static final int ID_MOVE_DOWN 		= 3;
	public static final int ID_IDLE_RIGHT 		= 4;
	public static final int ID_IDLE_UP 			= 5;
	public static final int ID_IDLE_LEFT 		= 6;
	public static final int ID_IDLE_DOWN 		= 7;
	public static final int ID_ATTACK_RIGHT 	= 8;
	public static final int ID_ATTACK_UP 		= 9;
	public static final int ID_ATTACK_LEFT 		= 10;
	public static final int ID_ATTACK_DOWN 		= 11;
	public static final int ID_CAST_RIGHT 		= 12;
	public static final int ID_CAST_UP 			= 13;
	public static final int ID_CAST_LEFT 		= 14;
	public static final int ID_CAST_DOWN 		= 15;
	public static final int ID_CAST_END_RIGHT 	= 16;
	public static final int ID_CAST_END_UP 		= 17;
	public static final int ID_CAST_END_LEFT 	= 18;
	public static final int ID_CAST_END_DOWN 	= 19;
	public static final int ID_ATTACK_END_RIGHT = 20;
	public static final int ID_ATTACK_END_UP 	= 21;
	public static final int ID_ATTACK_END_LEFT 	= 22;
	public static final int ID_ATTACK_END_DOWN 	= 23;
	
	private String spriteSheetPath;
	private int spriteWidth, spriteHeight;
	private double frameRate, frameRateAttack;
	private int[] framesMoveRight, framesMoveUp, framesMoveLeft, framesMoveDown;
	private int[] framesIdleRight, framesIdleUp, framesIdleLeft, framesIdleDown;
	private int[] framesAttackRight, framesAttackUp, framesAttackLeft, framesAttackDown;
	private int[] framesAttackEndRight, framesAttackEndUp, framesAttackEndLeft, framesAttackEndDown;
	private int[] framesCastRight, framesCastUp, framesCastLeft, framesCastDown;
	private int[] framesCastEndRight, framesCastEndUp, framesCastEndLeft, framesCastEndDown;
	
	public AnimationsActor(String animationPath) {
		addAnimation(new Animation(ID_MOVE_RIGHT));
		addAnimation(new Animation(ID_MOVE_UP));
		addAnimation(new Animation(ID_MOVE_LEFT));
		addAnimation(new Animation(ID_MOVE_DOWN));
		addAnimation(new Animation(ID_IDLE_RIGHT));
		addAnimation(new Animation(ID_IDLE_UP));
		addAnimation(new Animation(ID_IDLE_LEFT));
		addAnimation(new Animation(ID_IDLE_DOWN));
		addAnimation(new Animation(ID_ATTACK_RIGHT));
		addAnimation(new Animation(ID_ATTACK_UP));
		addAnimation(new Animation(ID_ATTACK_LEFT));
		addAnimation(new Animation(ID_ATTACK_DOWN));
		addAnimation(new Animation(ID_ATTACK_END_RIGHT));
		addAnimation(new Animation(ID_ATTACK_END_UP));
		addAnimation(new Animation(ID_ATTACK_END_LEFT));
		addAnimation(new Animation(ID_ATTACK_END_DOWN));
		addAnimation(new Animation(ID_CAST_RIGHT));
		addAnimation(new Animation(ID_CAST_UP));
		addAnimation(new Animation(ID_CAST_LEFT));
		addAnimation(new Animation(ID_CAST_DOWN));
		addAnimation(new Animation(ID_CAST_END_RIGHT));
		addAnimation(new Animation(ID_CAST_END_UP));
		addAnimation(new Animation(ID_CAST_END_LEFT));
		addAnimation(new Animation(ID_CAST_END_DOWN));
		
		try {
			Scanner sc = new Scanner(new File(animationPath));
			sc.useDelimiter("(\\s*)=(\\s*)|(\\r?\\n)");
			
			while (sc.hasNext()) {
				switch (sc.next()) {
					case "SPRITE_SHEET": 			spriteSheetPath 	= readString(sc.next()); break;
				
					case "SPRITE_WIDTH": 			spriteWidth 		= readInteger(sc.next()); break;
					case "SPRITE_HEIGHT": 			spriteHeight 		= readInteger(sc.next()); break;
				
					case "FRAME_RATE": 				frameRate 			= readDouble(sc.next()); break;
					case "FRAME_RATE_ATTACK": 		frameRateAttack 	= readDouble(sc.next()); break;
					
					case "FRAMES_MOVE_RIGHT":		framesMoveRight 	= readIntArray(sc.next()); break;
					case "FRAMES_MOVE_UP": 			framesMoveUp 		= readIntArray(sc.next()); break;
					case "FRAMES_MOVE_LEFT": 		framesMoveLeft 		= readIntArray(sc.next()); break;
					case "FRAMES_MOVE_DOWN": 		framesMoveDown 		= readIntArray(sc.next()); break;
					
					case "FRAMES_IDLE_RIGHT": 		framesIdleRight 	= readIntArray(sc.next()); break;
					case "FRAMES_IDLE_UP": 			framesIdleUp 		= readIntArray(sc.next()); break;
					case "FRAMES_IDLE_LEFT": 		framesIdleLeft 		= readIntArray(sc.next()); break;
					case "FRAMES_IDLE_DOWN": 		framesIdleDown 		= readIntArray(sc.next()); break;
					
					case "FRAMES_ATTACK_RIGHT": 	framesAttackRight 	= readIntArray(sc.next()); break;
					case "FRAMES_ATTACK_UP": 		framesAttackUp 		= readIntArray(sc.next()); break;
					case "FRAMES_ATTACK_LEFT": 		framesAttackLeft 	= readIntArray(sc.next()); break;
					case "FRAMES_ATTACK_DOWN": 		framesAttackDown 	= readIntArray(sc.next()); break;
					
					case "FRAMES_ATTACK_END_RIGHT": framesAttackEndRight = readIntArray(sc.next()); break;
					case "FRAMES_ATTACK_END_UP": 	framesAttackEndUp 	= readIntArray(sc.next()); break;
					case "FRAMES_ATTACK_END_LEFT": 	framesAttackEndLeft = readIntArray(sc.next()); break;
					case "FRAMES_ATTACK_END_DOWN": 	framesAttackEndDown = readIntArray(sc.next()); break;
					
					case "FRAMES_CAST_RIGHT": 		framesCastRight 	= readIntArray(sc.next()); break;
					case "FRAMES_CAST_UP": 			framesCastUp 		= readIntArray(sc.next()); break;
					case "FRAMES_CAST_LEFT": 		framesCastLeft 		= readIntArray(sc.next()); break;
					case "FRAMES_CAST_DOWN": 		framesCastDown 		= readIntArray(sc.next()); break;

					case "FRAMES_CAST_END_RIGHT": 	framesCastEndRight 	= readIntArray(sc.next()); break;
					case "FRAMES_CAST_END_UP": 		framesCastEndUp 	= readIntArray(sc.next()); break;
					case "FRAMES_CAST_END_LEFT": 	framesCastEndLeft 	= readIntArray(sc.next()); break;
					case "FRAMES_CAST_END_DOWN": 	framesCastEndDown 	= readIntArray(sc.next()); break;
				}
			}
			sc.close();
	    }
		catch (Exception ex) {
	        ex.printStackTrace();
	    }
		
		setSpriteSheet(new SpriteSheet("assets/" + spriteSheetPath, spriteWidth, spriteHeight));
		
		getAnimation(ID_MOVE_RIGHT).setAnimation(framesMoveRight, frameRate, false);
		getAnimation(ID_MOVE_UP).setAnimation(framesMoveUp, frameRate, false);
		getAnimation(ID_MOVE_LEFT).setAnimation(framesMoveLeft, frameRate, false);
		getAnimation(ID_MOVE_DOWN).setAnimation(framesMoveDown, frameRate, false);
		
		getAnimation(ID_IDLE_RIGHT).setAnimation(framesIdleRight, frameRate, false);
		getAnimation(ID_IDLE_UP).setAnimation(framesIdleUp, frameRate, false);
		getAnimation(ID_IDLE_LEFT).setAnimation(framesIdleLeft, frameRate, false);
		getAnimation(ID_IDLE_DOWN).setAnimation(framesIdleDown, frameRate, false);
		
		getAnimation(ID_ATTACK_RIGHT).setAnimation(framesAttackRight, frameRateAttack, true);
		getAnimation(ID_ATTACK_UP).setAnimation(framesAttackUp, frameRateAttack, true);
		getAnimation(ID_ATTACK_LEFT).setAnimation(framesAttackLeft, frameRateAttack, true);
		getAnimation(ID_ATTACK_DOWN).setAnimation(framesAttackDown, frameRateAttack, true);
		
		getAnimation(ID_ATTACK_END_RIGHT).setAnimation(framesAttackEndRight, frameRateAttack, true);
		getAnimation(ID_ATTACK_END_UP).setAnimation(framesAttackEndUp, frameRateAttack, true);
		getAnimation(ID_ATTACK_END_LEFT).setAnimation(framesAttackEndLeft, frameRateAttack, true);
		getAnimation(ID_ATTACK_END_DOWN).setAnimation(framesAttackEndDown, frameRateAttack, true);
		
		getAnimation(ID_CAST_RIGHT).setAnimation(framesCastRight, frameRateAttack, true);
		getAnimation(ID_CAST_UP).setAnimation(framesCastUp, frameRateAttack, true);
		getAnimation(ID_CAST_LEFT).setAnimation(framesCastLeft, frameRateAttack, true);
		getAnimation(ID_CAST_DOWN).setAnimation(framesCastDown, frameRateAttack, true);
		
		getAnimation(ID_CAST_END_RIGHT).setAnimation(framesCastEndRight, frameRateAttack, true);
		getAnimation(ID_CAST_END_UP).setAnimation(framesCastEndUp, frameRateAttack, true);
		getAnimation(ID_CAST_END_LEFT).setAnimation(framesCastEndLeft, frameRateAttack, true);
		getAnimation(ID_CAST_END_DOWN).setAnimation(framesCastEndDown, frameRateAttack, true);
		
		setCurrentAnimation(ID_IDLE_DOWN);
	}

	public void setAnimationMove(int direction) {
		if (direction == Vector2.Direction.RIGHT)
			setAnimationMoveRight();
		else if (direction == Vector2.Direction.UP)
			setAnimationMoveUp();
		else if (direction == Vector2.Direction.LEFT)
			setAnimationMoveLeft();
		else if (direction == Vector2.Direction.DOWN)
			setAnimationMoveDown();
	}
	public void setAnimationMoveRight() { setCurrentAnimation(ID_MOVE_RIGHT); }
	public void setAnimationMoveUp() { setCurrentAnimation(ID_MOVE_UP); }
	public void setAnimationMoveLeft() { setCurrentAnimation(ID_MOVE_LEFT); }
	public void setAnimationMoveDown() { setCurrentAnimation(ID_MOVE_DOWN); }
	
	public void setAnimationIdle(int direction) {
		if (direction == Vector2.Direction.RIGHT)
			setAnimationIdleRight();
		else if (direction == Vector2.Direction.UP)
			setAnimationIdleUp();
		else if (direction == Vector2.Direction.LEFT)
			setAnimationIdleLeft();
		else if (direction == Vector2.Direction.DOWN)
			setAnimationIdleDown();
	}
	public void setAnimationIdleRight() { setCurrentAnimation(ID_IDLE_RIGHT); }
	public void setAnimationIdleUp() { setCurrentAnimation(ID_IDLE_UP); }
	public void setAnimationIdleLeft() { setCurrentAnimation(ID_IDLE_LEFT); }
	public void setAnimationIdleDown() { setCurrentAnimation(ID_IDLE_DOWN); }
	
	public void setAnimationAttack(int direction) {
		if (direction == Vector2.Direction.RIGHT)
			setAnimationAttackRight();
		else if (direction == Vector2.Direction.UP)
			setAnimationAttackUp();
		else if (direction == Vector2.Direction.LEFT)
			setAnimationAttackLeft();
		else if (direction == Vector2.Direction.DOWN)
			setAnimationAttackDown();
	}
	public void setAnimationAttackRight() { setCurrentAnimation(ID_ATTACK_RIGHT); }
	public void setAnimationAttackUp() { setCurrentAnimation(ID_ATTACK_UP); }
	public void setAnimationAttackLeft() { setCurrentAnimation(ID_ATTACK_LEFT); }
	public void setAnimationAttackDown() { setCurrentAnimation(ID_ATTACK_DOWN); }
	
	public void setAnimationAttackEnd(int direction) {
		if (direction == Vector2.Direction.RIGHT)
			setAnimationAttackEndRight();
		else if (direction == Vector2.Direction.UP)
			setAnimationAttackEndUp();
		else if (direction == Vector2.Direction.LEFT)
			setAnimationAttackEndLeft();
		else if (direction == Vector2.Direction.DOWN)
			setAnimationAttackEndDown();
	}
	public void setAnimationAttackEndRight() { setCurrentAnimation(ID_ATTACK_END_RIGHT); }
	public void setAnimationAttackEndUp() { setCurrentAnimation(ID_ATTACK_END_UP); }
	public void setAnimationAttackEndLeft() { setCurrentAnimation(ID_ATTACK_END_LEFT); }
	public void setAnimationAttackEndDown() { setCurrentAnimation(ID_ATTACK_END_DOWN); }
	
	public void setAnimationCast(int direction) {
		if (direction == Vector2.Direction.RIGHT)
			setAnimationCastRight();
		else if (direction == Vector2.Direction.UP)
			setAnimationCastUp();
		else if (direction == Vector2.Direction.LEFT)
			setAnimationCastLeft();
		else if (direction == Vector2.Direction.DOWN)
			setAnimationCastDown();
	}
	public void setAnimationCastRight() { setCurrentAnimation(ID_CAST_RIGHT); }
	public void setAnimationCastUp() { setCurrentAnimation(ID_CAST_UP); }
	public void setAnimationCastLeft() { setCurrentAnimation(ID_CAST_LEFT); }
	public void setAnimationCastDown() { setCurrentAnimation(ID_CAST_DOWN); }
	
	public void setAnimationCastEnd(int direction) {
		if (direction == Vector2.Direction.RIGHT)
			setAnimationCastEndRight();
		else if (direction == Vector2.Direction.UP)
			setAnimationCastEndUp();
		else if (direction == Vector2.Direction.LEFT)
			setAnimationCastEndLeft();
		else if (direction == Vector2.Direction.DOWN)
			setAnimationCastEndDown();
	}
	public void setAnimationCastEndRight() { setCurrentAnimation(ID_CAST_END_RIGHT); }
	public void setAnimationCastEndUp() { setCurrentAnimation(ID_CAST_END_UP); }
	public void setAnimationCastEndLeft() { setCurrentAnimation(ID_CAST_END_LEFT); }
	public void setAnimationCastEndDown() { setCurrentAnimation(ID_CAST_END_DOWN); }
}