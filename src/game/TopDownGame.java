package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import framework.DrawUtility;
import framework.Resources;
import object.Actor;
import object.Player;
import ui.GameUI;
import world.World;

public class TopDownGame extends Game implements WorldListener {
	private static final int KEY_TOGGLE_FPS = KeyEvent.VK_ENTER;
	private static final int KEY_TOGGLE_INVENTORY = KeyEvent.VK_I;
	private static final int KEY_TOGGLE_HEALTH_BARS = KeyEvent.VK_V;
	private Camera camera;
	private World world;
	private GameUI ui;
	private Player player;
	
	public TopDownGame() {
		Resources.loadResources();
		
		world = new World(48, 48, this);
		player = new Player(256, 128, world);
		world.addPlayer(player);
		
		camera = new Camera(world, getGraphicsBuffer());
		camera.setTarget(player);
		DrawUtility.setCamera(camera);
		
		player.setCamera(camera);
		player.setInputBuffer(getInput());
		
		ui = new GameUI(getGraphicsBuffer());
		ui.getMousePointer().setInputBuffer(getInput());
		setUIElementReferences(player);
	}
	private void setUIElementReferences(Actor actor) {
		ui.setActor(actor);
	}
	protected void updateGame(double dT) {
		checkInputs();
		world.update(dT);
		camera.update(dT);
		ui.update(dT);
	}
	private void checkInputs() {
		if (getInput().keyPressed(KEY_TOGGLE_FPS))
			toggleFPS();
		if (getInput().keyPressed(KEY_TOGGLE_HEALTH_BARS))
			camera.toggleHealthBars();
		if (getInput().keyPressed(KEY_TOGGLE_INVENTORY))
			ui.toggleInventory();
	}
	protected void drawGameImage(Graphics g, int renderWidth, int renderHeight) {
		world.draw();
		ui.draw();
	}
	@Override
	public void playerDeath(Player player, World world) {
		Player p = new Player(256, 128, world);
		p.setInputBuffer(getInput());
		camera.setTarget(p);
		p.setCamera(camera);
		setUIElementReferences(player);
		world.addPlayer(p);
	}
}