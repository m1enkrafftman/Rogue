package com.m1enkrafftman.rogue.external;

import org.lwjgl.opengl.GL11;

import com.m1enkrafftman.rogue.entity.EntityWall;
import com.m1enkrafftman.rogue.misc.Cache;

public class BackTile extends EntityWall {

	public BackTile(int minX, int minY, int width, int color) {
		super(minX, minY, width, color);
	}
	
	@Override
	public void renderWall() {
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		Cache.IMAGE_HANDLER.renderImage(Cache.IMAGE_HANDLER.getTextureByName("floor"), this.getMinX(), this.getMinY());
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
	}

}