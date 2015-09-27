package com.placydia.aisuperfighter.gameObjects.objects;

import com.badlogic.gdx.math.Vector2;
import com.placydia.aisuperfighter.gameObjects.GameObject;
import com.placydia.aisuperfighter.gameObjects.components.Physic;
import com.placydia.aisuperfighter.gameObjects.components.Transform;

public class Propeller extends GameObject{
	private boolean active = false; 
	public Propeller(float x, float y, float width, float height){
		add(new Transform(new Vector2(x,y)));
		add(new Physic(width, height));
	}
	public void on(){
		active = true;
		
	}
	public void off(){
		active = false;
	}
	public void update(float delta){
		super.update(delta);
		if(get(Physic.class).isInitialized()){
			if(active){
				get(Physic.class).body.applyForceToCenter(500, 0, true);
			}
		}
	}
}
