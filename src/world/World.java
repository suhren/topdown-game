package world;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import abilityFramework.Ability;
import framework.DrawUtility;
import framework.GameUtility;
import game.Camera;
import game.WorldListener;
import npc.Enemy;
import object.Actor;
import object.AnimatedFoliage;
import object.Campfire;
import object.Entity;
import object.ObjectTile;
import object.Player;
import object.TreeA;
import object.TreeB;

public class World {
	private TileMap tileMap;
	private List<Actor> actors;
	private List<Enemy> enemies;
	private List<Entity> entities;
	private List<Entity> entityQue;
	private List<Entity> disposedEntities;
	private static final int N_ENEMIES = 10;
	private WorldListener listener;
	
	// Initialize world
	public World(int tilesX, int tilesY, WorldListener listener) {
		this.listener = listener;
		
		actors = new ArrayList<Actor>();
		disposedEntities = new ArrayList<Entity>();
		entityQue = new ArrayList<Entity>();
		entities = new ArrayList<Entity>();
		enemies = new ArrayList<Enemy>();
		
		createTileMap(tilesX, tilesY);
		
		createFoliage();
		createEnemies();
	}
	private void createFoliage() {
		createTreeA(20, 16);
		createTreeA(15, 18);
		createTreeA(17, 12);
		createTreeA(25, 12);
		createTreeA(30, 16);
		createTreeA(35, 19);
		createTreeA(30, 24);
		createTreeA(25, 22);
		createTreeB(5, 22);
		createTreeB(23, 18);
		createCampfire(350, 500);
		
	}
	private void createCampfire(int x, int y) {
		Campfire c = new Campfire(x, y, this);
		entities.add(c);
	}
	private void createTreeB(int x, int y) {
		AnimatedFoliage t = new TreeB(x, y, TileMap.TILE_SIZE, this);
		for (ObjectTile ot : t.getObjectTiles())
			tileMap.setObjectValue(x + ot.getX(), y + ot.getY(), ot.getType());
		entities.add(t);
	}
	private void createTreeA(int x, int y) {
		AnimatedFoliage t = new TreeA(x, y, TileMap.TILE_SIZE, this);
		for (ObjectTile ot : t.getObjectTiles())
			tileMap.setObjectValue(x + ot.getX(), y + ot.getY(), ot.getType());
		entities.add(t);
	}
	private void createTileMap(int tilesX, int tilesY) {
		tileMap = new TileMap(tilesX, tilesY);
		
		for (int x = 0; x < tilesX; x++)
			for (int y = 0; y < tilesY; y++) {
				if (Math.random() * 100.0 > 97)
					tileMap.addTile(x, y, 2);
				else
					tileMap.addTile(x, y, 1);
			}
		
		for (int x = 0; x < tilesX; x++) {
			tileMap.addTile(x, 0, 3);
			tileMap.addTile(x, tilesY - 1, 3);
		}
		
		for (int y = 0; y < tilesY; y++) {
			tileMap.addTile(0, y, 3);
			tileMap.addTile(tilesX - 1, y, 3);
		}
		
		tileMap.addTile(10, 10, 3);
		tileMap.addTile(11, 10, 3);
		tileMap.addTile(12, 10, 3);
		tileMap.addTile(12, 9, 3);
		tileMap.addTile(12, 8, 3);
		
		tileMap.drawImage();
	}
	private void createEnemies() {
		while (enemies.size() < N_ENEMIES) {
			Enemy e = new Enemy(800, 500, this);
			entities.add(e);
			enemies.add(e);
			actors.add(e);
		}
	}
	
	// Update world
	public void update(double dT) {
		createEnemies();
		
		for (Entity e : entities)
			e.update(dT);
		
		addEntities();
		disposeEntities();
	}
	private void addEntities() {
		for (Entity e : entityQue) {
			entities.add(e);
			e.onEntityCreation();
		}
		entityQue.clear();
	}
	private void disposeEntities() {
		for (Entity e : disposedEntities) {
			entities.remove(e);
			actors.remove(e);
			enemies.remove(e);
		}
		disposedEntities.clear();
	}
	
	// Draw world
	public void draw() {
		drawTiles();
		drawObjects();
	}
	private void drawObjects() {
		Collections.sort(entities, Entity.COMPARATOR);
		for (Entity e : entities)
			e.draw();
	}
	private void drawTiles() {
		Camera c = DrawUtility.getCamera();
		int tLX = GameUtility.clamp(0, getTileMap().getWidth(), (int)TileMap.getTilePos(c.getWorldX(0)));
		int tLY = GameUtility.clamp(0, getTileMap().getHeight(), (int)TileMap.getTilePos(c.getWorldY(0)));
		int bottomRightX = GameUtility.clamp(0, getTileMap().getWidth(), TileMap.getTilePos(c.getWorldX(c.getRenderWidth())) + 1);
		int bottomRightY = GameUtility.clamp(0, getTileMap().getHeight(), TileMap.getTilePos(c.getWorldY(c.getRenderHeight())) + 1);
		
		DrawUtility.drawImageWorldClippedToScreen(tileMap.getImage(), 0, 0);
		
		for (int x = tLX; x < bottomRightX; x++)
			for (int y = tLY; y < bottomRightY; y++) {
				Tile t = getTileMap().getTile(x, y);
				if (Camera.getDrawTileRectangles())
					DrawUtility.drawRect(TileMap.TILE_SIZE * x, TileMap.TILE_SIZE * y, TileMap.TILE_SIZE, TileMap.TILE_SIZE, Color.BLACK);
				if (Camera.getDrawObjectTiles() && t.getObjectValue() > 0)
					DrawUtility.drawRect(TileMap.TILE_SIZE * x, TileMap.TILE_SIZE * y, TileMap.TILE_SIZE - 1, TileMap.TILE_SIZE - 1, Color.WHITE);
			}
	}
	
	// Handle entities
	public void addAbility(Ability ability) {
		entityQue.add(ability);
	}
	public void addPlayer(Player player) {
		entityQue.add(player);
	}
	public void addEntity(Entity entity) {
		entityQue.add(entity);
	}
	public void actorAdded(Actor actor) {
		actors.add(actor);
	}
	public void playerDisposed(Player player) {
		listener.playerDeath(player, this);
	}
	public void disposeEntity(Entity entity) {
		disposedEntities.add(entity);
	}

	// Getters and setters
	public List<Actor> getActors() {
		return actors;
	}
	public TileMap getTileMap() {
		return tileMap; 
	}
}