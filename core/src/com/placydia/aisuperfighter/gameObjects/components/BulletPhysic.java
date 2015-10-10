package com.placydia.aisuperfighter.gameObjects.components;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.placydia.aisuperfighter.screens.GameScreen;

public class BulletPhysic extends Physic{
	public Transform transform;
	public float width, height, density;
	public BulletPhysic(Transform transform, float width, float height, float density){
		this.transform =transform;
		this.width= width;
		this.height = height;
		this.density = density;
	}
	@Override
	public void init() {
		setBodyDef();
		body = GameScreen.physicsWorld.world.createBody(bodyDef);        
        setShape(width, height);
        body.createFixture(shape, density);
		String str = new String("cacca");
		body.setUserData(str);
	}
	
	@Override
	public void dispose() {		
	}
	@Override
	public void update(float delta) {
		
	}
	public void setBodyDef(){
		bodyDef.position.set(getOwner().get(Transform.class).pos.x, getOwner().get(Transform.class).pos.y);
		bodyDef.angle = getOwner().get(Transform.class).rot;
		bodyDef.type = BodyDef.BodyType.DynamicBody;
	}
	

	
	public void setShape(float width, float height){
		shape.setAsBox(width, height);	
	}
}

