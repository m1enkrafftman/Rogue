package com.m1enkrafftman.rogue.entity;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.m1enkrafftman.rogue.misc.Cache;

public class EntityBoss extends EntityEnemy{

	private Step currentStep;
	public int animationTime;
	
	public EntityBoss(int minX, int minY, int width, int color) {
		super(minX, minY, width, color);
		this.currentStep = new Step(false);
		this.animationTime = 1;
		//TODO: Change this
		this.setSpeed(2);
		this.setHealth(100);
	}
	
	@Override
	public void renderEnemy(EntityPlayer p) {
		if(this.getIsLiving() == false) {
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
			GL11.glTranslatef((this.getMinX() - p.getMinX()) -16 , (this.getMinY() - p.getMinY()) - 16, 0);
			GL11.glRotatef(-this.getRotation(), 0, 0, 1);
			if(this.currentStep.isLeft()) {
				Cache.IMAGE_HANDLER.renderImage32(Cache.IMAGE_HANDLER.getTextureByName("entityDead"), -16, -16);
			}else {
				Cache.IMAGE_HANDLER.renderImage32(Cache.IMAGE_HANDLER.getTextureByName("entityDead"), -16, -16);
			}
			GL11.glRotatef(this.getRotation(), 0, 0, 1);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopMatrix();
			return;
		}
		if(this.getAttackInt() < 5) {
			this.checkWalk();
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
			GL11.glTranslatef((this.getMinX() - p.getMinX()) -16 , (this.getMinY() - p.getMinY()) - 16, 0);
			this.renderHealth();
			GL11.glRotatef(-this.getRotation(), 0, 0, 1);
			Cache.IMAGE_HANDLER.renderImage32(Cache.IMAGE_HANDLER.getTextureByName("bossAttack"), -16, -16);
			GL11.glRotatef(this.getRotation(), 0, 0, 1);
			this.renderHealth();
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopMatrix();
			return;
		}
		this.checkWalk();
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		GL11.glTranslatef((this.getMinX() - p.getMinX()) -16 , (this.getMinY() - p.getMinY()) - 16, 0);
		GL11.glRotatef(-this.getRotation(), 0, 0, 1);
		if(this.currentStep.isLeft()) {
			Cache.IMAGE_HANDLER.renderImage32(Cache.IMAGE_HANDLER.getTextureByName("bossLeft"), -16, -16);
		}else {
			Cache.IMAGE_HANDLER.renderImage32(Cache.IMAGE_HANDLER.getTextureByName("bossRight"), -16, -16);
		}
		GL11.glRotatef(this.getRotation(), 0, 0, 1);
		this.renderHealth();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
	}
	
	public void checkWalk() {
		if(this.animationTime % 5 == 0) {
			this.currentStep.updateStep();
			this.animationTime = 1;
		}
		this.setAttackInt(this.getAttackInt() + 1);
	}

	public void renderHealth() {
		GL11.glPushMatrix();
		GL11.glColor3f(1, 0, 0);
		int width = this.getHealth() * 2;
		GL11.glRecti(-(width/2), -20, (-(width/2)) + width, -18);
		GL11.glColor3f(1, 1, 1);
		GL11.glPopMatrix();
	}

}
