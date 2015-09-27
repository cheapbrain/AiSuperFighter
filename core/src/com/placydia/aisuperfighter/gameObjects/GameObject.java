package com.placydia.aisuperfighter.gameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class GameObject extends Component{
	private Array<Component> components;
	
	public GameObject() {
		components = new Array<Component>();
	}
	
	public void init() {
		for (Component component : components)
			component.init0();
	}
	
	public void dispose() {
		for (Component component : components)
			component.dispose();
	}
	
	public void update(float delta) {
		for (int i=0;i<components.size;i++) {
			Component component = components.get(i);
			if (component != null) {
				if (!component.isInitialized())
					component.init0();
				component.update(delta);
			}
				
		}
	}
	
	public void render(SpriteBatch batch) {
		
	}
	
	public GameObject add(Component component) {
		components.add(component);
		component.setOwner(this);
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
