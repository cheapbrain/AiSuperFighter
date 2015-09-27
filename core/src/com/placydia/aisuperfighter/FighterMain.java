package com.placydia.aisuperfighter;

import com.badlogic.gdx.Game;
import com.placydia.aisuperfighter.debug.ErmaTest;
import com.placydia.aisuperfighter.debug.GaloTest;
import com.placydia.aisuperfighter.screens.GameScreen;
import com.placydia.aisuperfighter.vcpu.compiler.Language;

public class FighterMain extends Game {
	public static GameScreen gameScreen;

	@Override
	public void create() {
		Language.init();
		
		gameScreen = new GameScreen();
		
		GaloTest.init();
		ErmaTest.init();
		
		setScreen(gameScreen);
		
	}
	
}
