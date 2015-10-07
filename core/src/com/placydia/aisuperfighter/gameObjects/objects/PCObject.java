package com.placydia.aisuperfighter.gameObjects.objects;

import com.placydia.aisuperfighter.gameObjects.Component;
import com.placydia.aisuperfighter.vcpu.Computer;

public class PCObject extends Component{
	Computer pc;
	
	public PCObject(Computer computer) {
		pc = computer;
	}

	@Override
	public void init() {
		
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void update(float delta) {
		pc.update(delta);
	}

}
