package com.placydia.aisuperfighter.gameObjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.utils.Array;
import com.placydia.aisuperfighter.gameObjects.components.BulletPhysic;
import com.placydia.aisuperfighter.gameObjects.components.ModulePhysic;
import com.placydia.aisuperfighter.gameObjects.objects.PhysicWall;
import com.placydia.aisuperfighter.gameObjects.objects.Shell;
import com.placydia.aisuperfighter.screens.GameScreen;

public class RayCastPerFighi implements RayCastCallback{
	public Vector2 point;
	public int idTarget;
	@Override
	public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
		Array<GameObject> objects = new Array<GameObject>();
		this.point=point;
		objects = GameScreen.gameWorld.objectManager.getObjects();
		for(GameObject obj:objects){
			if(obj!=null){
				if(obj.get(PhysicWall.class)!=null)
					if(obj.get(PhysicWall.class).body!=null)
						if(obj.get(PhysicWall.class).body.getFixtureList().contains(fixture, false)){
							idTarget=0;	
						}
				if(obj.get(BulletPhysic.class)!=null)
					if(obj.get(BulletPhysic.class).body!=null)
						if(obj.get(BulletPhysic.class).body.getFixtureList().contains(fixture, false)){
							idTarget=1;
						}
				if(obj.get(Shell.class)!=null)
					if(obj.get(Shell.class).get(ModulePhysic.class).body!=null)
						if(obj.get(Shell.class).get(ModulePhysic.class).body.getFixtureList().contains(fixture, false)){
							idTarget=2;
						}
			}
		}
		return 0;
	}

}
