package com.placydia.aisuperfighter.gameObjects.objects;

import com.badlogic.gdx.math.Vector2;
import com.placydia.aisuperfighter.gameObjects.GameObject;
import com.placydia.aisuperfighter.gameObjects.Ship;
import com.placydia.aisuperfighter.gameObjects.components.BulletPhysic;
import com.placydia.aisuperfighter.gameObjects.components.Transform;

public class Bullet extends GameObject{
	public static final float width=.1f, height=.1f;
	public float force = 10;
	public Bullet(float x, float y, float rot, Ship ship){
		setOwner(ship);
		add(new Transform(new Vector2(x,y), rot, new Vector2(1,1)));
		add(new BulletPhysic(get(Transform.class), width, height, .5f));
	}
	public void init(){
		super.init();			
		float x = (float) (Math.cos(get(Transform.class).rot)*force);
		float y = (float) (Math.sin(get(Transform.class).rot)*force);
		get(BulletPhysic.class).body.applyForceToCenter(new Vector2(x,y), true);
	}
	public void update(float delta){
		super.update(delta);
		Vector2 pos = get(BulletPhysic.class).body.getTransform().getPosition();
		float rot = getOwner().get(Transform.class).rot;
		get(BulletPhysic.class).body.setTransform(pos, rot);
	}
}
