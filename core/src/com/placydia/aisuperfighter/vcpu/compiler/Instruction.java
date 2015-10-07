package com.placydia.aisuperfighter.vcpu.compiler;

import com.placydia.aisuperfighter.vcpu.Computer;
import com.placydia.aisuperfighter.vcpu.Memory;

public class Instruction {
	private String name;
	private byte code;
	private byte args;
	private byte cycles;
	private Performer performer;
	
	public Instruction(String name, int code, int args, int cycles, Performer performer) {
		this.name = name;
		this.code = (byte)code;
		this.args = (byte)args;
		this.cycles = (byte)cycles;
		this.performer = performer;
	}
	
	public String getName() {
		return name;
	}
	
	public byte getCode() {
		return code;
	}
	
	public byte getArgs() {
		return args;
	}
	
	public byte getCycles() {
		return cycles;
	}
	
	public int execute(Computer comp, int a, int b) {
		return performer.execute(comp, a, b);
	}
	
}
