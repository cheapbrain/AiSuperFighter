package com.placydia.aisuperfighter.vcpu;

import com.badlogic.gdx.math.MathUtils;

public class Systems {
	public static final int CLOCK = 0x0001;
	public static final int L_PROPELLER = 0x0010;
	public static final int R_PROPELLER = 0x0011;
	public static final int GUN = 0x0020;
	private Computer comp;
	private float time;
	
	public Systems(Computer computer) {
		comp = computer;
	}
	
	public void reset() {
		time = 0;
	}

	public void update(float delta) {
		time += delta;
	}
	
	public void signal(int a, int b) {
		float force;
		
		switch(a) {
		case CLOCK:
			int round = (int)MathUtils.floorPositive(time*1000);
			comp.mem.regs[0] = round;
			time -= round/1000f;
			if (time<0)
				time = 0;
			break;
		case L_PROPELLER:
			force = (float)b/0xFFFF;
			comp.lPropeller.activate(force*500);
			break;
		case R_PROPELLER:
			force = (float)b/0xFFFF;
			comp.rPropeller.activate(force*500);
			break;
		case GUN:
			boolean shoot = b != 0;
			comp.gun.active = shoot;
			break;
		}
	}
	
}
