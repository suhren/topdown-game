package object;

public class Faction {
	public static final int FACTION_NEUTRAL = 0;
	public static final int FACTION_GOOD 	= 1;
	public static final int FACTION_EVIL 	= 2;
	
	public static final int DEFAULT_FACTION 				= FACTION_GOOD;
	public static final int[] DEFAULT_FACTIONS_FRIENDLY		= { FACTION_GOOD };
	public static final int[] DEFAULT_FACTIONS_ENEMY		= { FACTION_EVIL };
	public static final int[] FACTIONS_ALL 					= { FACTION_NEUTRAL, FACTION_GOOD, FACTION_EVIL };
}