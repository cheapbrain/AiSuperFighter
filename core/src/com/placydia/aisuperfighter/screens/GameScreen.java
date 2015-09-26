package com.placydia.aisuperfighter.screens;

import com.badlogic.gdx.Screen;
import com.placydia.aisuperfighter.gameWorld.GameWorld;
import com.placydia.aisuperfighter.utils.Camera;

public class GameScreen implements Screen{
	public static Camera cam;
	public static GameWorld gameWorld;
	
	public GameScreen() {
		
		cam = new Camera();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		cam.fit(world., height)
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
