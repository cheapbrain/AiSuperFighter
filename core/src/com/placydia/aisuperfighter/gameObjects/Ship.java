package com.placydia.aisuperfighter.gameObjects;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.placydia.aisuperfighter.gameObjects.components.Transform;
import com.placydia.aisuperfighter.gameObjects.objects.Gun;
import com.placydia.aisuperfighter.gameObjects.objects.Shell;

public class Ship extends GameObject{
	private float x,y,rot;
	public float counter, tmp = 0.5f;
	public Ship(float x, float y, float width, float height){
		add(new Transform(new Vector2(x,y)));
		addModule(new Shell(this, 0,0,width,height));
		rot = (float) (MathUtils.PI/2);
		this.x=x;
		this.y=y;
	}
	public void update(float delta){
		super.update(delta);
	}
	public void addModule(Module module){
		add(module);
	}
}
