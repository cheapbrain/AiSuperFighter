package com.placydia.aisuperfighter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.placydia.aisuperfighter.gameObjects.GameObject;
import com.placydia.aisuperfighter.gameObjects.components.Physic;
import com.placydia.aisuperfighter.gameObjects.components.Transform;
import com.placydia.aisuperfighter.gameWorld.GameWorld;
import com.placydia.aisuperfighter.physics.PhysicsWorld;
import com.placydia.aisuperfighter.utils.Camera;

public class GameScreen implements Screen{
	public static Camera cam;
	public static GameWorld gameWorld;
	public static PhysicsWorld physicsWorld;
	
	Texture tt;
	SpriteBatch batch;
	
	public GameScreen() {
		tt = new Texture(Gdx.files.internal("badlogic.jpg"));
		batch = new SpriteBatch();
		
		gameWorld = new GameWorld(10, 10);
		physicsWorld = new PhysicsWorld();
		
		GameObject test = new GameObject()
				.add(new Transform(new Vector2(0, 4)))
				.add(new Physic(1, 1));
		
		gameWorld.add(test);
		
		test = new GameObject()
				.add(new Transform(new Vector2(7, 3)))
				.add(new Physic(1, 1));
		
		gameWorld.add(test);
		
		cam = new Camera().fit(gameWorld.getWidth(), gameWorld.getHeight()).setPosition(0, 0);
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		gameWorld.update(delta);
		physicsWorld.update(delta);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		cam.update(delta);
		
		batch.setProjectionMatrix(cam.getMatrix());
		batch.begin();
		batch.end();
		
		physicsWorld.debugRender(cam.getMatrix());
	}

	@Override
	public void resize(int width, int height) {
		cam.fit(gameWorld.getWidth(), gameWorld.getHeight());
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
