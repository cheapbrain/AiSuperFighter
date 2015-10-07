package com.placydia.aisuperfighter.vcpu;

import com.placydia.aisuperfighter.vcpu.compiler.Instruction;
import com.placydia.aisuperfighter.vcpu.compiler.Language;

public class Processor {
	Computer comp;
	Memory mem;
	
	int target;
	int addr;
	boolean writable;
	int tempPC;
	
	public boolean crash;
	
	public Processor(Computer comp) {
		this.comp = comp;
		mem = comp.mem;
	}
	
	public void reset() {
		crash = false;
	}

	
	public void cycle() {
		if (crash)
			return;
		
		int word = mem.words[mem.regs[Memory.PC]];
		tempPC = mem.regs[Memory.PC] + 1;
		
		int op = word>>>10;
		int a = (word>>>5)&0x1F;
		int b = word&0x1F;
		
		mem.regs[Memory.PC] += Language.length(word);
		
		Instruction inst = Language.getInstruction(op);
		
		prepWriter(a);
		int valueA = getData(a);
		int valueB = getData(b);
		
		int result = inst.execute(comp, valueA, valueB);
		
		if (result!=-1&&writable) {
			switch(target) {
			case 0:
				mem.regs[addr] = result&0xFFFF;
				break;
			case 1:
				mem.stack[addr] = result&0xFFFF;
				break;
			case 2:
				mem.words[addr] = result&0xFFFF;
				break;
			}
		}
	}
	
	public void prepWriter(int code) {
		writable = true;
		if (code<0x08) {
			target = 0;
			addr = code;
		} else if (code<0x10) {
			target = 2;
			addr = mem.regs[code];
		} else if (code<0x13) {
			target = 0;
			addr = code-0x08;
		} else if (code==0x13) {
			writable = false;
		} else if (code==0x14) {
			writable = false;
		} else if (code==0x15) {
			target = 1;
			addr = (mem.regs[Memory.SP]+1)&(Memory.STACK_SIZE-1);
		} else if (code==0x16) {
			writable = false;
		} else if (code==0x17) {
			target = 2;
			addr = mem.words[mem.regs[Memory.PC]];
		} else {
			target = 2;
			addr = mem.words[mem.regs[Memory.PC]+code-0x18];
		}
	}
	
	public int getData(int code) {
		if (code<0x16) {
			if (code<0x08) {
				return mem.regs[code];
			} else if (code<0x10) {
				return mem.words[mem.regs[code-0x08]];
			} else if (code<0x13)
				return mem.regs[code-0x08];
			else {
				int stack;
				switch(code) {
				case 0x13:
					stack = mem.stack[mem.regs[Memory.SP]];
					if (mem.regs[Memory.SP]==0)
						mem.regs[Memory.SP] = Memory.STACK_SIZE-1;
					else
						mem.regs[Memory.SP]--;
					return stack;
				case 0x14:
					return mem.stack[mem.regs[Memory.SP]];
				case 0x15:
					if (mem.regs[Memory.SP]== Memory.STACK_SIZE-1) {
						mem.regs[Memory.SP] = 0;
					} else {
						mem.regs[Memory.SP]++;
					}
					return mem.stack[mem.regs[Memory.SP]];
				}
				return -1;
			}
		} else {
			int word = mem.words[tempPC++];
			if (code==0x16) {
				return word;
			} else {
				if (code>0x17) {
					word += mem.regs[code-0x18];
				}
				return mem.words[word];
			}
		}
	}
}
