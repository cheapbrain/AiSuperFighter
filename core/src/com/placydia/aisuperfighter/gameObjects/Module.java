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
	public void setBodyDef(){
		bodyDef.position.set(new Vector2(ship.get(Transform.class).pos.x+get(Transform.class).pos.x,
				 ship.get(Transform.class).pos.y+get(Transform.class).pos.y));
		bodyDef.angle = ship.get(Transform.class).rot+get(Transform.class).rot;
		bodyDef.type = BodyDef.BodyType.DynamicBody;
	}
	public void setShape(float width, float height){
		shape.setAsBox(width, height);	
	}
}
