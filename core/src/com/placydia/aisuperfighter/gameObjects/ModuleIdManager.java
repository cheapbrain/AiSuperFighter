package com.placydia.aisuperfighter.gameObjects;

public class ModuleIdManager {
	private static int id = 0;
	public static int nextId(){
		id++;
		return id-1;
	}
}
