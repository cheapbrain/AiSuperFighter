package com.placydia.aisuperfighter.vcpu.compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

public class Assembler {
	private String[] funcs;
	
	public Assembler() {
		
	}
	
	public String load(String path) {
		String text = Gdx.files.internal(path).readString();
		
		return text;
	}
	
	public String[] clean(String input) {
		return input.trim().toUpperCase().split("\\s+");
	}
	
	public int[] compile(String source) {
		funcs = clean(source);
		
		Array<Integer> words = new Array<Integer>();
		Array<Pair> labs = new Array<Pair>();
		Array<Pair> uses = new Array<Pair>();
		
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
						int addr = words.size+1;
						if (extra[0]!=-1) addr += j;
						uses.add(new Pair(funcs[i], addr));
						value = Language.encode("255");
						word = (word<<5)|(value[0]&0x1F);
						extra[j] = value[1];
					}
				}
				words.add(word&0xFFFF);
				for (int j=0;j<extra.length;j++) {
					if (extra[j]!=-1)
						words.add(extra[j]);
				}
			} else {
				if (func.charAt(0)==':') {
					labs.add(new Pair(func.substring(1), words.size));
				} else {
					System.err.println("WHAT IS THIS?!?!: "+func);
				}
			}
			i++;
		}
		words.shrink();
		int[] out = new int[words.size];
		for (int j=0;j<words.size;j++)
			out[j] = words.get(j);
		
		for (Pair use:uses) {
			for (Pair lab:labs) {
				if (lab.l.equals(use.l)) {
					out[use.a] = lab.a&0xFFFF;
				}
			}
		}
		
		return out;
	}
	
	private class Pair {
		public String l;
		public int a;
		
		public Pair(String label, int addr) {
			a = addr;
			l = label;
		}
	}

}
