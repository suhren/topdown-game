package object;

import itemFramework.ActorItems;

public class ActorData {
	private ActorStats stats = new ActorStats();
	private ActorItems items = new ActorItems();
	
	public ActorStats getStats() {
		return stats;
	}
	public void setActorStats(ActorStats stats) {
		this.stats = stats;
	}
	public ActorItems getActorItems() {
		return items;
	}
	public void setDamage(ActorItems items) {
		this.items = items;
	}
}