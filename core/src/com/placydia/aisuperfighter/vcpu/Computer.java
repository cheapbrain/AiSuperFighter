package com.placydia.aisuperfighter.vcpu;

import com.placydia.aisuperfighter.gameObjects.objects.Gun;
import com.placydia.aisuperfighter.gameObjects.objects.Propeller;

public class Computer {
	public static final int MHZ = 1000000;
	public static final int KHZ = 1000;

	private int speed = 100*KHZ;
	private float delay = 1f/speed;
	private float time;
	
	public Processor cpu;
	public Memory mem;
	public Systems sys;
	
	public Propeller lPropeller;
	public Propeller rPropeller;
	public Gun gun;
	
	public Computer(int[] program) {
		mem = new Memory(this);
		cpu = new Processor(this);
		sys = new Systems(this);
		
		init(program);
	}
	
	public void init(int[] program) {
		mem.wipe();
		mem.load(program, 0);
		cpu.reset();
		sys.reset();
	}
	
	public void update(float delta) {
		sys.update(delta);
		
		time += delta;
		while(time>=delay) {
			cpu.cycle();
			time -= delay;
		}
	}
}
