package com.m1enkrafftman.rogue.gui.game;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.m1enkrafftman.rogue.GameLoop;
import com.m1enkrafftman.rogue.Rogue;
import com.m1enkrafftman.rogue.backend.DisplayFactory;
import com.m1enkrafftman.rogue.entity.EntityEnemy;
import com.m1enkrafftman.rogue.entity.EntityOtherPlayer;
import com.m1enkrafftman.rogue.entity.EntityPlayer;
import com.m1enkrafftman.rogue.entity.extensions.Action;
import com.m1enkrafftman.rogue.external.BackTile;
import com.m1enkrafftman.rogue.external.Collideable;
import com.m1enkrafftman.rogue.external.World;
import com.m1enkrafftman.rogue.external.level.Level;
import com.m1enkrafftman.rogue.gui.FontRenderer;
import com.m1enkrafftman.rogue.gui.GuiScreen;
import com.m1enkrafftman.rogue.misc.Cache;
import com.m1enkrafftman.rogue.misc.MathHelper;

public class InGame extends GuiScreen {
	
	private EntityPlayer thePlayer;
	private ArrayList<String> debug;
	private boolean toDebug;
	private World theWorld;
	private Level currentLevel;
	private ArrayList<Action> curActions = new ArrayList<>();
	
	public InGame() {
		super();
		this.curActions.clear();
		this.thePlayer = new EntityPlayer(0, 0, 32, 0xff00ff00);
		this.currentLevel = new Level(1);
		this.theWorld = new World(this.currentLevel.getWorldFromLevel());
		debug = new ArrayList<>();
		this.toDebug = false;
	}
	
	public InGame(EntityPlayer p, World w, Level l) {
		super();
		this.curActions.clear();
		this.thePlayer = p;
		this.currentLevel = l;
		this.theWorld = w;
		debug = new ArrayList<>();
		this.toDebug = false;
	}
	
	public InGame(Level l) {
		super();
		this.curActions.clear();
		this.currentLevel = new Level(l.getLevelNumber() + 1);
		this.thePlayer = new EntityPlayer(0, 0, 32, 0xff00ff00);
		this.theWorld = new World(this.currentLevel.getWorldFromLevel());
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
		this.renderEntities();
		this.doPlayerRender();
		FontRenderer.drawStringWithColor("Rogue", 3, 12, 0xffffffff);
		this.renderOverlay();
		GL11.glPopMatrix();
	}
	
	public void renderOverlay() {
		this.renderDebug();
		this.renderHealth();
	}
	
	public void renderHealth() {
		FontRenderer.drawStringWithColor("Health ", 5, 575, 0xffffffff);
		GL11.glPushMatrix();
		int width = (this.thePlayer.getHealth() * 10) * 2;
		int height = 15;
		GL11.glColor3f(0, 0, 0);
		GL11.glRecti(5, 580, 5 + width, 581);
		GL11.glRecti(5, 580, 5 + 1, 580 + height);
		GL11.glRecti(5, 580 + height - 1, 5 + width, 580 + height);
		GL11.glRecti(5 + width - 1, 580, 5 + width, 580 + height);
		GL11.glColor3f(1, 0, 0.1f);
		GL11.glRecti(6, 581, 6 + width - 2, 580 + height - 1);
		GL11.glPopMatrix();
	}
	
	public void renderEntities() {
		for(EntityEnemy e : this.currentLevel.getEnemies()) {
			e.renderEnemy(this.thePlayer);
		}
		for(EntityOtherPlayer e : this.currentLevel.getPlayers()) {
			//e.renderLiving();
		}
	}
	
	public void doPlayerRender() {
		GL11.glTranslated(400, 300, 0);
		GL11.glRotatef(-this.thePlayer.rotation, 0, 0, 1);
		this.thePlayer.renderLiving();
		GL11.glRotatef(this.thePlayer.rotation, 0, 0, 1);
		GL11.glTranslated(-400, -300, 0);
	}
	
	public void renderDebug() {
		if(this.toDebug) {
			this.debug.clear();
			this.debug.add("X: " + this.thePlayer.getMinX());
			this.debug.add("Y: " + this.thePlayer.getMinY());
			this.debug.add("Rot: " + this.thePlayer.getRotation());
			this.debug.add("Col: " + this.thePlayer.getCollided());
			this.debug.add("Health: " + this.thePlayer.getHealth());
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
		
		case(Keyboard.KEY_ESCAPE):
			GameLoop.currentScreen = new PauseMenu(this.thePlayer, this.theWorld, this.currentLevel);
			break;
		
		case(Keyboard.KEY_SPACE):
			this.thePlayer.attack(currentLevel);
			break;
		}
	}
	
	public void checkForMovement() {
		int oldX = this.thePlayer.getPlayerPosX();
		int oldY = this.thePlayer.getPlayerPosY();
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.thePlayer.moveForward(this.thePlayer.getSpeed(), this.theWorld);
			this.thePlayer.animationTime += 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.thePlayer.rotateLeft();
		}else if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.thePlayer.rotateRight();
		}
		int delX = this.thePlayer.getPlayerPosX() - oldX;
		int delY = this.thePlayer.getPlayerPosY() - oldY;
		this.curActions.add(new Action(this.thePlayer, delX, delY, this.thePlayer.getRotation(), toDebug, 0));
	}

	@Override
	public void handleLogic() {
		//Cache.ingameMusic.loop();
		this.manageLevel();
		this.checkForMovement();
		this.handleEntities();
		Cache.pX = this.thePlayer.getMinX();
		Cache.pY = this.thePlayer.getMinY();
	}
	
	public void handleEntities() {
		for(EntityEnemy e : this.currentLevel.getEnemies()) {
			e.onUpdate(this.thePlayer, this.theWorld);
		}
		for(EntityOtherPlayer e : this.currentLevel.getPlayers()) {
			//e.update();
		}
	}
	
	public void manageLevel() {
		this.handleStuff();
		if(this.getLivingEnemies() < 1) {
			if(this.currentLevel.getLevelNumber() + 1 > Cache.LEVEL_AMOUNT) {
				GameLoop.currentScreen = new EndGame(this.currentLevel);
				return;
			}else
				GameLoop.currentScreen = new MidLevel(this.currentLevel);
		}
	}
	
	public void handleStuff() {
		if(this.thePlayer.getHealth() < 1) {
			this.currentLevel.getPlayers().add(new EntityOtherPlayer(0, 0, 0, 0));
			GameLoop.currentScreen = new DeathScreen(this.currentLevel);
		}
	}
	
	public int getLivingEnemies() {
		int x = 0;
		for(EntityEnemy e : this.currentLevel.getEnemies()) {
			if(e.getIsLiving()) {
				x += 1;
			}
		}
		return x;
	}

}
