package com.placydia.aisuperfighter.gameObjects;

public abstract class Component {
	private GameObject owner;
	private boolean initialized = false;
	
	public boolean isInitialized() {
		return initialized;
	}

	public void init0() {
		initialized = true;
		init();
	}
	
	public abstract void init();
	
	public abstract void dispose();
	
	public abstract void update(float delta);
	
	public final void setOwner(GameObject owner) {
		this.owner = owner;
	}
	
	public final GameObject getOwner() {
		return owner;
	}
}
