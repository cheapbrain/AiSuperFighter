package com.placydia.aisuperfighter.gameObjects.components;

import com.badlogic.gdx.physics.box2d.Body;
import com.placydia.aisuperfighter.gameObjects.Component;
import com.placydia.aisuperfighter.gameObjects.Module;
import com.placydia.aisuperfighter.gameObjects.Ship;
import com.placydia.aisuperfighter.screens.GameScreen;

public class Physic extends Component{
	public Body body;
	public Transform transform;
	public float width, height, density;
	public Physic(Transform transform, float width, float height, float density){
		this.transform =transform;
		this.width= width;
		this.height = height;
		this.density = density;
	}
	@Override
	public void init() {
		((Module)getOwner()).setBodyDef();
		body = GameScreen.physicsWorld.world.createBody(((Module)getOwner()).bodyDef);        
        ((Module)getOwner()).setShape(width, height);
        body.createFixture(((Module)getOwner()).shape, density);
	}
	
	@Override
	public void dispose() {		
	}
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
}
