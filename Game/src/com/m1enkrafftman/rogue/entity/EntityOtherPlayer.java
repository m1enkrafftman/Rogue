package com.m1enkrafftman.rogue.entity;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.m1enkrafftman.rogue.GameLoop;
import com.m1enkrafftman.rogue.entity.extensions.Action;
import com.m1enkrafftman.rogue.gui.game.DeathScreen;
import com.m1enkrafftman.rogue.misc.Cache;
import com.m1enkrafftman.rogue.misc.Location;

public class EntityOtherPlayer extends EntityLiving {

	private ArrayList<Action> actions;
	private int tick = 0;
	private Step currentStep;
	private int animationTime;
	private int attackTick;
	private Action prevAction;
	
	public EntityOtherPlayer(int minX, int minY, int width, int color) {
		super(minX, minY, width, color);
		this.actions = new ArrayList<>();
		this.tick = 0;
		this.currentStep = new Step(true);
		this.animationTime = 1;
		this.attackTick = 0;
		this.actions.add(new Action(new EntityPlayer(0, 0, 0, 0), 0, 0, 0, false, 0));
	}
	
	public ArrayList<Action> getActions() {
		return this.actions;
	}
	
	public int getPlayerX() {
		return this.getMinX() + 400;
	}
	
	public int getPlayerY() {
		return this.getMinY() + 300;
	}
	
	private void checkWalk() {
		if(this.animationTime % 5 == 0) {
			this.currentStep.updateStep();
			this.animationTime = 1;
		}
	}
	
	@Override
	public void renderLiving() {
		if(this.getIsLiving()) {
			this.checkWalk();
			Action a = this.prevAction;
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
			GL11.glTranslatef((this.getMinX() - Cache.pX + 400) -16 , (this.getMinY() - Cache.pY + 400) - 16, 0);
			GL11.glRotatef(-a.getRotation(), 0, 0, 1);
			if(this.currentStep.isLeft()) {
				Cache.IMAGE_HANDLER.renderImage32(Cache.IMAGE_HANDLER.getTextureByName("playerLeft"), -16, -16);
			}else {
				Cache.IMAGE_HANDLER.renderImage32(Cache.IMAGE_HANDLER.getTextureByName("playerRight"), -16, -16);
			}
			GL11.glRotatef(a.getRotation(), 0, 0, 1);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopMatrix();
			if(this.getHealth() < 1) {
				this.setLifeStatus(false);
			} 
		}
	}
	
	public void update() {
		if(this.tick < this.actions.size()) {
			Action a = this.actions.get(this.tick);
			Location toMove = new Location(this.getMinX() + 400 + a.getDeltaX(), this.getMinY() + 300 + a.getDeltaY());
			this.setLocation(toMove);
			this.animationTime += 1;
			if(a.shouldAttack()) {
				
			}
			this.prevAction = a;
			this.tick += 1;
		}
	}

}
