package com.placydia.aisuperfighter.physics;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class PhysicsWorld {
	public static World world;
	private float accumulator = 0;
	private Box2DDebugRenderer renderer;
	public PhysicsWorld(){
		world = new World(new Vector2(0, 0), true);
		renderer = new Box2DDebugRenderer();
	}
	public void update(float delta){
		accumulator+=delta;
		if(accumulator>=PhysicsConstants.TIME_STEP){
			world.step(PhysicsConstants.TIME_STEP, 6, 2);
			accumulator-=PhysicsConstants.TIME_STEP;
		}
	}
	public void debugRender(Matrix4 combined){
		renderer.render(world, combined);
	}
}
