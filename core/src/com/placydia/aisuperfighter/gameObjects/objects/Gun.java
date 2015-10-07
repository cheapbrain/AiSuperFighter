package com.placydia.aisuperfighter.gameObjects.objects;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.placydia.aisuperfighter.gameObjects.Module;
import com.placydia.aisuperfighter.gameObjects.Ship;
import com.placydia.aisuperfighter.gameObjects.components.Physic;
import com.placydia.aisuperfighter.gameObjects.components.Transform;
import com.placydia.aisuperfighter.physics.PhysicsWorld;
import com.placydia.aisuperfighter.screens.GameScreen;

public class Gun extends Module{
	public boolean active, ready = true;
	private float counter=0, cooldown = .1f;
	public Gun(Ship ship, float x, float y, float width, float height, float rot){
		super(ship);
		add(new Transform(new Vector2(x,y), rot, new Vector2(1,1)));
		add(new Physic(get(Transform.class), width, height, 1f));
		active=false;
	}
	public void init(){
		super.init();
		RevoluteJointDef joint = new RevoluteJointDef();
		joint.bodyA = ship.get(Shell.class).get(Physic.class).body;
		joint.bodyB = get(Physic.class).body;
	    joint.collideConnected = false;
	    joint.localAnchorA.set(get(Transform.class).pos);
	    PhysicsWorld.world.createJoint(joint);	
	}
	public void update(float delta){
		super.update(delta);
		Vector2 pos = get(Physic.class).body.getTransform().getPosition();
		float rot = ship.get(Transform.class).rot;
		get(Physic.class).body.setTransform(pos, rot);
		float x = get(Transform.class).pos.x+get(Physic.class).width+Bullet.width;
		float y = get(Transform.class).pos.y;		
		if(active&&ready){
			Bullet bullet = new Bullet(ship.get(Transform.class).pos.x+(float) (MathUtils.cos(rot)*x-MathUtils.sin(rot)*y), 
									   ship.get(Transform.class).pos.y+(float) (MathUtils.sin(rot)*x+MathUtils.cos(rot)*y),
									   rot, ship);
			ready=false;
			GameScreen.gameWorld.add(bullet);
		}
		counter+=delta;
		if(counter>=cooldown){
			counter-=cooldown;
			ready=true;
		}
	}
	public void activate(){
		this.active=true;
	}
	public void stop(){
		active=false;
	}
}
