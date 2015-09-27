package com.placydia.aisuperfighter.gameObjects;

import com.badlogic.gdx.utils.Array;

public class GameObjectManager {
	private Array<GameObject> objects;
	
	public GameObjectManager() {
		objects = new Array<GameObject>();
		
	}
	
	public void add(GameObject object) {
		objects.add(object);
	}
	
	public void update(float delta) {
		for (int i=0;i<objects.size;i++) {
			GameObject object = objects.get(i);
			object.superUpdate(delta);
		}
	}
	
	
	
	
}
