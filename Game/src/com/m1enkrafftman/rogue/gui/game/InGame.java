package com.m1enkrafftman.rogue.gui.game;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.m1enkrafftman.rogue.Rogue;
import com.m1enkrafftman.rogue.backend.DisplayFactory;
import com.m1enkrafftman.rogue.entity.EntityPlayer;
import com.m1enkrafftman.rogue.external.BackTile;
import com.m1enkrafftman.rogue.external.Collideable;
import com.m1enkrafftman.rogue.external.World;
import com.m1enkrafftman.rogue.gui.FontRenderer;
import com.m1enkrafftman.rogue.gui.GuiScreen;
import com.m1enkrafftman.rogue.misc.Cache;

public class InGame extends GuiScreen {
	
	private EntityPlayer thePlayer;
	private ArrayList<String> debug;
	private boolean toDebug;
	private World theWorld;
	
	public InGame() {
		super();
		this.thePlayer = new EntityPlayer(0, 0, 16, 0xff00ff00);
		this.theWorld = new World(50, 50);
		debug = new ArrayList<>();
		this.toDebug = false;
	}
	
	public InGame(EntityPlayer p, World w) {
		super();
		this.thePlayer = p;
		this.theWorld = w;
		debug = new ArrayList<>();
		this.toDebug = false;
	}

	public void preRender() {
		
	}

	@Override
	public void render() {
		GL11.glPushMatrix();
		this.preRender();
		//GL11.glTranslated(this.thePlayer.getMinX() - (Cache.width/2), this.thePlayer.getMinY() - (Cache.height/2), 0);
		this.theWorld.renderWorld();
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
			this.thePlayer.moveForward(5, this.theWorld);
		}else if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.thePlayer.moveBackward(2, this.theWorld);
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
