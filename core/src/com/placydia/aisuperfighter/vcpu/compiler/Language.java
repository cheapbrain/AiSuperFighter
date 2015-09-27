package com.placydia.aisuperfighter.vcpu.compiler;

import com.badlogic.gdx.utils.Array;
import com.placydia.aisuperfighter.vcpu.Memory;

public class Language {
	private static Array<Instruction> funcs;
	
	public static void init() {
		funcs = new Array<Instruction>();
		funcs.add(new Instruction("SET", 0x00, 2, 1, new Performer(){
			@Override
			public int execute(Memory mem, int a, int b) {
				return b;
			}
		}));
		funcs.add(new Instruction("ADD", 0x01, 2, 1, new Performer(){
			@Override
			public int execute(Memory mem, int a, int b) {
				return (a+b);
			}
		}));
		funcs.add(new Instruction("SUB", 0x02, 2, 1, new Performer(){
			@Override
			public int execute(Memory mem, int a, int b) {
				return (a-b);
			}
		}));
		funcs.add(new Instruction("MUL", 0x03, 2, 1, new Performer(){
			@Override
			public int execute(Memory mem, int a, int b) {
				return (a*b);
			}
		}));
		funcs.add(new Instruction("DIV", 0x04, 2, 1, new Performer(){
			@Override
			public int execute(Memory mem, int a, int b) {
				return (a/b);
			}
		}));
		funcs.add(new Instruction("MOD", 0x05, 2, 1, new Performer(){
			@Override
			public int execute(Memory mem, int a, int b) {
				return (a%b);
			}
		}));
		funcs.add(new Instruction("IEQ", 0x06, 2, 1, new Performer(){
			@Override
			public int execute(Memory mem, int a, int b) {
				return (a+b)&0xFFFF;
			}
		}));
	}
	
	public static Instruction getInstruction(String name) {
		for (int i=0;i<funcs.size;i++) {
			Instruction func = funcs.get(i);
			if (func!=null)
				if (func.getName().equals(name))
					return func;
		}
		return null;
	}
	
	public static Instruction getInstruction(int code) {
		for (int i=0;i<funcs.size;i++) {
			Instruction func = funcs.get(i);
			if (func!=null)
				if (func.getCode()==code)
					return func;
		}
		return null;
	}
	
	public static int[] encode(String input) {
		if (input.contains("[")) {
			input = input.substring(1, input.length()-1);
			if (input.contains("+")) {
				String[] vals = input.split("\\+");
				int num = getNumber(vals[0]);
				int reg = getRegister(vals[1]);
				if (num!=-1&&reg>=0&&reg<=7) {
					return new int[]{0x18 + reg, num};
				} else
					return null;
			} else {
				int reg = getRegister(input);
				if (reg==-1) {
					int num = getNumber(input);
					if (num!=-1) {
						return new int[]{0x17, num};
					} else
						return null;
				} else if (reg>=0&&reg<=7){
					return new int[]{0x08 + reg};
				} else 
					return null;
			}
		} else {
			int reg = getRegister(input);
			if (reg==-1) {
				int num = getNumber(input);
				if (num!=-1) {
					return new int[]{0x16, num};
				} else
					return null;
			} else {
				return new int[]{reg};
			}
		}
	}
	
	public static int getRegister(String name) {
		if (name.equals("A"))
			return 0x00;
		if (name.equals("B"))
			return 0x01;
		if (name.equals("C"))
			return 0x02;
		if (name.equals("X"))
			return 0x03;
		if (name.equals("Y"))
			return 0x04;
		if (name.equals("Z"))
			return 0x05;
		if (name.equals("I"))
			return 0x06;
		if (name.equals("J"))
			return 0x07;
		if (name.equals("PC"))
			return 0x10;
		if (name.equals("SP"))
			return 0x11;
		if (name.equals("O"))
			return 0x12;
		if (name.equals("POP"))
			return 0x13;
		if (name.equals("PEEK"))
			return 0x14;
		if (name.equals("PUSH"))
			return 0x15;
		
		return -1;
	}
	
	public static int getNumber(String name) {
		try {
			if (name.endsWith("B"))
				return Integer.parseInt(name.substring(0, name.length()-1), 2);
			if (name.endsWith("O"))
				return Integer.parseInt(name.substring(0, name.length()-1), 8);
			if (name.endsWith("D"))
				return Integer.parseInt(name.substring(0, name.length()-1), 10);
			if (name.endsWith("H"))
				return Integer.parseInt(name.substring(0, name.length()-1), 16);
			
			return Integer.parseInt(name);
		} catch(Exception e) {
			return -1;
		}
	}
	
}
