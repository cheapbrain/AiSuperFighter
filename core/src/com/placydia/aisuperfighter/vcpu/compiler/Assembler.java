package com.placydia.aisuperfighter.vcpu.compiler;

import com.badlogic.gdx.utils.Array;

public class Assembler {
	private String[] funcs;
	
	public Assembler() {
		
	}
	
	public String[] clean(String input) {
		
		return input.trim().toUpperCase().split("\\s+");
	}
	
	public short[] Compile(String source) {
		funcs = clean(source);
		
		Array<Short> words = new Array<Short>();
		
		int i = 0;
		while(i<funcs.length) {
			String func = funcs[i];
			Instruction inst = Language.getInstruction(func);
			if (inst!=null) {
				int word = inst.getCode();
				int[] extra = new int[inst.getArgs()];
				for (int j=0;j<extra.length;j++) {
					i++;
					int[] value = Language.encode(funcs[i]);
					if (value!=null) {
						word = (word<<5)|(value[0]&0x1F);
						if (value.length==1) {
							extra[j] = -1;
						} else {
							extra[j] = (value[1])&0xFFFF;
							if (extra[j]!=value[1]) {
								System.err.println("WHAT IS THIS?!?!: "+funcs[i]);
							}
						} 
					} else {
						System.err.println("WHAT IS THIS?!?!: "+funcs[i]);
					}
				}
				words.add((short)(word&0xFFFF));
				for (int j=0;j<extra.length;j++) {
					if (extra[j]!=-1)
						words.add((short)extra[j]);
				}
			} else {
				System.err.println("WHAT IS THIS?!?!: "+func);
			}
			i++;
		}
		words.shrink();
		short[] out = new short[words.size];
		for (int j=0;j<words.size;j++)
			out[j] = words.get(j);
		return out;
	}
	

}
