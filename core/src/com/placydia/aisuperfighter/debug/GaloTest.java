package com.placydia.aisuperfighter.debug;

import com.placydia.aisuperfighter.gameObjects.Ship;
import com.placydia.aisuperfighter.gameObjects.objects.Gun;
import com.placydia.aisuperfighter.gameObjects.objects.PhysicWall;
import com.placydia.aisuperfighter.gameObjects.objects.Propeller;
import com.placydia.aisuperfighter.screens.GameScreen;

public class GaloTest {

	public static void init() {
		float x = 3;
		float y = 3;
		float width = 1;
		float height = 1;
		Ship test = new Ship(x,y,width, height);
		Propeller p1 = new Propeller(test, 0,-height,width/3, height/3, 0);
		Propeller p2 = new Propeller(test, 0,height,width/3, height/3, 0);
		Gun gun = new Gun(test, width*4/3,0,width*2/5,height/4,0);
		test.addModule(p1);
		test.addModule(p2);
		test.addModule(gun);
		GameScreen.gameWorld.add(test);
		PhysicWall wall = new PhysicWall(0,0,0.5f,GameScreen.gameWorld.getHeight(),10);
		GameScreen.gameWorld.add(wall);
		wall = new PhysicWall(GameScreen.gameWorld.getWidth(),0,0.5f,GameScreen.gameWorld.getHeight(),10);
		GameScreen.gameWorld.add(wall);
		wall = new PhysicWall(0,0,GameScreen.gameWorld.getWidth(),0.5f,10);
		GameScreen.gameWorld.add(wall);
		wall = new PhysicWall(0,GameScreen.gameWorld.getHeight(),GameScreen.gameWorld.getWidth(),0.5f,10);
		GameScreen.gameWorld.add(wall);
		p1.activate(500);
		GameScreen.physicsWorld.ship=test;
	}
	
}
