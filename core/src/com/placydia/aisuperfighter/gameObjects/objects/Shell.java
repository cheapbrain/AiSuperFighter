package com.placydia.aisuperfighter.gameObjects.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.placydia.aisuperfighter.gameObjects.Module;
import com.placydia.aisuperfighter.gameObjects.Ship;
import com.placydia.aisuperfighter.gameObjects.components.ModulePhysic;
import com.placydia.aisuperfighter.gameObjects.components.Transform;

public class Shell extends Module{
	public Shell(Ship ship, float x, float y, float width, float height){
		super(ship);
		add(new Transform(new Vector2(x,y)));
		add(new ModulePhysic(get(Transform.class), width, height, 5, this));
	}
	public void update(float delta){
		super.update(delta);
		float rot = get(ModulePhysic.class).body.getAngle();
		ship.get(Transform.class).rot = rot;
		ship.get(Transform.class).pos = get(ModulePhysic.class).body.getPosition();
	}
}
