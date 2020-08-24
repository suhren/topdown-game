package npc;

import java.util.Random;
import framework.Vector2;
import object.Actor;
import world.Tile;

public class AIv1 extends AI {
	private Random rand = new Random();
	private double time = 0.0;
	private double minTime = 1.0;
	private double maxTime = 2.0;
	private double targetTime = minTime;
	private int direction;
	
	public AIv1(Actor actor) {
		super(actor);
		changeDirection();
	}

	@Override
	protected void update(double dT) {
		time += dT;
		
		if (time >= targetTime)
			changeDirection();
		
		getActor().moveDirection(direction, dT);
	}
	private void changeDirection() {
		setDirection(1 + rand.nextInt(4));
	}
	public void collideWithTile(Tile tile, int direction) {
		setDirection(Vector2.GetOppositeDirection(direction));
	}
	public void setDirection(int direction) {
		targetTime = minTime + (maxTime - minTime) * rand.nextDouble();
		time = 0.0;
		this.direction = direction;
	}
}