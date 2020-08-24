package object;

import world.World;

public class CastArrow extends PointEntity {

	public CastArrow(World world) {
		super(world);
	}

	@Override
	public void update(double dT) {
	}

	@Override
	protected void onEntityDispose() {
	}

	@Override
	public void onEntityCreation() {
	}

	@Override
	public int getDrawY() {
		return (int)getY();
	}

	@Override
	public void draw() {
	}
}