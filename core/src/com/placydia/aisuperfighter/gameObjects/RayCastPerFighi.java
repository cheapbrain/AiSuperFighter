package com.placydia.aisuperfighter.gameObjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;

public class RayCastPerFighi implements RayCastCallback{

	@Override
	public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
		System.out.println(point.x+"aaa"+point.y);
		return 0;
	}

}
