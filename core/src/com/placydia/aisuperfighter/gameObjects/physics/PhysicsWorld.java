package com.placydia.aisuperfighter.gameObjects.physics;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.placydia.aisuperfighter.gameObjects.GameObject;
import com.placydia.aisuperfighter.gameObjects.components.Physic;
import com.placydia.aisuperfighter.gameObjects.components.Transform;

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
	public void add(Physic physic){
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(physic.transform.pos);
		bodyDef.angle = physic.transform.rot;
		Body body = world.createBody(bodyDef);
		PolygonShape shape = new PolygonShape();
        shape.setAsBox(physic.width, physic.height);
        body.createFixture(shape, 0);
        shape.dispose();
		physic.body=body;
	}
	public void debugRender(Matrix4 combined){
		renderer.render(world, combined);
	}
}
