package com.m1enkrafftman.rogue.entity;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.geom.Rectangle;

import com.m1enkrafftman.rogue.external.BackTile;
import com.m1enkrafftman.rogue.external.Collideable;
import com.m1enkrafftman.rogue.external.World;
import com.m1enkrafftman.rogue.external.WorldStore;
import com.m1enkrafftman.rogue.misc.Cache;
import com.m1enkrafftman.rogue.misc.Location;
import com.m1enkrafftman.rogue.misc.MathHelper;

public class EntityPlayer extends EntityLiving {

	public float rotation;
	private boolean collided = false;
	
	public EntityPlayer(int minX, int minY, int width, int color) {
		super(minX, minY, width, color);
		this.rotation = 0.0f;
	}
	
	public void moveForward(int speed, World w) {
		double xDelta = Math.sin(Math.toRadians(this.rotation)) * speed;
		double yDelta = Math.cos(Math.toRadians(this.rotation)) * speed;
		int xInt = (int)xDelta;
		int yInt = (int)yDelta;
		
		Location toMove = new Location(this.getMinX() + xInt, this.getMinY() + yInt);
		Location negMove = new Location(this.getMinX() - xInt, this.getMinY() - yInt);
		
		if(this.canMove(w, toMove)) {
			this.setLocation(toMove);
			this.collided = false;
		}else {
			this.setLocation(negMove);
			this.collided = true;
		}
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
				if(distance < 32) {
					System.out.println("Collided at Distance: " + distance);
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public void renderLiving() {
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		Cache.IMAGE_HANDLER.renderImage32(Cache.IMAGE_HANDLER.getTextureByName("player"), -16, -16);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
	}

}
