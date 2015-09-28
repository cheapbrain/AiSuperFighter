package com.placydia.aisuperfighter.vcpu;

public class Memory {
	public static final int PC = 0x08;
	public static final int SP = 0x09;
	public static final int O = 0x0A;
	public static final int REGS_SIZE = 0x0B;
	public static final int STACK_SIZE = 0x100;
	public static final int WORDS_SIZE = 0x10000;
	
	public short[] regs;
	public short[] stack;
	public short[] words;
	
	public Memory() {
		regs = new short[REGS_SIZE];
		stack = new short[STACK_SIZE];
		words = new short[WORDS_SIZE];
	}
	
	public void load(short[] words, int offset) {
		for (int i=0;i<words.length;i++)
			this.words[i+offset] = words[i];
	}
}
