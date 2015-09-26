package com.placydia.aisuperfighter.gameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public abstract class GameObject extends Component{
	private Array<Component> components;
	
	public GameObject() {
		components = new Array<Component>();
	}
	
	public void init() {
		for (Component component : components)
			component.init();
	}
	
	public void dispose() {
		for (Component component : components)
			component.dispose();
	}
	
	public void update(float delta) {
		for (Component component : components)
			component.update(delta);
	}
	
	public void render(SpriteBatch batch) {
		
	}
	
	public GameObject add(Component component) {
		components.add(component);
		return this;
	}
	
	public <T extends Component> T get(Class<T> c) {
		for (Component component : components) {
			if (component.getClass()==c)
				return (T)component;
		}
		return null;
	}
	
	public void remove(Class<Component> c) {
		Component comp = null;
		for (Component component : components) {
			if (component.getClass()==c) {
				component.setOwner(null);
				component.dispose();
				
				comp = component;
				break;
			}
		}
		if (comp!=null)
			components.removeValue(comp, true);
	}
	
}
