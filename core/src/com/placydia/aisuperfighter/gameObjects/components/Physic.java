package com.placydia.aisuperfighter.gameObjects.components;

import com.badlogic.gdx.physics.box2d.Body;
import com.placydia.aisuperfighter.gameObjects.Component;
import com.placydia.aisuperfighter.screens.GameScreen;

public class Physic extends Component{
	public Body body;
	public float width, height;
	public Transform transform;
	public Physic(float width, float height){
		this.width=width;
		this.height=height;
	}
	@Override
	public void init() {
		transform = getOwner().get(Transform.class);
		GameScreen.physicsWorld.add(this);
	}
	
	@Override
	public void dispose() {		
	}

	@Override
	public void update(float delta) {
		transform.pos=body.getLocalCenter();
		transform.rot=body.getAngle();
	}

}
