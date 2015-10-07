package com.placydia.aisuperfighter.physics;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.placydia.aisuperfighter.gameObjects.RayCastPerFighi;
import com.placydia.aisuperfighter.gameObjects.Ship;
import com.placydia.aisuperfighter.gameObjects.components.Transform;

public class PhysicsWorld {
	public static World world;
	private float accumulator = 0;
	private RayCastPerFighi rayCast;
	public static Ship ship;
	private Box2DDebugRenderer renderer;
	public PhysicsWorld(){
		world = new World(new Vector2(0, 0), true);
		rayCast = new RayCastPerFighi();
		renderer = new Box2DDebugRenderer();
	}
	public void update(float delta){
		accumulator+=delta;
		if(accumulator>=PhysicsConstants.TIME_STEP){
			world.step(PhysicsConstants.TIME_STEP, 6, 2);
			accumulator-=PhysicsConstants.TIME_STEP;
			world.rayCast(rayCast, ship.get(Transform.class).pos, new Vector2(8,8));
		}
	}
	public void debugRender(Matrix4 combined){
		renderer.render(world, combined);
	}
}
