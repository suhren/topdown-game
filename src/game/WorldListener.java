package game;

import object.Player;
import world.World;

public interface WorldListener {
	public void playerDeath(Player player, World world);
}