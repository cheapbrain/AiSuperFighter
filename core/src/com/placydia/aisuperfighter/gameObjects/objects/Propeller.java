package com.placydia.aisuperfighter.gameObjects.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.placydia.aisuperfighter.gameObjects.Module;
import com.placydia.aisuperfighter.gameObjects.Ship;
import com.placydia.aisuperfighter.gameObjects.components.ModulePhysic;
import com.placydia.aisuperfighter.gameObjects.components.Transform;
import com.placydia.aisuperfighter.physics.PhysicsWorld;

public class Propeller extends Module{
	private float force = 0;
	public Propeller(Ship ship, float x, float y, float width, float height, float rot){
		super(ship);
		add(new Transform(new Vector2(x,y), rot, new Vector2(1,1)));
		add(new ModulePhysic(get(Transform.class), width, height, 0, this));
	}
	public void init(){
		super.init();
		RevoluteJointDef joint = new RevoluteJointDef();
		joint.bodyA = ship.get(Shell.class).get(ModulePhysic.class).body;
		joint.bodyB = get(ModulePhysic.class).body;
	    joint.collideConnected = false;
	    joint.localAnchorA.set(get(Transform.class).pos);
	    PhysicsWorld.world.createJoint(joint);
	}
	public void update(float delta){
		super.update(delta);
		Vector2 pos = get(ModulePhysic.class).body.getTransform().getPosition();
		float rot = ship.get(Transform.class).rot;
		get(ModulePhysic.class).body.setTransform(pos, rot);
		float x = (float) (Math.cos(rot)*force*delta);
		float y = (float) (Math.sin(rot)*force*delta);
		get(ModulePhysic.class).body.applyForceToCenter(new Vector2(x,y), true);
	}
	public void activate(float force){
		this.force=force;
	}
	public void stop(){
		force=0;
	}
}
