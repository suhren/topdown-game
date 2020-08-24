package abilityFramework;

import java.util.ArrayList;
import java.util.List;

import object.Actor;
import world.World;

public class AbilityProjectilePayload extends AbilityProjectile {
	private List<AbilityBrush> payloadAttacks;
	
	public AbilityProjectilePayload(Actor source, World world) {
		super(source, world);
		payloadAttacks = new ArrayList<AbilityBrush>();
	}
	
	public void addPayloadAttack(AbilityBrush attack) {
		payloadAttacks.add(attack);
	}
	@Override
	public void onEntityDispose() {
		super.onEntityDispose();
		for (AbilityBrush a : payloadAttacks) {
			a.onCast();
			a.getBrush().setPosition(getBrush().getPosition());
		}
		payloadAttacks.clear();
	}
	@Override
	protected void onCast() {
		super.onCast();
	}
}