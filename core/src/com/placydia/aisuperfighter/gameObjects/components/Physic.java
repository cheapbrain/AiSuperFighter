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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

}
