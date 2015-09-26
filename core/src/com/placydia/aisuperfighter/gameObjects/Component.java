package com.placydia.aisuperfighter.gameObjects;

public abstract class Component {

	public abstract void init();
	
	public abstract void dispose();
	
	public abstract void update(float delta);
	
	public abstract void setOwner(GameObject owner);
	
	public abstract GameObject getOwner();
}
