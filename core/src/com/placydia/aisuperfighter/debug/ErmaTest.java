package com.placydia.aisuperfighter.debug;

import com.placydia.aisuperfighter.vcpu.Memory;
import com.placydia.aisuperfighter.vcpu.Processor;
import com.placydia.aisuperfighter.vcpu.compiler.Assembler;

public class ErmaTest {

	public static void init() {
		Assembler asm = new Assembler();
		short[] compiled = asm.Compile("SET [1000h] 10 SET A 1000h SET B [A]");
		for (short word : compiled) {
			System.out.println(
					String.format("%16s", Integer.toBinaryString((word)&0xFFFF))
						.replace(' ', '0')+" "+
					String.format("%4s", Integer.toHexString((word)&0xFFFF))
						.replace(' ', '0')+" "+
					String.format("%2s", Integer.toHexString((word>>>10)&0x3F))
						.replace(' ', '0')+" "+
					String.format("%2s", Integer.toHexString((word>>>5)&0x1F))
						.replace(' ', '0')+" "+
					String.format("%2s", Integer.toHexString((word)&0x1F))
						.replace(' ', '0')+" "
				);
		}
		
		Memory mem = new Memory();
		Processor cpu = new Processor(mem);
		
		mem.load(compiled, 0);
		
		cpu.cycle();
		cpu.cycle();
		cpu.cycle();
	}
}
