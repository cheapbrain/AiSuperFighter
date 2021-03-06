package com.placydia.aisuperfighter.gameObjects.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.placydia.aisuperfighter.gameObjects.Component;
import com.placydia.aisuperfighter.gameObjects.Module;
import com.placydia.aisuperfighter.gameObjects.Ship;
import com.placydia.aisuperfighter.screens.GameScreen;

public class ModulePhysic extends Physic{
	public Transform transform;
	public float width, height, density;
	public Module owner;
	public ModulePhysic(Transform transform, float width, float height, float density, Module owner){
		this.transform =transform;
		this.width= width;
		this.height = height;
		this.density = density;
		this.owner = owner;
	}
	@Override
	public void init() {
		setBodyDef();
		body = GameScreen.physicsWorld.world.createBody(bodyDef);        
        setShape(width, height);
        Fixture f = body.createFixture(shape, density);
	}
	
	@Override
	public void dispose() {		
	}
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
	public void setBodyDef(){
		bodyDef.position.set(new Vector2(owner.ship.get(Transform.class).pos.x+getOwner().get(Transform.class).pos.x,
				owner.ship.get(Transform.class).pos.y+getOwner().get(Transform.class).pos.y));
		bodyDef.angle = owner.ship.get(Transform.class).rot+getOwner().get(Transform.class).rot;
		bodyDef.type = BodyDef.BodyType.DynamicBody;
	}
	
	public void setShape(float width, float height){
		shape.setAsBox(width, height);	
	}
}
