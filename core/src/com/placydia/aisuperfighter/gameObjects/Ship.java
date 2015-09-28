package com.placydia.aisuperfighter.gameObjects;

import com.badlogic.gdx.math.Vector2;
import com.placydia.aisuperfighter.gameObjects.components.Transform;
import com.placydia.aisuperfighter.gameObjects.objects.Shell;

public class Ship extends GameObject{
	public Ship(float x, float y, float width, float height){
		add(new Transform(new Vector2(x,y)));
		addModule(new Shell(this, 0,0,width,height));
	}
	public void addModule(Module module){
		add(module);
	}
}
