package object;

import java.util.Comparator;

import world.World;

public abstract class Entity {
	private World world;
	
	public Entity() {
		
	}
	public Entity(World world) {
		this.world = world;
	}
	
	public void dispose() {
		world.disposeEntity(this);
		onEntityDispose();
	}
	public int compareTo(Object o) {
        return ((Entity)o).getDrawY() - getDrawY();
    }
    public static Comparator<Entity> COMPARATOR = (Entity a, Entity b) -> {
	    return b.compareTo(a);
	};
	public World getWorld() { return world; }
	public void setWorld(World world) {
		this.world = world;
	}
	public abstract void update(double dT);
	protected abstract void onEntityDispose();
	public abstract void onEntityCreation();
	public abstract int getDrawY();
	public abstract void draw();
}