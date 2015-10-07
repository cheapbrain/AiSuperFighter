package com.placydia.aisuperfighter.gameObjects.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.placydia.aisuperfighter.gameObjects.Component;
import com.placydia.aisuperfighter.screens.GameScreen;

public class BulletPhysic extends Component{
	public Body body;
	public BodyDef bodyDef = new BodyDef();
	public PolygonShape shape = new PolygonShape();
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
	}
	
	@Override
	public void dispose() {		
	}
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
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

