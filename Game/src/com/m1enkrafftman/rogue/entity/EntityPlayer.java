package com.m1enkrafftman.rogue.entity;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.m1enkrafftman.rogue.external.Collideable;
import com.m1enkrafftman.rogue.misc.Cache;
import com.m1enkrafftman.rogue.misc.CollisionHelper;
import com.m1enkrafftman.rogue.misc.Location;
import com.m1enkrafftman.rogue.misc.MathHelper;

public class EntityPlayer extends EntityLiving {

	public float rotation;
	
	public EntityPlayer(int minX, int minY, int width, int color) {
		super(minX, minY, width, color);
		this.rotation = 0.0f;
	}
	
	public void moveForward(int speed) {
		double xDelta = Math.sin(Math.toRadians(this.rotation)) * speed;
		double yDelta = Math.cos(Math.toRadians(this.rotation)) * speed;
		int xInt = (int)xDelta;
		int yInt = (int)yDelta;
		this.setLocation(new Location(this.getMinX() + xInt, this.getMinY() + yInt));
	}
	
	public void moveBackward(int speed) {
		double xDelta = Math.sin(Math.toRadians(this.rotation)) * speed;
		double yDelta = Math.cos(Math.toRadians(this.rotation)) * speed;
		int xInt = (int)xDelta;
		int yInt = (int)yDelta;
		this.setLocation(new Location(this.getMinX() - xInt, this.getMinY() - yInt));
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
	
	public boolean canMove(int delX, int delY, ArrayList<Collideable> walls) {
		for(Collideable c : walls) {
			if(MathHelper.getDistance(getMinX()+delX, getMinY()+delY, c.getMinX(), c.getMinY()) <= 1); 
					return true;
		}
		return true;
	}
	
	@Override
	public void renderLiving() {
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		Cache.IMAGE_HANDLER.renderImage32(Cache.IMAGE_HANDLER.getTextureByName("player"), this.getMinX(), this.getMinY());
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
	}

}
