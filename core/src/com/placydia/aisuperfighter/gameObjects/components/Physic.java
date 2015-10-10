package com.placydia.aisuperfighter.gameObjects.components;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.placydia.aisuperfighter.gameObjects.Component;

public class Physic  extends Component{

	public Body body;
	public BodyDef bodyDef = new BodyDef();
	public PolygonShape shape = new PolygonShape();
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
	}

}
