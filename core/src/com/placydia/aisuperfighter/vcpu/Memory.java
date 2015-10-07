package com.placydia.aisuperfighter.vcpu;

public class Memory {
	public static final int PC = 0x08;
	public static final int SP = 0x09;
	public static final int O = 0x0A;
	public static final int REGS_SIZE = 0x0B;
	public static final int STACK_SIZE = 0x100;
	public static final int WORDS_SIZE = 0x10000;
	
	public int[] regs;
	public int[] stack;
	public int[] words;
	
	private Computer comp;
	
	public Memory(Computer computer) {
		comp = computer;
		regs = new int[REGS_SIZE];
		stack = new int[STACK_SIZE];
		words = new int[WORDS_SIZE];
	}
	
	public void wipe() {
		for (int i=0;i<REGS_SIZE;i++)
			regs[i] = 0;
		for (int i=0;i<STACK_SIZE;i++)
			stack[i] = 0;
		for (int i=0;i<WORDS_SIZE;i++)
			words[i] = 0;
	}
	
	public void load(int[] words, int offset) {
		for (int i=0;i<words.length;i++)
			this.words[i+offset] = words[i];
	}
}
