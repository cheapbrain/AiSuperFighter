package com.placydia.aisuperfighter.gameWorld;

import com.placydia.aisuperfighter.gameObjects.GameObject;
import com.placydia.aisuperfighter.gameObjects.GameObjectManager;

public class GameWorld {
	public GameObjectManager objectManager;
	private float width, height;

	public GameWorld(float width, float height) {
		objectManager = new GameObjectManager();
		this.width = width;
		this.height = height;
	}
	
	public void update(float delta) {
		objectManager.update(delta);
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public void add(GameObject object) {
		objectManager.add(object);
	}
	
}
