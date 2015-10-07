package com.placydia.aisuperfighter.gameObjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.placydia.aisuperfighter.gameObjects.components.Transform;

public abstract class Module extends GameObject{
	public Ship ship;
	public int id;
	public Module(Ship ship){
		this.ship = ship;
		id = ModuleIdManager.nextId();
	}

}
