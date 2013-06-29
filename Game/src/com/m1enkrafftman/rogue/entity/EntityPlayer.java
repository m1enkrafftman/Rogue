package com.m1enkrafftman.rogue.entity;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.geom.Rectangle;

import com.m1enkrafftman.rogue.GameLoop;
import com.m1enkrafftman.rogue.external.BackTile;
import com.m1enkrafftman.rogue.external.Collideable;
import com.m1enkrafftman.rogue.external.World;
import com.m1enkrafftman.rogue.external.WorldStore;
import com.m1enkrafftman.rogue.external.level.Level;
import com.m1enkrafftman.rogue.gui.game.DeathScreen;
import com.m1enkrafftman.rogue.misc.Cache;
import com.m1enkrafftman.rogue.misc.Location;
import com.m1enkrafftman.rogue.misc.MathHelper;

public class EntityPlayer extends EntityLiving {

	public float rotation;
	private boolean collided = false;
	private Step currentStep;
	public int animationTime;
	
	private boolean canControl;
	
	public EntityPlayer(int minX, int minY, int width, int color) {
		super(minX, minY, width, color);
		this.rotation = 0.0f;
		this.currentStep = new Step(true);
		this.animationTime = 1;
		this.canControl = true;this.setHealth(20);
	}
	
	public void moveForward(int speed, World w) {
		double xDelta = Math.sin(Math.toRadians(this.rotation)) * speed;
		double yDelta = Math.cos(Math.toRadians(this.rotation)) * speed;
		int xInt = (int)xDelta;
		int yInt = (int)yDelta;
		
		Location toMove = new Location(this.getMinX() + xInt, this.getMinY() + yInt);
		Location negMove = new Location(this.getMinX() - (xInt*2), this.getMinY() - (yInt*2));
		
		if(this.canMove(w, toMove)) {
			this.setLocation(toMove);
			this.collided = false;
		}else {
			this.setLocation(negMove);
			this.collided = true;
		}
	}
	
	public int getPlayerPosX() {
		return this.getMinX() + 384;
	}
	
	public int getPlayerPosY() {
		return this.getMinY() + 284;
	}
	
	public boolean getCollided() {
		return this.collided;
	}
	
	public boolean blockIsCollideable(WorldStore s) {
		if(s.getWall() instanceof BackTile) {
			return false;
		}else {
			return true;
		}
	}
	
	public void rotateLeft() {
		this.rotation += 10;
		if(this.rotation > 360) {
			this.rotation = 0;
		}
	}
	
	public void rotateRight() {
		this.rotation -= 10;
		if(this.rotation < 0) {
			this.rotation = 360;
		}
	}
	
	public int getRotation() {
		return (int)this.rotation;
	}
	
	public boolean canMove(World w, Location toMove) {
		for(WorldStore s : w.getStorage()) {
			if(s.getWall() instanceof Collideable) {
				Location you = this.getMid();
				Location it = s.getWall().getMid();
				double distance = MathHelper.getDistance(you.getX() + 384, you.getY() + 284, it.getX(), it.getY());
				if(distance <= 22) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void checkWalk() {
		if(this.animationTime % 5 == 0) {
			this.currentStep.updateStep();
			this.animationTime = 1;
		}
	}
	
	public void attack(Level l) {
		this.setAttackInt(0);
		if(this.getClosestEnemy(l) != null) {
			//TODO: FIX!!!
			//float rot = this.getPrefferedRotation(this.getClosestEnemy(l));
			//float rotDif = this.rotation - rot;
			//this.rotation = rot;
			//System.err.println("RotDif: " + rotDif);
			//System.err.println("Rot: " + rot);
			if(MathHelper.getDistance(this.getClosestEnemy(l).getMid(), new Location(this.getPlayerPosX() + 32, this.getPlayerPosY() + 32)) < 100){
				/**if(rotDif < 90 && rotDif > -90)*/ this.getClosestEnemy(l).setHealth(this.getClosestEnemy(l).getHealth() - 5);
			}
		}
	}
	
	private float getPrefferedRotation(EntityEnemy e) {
		float f = 0;
		double var2 = (this.getPlayerPosX() - e.getMinX()) + 32;
        double var6 = (this.getPlayerPosY() - e.getMinY()) + 32;
        float var10 = (float)(Math.atan2(var6, var2) * 180.0D / Math.PI) - 90.0F;
        f = -var10;
		return f + 90;
	}
	
	public EntityEnemy getClosestEnemy(Level l) {
		EntityEnemy e = null;
		for(EntityEnemy e1: l.getEnemies()) {
			if(e1.getIsLiving()){
				if(e != null) {
					if(MathHelper.getDistance(e.getMid(), new Location(this.getPlayerPosX() + 32, this.getPlayerPosY() + 32)) < 
							MathHelper.getDistance(e1.getMid(), new Location(this.getPlayerPosX() + 32, this.getPlayerPosY() + 32))){
						continue;
					}else {
						e = e1;
					}
				}else {
					e = e1;
				}
			}
		}
		return e;
	}
	
	@Override
	public void renderLiving() {
		this.setAttackInt(this.getAttackInt() + 1);
		if(this.getAttackInt() < 5) {
			this.checkWalk();
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.0f);
			Cache.IMAGE_HANDLER.renderImage32(Cache.IMAGE_HANDLER.getTextureByName("playerAttack"), -16, -16);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopMatrix();
			return;
		}
		this.checkWalk();
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.0f);
		if(this.currentStep.isLeft()) {
			Cache.IMAGE_HANDLER.renderImage32(Cache.IMAGE_HANDLER.getTextureByName("playerLeft"), -16, -16);
		}else {
			Cache.IMAGE_HANDLER.renderImage32(Cache.IMAGE_HANDLER.getTextureByName("playerRight"), -16, -16);
		}
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
	}
	
	public boolean getControlledByPlayer() {
		return true;
	}
	
	public void setPlayerControl(boolean t) {
		
	}

}
