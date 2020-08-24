package abilityFramework;

import framework.SpriteSheet;
import framework.DrawUtility;
import object.Actor;

public class EffectDamageTick extends Effect {
	private Damage damage;
	private double tickTime;
	private double lifeTime, currentLifeTime;
	private double currentTime = 0;
	private Actor actor;
	private SpriteSheet effectSprites;
	private double frameRate;
	private double frame = 0;
	
	public EffectDamageTick(int ID, Damage damage, double tickTime, double lifeTime, SpriteSheet effectSprites, double frameRate) {
		this(ID, damage, tickTime, lifeTime, effectSprites, frameRate, null);
	}
	public EffectDamageTick(int ID, Damage damage, double tickTime, double lifeTime, SpriteSheet effectSprites, double frameRate, Actor actor) {
		super(ID);
		this.damage = damage;
		this.tickTime = tickTime;
		this.lifeTime = lifeTime;
		this.effectSprites = effectSprites;
		this.frameRate = frameRate;
		this.actor = actor;
		currentLifeTime = lifeTime;
	}
	@Override
	public void applyToActor(Actor a) {
		actor = a;
		a.addEffect(this);
	}
	@Override
	public void onCast(Actor a) {
		damage.setDamage(damage.getDamage() + a.getData().getStats().getDamage());
	}
	@Override
	public void update(double dT) {
		if ((currentLifeTime -= dT) >= 0) {
			if ((currentTime += dT) >= tickTime) {
				currentTime %= tickTime;
				actor.applyDamage(damage);
			}
		}
		else
			actor.removeEffect(this);
		
		frame = (frame + dT * frameRate) % effectSprites.getNumberOfSprites();
	}
	@Override
	public void draw() {
		DrawUtility.drawImageCenter(effectSprites.getSprite((int)frame), actor.getX(), actor.getY());
	}
	@Override
	public void refresh() {
		currentLifeTime = lifeTime;
	}
	@Override
	public Effect getCopy() {
		return new EffectDamageTick(getID(), damage, tickTime, lifeTime, effectSprites, frameRate, actor);
	}
}