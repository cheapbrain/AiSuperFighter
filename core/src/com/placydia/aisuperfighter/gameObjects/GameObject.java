package com.placydia.aisuperfighter.gameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameObject extends Component{
	
	public abstract void init();
	
	public abstract void dispose();
	
	public abstract void update(float delta);
	
	public abstract void render(SpriteBatch batch);
	
	public abstract GameObject add(Component component);
	
	public abstract Component get(Class<Component> c);
	
	

}
