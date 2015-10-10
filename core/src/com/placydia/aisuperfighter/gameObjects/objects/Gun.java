package com.placydia.aisuperfighter.gameObjects.objects;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.placydia.aisuperfighter.gameObjects.Module;
import com.placydia.aisuperfighter.gameObjects.RayCastPerFighi;
import com.placydia.aisuperfighter.gameObjects.RayCastResult;
import com.placydia.aisuperfighter.gameObjects.Ship;
import com.placydia.aisuperfighter.gameObjects.components.ModulePhysic;
import com.placydia.aisuperfighter.gameObjects.components.Transform;
import com.placydia.aisuperfighter.physics.PhysicsConstants;
import com.placydia.aisuperfighter.physics.PhysicsWorld;
import com.placydia.aisuperfighter.screens.GameScreen;

public class Gun extends Module{
	public boolean active, ready = true;
	public float ray = 40, radarx, radary;
	public float radarx2;
	public float radary2;
	private RayCastPerFighi rayCast;
	private float counter=0, cooldown = .1f;
	public Gun(Ship ship, float x, float y, float width, float height, float rot){
		super(ship);
		add(new Transform(new Vector2(x,y), rot, new Vector2(1,1)));
		add(new ModulePhysic(get(Transform.class), width, height, 0, this));
		active=false;
		rayCast = new RayCastPerFighi();
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
	public RayCastResult radar(float counter2){
		counter2*=180;
		float rot = ship.get(Transform.class).rot;
		float tmpx = ship.get(Gun.class).get(Transform.class).pos.x;
		float tmpy = ship.get(Gun.class).get(Transform.class).pos.y;	
		radarx = ship.get(Transform.class).pos.x+(float) (MathUtils.cos(rot)*tmpx-MathUtils.sin(rot)*tmpy);
		radary = ship.get(Transform.class).pos.y+(float) (MathUtils.sin(rot)*tmpx+MathUtils.cos(rot)*tmpy);
		radarx2 = radarx+ray*MathUtils.cos(MathUtils.PI/360*(counter2)+rot);
		radary2 = radary+ray*MathUtils.sin(MathUtils.PI/360*(counter2)+rot);
		GameScreen.physicsWorld.world.rayCast(rayCast, new Vector2(radarx, radary), new Vector2(radarx2, radary2));
		RayCastResult result = new RayCastResult();
		result.idTarget = rayCast.idTarget;
		result.distance = ship.get(Transform.class).pos.dst(rayCast.point);
		return result;
	}
	public void update(float delta){
		super.update(delta);		
		Vector2 pos = get(ModulePhysic.class).body.getTransform().getPosition();
		float rot = ship.get(Transform.class).rot;
		get(ModulePhysic.class).body.setTransform(pos, rot);
		float x = get(Transform.class).pos.x+get(ModulePhysic.class).width+Bullet.width+0.2f;
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
