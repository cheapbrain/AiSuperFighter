package com.placydia.aisuperfighter.gameObjects;

import java.util.Arrays;

import com.placydia.aisuperfighter.vcpu.Memory;
import com.placydia.aisuperfighter.vcpu.Processor;

public class CPUTest extends GameObject{
	public static final int MHZ = 1000000;
	public static final int KHZ = 1000;
	
	private int speed = 100*KHZ;
	private float delay = 1f/speed;
	
	private Memory mem;
	private Processor proc;
	private float log;
	private float time;
	private long cycles;

	public CPUTest(Processor proc, Memory mem) {
		this.proc = proc;
		this.mem = mem;
		cycles = 0;
	}
	
	public void update(float delta) {
		time += delta;
		while(time>=delay) {
			proc.cycle();
			cycles++;
			time -= delay;
		}
		
		//log += delta;
		if (log>2) {
			System.out.println(Arrays.toString(mem.regs) + " "+cycles);
			cycles = 0;
			log -= 2;
		}
	}
}
