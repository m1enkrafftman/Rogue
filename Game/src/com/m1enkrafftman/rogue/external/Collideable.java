package com.m1enkrafftman.rogue.external;

import org.lwjgl.opengl.GL11;

import com.m1enkrafftman.rogue.entity.EntityWall;
import com.m1enkrafftman.rogue.misc.Cache;

public class Collideable extends EntityWall {

	//private boolean canBePushed;
	private int id;
	private int animTime;
	private boolean one = false;
	
	public Collideable(int minX, int minY, int width, int color, int id) {
		super(minX, minY, width, color);
		this.id = id;
		this.animTime = 0;
	}	
	
	
	public void renderCollide() {
		this.animTime += 1;
		if(this.animTime > 10) {
			this.one = !this.one;
			this.animTime = 0;
		}
		
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		if(this.id == 0) Cache.IMAGE_HANDLER.renderImage32(Cache.IMAGE_HANDLER.getTextureByName("wood"), this.getMinX()- Cache.pX, this.getMinY()- Cache.pY);
		if(this.id == 1) {
			if(this.one) Cache.IMAGE_HANDLER.renderImage32(Cache.IMAGE_HANDLER.getTextureByName("water1"), this.getMinX()- Cache.pX, this.getMinY()- Cache.pY);
			else Cache.IMAGE_HANDLER.renderImage32(Cache.IMAGE_HANDLER.getTextureByName("water0"), this.getMinX()- Cache.pX, this.getMinY()- Cache.pY);
		}
		if(this.id == 2) {
			if(this.one) Cache.IMAGE_HANDLER.renderImage32(Cache.IMAGE_HANDLER.getTextureByName("torch0"), this.getMinX()- Cache.pX, this.getMinY()- Cache.pY);
			else Cache.IMAGE_HANDLER.renderImage32(Cache.IMAGE_HANDLER.getTextureByName("torch1"), this.getMinX()- Cache.pX, this.getMinY()- Cache.pY);
		}
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
	}

}
