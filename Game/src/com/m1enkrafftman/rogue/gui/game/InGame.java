package com.m1enkrafftman.rogue.gui.game;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.m1enkrafftman.rogue.Rogue;
import com.m1enkrafftman.rogue.backend.DisplayFactory;
import com.m1enkrafftman.rogue.entity.EntityPlayer;
import com.m1enkrafftman.rogue.external.BackTile;
import com.m1enkrafftman.rogue.external.Collideable;
import com.m1enkrafftman.rogue.gui.FontRenderer;
import com.m1enkrafftman.rogue.gui.GuiScreen;
import com.m1enkrafftman.rogue.misc.Cache;

public class InGame extends GuiScreen {
	
	private EntityPlayer thePlayer;
	private ArrayList<Collideable> collides;
	private ArrayList<BackTile> backTiles;
	private ArrayList<String> debug;
	private boolean toDebug;
	
	public InGame() {
		super();
		this.thePlayer = new EntityPlayer(0, 0, 16, 0xff00ff00);
		collides = new ArrayList<>();
		backTiles = new ArrayList<>();
		debug = new ArrayList<>();
		this.setupWalls();
		this.toDebug = false;
	}
	
	private void setupWalls() {
		for(int x = 0; x < 50; x++) {
			for(int y = 0; y < 38; y++) {
				this.backTiles.add(new BackTile(x*16, y*16, 16, 0xffffffff));
			}
		}
	}

	public void preRender() {
		
	}

	@Override
	public void render() {
		GL11.glPushMatrix();
		this.preRender();
		//GL11.glTranslated(this.thePlayer.getMinX() - (Cache.width/2), this.thePlayer.getMinY() - (Cache.height/2), 0);
		for(BackTile b : this.backTiles) {
			b.renderWall();
		}
		this.thePlayer.renderLiving();
		FontRenderer.drawStringWithColor("Rogue", 3, 12, 0xffffffff);
		this.renderDebug();
		GL11.glPopMatrix();
	}
	
	public void renderDebug() {
		if(this.toDebug) {
			this.debug.clear();
			this.debug.add("X: " + this.thePlayer.getMinX());
			this.debug.add("Y: " + this.thePlayer.getMinY());
			this.debug.add("Rot: " + this.thePlayer.getRotation());
			this.debug.add("FPS: " + Cache.fps);
			int x = 3; int y = 22;
			for(String s: this.debug) {
				FontRenderer.drawStringWithColor(s, x, y, 0xffffffff);
				y+=10;
			}
		}else {
			FontRenderer.drawStringWithColor("G: Debug", 3, 22, 0xffffffff);
		}
	}

	@Override
	public void postRender() {
		
	}

	@Override
	public void onMouseClick(int key) {
		
	}

	@Override
	public void onKeyPress(int key) {
		switch(key) {
		case(Keyboard.KEY_G):
			this.toDebug = !this.toDebug;
			break;
		}
	}
	
	public void checkForMovement() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.thePlayer.moveForward(5);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.thePlayer.rotateLeft();
		}else if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.thePlayer.rotateRight();
		}
	}

	@Override
	public void handleLogic() {
		this.checkForMovement();
	}

}
