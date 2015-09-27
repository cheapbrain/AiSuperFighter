package com.placydia.aisuperfighter.debug;

import com.placydia.aisuperfighter.gameObjects.components.Physic;
import com.placydia.aisuperfighter.gameObjects.objects.Propeller;
import com.placydia.aisuperfighter.screens.GameScreen;

public class GaloTest {

	public static void init() {
		Propeller test = new Propeller(5,5,1,1);
		GameScreen.gameWorld.add(test);
		test.on();
	}
}
