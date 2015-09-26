package com.placydia.aisuperfighter.gameObjects.components;

import com.badlogic.gdx.math.Vector2;
import com.placydia.aisuperfighter.gameObjects.Component;
import com.placydia.aisuperfighter.gameObjects.GameObject;

public class Transform extends Component{
	
	public Vector2 pos;
	public float rot;
	public Vector2 scale;
	
	public Transform(Vector2 pos, float rot, Vector2 scale){
		this.pos = pos;
		this.rot = rot;
		this.scale = scale;
	}
	
	public Transform(Vector2 pos){
		this.pos=pos;
		rot=0;
		scale= new Vector2(1,1);
	}
	
	public void setPos(float x, float y){
		pos.x=x;
		pos.y=y;
	}
	
	public void setScale(float x, float y){
		scale.x=x;
		scale.y=y;
	}
	
	@Override
	public void init() {
	}
	
	@Override
	public void dispose() {
	}
	
	@Override
	public void update(float delta) {
	}
	
}
