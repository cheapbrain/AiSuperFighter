package com.placydia.aisuperfighter.vcpu.compiler;

import com.placydia.aisuperfighter.vcpu.Memory;

public interface Performer {

	public int execute(Memory mem, int a, int b);
}
