package com.placydia.aisuperfighter.physics;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.placydia.aisuperfighter.gameObjects.GameObject;
import com.placydia.aisuperfighter.gameObjects.RayCastPerFighi;
import com.placydia.aisuperfighter.gameObjects.Ship;
import com.placydia.aisuperfighter.gameObjects.components.BulletPhysic;
import com.placydia.aisuperfighter.gameObjects.components.ModulePhysic;
import com.placydia.aisuperfighter.gameObjects.components.Physic;
import com.placydia.aisuperfighter.gameObjects.components.Transform;
import com.placydia.aisuperfighter.gameObjects.objects.Bullet;
import com.placydia.aisuperfighter.gameObjects.objects.Gun;
import com.placydia.aisuperfighter.screens.GameScreen;

public class PhysicsWorld {
	public static World world;
	private float accumulator = 0;
	BodyDef bd = new BodyDef();
	private boolean cancella=false;
	private Array<Body> daCancellare = new Array<Body>();
	private Box2DDebugRenderer renderer;
	public PhysicsWorld(){
		world = new World(new Vector2(0, 0), true);
		renderer = new Box2DDebugRenderer();
		world.setContactListener(new ContactListener() {


			@Override
			public void beginContact(Contact contact) {
				if(contact.getFixtureB().getBody().getUserData()!=null)
				if(((String)contact.getFixtureB().getBody().getUserData()).contains("cacca")){
					cancella=true;
					if(!daCancellare.contains(contact.getFixtureB().getBody(), false))
						daCancellare.add(contact.getFixtureB().getBody());
				}	
				if(contact.getFixtureA().getBody().getUserData()!=null)
				if(((String)contact.getFixtureA().getBody().getUserData()).contains("cacca")){
					cancella=true;
					if(!daCancellare.contains(contact.getFixtureA().getBody(), false))
						daCancellare.add(contact.getFixtureA().getBody());
				}	
			}

			@Override
			public void endContact(Contact contact) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {

				
			}
		});
	}
	public void update(float delta){
		if(cancella){
			cancella=false;
			for(Body body:daCancellare){
				if(body!=null){
					world.destroyBody(body);
					for(GameObject obj:GameScreen.gameWorld.objectManager.getObjects()){
						if(obj!=null){
							if(obj.get(BulletPhysic.class)!=null){
								if(obj.get(BulletPhysic.class).body.equals(body)){
									obj.dispose();	
								}
							}
						}
					}
					daCancellare.removeValue(body, false);
				}
			}
		}
		//System.out.println(GameScreen.gameWorld.objectManager.getObjects().size);
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
