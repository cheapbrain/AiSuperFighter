package com.placydia.aisuperfighter.debug;

import com.placydia.aisuperfighter.vcpu.compiler.Assembler;

public class ErmaTest {

	public static void init() {
		Assembler asm = new Assembler();
		short[] compiled = asm.Compile("SET 377o J");
		for (short word : compiled) {
			System.out.println(
					String.format("%16s", Integer.toBinaryString(((int)word)&0xFFFF))
						.replace(' ', '0')+" "+
					String.format("%4s", Integer.toHexString(((int)word)&0xFFFF))
						.replace(' ', '0')+" "+
					String.format("%2s", Integer.toHexString(((int)word>>10)&0x3F))
						.replace(' ', '0')+" "+
					String.format("%2s", Integer.toHexString(((int)word>>5)&0x1F))
						.replace(' ', '0')+" "+
					String.format("%2s", Integer.toHexString(((int)word)&0x1F))
						.replace(' ', '0')+" "
				);
		}
	}
}
