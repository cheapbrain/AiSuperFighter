package com.placydia.aisuperfighter.gameObjects.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.placydia.aisuperfighter.gameObjects.GameObject;
import com.placydia.aisuperfighter.gameObjects.Module;
import com.placydia.aisuperfighter.gameObjects.components.Physic;
import com.placydia.aisuperfighter.screens.GameScreen;

public class PhysicWall extends Physic{
	public float width, height, density, x, y;
	public Module owner;
	public PhysicWall(float x, float y, float width, float height, float density){
		this.width= width;
		this.height = height;
		this.density = density;
		this.x = x;
		this.y = y;
	}
	@Override
	public void init() {
		setBodyDef();
		body = GameScreen.physicsWorld.world.createBody(bodyDef);        
        setShape(width, height);
        body.createFixture(shape, density);
	}
	
	@Override
	public void dispose() {		
	}
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
	public void setBodyDef(){
		bodyDef.position.set(new Vector2(x, y));
		bodyDef.angle = 0;
		bodyDef.type = BodyDef.BodyType.StaticBody;
	}
	
	public void setShape(float width, float height){
		shape.setAsBox(width, height);	
	}
}
