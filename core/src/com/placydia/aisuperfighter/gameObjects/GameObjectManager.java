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
	
	
	
}
