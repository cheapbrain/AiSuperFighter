package com.placydia.aisuperfighter.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;

public class Camera {
	private OrthographicCamera orto;
	
	public Camera() {
		orto = new OrthographicCamera();
	}
	
	public Matrix4 getMatrix() {
		return orto.combined;
	}
	
	public void update(float delta) {
		orto.update();
	}
	
	public Camera setSize(float width, float height) {
		orto.setToOrtho(false, width, height);
		return this;
	}
	
	public Camera setPosition(float x, float y) {
		orto.position.set(x, y, 0);
		return this;
	}
	
	public Camera setFixedWidth(float width) {
		float sw = Gdx.graphics.getWidth();
		float sh = Gdx.graphics.getHeight();
		
		setSize(width, width/sw*sh);
		return this;
	}
	
	public Camera setFixedHeight(float height) {
		float sw = Gdx.graphics.getWidth();
		float sh = Gdx.graphics.getHeight();
		
		setSize(height/sh*sw, height);
		return this;
	}
	
	public Camera fit(float width, float height) {
		float sw = Gdx.graphics.getWidth();
		float sh = Gdx.graphics.getHeight();
		
		
		return this;
	}
}
