package com.placydia.aisuperfighter.debug;

import com.placydia.aisuperfighter.gameObjects.Ship;
import com.placydia.aisuperfighter.gameObjects.objects.Gun;
import com.placydia.aisuperfighter.gameObjects.objects.PCObject;
import com.placydia.aisuperfighter.gameObjects.objects.Propeller;
import com.placydia.aisuperfighter.screens.GameScreen;
import com.placydia.aisuperfighter.vcpu.Computer;
import com.placydia.aisuperfighter.vcpu.compiler.Assembler;

public class ErmaTest {

	public static void init() {
		Assembler asm = new Assembler();
		int[] compiled = asm.compile(asm.load("test.asm"));
		
		
		float x = 20;
		float y = 20;
		float width = 1;
		float height = 1;
		Ship test = new Ship(x,y,width, height);
		Propeller p1 = new Propeller(test, 0,-height,width/3, height/3, 0);
		Propeller p2 = new Propeller(test, 0,height,width/3, height/3, 0);
		Gun gun = new Gun(test, width*4/3,0,width*2/5,height/4,0);
		test.addModule(p1);
		test.addModule(p2);
		test.addModule(gun);
		
		Computer comp = new Computer(compiled);
		comp.gun = gun;
		comp.rPropeller = p1;
		comp.lPropeller = p2;
		
		test.add(new PCObject(comp));
		
		GameScreen.gameWorld.add(test);
		//gun.activate();
		//p1.activate(500);
	}
}
